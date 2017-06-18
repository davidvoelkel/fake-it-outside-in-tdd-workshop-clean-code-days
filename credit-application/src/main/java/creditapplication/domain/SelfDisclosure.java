package creditapplication.domain;


public class SelfDisclosure {
    private Earnings earnings;

    private Outgoings outgoings;

    public SelfDisclosure() {
        earnings = new Earnings();
        outgoings = new Outgoings();
    }

    public Earnings getEarnings() {
        return earnings;
    }

    public void setEarnings(Earnings earnings) {
        this.earnings = earnings;
    }

    public Outgoings getOutgoings() {
        return outgoings;
    }

    public void setOutgoings(Outgoings outgoings) {
        this.outgoings = outgoings;
    }

    @Override
    public String toString() {
        return "SelfDisclosure{" +
                "earnings=" + earnings +
                ", outgoings=" + outgoings +
                '}';
    }
}
