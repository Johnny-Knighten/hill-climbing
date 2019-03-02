package baseclasses;

import com.knighten.ai.hillclimb.HillClimb;
import com.knighten.ai.hillclimb.HillClimbParams;
import com.knighten.ai.hillclimb.interfaces.IHillClimbProblem;
import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;
import com.knighten.ai.hillclimb.nqueens.NQueensProblem;
import com.knighten.ai.hillclimb.nqueens.NQueensSoln;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class HillClimbTests {

    private HillClimbParams mockParams;
    private IHillClimbProblem mockProblem;


    @Before
    public void setup() {
        mockParams = Mockito.mock(HillClimbParams.class);
        Mockito.when(mockParams.getGoalScore()).thenReturn(0.0);
        Mockito.when(mockParams.getMaxIterations()).thenReturn(50);

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

    ////////////////////
    // Method Testing //
    ////////////////////



    ///////////////////////////
    // Actual Algorithm Runs //
    ///////////////////////////

    @Test
    public void fourQueens() {
        NQueensSoln initialState = new NQueensSoln(new int[]{0,1,2,3});
        NQueensProblem problem = new NQueensProblem(initialState);
        HillClimb climber = new HillClimb(problem, mockParams);
        IHillClimbSolution solution = climber.optimize();

        Assert.assertEquals(2, solution.getScore(), 0.00001);
    }


    @Test
    public void eightQueens() {
        NQueensSoln initialState = new NQueensSoln(new int[]{0,1,2,3,4,5,6,7});
        NQueensProblem problem = new NQueensProblem(initialState);
        HillClimb climber = new HillClimb(problem, mockParams);
        IHillClimbSolution solution = climber.optimize();

        Assert.assertEquals(1, solution.getScore(), 0.00001);
    }

}
