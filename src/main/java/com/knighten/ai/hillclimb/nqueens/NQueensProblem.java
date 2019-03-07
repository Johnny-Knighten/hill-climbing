package com.knighten.ai.hillclimb.nqueens;

import com.knighten.ai.hillclimb.interfaces.IHillClimbProblem;
import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the NQueens problem and the operations that can be applied to NQueensSolution.
 */
public class NQueensProblem implements IHillClimbProblem {

    /**
     * The initial guess which is the starting point for optimization.
     */
    private IHillClimbSolution initialGuess;

    /**
     * Creates a NQueensProblem instance with the supplied initial guess.
     *
     * @param initialGuess the solution the optimizer starts at
     */
    public NQueensProblem(IHillClimbSolution initialGuess) {

        if (initialGuess == null)
            throw new IllegalArgumentException("Initial Guess Object Cannot Be Null");

        this.initialGuess = initialGuess;
    }

    /**
     * Gets the initial guess.
     *
     * @return the initial solution the optimizer starts at
     */
    @Override
    public IHillClimbSolution getInitialGuess() {
        return initialGuess;
    }


    /**
     * Gets the best solution out of the list of possible solutions. For this problem the best solution is the one
     * with the lowest score.
     *
     * @param possibleSolns list of possible solutions
     * @return the solution with the lowest score
     */
    @Override
    public IHillClimbSolution getBestSolution(List<IHillClimbSolution> possibleSolns) {
        IHillClimbSolution min = possibleSolns.get(0);
        for (int nextSoln = 1; nextSoln < possibleSolns.size(); nextSoln++) {
            if (min.getScore() > possibleSolns.get(nextSoln).getScore()) {
                min = possibleSolns.get(nextSoln);
            }
        }

        return min;
    }

    /**
     * Compares the current solution with the new solution to determine if there is a valley or plateau.
     *
     * @param currentSolution the current solution
     * @return true if currently in a valley or plateau, false otherwise
     */
    @Override
    public boolean atPeakOrPlateau(IHillClimbSolution currentSolution, IHillClimbSolution newSolution) {
        return newSolution.getScore() >= currentSolution.getScore();

    }

    /**
     * Determines if solution1 is better than solution2. We are minimizing so the current is better if it has a lower
     * score than the best.
     *
     * @param solution1 determining if this solution is better compared to other
     * @param solution2 solution to compare solution1 with
     * @return true if current is solution1 is better than solution2 else false
     */
    @Override
    public boolean firstSolutionBetterThanOther(IHillClimbSolution solution1, IHillClimbSolution solution2) {
        return solution1.getScore() < solution2.getScore();
    }

    /**
     * To determine the value for the NQueensSolution board the total number of conflicts on the board will be used. Queens
     * are in conflict if they can take one another(chess rules: same col/row or diagonal).
     *
     * @param solution the solution to be scored
     * @return the boards total number of conflicts
     */
    @Override
    public double scoreSolution(IHillClimbSolution solution) {

        int[] board = ((NQueensSolution) solution).getBoard();
        // Initialize score to 0
        int score = 0;

        // We Iterate Over Every Queen Starting With The Far Left Queen
        for (int queen = 0; queen < board.length; queen++) {

            // We Iterate Over Every Queen In Front Of The Queen Currently Being Evaluated
            for (int remainingQueen = queen + 1; remainingQueen < board.length; remainingQueen++) {

                // Check Row Conflict
                if (board[queen] == board[remainingQueen])
                    score++;

                // Check Diagonal Conflict
                if (Math.abs(board[queen] - board[remainingQueen]) == Math.abs(queen - remainingQueen))
                    score++;
            }
        }

        return score;
    }

    /**
     * The next potential solutions for the current solution will be the list of all possible next moves. We will limit
     * the number of moves by only allowing one queen to be moved at a time. Queens will be locked into their assigned
     * columns and then the n-1 row positions will be considered as possible moves(ignore its initial row position). For
     * each queen/column we will generate n-1 new states/boards. This means we will have n * (n-1) successors(branching
     * factor).
     *
     * @param solution solution used to generate the set of next solutions
     * @return a list of all possible next moves/board states
     */
    @Override
    public List<IHillClimbSolution> generateNextSolutions(IHillClimbSolution solution) {
        NQueensSolution solutionAsNQueens = (NQueensSolution) solution;
        ArrayList<IHillClimbSolution> nextStates = new ArrayList<>();

        // For Each Queen Iterate Over All Possible Row Moves(Ignoring Current Row Queen Is Positioned)
        for (int queen = 0; queen < solutionAsNQueens.getBoard().length; queen++)
            // For Each Row Score Board If Current Queen Was Moved To The Row (Skip Row If Queen Is Already On That Row)
            for (int row = 0; row < solutionAsNQueens.getBoard().length; row++) {

                // Skip Position If Queen Is Already In The Current Row
                if (solutionAsNQueens.getBoard()[queen] == row)
                    continue;

                // Make Copy Of Current Board
                int[] newBoard = new int[solutionAsNQueens.getBoard().length];
                System.arraycopy(solutionAsNQueens.getBoard(), 0, newBoard, 0, solutionAsNQueens.getBoard().length);

                // Modify Board To Represent Moving The Current Queen To The Current Row
                newBoard[queen] = row;

                nextStates.add(new NQueensSolution(newBoard));
            }

        return nextStates;
    }

}
