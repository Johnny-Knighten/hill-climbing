package baseclasses;

import com.knighten.ai.hillclimb.HillClimb;
import com.knighten.ai.hillclimb.HillClimbParams;
import com.knighten.ai.hillclimb.interfaces.IHillClimbProblem;
import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;
import com.knighten.ai.hillclimb.nqueens.NQueensProblem;
import com.knighten.ai.hillclimb.nqueens.NQueensSolution;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Iterator;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class HillClimbTests {

    private HillClimbParams mockParams;
    private HillClimbParams mockParamsRealRuns;
    private IHillClimbProblem mockProblemNoPeak;
    private IHillClimbProblem mockProblemPeak;
    private IHillClimbSolution mockSolution;
    private IHillClimbSolution mockSolutionNextSolutions;
    private List mockList;
    private Iterator mockIterator;


    @Before
    public void setup() {
        mockParams = Mockito.mock(HillClimbParams.class);
        Mockito.when(mockParams.getGoalScore()).thenReturn(0.0);
        Mockito.when(mockParams.getMaxIterations()).thenReturn(0);

        mockSolutionNextSolutions = Mockito.mock(IHillClimbSolution.class);
        Mockito.when(mockSolutionNextSolutions.getScore()).thenReturn(1.0);

        mockList = Mockito.mock(List.class);
        mockIterator = Mockito.mock(Iterator.class);
        Mockito.when(mockIterator.hasNext()).thenReturn(true, false);
        Mockito.when(mockIterator.next()).thenReturn(mockSolutionNextSolutions);
        Mockito.when(mockList.get(0)).thenReturn(mockSolutionNextSolutions);
        Mockito.when(mockList.iterator()).thenReturn(mockIterator);

        mockSolution = Mockito.mock(IHillClimbSolution.class);


        mockProblemNoPeak = Mockito.mock(IHillClimbProblem.class);
        Mockito.when(mockProblemNoPeak.getInitialGuess()).thenReturn(mockSolution);
        Mockito.when(mockProblemNoPeak.getBestSolution(any())).thenReturn(mockSolutionNextSolutions);
        Mockito.when(mockProblemNoPeak.atPeakOrPlateau(any(), any())).thenReturn(false);
        Mockito.when(mockProblemNoPeak.generateNextSolutions(any())).thenReturn(mockList);

        mockProblemPeak = Mockito.mock(IHillClimbProblem.class);
        Mockito.when(mockProblemPeak.getInitialGuess()).thenReturn(mockSolution);
        Mockito.when(mockProblemPeak.getBestSolution(any())).thenReturn(mockSolutionNextSolutions);
        Mockito.when(mockProblemPeak.atPeakOrPlateau(any(), any())).thenReturn(true);
        Mockito.when(mockProblemPeak.generateNextSolutions(any())).thenReturn(mockList);

        mockParamsRealRuns = Mockito.mock(HillClimbParams.class);
        Mockito.when(mockParamsRealRuns.getGoalScore()).thenReturn(0.0);
        Mockito.when(mockParamsRealRuns.getMaxIterations()).thenReturn(50);
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
        new HillClimb(mockProblemNoPeak, null);
    }

    ////////////////////
    // Method Testing //
    ////////////////////

    @Test
    public void optimizeOneIterationNoPeak() {
        HillClimb testObject = new HillClimb(mockProblemNoPeak, mockParams);
        testObject.optimize();

        // Get Initial Guess At Beginning
        verify(mockProblemNoPeak, times(1)).getInitialGuess();

        // Initial Guess Score Is Calculated And Stored
        verify(mockSolution, times(1)).setScore(anyDouble());
        verify(mockProblemNoPeak, times(1)).scoreSolution(mockSolution);

        // Generate Next Possible Solutions
        verify(mockProblemNoPeak, times(1)).generateNextSolutions(mockSolution);

        // Next Possible Solutions Scores Are Set
        verify(mockSolutionNextSolutions, times(1)).setScore(anyDouble());

        // Best Solution Is Retrieved
        verify(mockProblemNoPeak, times(1)).getBestSolution(mockList);

        // Ensure Check For Peak Or Plateau
        verify(mockProblemNoPeak, times(1)).atPeakOrPlateau(mockSolution, mockSolutionNextSolutions);

        // Check Goal Score
        verify(mockParams, times(1)).getGoalScore();

        // Check Max Iterations
        verify(mockParams, times(1)).getMaxIterations();
    }

    @Test
    public void optimizeOneIterationWithPeak() {
        HillClimb testObject = new HillClimb(mockProblemPeak, mockParams);
        testObject.optimize();

        // Get Initial Guess At Beginning
        verify(mockProblemPeak, times(1)).getInitialGuess();

        // Initial Guess Score Is Calculated And Stored
        verify(mockSolution, times(1)).setScore(anyDouble());
        verify(mockProblemPeak, times(1)).scoreSolution(mockSolution);

        // Generate Next Possible Solutions
        verify(mockProblemPeak, times(1)).generateNextSolutions(mockSolution);

        // Next Possible Solutions Scores Are Set
        verify(mockSolutionNextSolutions, times(1)).setScore(anyDouble());

        // Best Solution Is Retrieved
        verify(mockProblemPeak, times(1)).getBestSolution(mockList);

        // Ensure Check For Peak Or Plateau
        verify(mockProblemPeak, times(1)).atPeakOrPlateau(mockSolution, mockSolutionNextSolutions);

        // Check Goal Score
        verify(mockParams, times(1)).getGoalScore();

        // Check Max Iterations - Not Called Since There Is A Peak
    }

    ///////////////////////////
    // Actual Algorithm Runs //
    ///////////////////////////

    @Test
    public void fourQueens() {
        NQueensSolution initialState = new NQueensSolution(new int[]{0,1,2,3});
        NQueensProblem problem = new NQueensProblem(initialState);
        HillClimb climber = new HillClimb(problem, mockParamsRealRuns);
        IHillClimbSolution solution = climber.optimize();

        Assert.assertEquals(2, solution.getScore(), 0.00001);
    }

    @Test
    public void eightQueens() {
        NQueensSolution initialState = new NQueensSolution(new int[]{0,1,2,3,4,5,6,7});
        NQueensProblem problem = new NQueensProblem(initialState);
        HillClimb climber = new HillClimb(problem, mockParamsRealRuns);
        IHillClimbSolution solution = climber.optimize();

        Assert.assertEquals(1, solution.getScore(), 0.00001);
    }

}
