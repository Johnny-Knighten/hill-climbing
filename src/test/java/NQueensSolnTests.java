import interfaces.IHillClimbSolution;
import nqueens.NQueensSoln;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class NQueensSolnTests {

    private NQueensSoln zeroConflictBoard;
    private NQueensSoln oneConflictBoard;
    private NQueensSoln threeConflictBoard;
    private NQueensSoln maxConflictBoard;

    private NQueensSoln twelveNextStates;
    private NQueensSoln twentyNextStates;
    private NQueensSoln thirtyNextStates;
    private NQueensSoln fortyTwoNextStates;
    private NQueensSoln fiftySixNextStates;

    @Before
    public void setup() {
        zeroConflictBoard = new NQueensSoln(new int[]{4,2,0,6,1,7,5,3});
        oneConflictBoard = new NQueensSoln(new int[]{0,1});
        threeConflictBoard = new NQueensSoln(new int[]{4,2,0,6,1,6,5,3});
        maxConflictBoard = new NQueensSoln(new int[]{0,1,2,3,4,5,6,7});

        twelveNextStates = new NQueensSoln(new int[]{0,1,2,3});
        twentyNextStates = new NQueensSoln(new int[]{0,1,2,3,4});
        thirtyNextStates = new NQueensSoln(new int[]{0,1,2,3,4,5});
        fortyTwoNextStates = new NQueensSoln(new int[]{0,1,2,3,4,5,6});
        fiftySixNextStates = new NQueensSoln(new int[]{0,1,2,3,4,5,6,7});
    }

    //////////////////////////
    // Scoring Method Tests //
    //////////////////////////

    @Test
    public void scoreBoardWithZeroConflict() {
        double score = zeroConflictBoard.scoreState();
        Assert.assertEquals(0.0, score, 0.0001);
    }

    @Test
    public void scoreBoardWithOneConflict() {
        double score = oneConflictBoard.scoreState();
        Assert.assertEquals(1.0, score, 0.0001);
    }

    @Test
    public void scoreBoardWithThreeConflict() {
        double score = threeConflictBoard.scoreState();
        Assert.assertEquals(3.0, score, 0.0001);
    }

    @Test
    public void scoreBoardWithMaxConflict() {
        double score = maxConflictBoard.scoreState();
        Assert.assertEquals(28, score, 0.0001);
    }

    ////////////////////////////////////////
    // Next State Generation Method Tests //
    ////////////////////////////////////////

    @Test
    public void generateTwelveNextStates() {
        List<IHillClimbSolution> nextStates = twelveNextStates.generateNextSolns();
        Assert.assertEquals(12, nextStates.size(), 0.0001);
    }

    @Test
    public void generateTwentyNextStates() {
        List<IHillClimbSolution> nextStates = twentyNextStates.generateNextSolns();
        Assert.assertEquals(20, nextStates.size(), 0.0001);
    }

    @Test
    public void generateThirtyNextStates() {
        List<IHillClimbSolution> nextStates = thirtyNextStates.generateNextSolns();
        Assert.assertEquals(30, nextStates.size(), 0.0001);
    }

    @Test
    public void generateFortyTwoNextStates() {
        List<IHillClimbSolution> nextStates = fortyTwoNextStates.generateNextSolns();
        Assert.assertEquals(42, nextStates.size(), 0.0001);
    }

    @Test
    public void generateFiftySixNextStates() {
        List<IHillClimbSolution> nextStates = fiftySixNextStates.generateNextSolns();
        Assert.assertEquals(56, nextStates.size(), 0.0001);
    }

    //////////////////////////////////////////
    // Ensure Correct Next States Generated //
    //////////////////////////////////////////

    @Test
    public void ensureCorrectTwelveNextStates() {
        List<IHillClimbSolution> nextStates = twelveNextStates.generateNextSolns();
        Assert.assertTrue(nextStates.contains(new NQueensSoln(new int[]{1,1,2,3})));
        Assert.assertTrue(nextStates.contains(new NQueensSoln(new int[]{2,1,2,3})));
        Assert.assertTrue(nextStates.contains(new NQueensSoln(new int[]{3,1,2,3})));
        Assert.assertTrue(nextStates.contains(new NQueensSoln(new int[]{0,0,2,3})));
        Assert.assertTrue(nextStates.contains(new NQueensSoln(new int[]{0,2,2,3})));
        Assert.assertTrue(nextStates.contains(new NQueensSoln(new int[]{0,3,2,3})));
        Assert.assertTrue(nextStates.contains(new NQueensSoln(new int[]{0,1,0,3})));
        Assert.assertTrue(nextStates.contains(new NQueensSoln(new int[]{0,1,1,3})));
        Assert.assertTrue(nextStates.contains(new NQueensSoln(new int[]{0,1,3,3})));
        Assert.assertTrue(nextStates.contains(new NQueensSoln(new int[]{0,1,2,0})));
        Assert.assertTrue(nextStates.contains(new NQueensSoln(new int[]{0,1,2,1})));
        Assert.assertTrue(nextStates.contains(new NQueensSoln(new int[]{0,1,2,2})));
    }

}
