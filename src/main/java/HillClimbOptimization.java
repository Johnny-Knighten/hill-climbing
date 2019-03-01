import interfaces.IHillClimbProblem;
import interfaces.IHillClimbSolution;

/**
 * Abstract class extended by HillClimb and HillClimbRandRestart. Stores the problem and optimizer parameters. Also has
 * a method to check if a solution is the goal solution.
 */
public abstract class HillClimbOptimization {

    /**
     * The set of parameters used by the optimizer.
     */
    private HillClimbParams params;

    /**
     * The problem that is being solved with the optimizer.
     */
    private IHillClimbProblem problem;

    /**
     * Begins the hill climbing process.
     *
     * @return the most optimal solution found by the optimizer
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
     * Gets the problem used by the optimizer.
     *
     * @return the problem
     */
    public IHillClimbProblem getProblem() {
        return this.problem;
    }

    /**
     *  Sets the problem used by the optimizer.
     *
     * @param problem the problem
     */
    public void setProblem(IHillClimbProblem problem) {
        this.problem = problem;
    }

    /**
     * Checks if the solution is the target solution.
     *
     * @param solution the solution to check
     * @return true if solution is the target false otherwise
     */
    public boolean isGoalScore(IHillClimbSolution solution) {
        if(solution.getScore() == this.params.getGoalScore())
            return true;

        return false;
    }

}
