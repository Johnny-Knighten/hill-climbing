package interfaces;

import java.util.List;

/**
 * Represents the state of a problem to be optimized using HillClimb or HillClimbRandRestart.
 */
public interface IHillClimbProblem {
    /**
     * Calculates the score/value of the current IHillClimbProblem. Does not set the score on the object.
     *
     * @return the score of the IHillClimbProblem
     */
    double scoreState();

    /**
     * Gets the IHillClimbProblem's assigned score.
     *
     * @return the IHillClimbProblem's score
     */
    double getScore();

    /**
     * Assigns the current IHillClimbProblem a specified score.
     *
     * @param score the score to assign the IHillClimbProblem
     */
    void setScore(double score);

    /**
     * Generates a list of the next set of reachable states/IHillClimbProblems.
     *
     * @return list of possible next states/IHillClimbProblems
     */
    List<IHillClimbProblem> generateNextStates();
}
