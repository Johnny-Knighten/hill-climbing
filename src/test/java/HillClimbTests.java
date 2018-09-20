import interfaces.IHillClimbSolution;
import nqueens.NQueensSoln;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HillClimbTests {

    private HillClimbParams minimizeHillClimb;
    private HillClimbParams maximizeHillClimb;

    @Before
    public void setup() {
        minimizeHillClimb = new HillClimbParams();
        minimizeHillClimb.setMinimization(true);
        maximizeHillClimb = new HillClimbParams();
    }

    //////////////////////////////////////
    // Ensure Correct Algorithm Results //
    //////////////////////////////////////

    @Test
    public void fourQueensMinimize() {
        NQueensSoln initialState = new NQueensSoln(new int[]{0,1,2,3});
        HillClimb climber = new HillClimb(initialState, minimizeHillClimb);
        IHillClimbSolution solution = climber.optimize();

        Assert.assertEquals(2, solution.getScore(), 0.00001);
    }

    @Test
    public void fourQueensMaximize() {
        NQueensSoln initialState = new NQueensSoln(new int[]{1,0,2,3});
        HillClimb climber = new HillClimb(initialState, maximizeHillClimb);
        IHillClimbSolution solution = climber.optimize();

        Assert.assertEquals(6, solution.getScore(), 0.00001);
    }

    @Test
    public void eightQueensMinimize() {
        NQueensSoln initialState = new NQueensSoln(new int[]{0,1,2,3,4,5,6,7});
        HillClimb climber = new HillClimb(initialState, minimizeHillClimb);
        IHillClimbSolution solution = climber.optimize();

        Assert.assertEquals(1, solution.getScore(), 0.00001);
    }

    @Test
    public void eightQueensMaximize() {
        NQueensSoln initialState = new NQueensSoln(new int[]{7,2,0,5,1,4,6,3});
        HillClimb climber = new HillClimb(initialState, maximizeHillClimb);
        IHillClimbSolution solution = climber.optimize();

        Assert.assertEquals(28, solution.getScore(), 0.00001);
    }

}
