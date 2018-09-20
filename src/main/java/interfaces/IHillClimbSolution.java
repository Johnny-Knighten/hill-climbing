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
}
