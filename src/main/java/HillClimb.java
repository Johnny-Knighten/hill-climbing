import interfaces.IHillClimbSolution;
import nqueens.NQueensSoln;

import java.util.List;

/**
 * An implementation of the Hill Climbing optimization algorithm. This is the standard implementation with no
 * restarts, so it is unlikely the optimal state will be found. It has the ability for early termination when a specific
 * value is found and can terminate when a set number of iterations are executed.
 */
public class HillClimb extends HillClimbOptimization {

    /**
     * Creates an instance of HillClimb solving the supplied problems using the supplied parameters.
     *
     * @param initialSolution the starting solution of the problem
     * @param params parameters used by the optimizer
     */
    public HillClimb(IHillClimbSolution initialSolution, HillClimbParams params) {
        this.setInitialSolution(initialSolution);
        this.setParams(params);
    }

    /**
     * Starts the hill climbing process to find an optimal state.
     *
     * @return the most optimal state found
     */
    public IHillClimbSolution optimize() {

        // Make current The Initial Solution
        IHillClimbSolution current = this.getInitialSolution();
        current.setScore(current.scoreState());

        // Used To Mark a Valley or Peak
        boolean isValleyOrPeak = false;

        // Keep Track of The Number Of Iterations That Have Occurred
        int iterations = 0;

        do {
            // Generate Next Solutions
            List<IHillClimbSolution> nextSolns = current.generateNextSolns();

            // Score Each Next Solution
            for(IHillClimbSolution state: nextSolns)
                state.setScore(state.scoreState());

            // Get The Best Next Solution
            IHillClimbSolution bestNextSolution = current.getBestSolution(nextSolns);

            // Check If We Hit Valley/Peak Or Plateau Otherwise Update Current And Continue
             if(bestNextSolution.isPeakOrPlateau(current))
                 isValleyOrPeak = true;
             else
                 current = bestNextSolution;

             // Update Number Of Iterations
             iterations++;

        } while(!this.isGoalScore(current) && !isValleyOrPeak && iterations < this.getParams().getMaxIterations());

        return current;
    }

    public static void main(String[] args) {
        HillClimbParams params = new HillClimbParams();
        params.setMinimization(true);
        params.setGoalScore(0);
        params.setMaxIterations(100);

        NQueensSoln initialState = new NQueensSoln(new int[]{0,1,2,3,4,5,6,7});
        HillClimb climber = new HillClimb(initialState, params);
        IHillClimbSolution optimal = climber.optimize();

        System.out.println(optimal);
        System.out.println("Score: " + optimal.getScore());
    }

}
