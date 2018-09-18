import interfaces.IHillClimbProblem;
import nqueens.NQueensProblem;
import nqueens.NQueensProblemHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HillClimbingRandRestartTests {

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
        NQueensProblem initialState = new NQueensProblem(new int[]{0,1,2,3});
        NQueensProblemHelper helper = new NQueensProblemHelper(4, 0);
        HillClimbingRandRestart climber = new HillClimbingRandRestart(initialState, helper , minimizeHillClimb);
        IHillClimbProblem solution = climber.optimize();

        Assert.assertEquals(0, solution.getScore(), 00000.1);
    }

    @Test
    public void fourQueensMaximize() {
        NQueensProblem initialState = new NQueensProblem(new int[]{1,0,2,3});
        NQueensProblemHelper helper = new NQueensProblemHelper(4, 0);
        maximizeHillClimb.setGoalScore(6);
        HillClimbingRandRestart climber = new HillClimbingRandRestart(initialState, helper , maximizeHillClimb);
        IHillClimbProblem solution = climber.optimize();

        Assert.assertEquals(6, solution.getScore(), 00000.1);
    }

    @Test
    public void eightQueensMinimize() {
        NQueensProblem initialState = new NQueensProblem(new int[]{0,1,2,3,4,5,6,7});
        NQueensProblemHelper helper = new NQueensProblemHelper(8, 0);
        HillClimbingRandRestart climber = new HillClimbingRandRestart(initialState, helper , minimizeHillClimb);
        IHillClimbProblem solution = climber.optimize();

        Assert.assertEquals(0, solution.getScore(), 00000.1);
    }

    @Test
    public void eightQueensMaximize() {
        NQueensProblem initialState = new NQueensProblem(new int[]{7,2,0,5,1,4,6,3});
        NQueensProblemHelper helper = new NQueensProblemHelper(8, 0);
        maximizeHillClimb.setGoalScore(28);
        HillClimbingRandRestart climber = new HillClimbingRandRestart(initialState, helper , maximizeHillClimb);
        IHillClimbProblem solution = climber.optimize();

        Assert.assertEquals(28, solution.getScore(), 00000.1);
    }

}
