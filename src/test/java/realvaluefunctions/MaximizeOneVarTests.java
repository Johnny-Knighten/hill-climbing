package realvaluefunctions;

import com.knighten.ai.hillclimb.function.realvalue.IOneVariableFunction;
import com.knighten.ai.hillclimb.function.realvalue.MaximizeOneVar;
import com.knighten.ai.hillclimb.function.realvalue.OneVarSolution;
import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MaximizeOneVarTests {

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
        Mockito.when(mockListSize1.stream()).thenReturn(Arrays.stream(new IHillClimbSolution[]{mockSolutionFitness0}));

        mockListSize2Ascending = Mockito.mock(List.class);
        Mockito.when(mockListSize2Ascending.size()).thenReturn(2);
        Mockito.when(mockListSize2Ascending.get(0)).thenReturn(mockSolutionFitness0);
        Mockito.when(mockListSize2Ascending.get(1)).thenReturn(mockSolutionFitness1);
        Mockito.when(mockListSize2Ascending.stream()).thenReturn(
                Arrays.stream(new IHillClimbSolution[]{mockSolutionFitness0, mockSolutionFitness1}));

        mockListSize2Descending = Mockito.mock(List.class);
        Mockito.when(mockListSize2Descending.size()).thenReturn(2);
        Mockito.when(mockListSize2Descending.get(0)).thenReturn(mockSolutionFitness1);
        Mockito.when(mockListSize2Descending.get(1)).thenReturn(mockSolutionFitness0);
        Mockito.when(mockListSize2Descending.stream()).thenReturn(
                Arrays.stream(new IHillClimbSolution[]{mockSolutionFitness1, mockSolutionFitness0}));
    }

    ////////////////////////
    // Parameter Checking //
    ////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void constructorNullInitialGuess() {
        new MaximizeOneVar(null, mockFunction, -10, 10, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorNullFunction() {
        new MaximizeOneVar(mockSolutionFitness0, null, -10, 10, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorStepSizeIsZero() {
        new MaximizeOneVar(mockSolutionFitness0, mockFunction, -10, 10, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorStepSizeIsNaN() {
        new MaximizeOneVar(mockSolutionFitness0, mockFunction, -10, 10, Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorStepSizeIsInfinity() {
        new MaximizeOneVar(mockSolutionFitness0, mockFunction, -10, 10, Double.POSITIVE_INFINITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorStepSizeIsMaxDouble() {
        new MaximizeOneVar(mockSolutionFitness0, mockFunction, -10, 10, Double.MAX_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorStepSizeIsMinDouble() {
        new MaximizeOneVar(mockSolutionFitness0, mockFunction, -10, 10, Double.MIN_VALUE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMinDomainIsNaN() {
        new MaximizeOneVar(mockSolutionFitness0, mockFunction, Double.NaN, 10, 1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMinDomainIsInfinity() {
        new MaximizeOneVar(mockSolutionFitness0, mockFunction, Double.POSITIVE_INFINITY, 10, 1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMinDomainIsDoubleMax() {
        new MaximizeOneVar(mockSolutionFitness0, mockFunction, Double.MAX_VALUE, 10, 1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMinDomainIsDoubleMin() {
        new MaximizeOneVar(mockSolutionFitness0, mockFunction, Double.MIN_VALUE, 10, 1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMaxDomainIsNaN() {
        new MaximizeOneVar(mockSolutionFitness0, mockFunction, -10, Double.NaN, 1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMaxDomainIsInfinity() {
        new MaximizeOneVar(mockSolutionFitness0, mockFunction, -10, Double.POSITIVE_INFINITY, 1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMaxDomainIsDoubleMax() {
        new MaximizeOneVar(mockSolutionFitness0, mockFunction, -10, Double.MAX_VALUE, 1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMaxDomainIsDoubleMin() {
        new MaximizeOneVar(mockSolutionFitness0, mockFunction, -10, Double.MIN_VALUE, 1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMinDomainLargerThanMaxDomain() {
        new MaximizeOneVar(mockSolutionFitness0, mockFunction, -1, -2 , 1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMinDomainEqualToMaxDomain() {
        new MaximizeOneVar(mockSolutionFitness0, mockFunction, -1, -1 , 1.0);
    }

    ////////////////////
    // Method Testing //
    ////////////////////

    @Test
    public void getBestSolutionListOfSizeOne() {
        MaximizeOneVar testObject = new MaximizeOneVar(mockSolutionFitness0, mockFunction, -1, 1 , 1.0);
        IHillClimbSolution result = testObject.getBestSolution(mockListSize1);

        Assert.assertEquals(mockSolutionFitness0, result);
    }

    @Test
    public void getBestSolutionListOfSizeTwoAscending() {
        MaximizeOneVar testObject = new MaximizeOneVar(mockSolutionFitness0, mockFunction, -1, 1 , 1.0);
        IHillClimbSolution result = testObject.getBestSolution(mockListSize2Ascending);

        verify(mockSolutionFitness0, times(1)).getScore();
        verify(mockSolutionFitness1, times(1)).getScore();

        Assert.assertEquals(mockSolutionFitness1, result);
    }

    @Test
    public void getBestSolutionListOfSizeTwoDescending() {
        MaximizeOneVar testObject = new MaximizeOneVar(mockSolutionFitness0, mockFunction, -1, 1 , 1.0);
        IHillClimbSolution result = testObject.getBestSolution(mockListSize2Descending);

        verify(mockSolutionFitness0, times(1)).getScore();
        verify(mockSolutionFitness1, times(1)).getScore();

        Assert.assertEquals(mockSolutionFitness1, result);
    }


    @Test
    public void isPeakOrPlateauPeak() {
        MaximizeOneVar testObject = new MaximizeOneVar(mockSolutionFitness0, mockFunction, -1, 1 , 1.0);
        boolean result = testObject.atPeakOrPlateau(mockSolutionFitness1, mockSolutionFitness0);

        Assert.assertTrue(result);
    }

    @Test
    public void isPeakOrPlateauPlateau() {
        MaximizeOneVar testObject = new MaximizeOneVar(mockSolutionFitness0, mockFunction, -1, 1 , 1.0);
        boolean result = testObject.atPeakOrPlateau(mockSolutionFitness0, mockSolutionFitness0);

        Assert.assertTrue(result);
    }

    @Test
    public void isPeakOrPlateauNotPeak() {
        MaximizeOneVar testObject = new MaximizeOneVar(mockSolutionFitness0, mockFunction, -1, 1 , 1.0);
        boolean result = testObject.atPeakOrPlateau(mockSolutionFitness0, mockSolutionFitness1);

        Assert.assertTrue(!result);
    }

    @Test
    public void scoreSolutionFunctionIsUed() {
        MaximizeOneVar testObject = new MaximizeOneVar(mockSolutionFitness0, mockFunction, -1, 1 , 1.0);

        testObject.scoreSolution(mockSolutionFitness0);
        verify(mockFunction, times(1)).getFuncValue(anyDouble());
    }

    @Test
    public void currentBetterThanBestNotBetter() {
        MaximizeOneVar testObject = new MaximizeOneVar(mockSolutionFitness0, mockFunction, -1, 1 , 1.0);

        Assert.assertTrue(testObject.firstSolutionBetterThanOther(mockSolutionFitness1, mockSolutionFitness0));
    }

    @Test
    public void currentBetterThanBestBetter() {
        MaximizeOneVar testObject = new MaximizeOneVar(mockSolutionFitness0, mockFunction, -1, 1 , 1.0);
        Assert.assertTrue(!testObject.firstSolutionBetterThanOther(mockSolutionFitness0, mockSolutionFitness1));
    }

    @Test
    public void generateNextSolutionsCorrectSolutionsCreated() {
        MaximizeOneVar testObject = new MaximizeOneVar(mockSolutionFitness0, mockFunction, -2, 2 , 1.0);
        List<IHillClimbSolution> results = testObject.generateNextSolutions(mockSolutionFitness0);

        Assert.assertEquals(2, results.size());
        Assert.assertTrue(IntStream.range(0, 2).anyMatch((i) ->((OneVarSolution) results.get(i)).getXValue() == 0.0));
        Assert.assertTrue(IntStream.range(0, 2).anyMatch((i) ->((OneVarSolution) results.get(i)).getXValue() == 2.0));
    }

}
