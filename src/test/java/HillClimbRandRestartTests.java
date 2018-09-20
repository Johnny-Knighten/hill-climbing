import interfaces.IHillClimbProblem;
import nqueens.NQueens;
import nqueens.NQueensGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HillClimbRandRestartTests {

    private HillClimbParams minimizeHillClimb;
    private HillClimbParams maximizeHillClimb;

    @Before
    public void setup() {
        minimizeHillClimb = new HillClimbParams();
        minimizeHillClimb.setMinimization(true);
        minimizeHillClimb.setGoalScore(0);

        maximizeHillClimb = new HillClimbParams();
    }

    //////////////////////////////////////
    // Ensure Correct Algorithm Results //
    //////////////////////////////////////

    @Test
    public void fourQueensMinimize() {
        NQueens initialState = new NQueens(new int[]{0,1,2,3});
        NQueensGenerator helper = new NQueensGenerator(4, 0);
        HillClimbRandRestart climber = new HillClimbRandRestart(initialState, helper , minimizeHillClimb);
        IHillClimbProblem solution = climber.optimize();

        Assert.assertEquals(0, solution.getScore(), 00000.1);
    }

    @Test
    public void fourQueensMaximize() {
        NQueens initialState = new NQueens(new int[]{1,0,2,3});
        NQueensGenerator helper = new NQueensGenerator(4, 0);
        maximizeHillClimb.setGoalScore(6);
        HillClimbRandRestart climber = new HillClimbRandRestart(initialState, helper , maximizeHillClimb);
        IHillClimbProblem solution = climber.optimize();

        Assert.assertEquals(6, solution.getScore(), 00000.1);
    }

    @Test
    public void eightQueensMinimize() {
        NQueens initialState = new NQueens(new int[]{0,1,2,3,4,5,6,7});
        NQueensGenerator helper = new NQueensGenerator(8, 0);
        HillClimbRandRestart climber = new HillClimbRandRestart(initialState, helper , minimizeHillClimb);
        IHillClimbProblem solution = climber.optimize();

        Assert.assertEquals(0, solution.getScore(), 00000.1);
    }

    @Test
    public void eightQueensMaximize() {
        NQueens initialState = new NQueens(new int[]{7,2,0,5,1,4,6,3});
        NQueensGenerator helper = new NQueensGenerator(8, 0);
        maximizeHillClimb.setGoalScore(28);
        HillClimbRandRestart climber = new HillClimbRandRestart(initialState, helper , maximizeHillClimb);
        IHillClimbProblem solution = climber.optimize();

        Assert.assertEquals(28, solution.getScore(), 00000.1);
    }

}
