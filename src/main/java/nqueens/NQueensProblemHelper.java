package nqueens;

import interfaces.IHillClimbProbHelper;
import interfaces.IHillClimbProblem;

public class NQueensProblemHelper implements IHillClimbProbHelper{

    private int n;

    public NQueensProblemHelper(int n) {
        this.n = n;
    }

    @Override
    public IHillClimbProblem randomState() {
        int[] queensPos = new int[this.n];

        // Generate Random Row Positions For Every Queen
        for(int queen = 0; queen < this.n; queen++)
            queensPos[queen] = (int) (Math.random() * ((this.n-1) + 1));

        // Create And Score New Random State
        NQueensProblem randomState = new NQueensProblem(queensPos);
        randomState.setScore(randomState.scoreState());
        return randomState;
    }
}
