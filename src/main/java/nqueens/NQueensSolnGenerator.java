package nqueens;

import interfaces.IHillClimbSolnGenerator;
import interfaces.IHillClimbSolution;

import java.util.Random;

/**
 * Used to generate random NQueensProblems to be used by HillClimbingRandRestarts. Allows the use of seeds for
 * repeatable results.
 */
public class NQueensSolnGenerator implements IHillClimbSolnGenerator {

    private int n;
    private Random rand;

    public NQueensSolnGenerator(int n) {
        this.n = n;
        rand = new Random();
    }

    public NQueensSolnGenerator(int n, long randGenSeed) {
        this.n = n;
        rand = new Random(randGenSeed);
    }

    /**
     * Generates random NQueensSoln object. Creates a random int array with values limited to a specific N, then creates
     * a NQueensSoln. Will score the state before returning.
     *
     * @return a randomly generated NQueensSoln
     */
    @Override
    public IHillClimbSolution randomSolution() {
        int[] queensPos = new int[this.n];

        // Generate Random Row Positions For Every Queen
        for(int queen = 0; queen < this.n; queen++)
            queensPos[queen] = rand.nextInt(this.n);

        // Create And Score New Random State
        NQueensSoln randomState = new NQueensSoln(queensPos);
        return randomState;
    }

}
