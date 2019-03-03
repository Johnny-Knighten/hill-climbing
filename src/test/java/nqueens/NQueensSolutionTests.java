package nqueens;

import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;
import com.knighten.ai.hillclimb.nqueens.NQueensSolution;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class NQueensSolutionTests {

    private NQueensSolution twelveNextStates;
    private NQueensSolution twentyNextStates;
    private NQueensSolution thirtyNextStates;
    private NQueensSolution fortyTwoNextStates;
    private NQueensSolution fiftySixNextStates;

    @Before
    public void setup() {
        twelveNextStates = new NQueensSolution(new int[]{0,1,2,3});
        twentyNextStates = new NQueensSolution(new int[]{0,1,2,3,4});
        thirtyNextStates = new NQueensSolution(new int[]{0,1,2,3,4,5});
        fortyTwoNextStates = new NQueensSolution(new int[]{0,1,2,3,4,5,6});
        fiftySixNextStates = new NQueensSolution(new int[]{0,1,2,3,4,5,6,7});
    }

    ////////////////////////
    // Parameter Checking //
    ////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void constructorBoardIsNull() {
        new NQueensSolution(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorBoardIsInvalidLength() {
        new NQueensSolution(new int[]{0, 1});
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorBoardIsInvalidArrayValues() {
        new NQueensSolution(new int[]{9, 9, 9, 9, 9, 9});
    }

    @Test(expected = IllegalArgumentException.class)
    public void setScoreToInfinity() {
        NQueensSolution testObject = new NQueensSolution(new int[]{0, 1, 2, 3});
        testObject.setScore(Double.POSITIVE_INFINITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setScoreToNaN() {
        NQueensSolution testObject = new NQueensSolution(new int[]{0, 1, 2, 3});
        testObject.setScore(Double.NaN);
    }
    
    ////////////////////
    // Method Testing //
    ////////////////////

    @Test
    public void toStringCorrectStringGenerated() {
        NQueensSolution testObject = new NQueensSolution(new int[]{0, 1, 2, 3});
        Assert.assertEquals("Q * * * \n* Q * * \n* * Q * \n* * * Q ", testObject.toString());
    }

    @Test
    public void equalsWithEqualNQueens() {
        NQueensSolution testObject = new NQueensSolution(new int[]{0, 1, 2, 3});
        Assert.assertTrue(testObject.equals(new NQueensSolution(new int[]{0, 1, 2, 3})));
    }

    @Test
    public void equalsWitUnequalNQueens() {
        NQueensSolution testObject = new NQueensSolution(new int[]{0, 1, 2, 3});
        Assert.assertTrue(!testObject.equals(new NQueensSolution(new int[]{3, 2, 1, 0})));
    }

    @Test
    public void hashCodeCorrespondsWithEqualsWith() {
        NQueensSolution testObject1 = new NQueensSolution(new int[]{0, 1, 2, 3});
        NQueensSolution testObject2 = new NQueensSolution(new int[]{0, 1, 2, 3});
        NQueensSolution testObject3 = new NQueensSolution(new int[]{3, 2, 1, 0});

        Assert.assertTrue((testObject1.hashCode() == testObject2.hashCode()) == (testObject1.equals(testObject2)));
        Assert.assertTrue((testObject1.hashCode() != testObject3.hashCode()) == (!testObject1.equals(testObject3)));
    }

    @Test
    public void generateNextSolutionsTwelveNextStates() {
        List<IHillClimbSolution> nextStates = twelveNextStates.generateNextSolutions();
        Assert.assertEquals(12, nextStates.size(), 0.0001);
    }

    @Test
    public void generateNextSolutionsTwentyNextStates() {
        List<IHillClimbSolution> nextStates = twentyNextStates.generateNextSolutions();
        Assert.assertEquals(20, nextStates.size(), 0.0001);
    }

    @Test
    public void generateNextSolutionsNextStates() {
        List<IHillClimbSolution> nextStates = thirtyNextStates.generateNextSolutions();
        Assert.assertEquals(30, nextStates.size(), 0.0001);
    }

    @Test
    public void generateNextSolutionsFortyTwoNextStates() {
        List<IHillClimbSolution> nextStates = fortyTwoNextStates.generateNextSolutions();
        Assert.assertEquals(42, nextStates.size(), 0.0001);
    }

    @Test
    public void generateNextSolutionsFiftySixNextStates() {
        List<IHillClimbSolution> nextStates = fiftySixNextStates.generateNextSolutions();
        Assert.assertEquals(56, nextStates.size(), 0.0001);
    }

    @Test
    public void generateNextSolutionsEnsureCorrectTwelveNextStates() {
        List<IHillClimbSolution> nextStates = twelveNextStates.generateNextSolutions();
        Assert.assertTrue(nextStates.contains(new NQueensSolution(new int[]{1,1,2,3})));
        Assert.assertTrue(nextStates.contains(new NQueensSolution(new int[]{2,1,2,3})));
        Assert.assertTrue(nextStates.contains(new NQueensSolution(new int[]{3,1,2,3})));
        Assert.assertTrue(nextStates.contains(new NQueensSolution(new int[]{0,0,2,3})));
        Assert.assertTrue(nextStates.contains(new NQueensSolution(new int[]{0,2,2,3})));
        Assert.assertTrue(nextStates.contains(new NQueensSolution(new int[]{0,3,2,3})));
        Assert.assertTrue(nextStates.contains(new NQueensSolution(new int[]{0,1,0,3})));
        Assert.assertTrue(nextStates.contains(new NQueensSolution(new int[]{0,1,1,3})));
        Assert.assertTrue(nextStates.contains(new NQueensSolution(new int[]{0,1,3,3})));
        Assert.assertTrue(nextStates.contains(new NQueensSolution(new int[]{0,1,2,0})));
        Assert.assertTrue(nextStates.contains(new NQueensSolution(new int[]{0,1,2,1})));
        Assert.assertTrue(nextStates.contains(new NQueensSolution(new int[]{0,1,2,2})));
    }

}
