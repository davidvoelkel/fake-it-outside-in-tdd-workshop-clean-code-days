package creditapplication.events;

import creditapplication.domain.CreditApplicationForm;

import java.math.BigDecimal;

public class CreditApplicationApprovedEvent {

    private CreditApplicationForm creditApplicationForm;


    public CreditApplicationApprovedEvent(CreditApplicationForm creditApplicationForm) {

        this.creditApplicationForm = creditApplicationForm;
    }

    public int getTerm() {
        return creditApplicationForm.getTerm();
    }

    public BigDecimal getAmount() {
        return creditApplicationForm.getAmount();
    }

    public BigDecimal getPercentage() {
        return creditApplicationForm.getPercentage();
    }

    public String getCustomerId() {
        return creditApplicationForm.getCustomerId().toString();
    }

    public String getCreditApplicationId() {
        return creditApplicationForm.getId().toString();
    }
}
