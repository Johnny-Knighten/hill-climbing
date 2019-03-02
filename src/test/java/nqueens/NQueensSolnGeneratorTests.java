package nqueens;

import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;
import com.knighten.ai.hillclimb.nqueens.NQueensSolnGenerator;
import org.junit.Assert;
import org.junit.Test;

public class NQueensSolnGeneratorTests {

    @Test
    public void testSeedingGeneratesConsistentResults() {
        NQueensSolnGenerator probHelper1 = new NQueensSolnGenerator(8, 0);
        IHillClimbSolution randomState1 = probHelper1.randomSolution();

        NQueensSolnGenerator probHelper2 = new NQueensSolnGenerator(8, 0);
        IHillClimbSolution randomState2 = probHelper2.randomSolution();

        Assert.assertEquals(randomState1, randomState2);
    }

}
