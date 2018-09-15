/**
 * Contains parameters used by HillCLimb. Contains a goal score early termination parameter and a parameter to control
 * if hill climbing will me minimizing or maximizing.
 */
public class HillClimbParams {

    private double goalScore = Integer.MAX_VALUE;
    private boolean descend = false;

    /**
     * Returns the early termination goal score parameter.
     *
     * @return the goal score
     */
    public double getGoalScore() {
        return goalScore;
    }

    /**
     * Sets the early termination goal score parameter.
     *
     * @param goalScore the early termination score
     */
    public void setGoalScore(double goalScore) {
        this.goalScore = goalScore;
    }

    /**
     * Returns true if hill climbing will be performing minimization.
     *
     * @return true if minimizing
     */
    public boolean isDescend() {
        return descend;
    }

    /**
     * Sets the parameter that indicates minimization should be performed. The default is maximization. Note: if descend
     * is selected and the default goal score is not changed, then the goal score is automatically set to
     * Integer.MAX_VALUE.
     *
     * @param descend
     */
    public void setDescend(boolean descend) {
        this.descend = descend;

        if(descend && goalScore == Integer.MAX_VALUE)
            goalScore = Integer.MIN_VALUE;
    }
}
