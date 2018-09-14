import java.util.ArrayList;
import java.util.List;

public class NQueensProblem implements IHillClimbProblem {

    private int[] board;
    private double score;

    public NQueensProblem(int[] board) {
        this.board = board;
    }

    /**
     * For the NQueens problem, the state/board score will be based on the number of total conflicts. Queens are in
     * conflict if they can take one another(chess rules: same col/row or diagonal).
     *
     * @return the boards total number of conflicts
     */
    @Override
    public double scoreState() {
        // Initialize score to 0
        int score = 0;

        // We Iterate Over Every Queen Starting With The Far Left Queen
        for(int queen = 0; queen < this.board.length; queen++){

            // We Iterate Over Every Queen In Front Of The Queen Currently Being Evaluated
            for(int remainingQueen=queen+1; remainingQueen<this.board.length; remainingQueen++){

                // Check Row Conflict
                if(this.board[queen] == this.board[remainingQueen]){
                    score++;
                    continue;
                }

                // Check Diagonals Conflict
                if(Math.abs(this.board[queen]-this.board[remainingQueen]) == Math.abs(queen-remainingQueen)) {
                    score++;
                    continue;
                }

            }

        }

        return  score;
    }

    /**
     * The successor states/boards for the current state will be a list of all possible next moves. We will limit the
     * number of moves by only allowing one queen to be moved. Queens will be locked into their assigned columns and
     * then the n-1 row positions will be considered as possible moves(ignore its initial row position). For each
     * queen/column we will generate n-1 new states/boards. This means we will have n * (n-1) successors(branching factor).
     *
     * @return a list of all possible next moves
     */
    @Override
    public List<IHillClimbProblem> generateNextStates() {

        ArrayList<IHillClimbProblem> nextStates = new ArrayList<>();

        // For Each Queen Iterate Over All Possible Row Moves(Ignoring Current Row Queen Is Positioned)
        for(int queen = 0; queen < this.board.length; queen++)
            // For Each Row Score Board If Current Queen Was Moved To The Row (Skip Row If Queen Is Already On That Row)
            for(int row = 0; row < this.board.length; row++){

                // Skip Position If Queen Is Already In The Current Row
                if(this.board[queen] == row)
                    continue;

                // Make Copy Of Current Board
                int[] newBoard = new int[this.board.length];
                System.arraycopy(newBoard, 0, newBoard, 0, this.board.length );

                // Modify Board To Represent Moving The Current Queen To The Current Row
                newBoard[queen] = row;

                nextStates.add(new NQueensProblem(newBoard));
            }

        return nextStates;
    }

    /**
     * Returns the boards/states assigned score
     *
     * @return the boards/states score
     */
    @Override
    public double getScore() {
        return this.score;
    }

    /**
     * Assigns the board a score.
     *
     * @param score the score to be assigned
     */
    @Override
    public void setScore(double score) {
        this.score = score;
    }

}
