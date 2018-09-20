package nqueens;

import interfaces.IHillClimbProbGenerator;
import interfaces.IHillClimbProblem;

import java.util.Random;

/**
 * Used to generate random NQueensProblems to be used by HillClimbingRandRestarts. Allows the use of seeds for
 * repeatable results.
 */
public class NQueensGenerator implements IHillClimbProbGenerator {

    private int n;
    private Random rand;

    public NQueensGenerator(int n) {
        this.n = n;
        rand = new Random();
    }

    public NQueensGenerator(int n, long randGenSeed) {
        this.n = n;
        rand = new Random(randGenSeed);
    }

    /**
     * Generates random NQueens object. Creates a random int array with values limited to a specific N, then creates a
     * NQueens. Will score the state before returning.
     *
     * @return a randomly generated NQueens
     */
    @Override
    public IHillClimbProblem randomState() {
        int[] queensPos = new int[this.n];

        // Generate Random Row Positions For Every Queen
        for(int queen = 0; queen < this.n; queen++)
            queensPos[queen] = rand.nextInt(this.n);

        // Create And Score New Random State
        NQueens randomState = new NQueens(queensPos);
        randomState.setScore(randomState.scoreState());
        return randomState;
    }

}
