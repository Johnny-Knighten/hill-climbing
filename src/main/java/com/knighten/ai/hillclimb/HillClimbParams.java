package com.knighten.ai.hillclimb;


/**
 * Contains parameters used by HillCLimb. Has parameters to control: termination when specific score is found, if hill
 * climbing is performing minimization or maximization, and to limit the number of iterations hill climbing can run.
 */
public class HillClimbParams {

    private double goalScore = Integer.MAX_VALUE;
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

        if(Double.isNaN(goalScore))
            throw new IllegalArgumentException("Goal Score Cannot Be NaN");

        this.goalScore = goalScore;
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

        if(maxIterations < 1)
            throw new IllegalArgumentException("Max Iterations Must Be Greater Than 0");

        this.maxIterations = maxIterations;
    }

}
