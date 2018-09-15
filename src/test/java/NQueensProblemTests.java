import interfaces.IHillClimbProblem;
import nqueens.NQueensProblem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class NQueensProblemTests {

    private NQueensProblem zeroConflictBoard;
    private NQueensProblem oneConflictBoard;
    private NQueensProblem threeConflictBoard;
    private NQueensProblem maxConflictBoard;

    private NQueensProblem twelveNextStates;
    private NQueensProblem twentyNextStates;
    private NQueensProblem thirtyNextStates;
    private NQueensProblem fortyTwoNextStates;
    private NQueensProblem fiftySixNextStates;

    @Before
    public void setup() {
        zeroConflictBoard = new NQueensProblem(new int[]{4,2,0,6,1,7,5,3});
        oneConflictBoard = new NQueensProblem(new int[]{0,1});
        threeConflictBoard = new NQueensProblem(new int[]{4,2,0,6,1,6,5,3});
        maxConflictBoard = new NQueensProblem(new int[]{0,1,2,3,4,5,6,7});

        twelveNextStates = new NQueensProblem(new int[]{0,1,2,3});
        twentyNextStates = new NQueensProblem(new int[]{0,1,2,3,4});
        thirtyNextStates = new NQueensProblem(new int[]{0,1,2,3,4,5});
        fortyTwoNextStates = new NQueensProblem(new int[]{0,1,2,3,4,5,6});
        fiftySixNextStates = new NQueensProblem(new int[]{0,1,2,3,4,5,6,7});
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
        List<IHillClimbProblem> nextStates = twelveNextStates.generateNextStates();
        Assert.assertEquals(12, nextStates.size(), 0.0001);
    }

    @Test
    public void generateTwentyNextStates() {
        List<IHillClimbProblem> nextStates = twentyNextStates.generateNextStates();
        Assert.assertEquals(20, nextStates.size(), 0.0001);
    }

    @Test
    public void generateThirtyNextStates() {
        List<IHillClimbProblem> nextStates = thirtyNextStates.generateNextStates();
        Assert.assertEquals(30, nextStates.size(), 0.0001);
    }

    @Test
    public void generateFortyTwoNextStates() {
        List<IHillClimbProblem> nextStates = fortyTwoNextStates.generateNextStates();
        Assert.assertEquals(42, nextStates.size(), 0.0001);
    }

    @Test
    public void generateFiftySixNextStates() {
        List<IHillClimbProblem> nextStates = fiftySixNextStates.generateNextStates();
        Assert.assertEquals(56, nextStates.size(), 0.0001);
    }

    //////////////////////////////////////////
    // Ensure Correct Next States Generated //
    //////////////////////////////////////////

    @Test
    public void ensureCorrectTwelveNextStates() {
        List<IHillClimbProblem> nextStates = twelveNextStates.generateNextStates();
        Assert.assertTrue(nextStates.contains(new NQueensProblem(new int[]{1,1,2,3})));
        Assert.assertTrue(nextStates.contains(new NQueensProblem(new int[]{2,1,2,3})));
        Assert.assertTrue(nextStates.contains(new NQueensProblem(new int[]{3,1,2,3})));
        Assert.assertTrue(nextStates.contains(new NQueensProblem(new int[]{0,0,2,3})));
        Assert.assertTrue(nextStates.contains(new NQueensProblem(new int[]{0,2,2,3})));
        Assert.assertTrue(nextStates.contains(new NQueensProblem(new int[]{0,3,2,3})));
        Assert.assertTrue(nextStates.contains(new NQueensProblem(new int[]{0,1,0,3})));
        Assert.assertTrue(nextStates.contains(new NQueensProblem(new int[]{0,1,1,3})));
        Assert.assertTrue(nextStates.contains(new NQueensProblem(new int[]{0,1,3,3})));
        Assert.assertTrue(nextStates.contains(new NQueensProblem(new int[]{0,1,2,0})));
        Assert.assertTrue(nextStates.contains(new NQueensProblem(new int[]{0,1,2,1})));
        Assert.assertTrue(nextStates.contains(new NQueensProblem(new int[]{0,1,2,2})));
    }
}
