package creditapplication.controller;

import creditapplication.domain.CreditApplicationForm;
import creditapplication.events.CreditApplicationApprovedEvent;
import creditapplication.events.CreditApplicationDeclinedEvent;
import creditapplication.repository.CreditApplicationFormRepository;

import java.util.logging.Logger;


public class CreditApplicationController {

    private CreditApplicationFormRepository creditApplicationFormRepository;

    private ScoringService scoringService;

    private CreditEventQueue creditEventQueue;

    private Logger LOGGER = Logger.getLogger(CreditApplicationController.class.getName());

    public CreditApplicationController(CreditApplicationFormRepository creditApplicationFormRespository,
                                       ScoringService scoringService,
                                       CreditEventQueue creditEventQueue) {
        this.creditApplicationFormRepository = creditApplicationFormRespository;
        this.scoringService = scoringService;
        this.creditEventQueue = creditEventQueue;
    }

    public String performScoring(ProcessContainer processContainer, Model model) {

        CreditApplicationForm creditApplicationForm = creditApplicationFormRepository.findOne(processContainer.getCreditApplicationForm().getId());

        ScoringInput scoringInput = new ScoringInput();
        scoringInput.setIncome(creditApplicationForm.getSelfDisclosure().getEarnings().sum());
        scoringInput.setSpendings(creditApplicationForm.getSelfDisclosure().getOutgoings().sum());
        scoringInput.setReason(creditApplicationForm.getPurpose());
        scoringInput.setMonthlyPayment(creditApplicationForm.getMonthlyPayment().longValue());
        scoringInput.setFirstName(processContainer.getCustomer().getFirstName());
        scoringInput.setLastName(processContainer.getCustomer().getLastName());
        scoringInput.setStreet(processContainer.getCustomer().getStreet());
        scoringInput.setPostCode(processContainer.getCustomer().getPostCode());

        LOGGER.info("Remotely and synchronously calling the Scoring Application in order to perform a scoring");
        ScoringResult scoringResult = scoringService.performScoring(scoringInput);

        Object event = calculateResultingEvent(creditApplicationForm, scoringResult.getScoringColor());

        creditEventQueue.send(event);

        model.addAttribute("scoringResult", scoringResult);

        return "scoringResult";
    }

    Object calculateResultingEvent(CreditApplicationForm creditApplicationForm, ScoringColor scoringColor) {

        boolean scoreGreen = scoringColor.equals(ScoringColor.GREEN);

        Object event = scoreGreen ? new CreditApplicationApprovedEvent(creditApplicationForm) :
                                    new CreditApplicationDeclinedEvent(creditApplicationForm);

        String color = scoreGreen ? "green" :
                                    "NOT green";
        LOGGER.info("Scoring was " + color + ", " +
                    "sending " + event.getClass().getSimpleName());
        return event;
    }
}
