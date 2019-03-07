package nqueens;

import com.knighten.ai.hillclimb.nqueens.NQueensSolution;
import org.junit.Assert;
import org.junit.Test;

public class NQueensSolutionTests {

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

}
