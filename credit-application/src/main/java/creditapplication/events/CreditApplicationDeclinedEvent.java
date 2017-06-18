package creditapplication.events;

import creditapplication.domain.CreditApplicationForm;

import java.math.BigDecimal;

public class CreditApplicationDeclinedEvent {

    private int term;

    private BigDecimal amount;

    String customerId;

    String creditApplicationId;
    private CreditApplicationForm creditApplicationForm;


    public CreditApplicationDeclinedEvent(CreditApplicationForm creditApplicationForm) {
        this.creditApplicationForm = creditApplicationForm;
    }

    public int getTerm() {
        return creditApplicationForm.getTerm();
    }

    public BigDecimal getAmount() {
        return creditApplicationForm.getAmount();
    }

    public String getCustomerId() {
        return creditApplicationForm.getCustomerId().toString();
    }

    public String getCreditApplicationId() {
        return creditApplicationForm.getId().toString();
    }
}
