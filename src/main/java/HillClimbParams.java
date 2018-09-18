/**
 * Contains parameters used by HillCLimb. Has parameters to control: termination when specific score is found, if hill
 * climbing is performing minimization or maximization, and to limit the number of iterations hill climbing can run.
 */
public class HillClimbParams {

    private double goalScore = Integer.MAX_VALUE;
    private boolean minimization = false;
    private int maxIterations = Integer.MAX_VALUE;

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
    public boolean isMinimization() {
        return minimization;
    }

    /**
     * Sets the parameter that indicates minimization should be performed. The default is maximization. Note: if minimization
     * is selected and the default goal score is not changed, then the goal score is automatically set to
     * Integer.MAX_VALUE.
     *
     * @param minimization true if hill climbing is to minimize
     */
    public void setMinimization(boolean minimization) {
        this.minimization = minimization;

        // Swap Default goalScore If Minimization Is Selected
        if(minimization && goalScore == Integer.MAX_VALUE)
            goalScore = Integer.MIN_VALUE;
    }

    /**
     * Returns the maximum number of iterations hill climbing can run.
     *
     * @return the max number of iterations to run
     */
    public int getMaxIterations() {
        return maxIterations;
    }

    /**
     * Sets the maximum number of iterations hill climbing can run.
     *
     * @param maxIterations the max number of iterations to run
     */
    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }
}
