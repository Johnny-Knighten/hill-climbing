package nqueens;

import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;
import com.knighten.ai.hillclimb.nqueens.NQueensSolnGenerator;
import com.knighten.ai.hillclimb.nqueens.NQueensSolution;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

public class NQueensSolutionGeneratorTests {

    private Random mockRandom;

    @Before
    public void setup() {
        mockRandom = Mockito.mock(Random.class);
    }

    ////////////////////////
    // Parameter Checking //
    ////////////////////////

    @Test(expected = IllegalArgumentException.class)
    public void constructorRandomIsNull() {
        new NQueensSolnGenerator(12, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorNIsLessThan4() {
        new NQueensSolnGenerator(3, mockRandom);
    }

    ////////////////////
    // Method Testing //
    ////////////////////

    @Test
    public void randomSolutionVerifySize() {
        NQueensSolnGenerator testObjectSize4 = new NQueensSolnGenerator(4, mockRandom);
        NQueensSolution resultSize4 = (NQueensSolution) testObjectSize4.randomSolution();

        NQueensSolnGenerator testObjectSize8 = new NQueensSolnGenerator(8, mockRandom);
        NQueensSolution resultSize8 = (NQueensSolution) testObjectSize8.randomSolution();

        NQueensSolnGenerator testObjectSize12 = new NQueensSolnGenerator(12, mockRandom);
        NQueensSolution resultSize12 = (NQueensSolution) testObjectSize12.randomSolution();

        NQueensSolnGenerator testObjectSize24 = new NQueensSolnGenerator(24, mockRandom);
        NQueensSolution resultSize24 = (NQueensSolution) testObjectSize24.randomSolution();

        NQueensSolnGenerator testObjectSize256 = new NQueensSolnGenerator(256, mockRandom);
        NQueensSolution resultSize256 = (NQueensSolution) testObjectSize256.randomSolution();
        
        Assert.assertEquals(4, resultSize4.getBoard().length);
        Assert.assertEquals(8, resultSize8.getBoard().length);
        Assert.assertEquals(12, resultSize12.getBoard().length);
        Assert.assertEquals(24, resultSize24.getBoard().length);
        Assert.assertEquals(256, resultSize256.getBoard().length);
    }

}
