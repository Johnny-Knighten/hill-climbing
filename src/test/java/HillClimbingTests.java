import interfaces.IHillClimbProblem;
import nqueens.NQueensProblem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HillClimbingTests {

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
        NQueensProblem initialState = new NQueensProblem(new int[]{0,1,2,3});
        HillClimbing climber = new HillClimbing(initialState, minimizeHillClimb);
        IHillClimbProblem solution = climber.optimize();

        Assert.assertEquals(2, solution.getScore(), 0.00001);
    }

    @Test
    public void fourQueensMaximize() {
        NQueensProblem initialState = new NQueensProblem(new int[]{1,0,2,3});
        HillClimbing climber = new HillClimbing(initialState, maximizeHillClimb);
        IHillClimbProblem solution = climber.optimize();

        Assert.assertEquals(6, solution.getScore(), 0.00001);
    }

    @Test
    public void eightQueensMinimize() {
        NQueensProblem initialState = new NQueensProblem(new int[]{0,1,2,3,4,5,6,7});
        HillClimbing climber = new HillClimbing(initialState, minimizeHillClimb);
        IHillClimbProblem solution = climber.optimize();

        Assert.assertEquals(1, solution.getScore(), 0.00001);
    }

    @Test
    public void eightQueensMaximize() {
        NQueensProblem initialState = new NQueensProblem(new int[]{7,2,0,5,1,4,6,3});
        HillClimbing climber = new HillClimbing(initialState, maximizeHillClimb);
        IHillClimbProblem solution = climber.optimize();

        Assert.assertEquals(28, solution.getScore(), 0.00001);
    }

}
