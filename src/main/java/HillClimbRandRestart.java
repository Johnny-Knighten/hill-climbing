import interfaces.IHillClimbSolnGenerator;
import interfaces.IHillClimbSolution;
import nqueens.NQueensSolnGenerator;

import java.util.List;

/**
 * An implementation of the Hill Climbing With Random Restarts optimization algorithm. The optimal state is more likely
 * to be found if the algorithm is ran for many iterations. Also has the ability for early termination when a specific
 * value is found and can terminate when a set number of iterations are executed.
 */
public class HillClimbRandRestart extends HillClimbOptimization {

    /**
     * Used to generate random solutions when a plateau or valley is found.
     */
    private IHillClimbSolnGenerator generator;

    /**
     * Creates an instance of HillClimbRandRestart solving the supplied problems using the supplied parameters.
     *
     * @param initialSolution the starting solution of the problem
     * @param params parameters used by the optimizer
     * @param generator used to generate random solutions
     */
    public HillClimbRandRestart(IHillClimbSolution initialSolution, HillClimbParams params, IHillClimbSolnGenerator generator) {
        this.setInitialSolution(initialSolution);
        this.setParams(params);
        this.generator = generator;
    }

    /**
     * Starts the hill climbing process to find an optimal state.
     *
     * @return the most optimal state found
     */
    public IHillClimbSolution optimize() {
        // Make current The Initial State
        IHillClimbSolution current = this.getInitialSolution();
        IHillClimbSolution bestSoFar =  this.getInitialSolution(); // Keeps the best state found over all restarts
        current.setScore(current.scoreState());

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


            // Check If We Hit Valley/Peak or Plateau then Random Restart Otherwise Update Current And Continue
            if(bestNextSolution.isPeakOrPlateau(current)) {
                current = generator.randomSolution();
                if(current.getScore() > bestSoFar.getScore())
                    bestSoFar = current;
            } else {
                current = bestNextSolution;
                bestSoFar = current;
            }

            // Update Number Of Iterations
            iterations++;

        } while(!this.isGoalScore(current) && iterations < this.getParams().getMaxIterations());

        return bestSoFar;
    }

    public static void main(String args[]) {
        HillClimbParams params = new HillClimbParams();
        params.setMinimization(true);
        params.setGoalScore(0);
        params.setMaxIterations(1000);

        NQueensSolnGenerator generator = new NQueensSolnGenerator(8, 0);

        IHillClimbSolution initialState = generator.randomSolution();
        HillClimbRandRestart climber = new HillClimbRandRestart(initialState, params, generator);
        IHillClimbSolution optimal = climber.optimize();

        System.out.println(optimal);
        System.out.println("Score: " + optimal.getScore());
    }

}
