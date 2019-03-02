package baseclasses;

import com.knighten.ai.hillclimb.HillClimbParams;
import com.knighten.ai.hillclimb.HillClimbRandRestart;
import com.knighten.ai.hillclimb.interfaces.IHillClimbProblem;
import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;
import com.knighten.ai.hillclimb.nqueens.NQueensProblem;
import com.knighten.ai.hillclimb.nqueens.NQueensSoln;
import com.knighten.ai.hillclimb.nqueens.NQueensSolnGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class HillClimbRandRestartTests {

    private HillClimbParams mockParams;
    private IHillClimbProblem mockProblem;
    private NQueensSolnGenerator mockGenerator;

    @Before
    public void setup() {
        mockParams = Mockito.mock(HillClimbParams.class);
        Mockito.when(mockParams.getGoalScore()).thenReturn(0.0);
        Mockito.when(mockParams.getMaxIterations()).thenReturn(100);

        mockProblem = Mockito.mock(IHillClimbProblem.class);

        mockGenerator = Mockito.mock(NQueensSolnGenerator.class);
    }

    ////////////////////////
    // Parameter Checking //
    ////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void constructorNullProblem() {
        new HillClimbRandRestart(null, mockParams, mockGenerator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorNullParams() {
        new HillClimbRandRestart(mockProblem, null, mockGenerator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorNullGenerator() {
        new HillClimbRandRestart(mockProblem, mockParams,null);
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
