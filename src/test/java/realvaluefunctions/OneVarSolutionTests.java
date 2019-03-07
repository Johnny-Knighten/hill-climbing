package realvaluefunctions;

import com.knighten.ai.hillclimb.function.realvalue.OneVarSolution;
import org.junit.Test;

public class OneVarSolutionTests {

    ////////////////////////
    // Parameter Checking //
    ////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void constructorXValueIsNaN() {
        new OneVarSolution(Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorXValueInfinity() {
        new OneVarSolution(Double.POSITIVE_INFINITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setScoreToNaN() {
        OneVarSolution testObject = new OneVarSolution(0.0);
        testObject.setScore(Double.NaN);
    }

}
