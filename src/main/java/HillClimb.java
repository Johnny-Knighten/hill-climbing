import interfaces.IHillClimbProblem;
import interfaces.IHillClimbSolution;
import nqueens.NQueensProblem;
import nqueens.NQueensSoln;

import java.util.List;

/**
 * An implementation of the Hill Climbing optimization algorithm. This is the standard implementation with no
 * restarts, so it is unlikely the optimal state will be found. It has the ability for early termination when a specific
 * value is found and can terminate when a set number of iterations are executed.
 */
public class HillClimb extends HillClimbOptimization {

    /**
     * Creates an instance of HillClimb solving the supplied problem using the supplied parameters.
     *
     * @param problem the problem being solved by the optimizer
     * @param params parameters used by the optimizer
     */
    public HillClimb(IHillClimbProblem problem, HillClimbParams params) {

        if(problem == null)
            throw new IllegalArgumentException("Problem Object Cannot Be Null");

        if(params == null)
            throw new IllegalArgumentException("Optimizer Parameters Object Cannot Be Null");

        this.setProblem(problem);
        this.setParams(params);
    }

    /**
     * Starts the hill climbing process to find an optimal solution.
     *
     * @return the most optimal solution found
     */
    public IHillClimbSolution optimize() {

        // Make current The Initial Solution
        IHillClimbSolution current = this.getProblem().getInitialGuess();
        current.setScore(this.getProblem().scoreSolution(current));

        // Used To Mark a Valley or Peak
        boolean isPeakOrPlateau = false;

        // Keep Track of The Number Of Iterations That Have Occurred
        int iterations = 0;

        do {
            // Generate Next Solutions
            List<IHillClimbSolution> nextSolutions = current.generateNextSolutions();

            // Score Each Next Solution
            for(IHillClimbSolution solution: nextSolutions)
                solution.setScore(this.getProblem().scoreSolution(solution));

            // Get The Best Next Solution
            IHillClimbSolution bestNextSolution = this.getProblem().getBestSolution(nextSolutions);

            // Check If We Hit Valley/Peak Or Plateau Otherwise Update Current And Continue
             if(this.getProblem().isPeakOrPlateau(current, bestNextSolution))
                 isPeakOrPlateau = true;
             else
                 current = bestNextSolution;

             // Update Number Of Iterations
             iterations++;

        } while(!this.isGoalScore(current) && !isPeakOrPlateau && iterations < this.getParams().getMaxIterations());

        return current;
    }

    public static void main(String[] args) {
        HillClimbParams params = new HillClimbParams();
        params.setGoalScore(0);
        params.setMaxIterations(100);

        NQueensSoln initialState = new NQueensSoln(new int[]{0,1,2,3,4,5,6,7});

        NQueensProblem problem = new NQueensProblem(initialState);

        HillClimb climber = new HillClimb(problem, params);
        IHillClimbSolution optimal = climber.optimize();

        System.out.println(optimal);
        System.out.println("Score: " + optimal.getScore());
    }

}
