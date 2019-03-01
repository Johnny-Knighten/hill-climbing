package nqueens;

import interfaces.IHillClimbProblem;
import interfaces.IHillClimbSolution;

import java.util.List;

/**
 * Represents the NQueens problem and the operations that can be applied to NQueensSoln.
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
        this.setInitialGuess(initialGuess);
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
     * Sets the initial guess.
     *
     * @param initialGuess the initial solution the problem starts at
     */
    @Override
    public void setInitialGuess(IHillClimbSolution initialGuess) {
        this.initialGuess = initialGuess;
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
        IHillClimbSolution best = possibleSolns.get(0);
        for(int nextSoln=1; nextSoln < possibleSolns.size(); nextSoln++) {
            if (best.getScore() > possibleSolns.get(nextSoln).getScore()) {
                best = possibleSolns.get(nextSoln);
            }
        }

        return best;
    }

    /**
     * Compares the current solution with the new solution to determine if there is a valley or plateau.
     *
     * @param currentSolution the current solution
     * @return true if currently in a valley or plateau, false otherwise
     */
    @Override
    public boolean isPeakOrPlateau(IHillClimbSolution currentSolution, IHillClimbSolution newSolution) {
        if(newSolution.getScore() >= currentSolution.getScore())
            return true;

        return false;
    }

    /**
     * To determine the value for the NQueensSoln board the total number of conflicts on the board will be used. Queens
     * are in conflict if they can take one another(chess rules: same col/row or diagonal).
     *
     * @param solution the solution to be scored
     * @return the boards total number of conflicts
     */
    @Override
    public double scoreSolution(IHillClimbSolution solution) {

        int[] board = ((NQueensSoln) solution).getBoard();
        // Initialize score to 0
        int score = 0;

        // We Iterate Over Every Queen Starting With The Far Left Queen
        for(int queen = 0; queen < board.length; queen++){

            // We Iterate Over Every Queen In Front Of The Queen Currently Being Evaluated
            for(int remainingQueen = queen + 1; remainingQueen < board.length; remainingQueen++){

                // Check Row Conflict
                if(board[queen] == board[remainingQueen])
                    score++;

                // Check Diagonal Conflict
                if(Math.abs(board[queen] - board[remainingQueen]) == Math.abs(queen - remainingQueen))
                    score++;
            }
        }

        return  score;
    }

}
