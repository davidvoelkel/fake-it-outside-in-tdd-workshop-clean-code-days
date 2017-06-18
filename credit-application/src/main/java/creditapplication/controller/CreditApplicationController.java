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

        if(scoringResult.getScoringColor().equals(ScoringColor.GREEN)) {
            LOGGER.info("Scoring was green, sending CreditApplicationApprovedEvent");
            creditEventQueue.send(new CreditApplicationApprovedEvent(creditApplicationForm));
        } else {
            LOGGER.info("Scoring was NOT green, sending CreditApplicationDeclinedEvent");
            creditEventQueue.send(new CreditApplicationDeclinedEvent(creditApplicationForm));
        }

        model.addAttribute("scoringResult", scoringResult);

        return "scoringResult";
    }
}
