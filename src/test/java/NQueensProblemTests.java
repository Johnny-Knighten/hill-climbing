import nqueens.NQueensProblem;
import nqueens.NQueensSoln;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class NQueensProblemTests {

    private NQueensSoln zeroConflictBoard;
    private NQueensSoln oneConflictBoard;
    private NQueensSoln threeConflictBoard;
    private NQueensSoln maxConflictBoard;

    private NQueensSoln mockSolution;

    @Before
    public void setup() {
        mockSolution = Mockito.mock(NQueensSoln.class);

        zeroConflictBoard = new NQueensSoln(new int[]{4,2,0,6,1,7,5,3});
        oneConflictBoard = new NQueensSoln(new int[]{0,1});
        threeConflictBoard = new NQueensSoln(new int[]{4,2,0,6,1,6,5,3});
        maxConflictBoard = new NQueensSoln(new int[]{0,1,2,3,4,5,6,7});
    }

    ////////////////////
    // Method Testing //
    ////////////////////

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
