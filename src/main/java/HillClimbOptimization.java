import interfaces.IHillClimbSolution;

/**
 * Abstract class extended by HillClimb and HillClimbRandRestart. Stores the initial solution and optimizer solutions.
 * Has a method to check if a solution is the goal solution.
 */
public abstract class HillClimbOptimization {

    /**
     * The set of parameters used by the optimizer.
     */
    private HillClimbParams params;

    /**
     * The starting point used by the optimizer.
     */
    private IHillClimbSolution initialSolution;

    /**
     * Beings the hill climbing process.
     *
     * @return the optimal solution found by the optimizer.
     */
    public abstract IHillClimbSolution optimize();

    /**
     *  Gets the optimizers parameters.
     *
     * @return the optimizers parameters
     */
    public HillClimbParams getParams() {
        return this.params;
    }

    /**
     * Sets the optimizers parameters.
     *
     * @param params the params used by the optimizer
     */
    public void setParams(HillClimbParams params) {
        this.params = params;
    }

    /**
     * Gets the staring solution used by the optimizer.
     *
     * @return the starting solution
     */
    public IHillClimbSolution getInitialSolution() {
        return this.initialSolution;
    }

    /**
     *  Sets the starting solution used by the optimizer.
     *
     * @param initialSolution the starting solution
     */
    public void setInitialSolution(IHillClimbSolution initialSolution) {
        this.initialSolution = initialSolution;
    }

    /**
     * Checks if the solution is the target solution.
     *
     * @param solution the solution to check
     * @return true if solution is the target false otherwise
     */
    public boolean isGoalScore(IHillClimbSolution solution) {
        if(!this.params.isMinimization())
            return solution.getScore() >= this.params.getGoalScore();

        return solution.getScore() <= this.params.getGoalScore();
    }

}
