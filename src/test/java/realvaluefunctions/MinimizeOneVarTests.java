package realvaluefunctions;

import com.knighten.ai.hillclimb.function.realvalue.IOneVariableFunction;
import com.knighten.ai.hillclimb.function.realvalue.MinimizeOneVar;
import com.knighten.ai.hillclimb.function.realvalue.OneVarSolution;
import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;
import com.knighten.ai.hillclimb.nqueens.NQueensSolution;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MinimizeOneVarTests {


    private IOneVariableFunction mockFunction;
    private OneVarSolution mockSolutionFitness0;
    private OneVarSolution mockSolutionFitness1;
    private List mockListSize1;
    private List mockListSize2Ascending;
    private List mockListSize2Descending;

    @Before
    public void setup() {
        mockFunction = Mockito.mock(IOneVariableFunction.class);

        mockSolutionFitness0 = Mockito.mock(OneVarSolution.class);
        Mockito.when(mockSolutionFitness0.getScore()).thenReturn(1.0);
        Mockito.when(mockSolutionFitness0.getXValue()).thenReturn(1.0);
        mockSolutionFitness1 = Mockito.mock(OneVarSolution.class);
        Mockito.when(mockSolutionFitness1.getScore()).thenReturn(2.0);

        mockListSize1 = Mockito.mock(List.class);
        Mockito.when(mockListSize1.size()).thenReturn(1);
        Mockito.when(mockListSize1.get(0)).thenReturn(mockSolutionFitness0);

        mockListSize2Ascending = Mockito.mock(List.class);
        Mockito.when(mockListSize2Ascending.size()).thenReturn(2);
        Mockito.when(mockListSize2Ascending.get(0)).thenReturn(mockSolutionFitness0);
        Mockito.when(mockListSize2Ascending.get(1)).thenReturn(mockSolutionFitness1);

        mockListSize2Descending = Mockito.mock(List.class);
        Mockito.when(mockListSize2Descending.size()).thenReturn(2);
        Mockito.when(mockListSize2Descending.get(0)).thenReturn(mockSolutionFitness1);
        Mockito.when(mockListSize2Descending.get(1)).thenReturn(mockSolutionFitness0);
    }

    ////////////////////////
    // Parameter Checking //
    ////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void constructorNullInitialGuess() {
        new MinimizeOneVar(null, mockFunction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorNullFunction() {
        new MinimizeOneVar(mockSolutionFitness0, null);
    }

    ////////////////////
    // Method Testing //
    ////////////////////

    @Test
    public void getBestSolutionListOfSizeOne() {
        MinimizeOneVar testObject = new MinimizeOneVar(mockSolutionFitness0, mockFunction);
        IHillClimbSolution result = testObject.getBestSolution(mockListSize1);

        verify(mockListSize1, times(1)).get(0);
        Assert.assertEquals(mockSolutionFitness0, result);
    }

    @Test
    public void getBestSolutionListOfSizeTwoAscending() {
        MinimizeOneVar testObject = new MinimizeOneVar(mockSolutionFitness0, mockFunction);
        IHillClimbSolution result = testObject.getBestSolution(mockListSize2Ascending);

        verify(mockListSize2Ascending, times(1)).get(0);
        verify(mockSolutionFitness0, times(1)).getScore();
        verify(mockListSize2Ascending, times(1)).get(1);
        verify(mockSolutionFitness1, times(1)).getScore();

        Assert.assertEquals(mockSolutionFitness0, result);
    }

    @Test
    public void getBestSolutionListOfSizeTwoDescending() {
        MinimizeOneVar testObject = new MinimizeOneVar(mockSolutionFitness0, mockFunction);
        IHillClimbSolution result = testObject.getBestSolution(mockListSize2Descending);

        verify(mockListSize2Descending, times(1)).get(0);
        verify(mockSolutionFitness0, times(1)).getScore();
        // Gets Twice To Set Current Min
        verify(mockListSize2Descending, times(2)).get(1);
        verify(mockSolutionFitness1, times(1)).getScore();

        Assert.assertEquals(mockSolutionFitness0, result);
    }


    @Test
    public void isPeakOrPlateauPeak() {
        MinimizeOneVar testObject = new MinimizeOneVar(mockSolutionFitness0, mockFunction);
        boolean result = testObject.isPeakOrPlateau(mockSolutionFitness0, mockSolutionFitness1);

        Assert.assertTrue(result);
    }

    @Test
    public void isPeakOrPlateauPlateau() {
        MinimizeOneVar testObject = new MinimizeOneVar(mockSolutionFitness0, mockFunction);
        boolean result = testObject.isPeakOrPlateau(mockSolutionFitness0, mockSolutionFitness0);

        Assert.assertTrue(result);
    }

    @Test
    public void isPeakOrPlateauNotPeak() {
        MinimizeOneVar testObject = new MinimizeOneVar(mockSolutionFitness0, mockFunction);
        boolean result = testObject.isPeakOrPlateau(mockSolutionFitness1, mockSolutionFitness0);

        Assert.assertTrue(!result);
    }

    @Test
    public void scoreSolutionFunctionIsUed() {
        MinimizeOneVar testObject = new MinimizeOneVar(mockSolutionFitness0, mockFunction);

        testObject.scoreSolution(mockSolutionFitness0);
        verify(mockFunction, times(1)).getFuncValue(anyDouble());
    }

    @Test
    public void currentBetterThanBestNotBetter() {
        MinimizeOneVar testObject = new MinimizeOneVar(mockSolutionFitness0, mockFunction);

        Assert.assertTrue(testObject.currentBetterThanBest(mockSolutionFitness0, mockSolutionFitness1));
    }

    @Test
    public void currentBetterThanBestBetter() {
        MinimizeOneVar testObject = new MinimizeOneVar(mockSolutionFitness0, mockFunction);
        Assert.assertTrue(!testObject.currentBetterThanBest(mockSolutionFitness1, mockSolutionFitness0));
    }

}
