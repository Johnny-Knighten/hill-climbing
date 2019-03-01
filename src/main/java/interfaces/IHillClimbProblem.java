package interfaces;

import java.util.List;

/**
 * Represents the problem being optimized by class that extends HillClimbOptimization.
 */
public interface IHillClimbProblem {

    /**
     * Gets the initial guess at solution of the problem, this is an initial solution.
     *
     * @return the initial solution the problem starts at
     */
    IHillClimbSolution getInitialGuess();

    /**
     * Sets the initial guess at solution of the problem.
     *
     * @param initialGuess the initial solution the problem starts at
     */
    void setInitialGuess(IHillClimbSolution initialGuess);

    /**
     * Finds the most optimal solutions in the list.
     *
     * @param possibleSolns a list of possible solutions
     * @return the most optimal solution in the list
     */
    IHillClimbSolution getBestSolution(List<IHillClimbSolution> possibleSolns);

    /**
     * Checks if the algorithm has encountered a peak/valley or a plateau.
     *
     * @param currentSolution the current solution in the hill climb iteration
     * @param newSolution a solution generated from the current solution
     * @return true is a peak/valley or plateau is found, false otherwise
     */
    boolean isPeakOrPlateau(IHillClimbSolution currentSolution, IHillClimbSolution newSolution);

    /**
     * Calculates the score of the solution. This score is used to guide the hill climbing.
     *
     * @param solution the solution being scored.
     * @return he score for the solution
     */
    double scoreSolution(IHillClimbSolution solution);

}
