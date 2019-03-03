package nqueens;

import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;
import com.knighten.ai.hillclimb.nqueens.NQueensProblem;
import com.knighten.ai.hillclimb.nqueens.NQueensSolution;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class NQueensProblemTests {

    private NQueensSolution zeroConflictBoard;
    private NQueensSolution oneConflictBoard;
    private NQueensSolution threeConflictBoard;
    private NQueensSolution maxConflictBoard;

    private NQueensSolution mockSolution;
    private NQueensSolution mockSolution2;

    private List mockListSize1;
    private List mockListSize2Ascending;
    private List mockListSize2Descending;

    @Before
    public void setup() {
        mockSolution = Mockito.mock(NQueensSolution.class);
        Mockito.when(mockSolution.getScore()).thenReturn(1.0);
        mockSolution2 = Mockito.mock(NQueensSolution.class);
        Mockito.when(mockSolution2.getScore()).thenReturn(2.0);

        mockListSize1 = Mockito.mock(List.class);
        Mockito.when(mockListSize1.size()).thenReturn(1);
        Mockito.when(mockListSize1.get(0)).thenReturn(mockSolution);

        mockListSize2Ascending = Mockito.mock(List.class);
        Mockito.when(mockListSize2Ascending.size()).thenReturn(2);
        Mockito.when(mockListSize2Ascending.get(0)).thenReturn(mockSolution);
        Mockito.when(mockListSize2Ascending.get(1)).thenReturn(mockSolution2);

        mockListSize2Descending = Mockito.mock(List.class);
        Mockito.when(mockListSize2Descending.size()).thenReturn(2);
        Mockito.when(mockListSize2Descending.get(0)).thenReturn(mockSolution2);
        Mockito.when(mockListSize2Descending.get(1)).thenReturn(mockSolution);

        zeroConflictBoard = new NQueensSolution(new int[]{4,2,0,6,1,7,5,3});
        oneConflictBoard = new NQueensSolution(new int[]{3, 3, 0, 2});
        threeConflictBoard = new NQueensSolution(new int[]{4,2,0,6,1,6,5,3});
        maxConflictBoard = new NQueensSolution(new int[]{0,1,2,3,4,5,6,7});
    }

    ////////////////////////
    // Parameter Checking //
    ////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void constructorNullInitialGuess() {
        new NQueensProblem(null);
    }

    ////////////////////
    // Method Testing //
    ////////////////////

    @Test
    public void getBestIndividualListOfSizeOne() {
        NQueensProblem testObject = new NQueensProblem(mockSolution);
        IHillClimbSolution result = testObject.getBestSolution(mockListSize1);

        verify(mockListSize1, times(1)).get(0);
        Assert.assertEquals(mockSolution, result);
    }

    @Test
    public void getBestIndividualListOfSizeTwoAscending() {
        NQueensProblem testObject = new NQueensProblem(mockSolution);
        IHillClimbSolution result = testObject.getBestSolution(mockListSize2Ascending);

        verify(mockListSize2Ascending, times(1)).get(0);
        verify(mockSolution, times(1)).getScore();
        verify(mockListSize2Ascending, times(1)).get(1);
        verify(mockSolution2, times(1)).getScore();

        Assert.assertEquals(mockSolution, result);
    }

    @Test
    public void getBestIndividualListOfSizeTwoDescending() {
        NQueensProblem testObject = new NQueensProblem(mockSolution);
        IHillClimbSolution result = testObject.getBestSolution(mockListSize2Descending);

        verify(mockListSize2Descending, times(1)).get(0);
        verify(mockSolution, times(1)).getScore();
        // Gets Twice To Set Current Min
        verify(mockListSize2Descending, times(2)).get(1);
        verify(mockSolution2, times(1)).getScore();

        Assert.assertEquals(mockSolution, result);
    }

    @Test
    public void isPeakOrPlateauPeak (){
        NQueensProblem testObject = new NQueensProblem(mockSolution);
        boolean result = testObject.isPeakOrPlateau(mockSolution, mockSolution2);

        Assert.assertTrue(result);
    }

    @Test
    public void isPeakOrPlateauPlateau (){
        NQueensProblem testObject = new NQueensProblem(mockSolution);
        boolean result = testObject.isPeakOrPlateau(mockSolution, mockSolution);

        Assert.assertTrue(result);
    }

    @Test
    public void isPeakOrPlateauNotPeak (){
        NQueensProblem testObject = new NQueensProblem(mockSolution);
        boolean result = testObject.isPeakOrPlateau(mockSolution2, mockSolution);

        Assert.assertTrue(!result);
    }

    @Test
    public void scoreSolutionBoardWithZeroConflicts() {
        NQueensProblem testObject = new NQueensProblem(mockSolution);
        double score = testObject.scoreSolution(zeroConflictBoard);

        Assert.assertEquals(0.0, score, 0.0001);
    }

    @Test
    public void scoreSolutionBoardWithOneConflicts() {
        NQueensProblem testObject = new NQueensProblem(mockSolution);
        double score = testObject.scoreSolution(oneConflictBoard);

        Assert.assertEquals(1.0, score, 0.0001);
    }

    @Test
    public void scoreSolutionBoardWithThreeConflicts() {
        NQueensProblem testObject = new NQueensProblem(mockSolution);
        double score = testObject.scoreSolution(threeConflictBoard);

        Assert.assertEquals(3.0, score, 0.0001);
    }

    @Test
    public void scoreSolutionBoardWithMaxConflicts() {
        NQueensProblem testObject = new NQueensProblem(mockSolution);
        double score = testObject.scoreSolution(maxConflictBoard);

        Assert.assertEquals(28, score, 0.0001);
    }

}
