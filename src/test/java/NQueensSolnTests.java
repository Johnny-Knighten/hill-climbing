import interfaces.IHillClimbSolution;
import nqueens.NQueensSoln;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class NQueensSolnTests {



    private NQueensSoln twelveNextStates;
    private NQueensSoln twentyNextStates;
    private NQueensSoln thirtyNextStates;
    private NQueensSoln fortyTwoNextStates;
    private NQueensSoln fiftySixNextStates;

    @Before
    public void setup() {
        twelveNextStates = new NQueensSoln(new int[]{0,1,2,3});
        twentyNextStates = new NQueensSoln(new int[]{0,1,2,3,4});
        thirtyNextStates = new NQueensSoln(new int[]{0,1,2,3,4,5});
        fortyTwoNextStates = new NQueensSoln(new int[]{0,1,2,3,4,5,6});
        fiftySixNextStates = new NQueensSoln(new int[]{0,1,2,3,4,5,6,7});
    }

    //////////////////////////
    // Scoring Method Tests //
    //////////////////////////



    ////////////////////////////////////////
    // Next State Generation Method Tests //
    ////////////////////////////////////////

    @Test
    public void generateTwelveNextStates() {
        List<IHillClimbSolution> nextStates = twelveNextStates.generateNextSolutions();
        Assert.assertEquals(12, nextStates.size(), 0.0001);
    }

    @Test
    public void generateTwentyNextStates() {
        List<IHillClimbSolution> nextStates = twentyNextStates.generateNextSolutions();
        Assert.assertEquals(20, nextStates.size(), 0.0001);
    }

    @Test
    public void generateThirtyNextStates() {
        List<IHillClimbSolution> nextStates = thirtyNextStates.generateNextSolutions();
        Assert.assertEquals(30, nextStates.size(), 0.0001);
    }

    @Test
    public void generateFortyTwoNextStates() {
        List<IHillClimbSolution> nextStates = fortyTwoNextStates.generateNextSolutions();
        Assert.assertEquals(42, nextStates.size(), 0.0001);
    }

    @Test
    public void generateFiftySixNextStates() {
        List<IHillClimbSolution> nextStates = fiftySixNextStates.generateNextSolutions();
        Assert.assertEquals(56, nextStates.size(), 0.0001);
    }

    //////////////////////////////////////////
    // Ensure Correct Next States Generated //
    //////////////////////////////////////////

    @Test
    public void ensureCorrectTwelveNextStates() {
        List<IHillClimbSolution> nextStates = twelveNextStates.generateNextSolutions();
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
