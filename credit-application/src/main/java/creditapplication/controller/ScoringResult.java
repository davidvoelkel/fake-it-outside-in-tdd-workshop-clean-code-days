package creditapplication.controller;


public class ScoringResult {

    public ScoringResult(ScoringColor scoringColor) {
        this.scoringColor = scoringColor;
    }

    private ScoringColor scoringColor;

    public ScoringColor getScoringColor() {
        return scoringColor;
    }
}
