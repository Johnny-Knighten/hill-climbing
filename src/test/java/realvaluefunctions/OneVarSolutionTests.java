package realvaluefunctions;

import com.knighten.ai.hillclimb.function.realvalue.OneVarSolution;
import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;
import com.knighten.ai.hillclimb.nqueens.NQueensSolution;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;

public class OneVarSolutionTests {

    ////////////////////////
    // Parameter Checking //
    ////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void constructorXValueIsNaN() {
        new OneVarSolution(Double.NaN, 1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorXValueInfinity() {
        new OneVarSolution(Double.POSITIVE_INFINITY, 1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorStepSizeIsZero() {
        new OneVarSolution(0.0, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setScoreToInfinity() {
        OneVarSolution testObject = new OneVarSolution(0.0, 1.0);
        testObject.setScore(Double.POSITIVE_INFINITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setScoreToNaN() {
        OneVarSolution testObject = new OneVarSolution(0.0, 1.0);
        testObject.setScore(Double.NaN);
    }


    ////////////////////
    // Method Testing //
    ////////////////////

    @Test
    public void generateNextSolutionsCorrectSolutionsCreated() {
        OneVarSolution testObject = new OneVarSolution(0.0, 1.0);
        List<IHillClimbSolution> results = testObject.generateNextSolutions();

        Assert.assertEquals(2, results.size());
        Assert.assertTrue(IntStream.range(0, 2).anyMatch((i) ->((OneVarSolution) results.get(i)).getXValue() == -1.0));
        Assert.assertTrue(IntStream.range(0, 2).anyMatch((i) ->((OneVarSolution) results.get(i)).getXValue() == 1.0));
    }

}
