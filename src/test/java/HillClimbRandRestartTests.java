import interfaces.IHillClimbSolution;
import nqueens.NQueensSoln;
import nqueens.NQueensSolnGenerator;
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
        NQueensSoln initialState = new NQueensSoln(new int[]{0,1,2,3});
        NQueensSolnGenerator helper = new NQueensSolnGenerator(4, 0);
        HillClimbRandRestart climber = new HillClimbRandRestart(initialState, helper , minimizeHillClimb);
        IHillClimbSolution solution = climber.optimize();

        Assert.assertEquals(0, solution.getScore(), 00000.1);
    }

    @Test
    public void fourQueensMaximize() {
        NQueensSoln initialState = new NQueensSoln(new int[]{1,0,2,3});
        NQueensSolnGenerator helper = new NQueensSolnGenerator(4, 0);
        maximizeHillClimb.setGoalScore(6);
        HillClimbRandRestart climber = new HillClimbRandRestart(initialState, helper , maximizeHillClimb);
        IHillClimbSolution solution = climber.optimize();

        Assert.assertEquals(6, solution.getScore(), 00000.1);
    }

    @Test
    public void eightQueensMinimize() {
        NQueensSoln initialState = new NQueensSoln(new int[]{0,1,2,3,4,5,6,7});
        NQueensSolnGenerator helper = new NQueensSolnGenerator(8, 0);
        HillClimbRandRestart climber = new HillClimbRandRestart(initialState, helper , minimizeHillClimb);
        IHillClimbSolution solution = climber.optimize();

        Assert.assertEquals(0, solution.getScore(), 00000.1);
    }

    @Test
    public void eightQueensMaximize() {
        NQueensSoln initialState = new NQueensSoln(new int[]{7,2,0,5,1,4,6,3});
        NQueensSolnGenerator helper = new NQueensSolnGenerator(8, 0);
        maximizeHillClimb.setGoalScore(28);
        HillClimbRandRestart climber = new HillClimbRandRestart(initialState, helper , maximizeHillClimb);
        IHillClimbSolution solution = climber.optimize();

        Assert.assertEquals(28, solution.getScore(), 00000.1);
    }

}
