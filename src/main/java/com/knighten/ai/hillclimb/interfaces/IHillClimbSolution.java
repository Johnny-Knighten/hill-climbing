package com.knighten.ai.hillclimb.interfaces;


/**
 * Represents a possible solution in the optimization process.
 */
public interface IHillClimbSolution {

    /**
     * Gets the IHillClimbSolution's assigned score.
     *
     * @return the solutions score
     */
    double getScore();

    /**
     * Assigns the current IHillClimbSolution a specified score.
     *
     * @param score the score to assign the solution
     */
    void setScore(double score);

}
