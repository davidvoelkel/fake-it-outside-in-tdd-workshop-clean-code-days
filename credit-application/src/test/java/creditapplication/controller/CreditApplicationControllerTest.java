package creditapplication.controller;

import creditapplication.domain.CreditApplicationForm;
import creditapplication.events.CreditApplicationApprovedEvent;
import creditapplication.events.CreditApplicationDeclinedEvent;
import creditapplication.repository.CreditApplicationFormRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockSettings;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class CreditApplicationControllerTest {

    private CreditApplicationFormRepository repo;
    private ScoringService scoringService;
    private CreditEventQueue eventQueue;
    private CreditApplicationController controller;

    @Before
    public void setUp() throws Exception {
        repo = mock(CreditApplicationFormRepository.class);
        scoringService = mock(ScoringService.class);
        eventQueue = mock(CreditEventQueue.class);
        controller = new CreditApplicationController(repo, scoringService, eventQueue);
    }

    // ************************* Mocking ****************************

    @Test
    public void applicationDeclined_whenScoringIsRed() throws Exception {
        when(repo.findOne(anyLong()))
                 .thenReturn(mock(CreditApplicationForm.class, RETURNS_DEEP_STUBS));
        when(scoringService.performScoring(any(ScoringInput.class)))
                 .thenReturn(new ScoringResult(ScoringColor.RED));

        controller.performScoring(new ProcessContainer(), new Model());

        verify(eventQueue).send(any(CreditApplicationDeclinedEvent.class));
    }

    @Test
    public void applicationApproved_whenScoringIsGreen() throws Exception {
        when(repo.findOne(anyLong()))
                .thenReturn(mock(CreditApplicationForm.class, RETURNS_DEEP_STUBS));
        when(scoringService.performScoring(any(ScoringInput.class)))
                .thenReturn(new ScoringResult(ScoringColor.GREEN));

        controller.performScoring(new ProcessContainer(), new Model());

        verify(eventQueue).send(any(CreditApplicationApprovedEvent.class));
    }

    // ************************* Mocking ****************************
}