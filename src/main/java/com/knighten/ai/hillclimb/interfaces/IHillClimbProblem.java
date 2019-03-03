package com.knighten.ai.hillclimb.interfaces;


import java.util.List;

/**
 * Represents the problem being optimized by class that extends HillClimbOptimizer.
 */
public interface IHillClimbProblem {

    /**
     * Gets the initial guess at solution of the problem, this is an initial solution. Should be set using a
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


    /**
     * Used by HillClimbRandRestart to determine if the solution currently being operated on is better than the best
     * found so far. Will return true if the current is better than the best and false otherwise. Each problem will
     * have their own definition if the current is better than the best.
     *
     * @param current the current solution being operated on
     * @param best the best solution found so far
     * @return true if current is better than the best else false
     */
    boolean currentBetterThanBest(IHillClimbSolution current, IHillClimbSolution best);

}
