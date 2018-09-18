package nqueens;

import interfaces.IHillClimbProbHelper;
import interfaces.IHillClimbProblem;

import java.util.Random;

/**
 * Used to generate random NQueensProblems to be used by HillClimbingRandRestarts. Allows the use of seeds for
 * repeatable results.
 */
public class NQueensProblemHelper implements IHillClimbProbHelper{

    private int n;
    private Random rand;

    public NQueensProblemHelper(int n) {
        this.n = n;
        rand = new Random();
    }

    public NQueensProblemHelper(int n, long randGenSeed) {
        this.n = n;
        rand = new Random(randGenSeed);
    }

    /**
     * Generates random NQueensProblem. Creates a random int array with values limited to a specific N, then creates a
     * NQueensProblem. Will score the state before returning.
     *
     * @return a randomly generated NQueensProblem
     */
    @Override
    public IHillClimbProblem randomState() {
        int[] queensPos = new int[this.n];

        // Generate Random Row Positions For Every Queen
        for(int queen = 0; queen < this.n; queen++)
            queensPos[queen] = rand.nextInt(this.n);

        // Create And Score New Random State
        NQueensProblem randomState = new NQueensProblem(queensPos);
        randomState.setScore(randomState.scoreState());
        return randomState;
    }
}
