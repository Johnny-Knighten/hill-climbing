import interfaces.IHillClimbProblem;
import interfaces.IHillClimbSolution;
import nqueens.NQueensProblem;
import nqueens.NQueensSoln;
import nqueens.NQueensSolnGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class HillClimbRandRestartTests {

    private HillClimbParams mockParams;
    private IHillClimbProblem mockProblem;

    @Before
    public void setup() {
        mockParams = Mockito.mock(HillClimbParams.class);
        Mockito.when(mockParams.getGoalScore()).thenReturn(0.0);
        Mockito.when(mockParams.getMaxIterations()).thenReturn(100);

        mockProblem = Mockito.mock(IHillClimbProblem.class);
    }

    ////////////////////////
    // Parameter Checking //
    ////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void constructorNullProblem() {
        new HillClimb(null, mockParams);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorNullParams() {
        new HillClimb(mockProblem, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorNullGenerator() {
        new HillClimb(mockProblem, null);
    }

    ////////////////////
    // Method Testing //
    ////////////////////


    ///////////////////////////
    // Actual Algorithm Runs //
    ///////////////////////////

    @Test
    public void fourQueensMinimize() {
        NQueensSoln initialState = new NQueensSoln(new int[]{0,1,2,3});
        NQueensProblem problem = new NQueensProblem(initialState);
        NQueensSolnGenerator generator = new NQueensSolnGenerator(4, 0);
        HillClimbRandRestart climber = new HillClimbRandRestart(problem, mockParams , generator);
        IHillClimbSolution solution = climber.optimize();

        Assert.assertEquals(0, solution.getScore(), 00000.1);
    }


    @Test
    public void eightQueensMinimize() {
        NQueensSoln initialState = new NQueensSoln(new int[]{0,1,2,3,4,5,6,7});
        NQueensProblem problem = new NQueensProblem(initialState);
        NQueensSolnGenerator generator = new NQueensSolnGenerator(8, 0);
        HillClimbRandRestart climber = new HillClimbRandRestart(problem, mockParams , generator);
        IHillClimbSolution solution = climber.optimize();

        Assert.assertEquals(0, solution.getScore(), 00000.1);
    }

}
