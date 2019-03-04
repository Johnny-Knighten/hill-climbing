package baseclasses;

import com.knighten.ai.hillclimb.HillClimbParams;
import com.knighten.ai.hillclimb.HillClimbRandRestart;
import com.knighten.ai.hillclimb.interfaces.IHillClimbProblem;
import com.knighten.ai.hillclimb.interfaces.IHillClimbSolnGenerator;
import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;
import com.knighten.ai.hillclimb.nqueens.NQueensProblem;
import com.knighten.ai.hillclimb.nqueens.NQueensSolution;
import com.knighten.ai.hillclimb.nqueens.NQueensSolnGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class HillClimbRandRestartTests {

    private HillClimbParams mockParams;
    private HillClimbParams mockParamsRealRuns;
    private IHillClimbProblem mockProblemNoPeak;
    private IHillClimbProblem mockProblemPeak;
    private IHillClimbProblem mockProblemMaxIteration;
    private IHillClimbSolution mockSolutionIsGoal;
    private IHillClimbSolution mockSolutionNotGoal;
    private IHillClimbSolution mockSolutionReturnedByGenNextSolutions;
    private IHillClimbSolution mockSolutionGeneratedByGenerator;
    private List mockList;
    private Iterator mockIteratorOfMockList;
    private IHillClimbSolnGenerator mockGenerator;


    @Before
    public void setup() {
        mockParams = Mockito.mock(HillClimbParams.class);
        Mockito.when(mockParams.getGoalScore()).thenReturn(0.0);
        Mockito.when(mockParams.getMaxIterations()).thenReturn(0);

        mockSolutionReturnedByGenNextSolutions = Mockito.mock(IHillClimbSolution.class);

        mockList = Mockito.mock(List.class);
        mockIteratorOfMockList = Mockito.mock(Iterator.class);
        Mockito.when(mockIteratorOfMockList.hasNext()).thenReturn(true, false);
        Mockito.when(mockIteratorOfMockList.next()).thenReturn(mockSolutionReturnedByGenNextSolutions);
        Mockito.when(mockList.get(0)).thenReturn(mockSolutionReturnedByGenNextSolutions);
        Mockito.when(mockList.iterator()).thenReturn(mockIteratorOfMockList);


        mockSolutionIsGoal = Mockito.mock(IHillClimbSolution.class);

        Mockito.when(mockSolutionIsGoal.getScore()).thenReturn(0.0);

        mockProblemNoPeak = Mockito.mock(IHillClimbProblem.class);
        Mockito.when(mockProblemNoPeak.getInitialGuess()).thenReturn(mockSolutionIsGoal);
        Mockito.when(mockProblemNoPeak.getBestSolution(any())).thenReturn(mockSolutionReturnedByGenNextSolutions);
        Mockito.when(mockProblemNoPeak.isPeakOrPlateau(any(), any())).thenReturn(false);
        Mockito.when(mockProblemNoPeak.generateNextSolutions(any())).thenReturn(mockList);

        mockProblemPeak = Mockito.mock(IHillClimbProblem.class);
        Mockito.when(mockProblemPeak.getInitialGuess()).thenReturn(mockSolutionIsGoal);
        Mockito.when(mockProblemPeak.getBestSolution(any())).thenReturn(mockSolutionReturnedByGenNextSolutions);
        Mockito.when(mockProblemPeak.isPeakOrPlateau(any(), any())).thenReturn(true);
        Mockito.when(mockProblemPeak.generateNextSolutions(any())).thenReturn(mockList);

        mockSolutionNotGoal = Mockito.mock(IHillClimbSolution.class);
        Mockito.when(mockSolutionNotGoal.getScore()).thenReturn(1.0);

        mockProblemMaxIteration = Mockito.mock(IHillClimbProblem.class);
        Mockito.when(mockProblemMaxIteration.getInitialGuess()).thenReturn(mockSolutionNotGoal);
        Mockito.when(mockProblemMaxIteration.getBestSolution(any())).thenReturn(mockSolutionReturnedByGenNextSolutions);
        Mockito.when(mockProblemMaxIteration.isPeakOrPlateau(any(), any())).thenReturn(false);
        Mockito.when(mockProblemMaxIteration.generateNextSolutions(any())).thenReturn(mockList);

        mockSolutionGeneratedByGenerator = Mockito.mock(IHillClimbSolution.class);
        mockGenerator = Mockito.mock(IHillClimbSolnGenerator.class);
        Mockito.when(mockGenerator.randomSolution()).thenReturn(mockSolutionGeneratedByGenerator);

        mockParamsRealRuns = Mockito.mock(HillClimbParams.class);
        Mockito.when(mockParamsRealRuns.getGoalScore()).thenReturn(0.0);
        Mockito.when(mockParamsRealRuns.getMaxIterations()).thenReturn(100);

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
        new HillClimbRandRestart(mockProblemNoPeak, null, mockGenerator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorNullGenerator() {
        new HillClimbRandRestart(mockProblemNoPeak, mockParams,null);
    }

    ////////////////////
    // Method Testing //
    ////////////////////

    @Test
    public void optimizeOneIterationNoPeakGoalScoreFound() {
        HillClimbRandRestart testObject = new HillClimbRandRestart(mockProblemNoPeak, mockParams, mockGenerator);
        testObject.optimize();

        // Get Initial Guess At Beginning
        verify(mockProblemNoPeak, times(1)).getInitialGuess();

        // Initial Guess Score Is Calculated And Stored
        verify(mockSolutionIsGoal, times(1)).setScore(anyDouble());
        verify(mockProblemNoPeak, times(1)).scoreSolution(mockSolutionIsGoal);

        // Check If Current Is Better Than Best
        verify(mockProblemNoPeak, times(1)).currentBetterThanBest(mockSolutionIsGoal, mockSolutionIsGoal);

        // Generate Next Possible Solutions
        verify(mockProblemNoPeak, times(1)).generateNextSolutions(mockSolutionIsGoal);

        // Next Possible Solutions Scores Are Set
        verify(mockSolutionReturnedByGenNextSolutions, times(1)).setScore(anyDouble());

        // Best Solution Is Retrieved
        verify(mockProblemNoPeak, times(1)).getBestSolution(mockList);

        // Ensure Check For Peak Or Plateau
        verify(mockProblemNoPeak, times(1)).isPeakOrPlateau(mockSolutionIsGoal, mockSolutionReturnedByGenNextSolutions);

        // Check Goal Score
        verify(mockParams, times(1)).getGoalScore();

    }

    @Test
    public void optimizeOneIterationWithPeakGoalScoreFound() {
        HillClimbRandRestart testObject = new HillClimbRandRestart(mockProblemPeak, mockParams, mockGenerator);
        testObject.optimize();

        // Get Initial Guess At Beginning
        verify(mockProblemPeak, times(1)).getInitialGuess();

        // Initial Guess Score Is Calculated And Stored
        verify(mockSolutionIsGoal, times(1)).setScore(anyDouble());
        verify(mockProblemPeak, times(1)).scoreSolution(mockSolutionIsGoal);

        // Check If Current Is Better Than Best
        verify(mockProblemPeak, times(1)).currentBetterThanBest(mockSolutionIsGoal, mockSolutionIsGoal);

        // Generate Next Possible Solutions
        verify(mockProblemPeak, times(1)).generateNextSolutions(mockSolutionIsGoal);

        // Next Possible Solutions Scores Are Set
        verify(mockSolutionReturnedByGenNextSolutions, times(1)).setScore(anyDouble());

        // Best Solution Is Retrieved
        verify(mockProblemPeak, times(1)).getBestSolution(mockList);

        // Ensure Check For Peak Or Plateau
        verify(mockProblemPeak, times(1)).isPeakOrPlateau(mockSolutionIsGoal, mockSolutionReturnedByGenNextSolutions);

        // Random Restart Occurred
        verify(mockGenerator, times(1)).randomSolution();

        // Check Goal Score
        verify(mockParams, times(1)).getGoalScore();

    }

    @Test
    public void optimizeMaxIterationCheck() {
        HillClimbRandRestart testObject = new HillClimbRandRestart(mockProblemMaxIteration, mockParams, mockGenerator);
        testObject.optimize();

        // Get Initial Guess At Beginning
        verify(mockProblemMaxIteration, times(1)).getInitialGuess();

        // Initial Guess Score Is Calculated And Stored
        verify(mockSolutionNotGoal, times(1)).setScore(anyDouble());
        verify(mockProblemMaxIteration, times(1)).scoreSolution(mockSolutionNotGoal);

        // Check If Current Is Better Than Best
        verify(mockProblemMaxIteration, times(1)).currentBetterThanBest(mockSolutionNotGoal, mockSolutionNotGoal);

        // Generate Next Possible Solutions
        verify(mockProblemMaxIteration, times(1)).generateNextSolutions(mockSolutionNotGoal);

        // Next Possible Solutions Scores Are Set
        verify(mockSolutionReturnedByGenNextSolutions, times(1)).setScore(anyDouble());

        // Best Solution Is Retrieved
        verify(mockProblemMaxIteration, times(1)).getBestSolution(mockList);

        // Ensure Check For Peak Or Plateau
        verify(mockProblemMaxIteration, times(1)).isPeakOrPlateau(mockSolutionNotGoal, mockSolutionReturnedByGenNextSolutions);

        // Check Goal Score
        verify(mockParams, times(1)).getGoalScore();

        // Check Max Iterations
        verify(mockParams, times(1)).getMaxIterations();
    }

    ///////////////////////////
    // Actual Algorithm Runs //
    ///////////////////////////

    @Test
    public void fourQueensMinimize() {
        NQueensSolution initialState = new NQueensSolution(new int[]{0,1,2,3});
        NQueensProblem problem = new NQueensProblem(initialState);
        Random random = new Random(0);
        NQueensSolnGenerator generator = new NQueensSolnGenerator(4, random);
        HillClimbRandRestart climber = new HillClimbRandRestart(problem, mockParamsRealRuns , generator);
        IHillClimbSolution solution = climber.optimize();

        Assert.assertEquals(0, solution.getScore(), 00000.1);
    }

    @Test
    public void eightQueensMinimize() {
        NQueensSolution initialState = new NQueensSolution(new int[]{0,1,2,3,4,5,6,7});
        NQueensProblem problem = new NQueensProblem(initialState);
        Random random = new Random(0);
        NQueensSolnGenerator generator = new NQueensSolnGenerator(8, random);
        HillClimbRandRestart climber = new HillClimbRandRestart(problem, mockParamsRealRuns , generator);
        IHillClimbSolution solution = climber.optimize();

        Assert.assertEquals(0, solution.getScore(), 00000.1);
    }

}
