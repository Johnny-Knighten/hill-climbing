package com.knighten.ai.hillclimb.nqueens;

import com.knighten.ai.hillclimb.interfaces.IHillClimbSolnGenerator;
import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;

import java.util.Random;

/**
 * Used to generate random NQueensProblems to be used by HillClimbingRandRestarts.
 */
public class NQueensSolnGenerator implements IHillClimbSolnGenerator {

    /**
     * Size Of NQueensSolutions Being Generated.
     */
    private int n;

    /**
     * Random object used to generate random numbers.
     */
    private Random random;

    /**
     * Creates a NQueensSolnGenerator that is used to generate random NQueensSolutions.
     *
     * @param n      size of n queens board to generate
     * @param random random number generator
     */
    public NQueensSolnGenerator(int n, Random random) {

        if (random == null)
            throw new IllegalArgumentException("Random Object Cannot Be Null");

        if (n <= 3)
            throw new IllegalArgumentException("NQueens Boards Are Only Solvable When N Is Greater Than 3");

        this.n = n;
        this.random = random;
    }

    /**
     * Generates random NQueensSolution object. Creates a random int array with values limited to a specific N, then creates
     * a NQueensSolution. Will score the state before returning.
     *
     * @return a randomly generated NQueensSolution
     */
    @Override
    public IHillClimbSolution randomSolution() {
        int[] board = new int[this.n];

        // Generate Random Row Positions For Every Queen
        for (int column = 0; column < this.n; column++)
            board[column] = this.random.nextInt(this.n);

        // Create And Score New Random State
        return new NQueensSolution(board);
    }

}
