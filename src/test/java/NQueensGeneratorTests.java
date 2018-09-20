import interfaces.IHillClimbProblem;
import nqueens.NQueensGenerator;
import org.junit.Assert;
import org.junit.Test;

public class NQueensGeneratorTests {

    @Test
    public void testSeedingGeneratesConsistentResults() {
        NQueensGenerator probHelper1 = new NQueensGenerator(8, 0);
        IHillClimbProblem randomState1 = probHelper1.randomState();

        NQueensGenerator probHelper2 = new NQueensGenerator(8, 0);
        IHillClimbProblem randomState2 = probHelper2.randomState();

        Assert.assertEquals(randomState1, randomState2);
    }

}
