package com.knighten.ai.hillclimb.interfaces;


import java.util.List;

/**
 * Represents the problem being optimized by classes that extends AbstractHillClimbOptimizer.
 */
public interface IHillClimbProblem {

    /**
     * Gets the initial guess at solution of the problem. Should be set using a
     * constructor.
     *
     * @return the initial solution the problem starts at
     */
    IHillClimbSolution getInitialGuess();

    /**
     * Finds the most optimal solutions in the list. This is put into an interface because some problems may be
     * minimizing or maximizing.
     *
     * @param possibleSolns a list of possible solutions
     * @return the most optimal solution in the list
     */
    IHillClimbSolution getBestSolution(List<IHillClimbSolution> possibleSolns);

    /**
     * Checks if the algorithm has encountered a peak/valley or a plateau. This is put into an interface because
     * some problems may be minimizing or maximizing.
     *
     * @param currentSolution the current solution in the hill climb iteration
     * @param newSolution     a solution generated from the current solution
     * @return true is a peak/valley or plateau is found, false otherwise
     */
    boolean atPeakOrPlateau(IHillClimbSolution currentSolution, IHillClimbSolution newSolution);

    /**
     * Calculates the score of the solution. This score is used to guide the hill climbing.
     *
     * @param solution the solution being scored.
     * @return he score for the solution
     */
    double scoreSolution(IHillClimbSolution solution);

    /**
     * Used by HillClimbRandRestart to determine if the solution currently being operated on is better than the best
     * found so far. Will return true if the solution1 is better than solution2 and false otherwise. Each problem will
     * have their own definition of what "best" is.
     *
     * @param solution1 determining if this solution is better compared to other
     * @param solution2 solution to compare solution1 with
     * @return true if current is solution1 is better than solution2 else false
     */
    boolean firstSolutionBetterThanOther(IHillClimbSolution solution1, IHillClimbSolution solution2);

    /**
     * Generates a list of the next set of potential solutions from a solution.
     *
     * @param solution solution used to generate next solutions
     * @return list of possible next solutions
     */
    List<IHillClimbSolution> generateNextSolutions(IHillClimbSolution solution);

}
