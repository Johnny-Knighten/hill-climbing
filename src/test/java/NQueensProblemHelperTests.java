import interfaces.IHillClimbProblem;
import nqueens.NQueensProblemHelper;
import org.junit.Assert;
import org.junit.Test;

public class NQueensProblemHelperTests {

    @Test
    public void testSeedingGeneratesConsistentResults() {
        NQueensProblemHelper probHelper1 = new NQueensProblemHelper(8, 0);
        IHillClimbProblem randomState1 = probHelper1.randomState();

        NQueensProblemHelper probHelper2 = new NQueensProblemHelper(8, 0);
        IHillClimbProblem randomState2 = probHelper2.randomState();

        Assert.assertEquals(randomState1, randomState2);
    }

}
