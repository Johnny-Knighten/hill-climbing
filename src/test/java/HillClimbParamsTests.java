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
        setAscendWithDefaulGSParams.setDescend(true);
        Assert.assertTrue(setAscendWithDefaulGSParams.isDescend());
        Assert.assertEquals(Integer.MIN_VALUE, setAscendWithDefaulGSParams.getGoalScore(), .000001);
    }
}
