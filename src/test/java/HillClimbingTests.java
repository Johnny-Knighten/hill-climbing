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
        minimizeHillClimb.setDescend(true);
        maximizeHillClimb = new HillClimbParams();
    }


    @Test
    public void fourQueensMinimize() {
        NQueensProblem initialState = new NQueensProblem(new int[]{0,1,2,3});
        HillClimbing climber = new HillClimbing(initialState, minimizeHillClimb);
        IHillClimbProblem solution = climber.optimize();

        Assert.assertTrue(solution.equals(new NQueensProblem(new int[]{1,0,2,3})));
    }

    @Test
    public void fourQueensMaximize() {
        NQueensProblem initialState = new NQueensProblem(new int[]{1,0,2,3});
        HillClimbing climber = new HillClimbing(initialState, maximizeHillClimb);
        IHillClimbProblem solution = climber.optimize();

        Assert.assertTrue(solution.equals(new NQueensProblem(new int[]{0,1,2,3})));
    }
}
