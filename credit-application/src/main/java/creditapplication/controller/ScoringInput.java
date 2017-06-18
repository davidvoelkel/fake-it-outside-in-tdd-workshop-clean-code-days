package creditapplication.controller;

public class ScoringInput {
    private long income;
    private long spendings;
    private String reason;
    private long monthlyPayment;
    private String firstName;
    private String lastName;
    private String street;
    private String postCode;

    public void setIncome(long income) {
        this.income = income;
    }

    public void setSpendings(long spendings) {
        this.spendings = spendings;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setMonthlyPayment(long monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
