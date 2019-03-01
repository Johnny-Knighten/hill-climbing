package interfaces;

import java.util.List;

/**
 * Represents the state of a problem to be optimized using HillClimb or HillClimbRandRestart.
 */
public interface IHillClimbSolution {

    /**
     * Calculates the score/value of the current IHillClimbSolution. Does not set the score on the object.
     *
     * @return the score of the IHillClimbSolution
     */
    double scoreState();

    /**
     * Gets the IHillClimbSolution's assigned score.
     *
     * @return the IHillClimbSolution's score
     */
    double getScore();

    /**
     * Assigns the current IHillClimbSolution a specified score.
     *
     * @param score the score to assign the IHillClimbSolution
     */
    void setScore(double score);

    /**
     * Generates a list of the next set of potential solutions.
     *
     * @return list of possible next states/IHillClimbProblems
     */
    List<IHillClimbSolution> generateNextSolns();

    /**
     * Find the best solution in a list of possible solutions.
     *
     * @param possibleSolns list of possible solutions
     * @return the best solution in the list
     */
    IHillClimbSolution getBestSolution(List<IHillClimbSolution> possibleSolns);

    /**
     * Checks if the algorithm has found a valley or plateau.
     *
     * @param currentSoln the current solution
     * @return true if currently in a valley or plateau, false otherwise
     */
    boolean isPeakOrPlateau(IHillClimbSolution currentSoln);

}
