import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HillClimbParamsTests {

    HillClimbParams setAscendWithDefaulGSParams;

    @Before
    public void setup() {
        setAscendWithDefaulGSParams = new HillClimbParams();
    }

    @Test
    public void setAscendWithDefaultGoalScore() {
        setAscendWithDefaulGSParams.setMinimization(true);
        Assert.assertTrue(setAscendWithDefaulGSParams.isMinimization());
        Assert.assertEquals(Integer.MIN_VALUE, setAscendWithDefaulGSParams.getGoalScore(), .000001);
    }

}
