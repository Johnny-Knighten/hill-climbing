package baseclasses;

import com.knighten.ai.hillclimb.HillClimbParams;
import org.junit.Test;

public class HillClimbParamsTests {

    ////////////////////////
    // Parameter Checking //
    ////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void setGoalScoreGoalIsNaN() {
        HillClimbParams testObject = new HillClimbParams();
        testObject.setGoalScore(Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMaxIterationsMaxIterationsLessThan1() {
        HillClimbParams testObject = new HillClimbParams();
        testObject.setMaxIterations(-1);
    }

}
