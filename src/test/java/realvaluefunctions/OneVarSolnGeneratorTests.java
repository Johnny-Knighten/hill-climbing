package realvaluefunctions;

import com.knighten.ai.hillclimb.function.realvalue.OneVarSolnGenerator;
import com.knighten.ai.hillclimb.function.realvalue.OneVarSolution;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.mockito.ArgumentMatchers.anyInt;

public class OneVarSolnGeneratorTests {

    private Random mockRandom;

    @Before
    public void setup() {
        mockRandom = Mockito.mock(Random.class);
        Mockito.when(mockRandom.nextInt(anyInt())).thenReturn(10);
    }

    ////////////////////////
    // Parameter Checking //
    ////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void constructorRandomIsNull() {
        new OneVarSolnGenerator(-1.0, 1.0, 0.01, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorStepSizeIs0() {
        new OneVarSolnGenerator(-1.0, 1.0, 0.00, mockRandom);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorStepSizeIsNaN() {
        new OneVarSolnGenerator(-1.0, 1.0, Double.NaN, mockRandom);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorStepSizeIsInfinity() {
        new OneVarSolnGenerator(-1.0, 1.0, Double.POSITIVE_INFINITY, mockRandom);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMinDomainIsNaN() {
        new OneVarSolnGenerator(Double.NaN, 1.0, 0.1, mockRandom);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMinDomainIsInfinity() {
        new OneVarSolnGenerator(Double.POSITIVE_INFINITY, 1.0, 0.1, mockRandom);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMinDomainIsDoubleMax() {
        new OneVarSolnGenerator(Double.MAX_VALUE, 1.0, 0.1, mockRandom);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMinDomainIsDoubleMin() {
        new OneVarSolnGenerator(Double.MIN_VALUE, 1.0, 0.1, mockRandom);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMaxDomainIsNaN() {
        new OneVarSolnGenerator(-1.0, Double.NaN, 0.1, mockRandom);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMaxDomainIsInfinity() {
        new OneVarSolnGenerator(-1.0, Double.POSITIVE_INFINITY, 0.1, mockRandom);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMaxDomainIsDoubleMax() {
        new OneVarSolnGenerator(1.0, Double.MAX_VALUE, 0.1, mockRandom);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMaxDomainIsDoubleMin() {
        new OneVarSolnGenerator(-1.0, Double.MIN_VALUE, 0.1, mockRandom);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMinDomainLargerThanMaxDomain() {
        new OneVarSolnGenerator(-1.0, -2.0, 0.1, mockRandom);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMinDomainEqualToMaxDomain() {
        new OneVarSolnGenerator(-1.0, -1.0, 0.1, mockRandom);
    }

    ////////////////////
    // Method Testing //
    ////////////////////

    @Test
    public void randomSolutionGenerateSolutionCorrectValue() {
        OneVarSolnGenerator testObject =  new OneVarSolnGenerator(-1.0, 1.0, 0.1, mockRandom);
        Assert.assertEquals(0.0, ((OneVarSolution) testObject.randomSolution()).getXValue(), .00001);
    }

    @Test
    public void randomSolution100ValuesInRange() {
        OneVarSolnGenerator testObject =  new OneVarSolnGenerator(-1.0, 1.0, 0.1, new Random());

        for(int i=0; i<100; i++) {
            OneVarSolution randomSolution = (OneVarSolution) testObject.randomSolution();
            Assert.assertTrue(randomSolution.getXValue() <= 1.0 && randomSolution.getXValue() >= -1);
        }
    }

}
