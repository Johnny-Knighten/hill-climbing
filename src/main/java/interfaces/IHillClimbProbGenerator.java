package interfaces;

/**
 * Generates random states(IHillClimbProblems) to be used for random restarts in HillClimbRandRestart.
 */
public interface IHillClimbProbGenerator {
    /**
     *  Generates a random IHillClimbProblem to be used by HillClimbRandRestart
     *  
     * @return a random IHillClimbProblem
     */
    IHillClimbProblem randomState();
}
