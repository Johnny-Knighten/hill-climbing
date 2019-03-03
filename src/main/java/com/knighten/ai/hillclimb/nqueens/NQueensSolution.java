package com.knighten.ai.hillclimb.nqueens;

import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * This is the representation of a solution to the nqueens problem. Contains the current positions of queens on the
 * board; we make a restriction that only one queen can be in a column. This allows the board/state to be represented by
 * a one dimensional array.
 */
public class NQueensSolution implements IHillClimbSolution {

    /**
     * Represents the queen layout on the board. Each value represents the row the queen is on for that column(the first
     * value in the array represents the first column and so on).
     */
    private int[] board;

    /**
     * The number of conflicts the board is in.
     */
    private double score;

    public NQueensSolution(int[] board) {

        if(board == null)
            throw new IllegalArgumentException("NQueens Board Object Cannot Be Null");

        if(board.length <= 3)
            throw new IllegalArgumentException("NQueens Boards Are Only Solvable When N Is Greater Than 3");

        if(IntStream.range(0, board.length).anyMatch((i) -> board[i] < 0 || board[i] >= board.length))
            throw new IllegalArgumentException("Values On The Board Must Be Between 0 And N-1");



        this.board = board;
    }

    /**
     * Returns the board's assigned score
     *
     * @return the solutions score
     */
    @Override
    public double getScore() {
        return this.score;
    }

    /**
     * Assigns the solution a score.
     *
     * @param score the score to be assigned to the solution
     */
    @Override
    public void setScore(double score) {

        if(!Double.isFinite(score))
            throw new IllegalArgumentException("A NQueens Score Cannot Be Infinity Or NaN");

        this.score = score;
    }

    /**
     * Returns the array that represents the board. Each array cell represents a column and the number stored in that
     * cell represents the row the queen occupies.
     *
     * @return the NQueen's board
     */
    public int[] getBoard() {
        return this.board;
    }

    /**
     * The next potential solutions for the current solution will be the list of all possible next moves. We will limit
     * the number of moves by only allowing one queen to be moved at a time. Queens will be locked into their assigned
     * columns and then the n-1 row positions will be considered as possible moves(ignore its initial row position). For
     * each queen/column we will generate n-1 new states/boards. This means we will have n * (n-1) successors(branching
     * factor).
     *
     * @return a list of all possible next moves/board states
     */
    @Override
    public List<IHillClimbSolution> generateNextSolutions() {
        ArrayList<IHillClimbSolution> nextStates = new ArrayList<>();

        // For Each Queen Iterate Over All Possible Row Moves(Ignoring Current Row Queen Is Positioned)
        for(int queen = 0; queen < this.board.length; queen++)
            // For Each Row Score Board If Current Queen Was Moved To The Row (Skip Row If Queen Is Already On That Row)
            for(int row = 0; row < this.board.length; row++){

                // Skip Position If Queen Is Already In The Current Row
                if(this.board[queen] == row)
                    continue;

                // Make Copy Of Current Board
                int[] newBoard = new int[this.board.length];
                System.arraycopy(this.board, 0, newBoard, 0, this.board.length );

                // Modify Board To Represent Moving The Current Queen To The Current Row
                newBoard[queen] = row;

                nextStates.add(new NQueensSolution(newBoard));
            }

        return nextStates;
    }

    /**
     * Converts the NQueensSolution's board into a string. Q will represent queens and * will represent empty board spaces.
     *
     * @return string representation of the NQueensIndividual's board
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int row = 0; row < this.board.length; row++) {
            for (int queenRowPos : this.board)
                sb.append((queenRowPos == row) ? "Q " : "* ");

            // Start New Row
            if(row != (board.length-1))
                sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Compares the board of the current NQueen with another.
     *
     * @param obj NQueensSolution to compare with
     * @return true if two NQueen have the same board
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NQueensSolution)
            return Arrays.equals(this.board, ((NQueensSolution) obj).getBoard());

        return false;
    }

    /**
     * Generates a hashcode for the NQueen, this hash code is based off the board.
     *
     * @return hashcode for NQueensSolution
     */
    @Override
    public int hashCode(){
        return Arrays.hashCode(this.board);
    }

}
