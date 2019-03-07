package com.knighten.ai.hillclimb;

import com.knighten.ai.hillclimb.interfaces.IHillClimbProblem;
import com.knighten.ai.hillclimb.interfaces.IHillClimbSolnGenerator;
import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;
import com.knighten.ai.hillclimb.nqueens.NQueensProblem;
import com.knighten.ai.hillclimb.nqueens.NQueensSolnGenerator;

import java.util.List;
import java.util.Random;

/**
 * An implementation of the Hill Climbing With Random Restarts optimization algorithm. The optimal state is more likely
 * to be found if the algorithm is ran for many iterations. Also has the ability for early termination when a specific
 * value is found and can terminate when a set number of iterations are executed.
 */
public class HillClimbRandRestart extends AbstractHillClimbOptimizer {

    /**
     * Used to generate random solutions when a plateau or valley is found.
     */
    private IHillClimbSolnGenerator generator;

    /**
     * Creates an instance of HillClimbRandRestart solving the supplied problem using the supplied parameters. A
     * IHillClimbSolnGenerator is provided to generate random solutions when a restart occurs.
     *
     * @param problem   the problem being optimized
     * @param params    parameters used by the optimizer
     * @param generator used to generate random solutions
     */
    public HillClimbRandRestart(IHillClimbProblem problem, HillClimbParams params, IHillClimbSolnGenerator generator) {

        if (problem == null)
            throw new IllegalArgumentException("Problem Object Cannot Be Null");

        if (params == null)
            throw new IllegalArgumentException("Optimizer Parameters Object Cannot Be Null");

        if (generator == null)
            throw new IllegalArgumentException(" Solution Generator Object Cannot Be Null");

        this.setProblem(problem);
        this.setParams(params);
        this.generator = generator;
    }

    /**
     * Starts the hill climbing process to find an optimal solution.
     *
     * @return the most optimal solution found
     */
    public IHillClimbSolution optimize() {
        // Make current The Initial State
        IHillClimbSolution current = this.getProblem().getInitialGuess();
        IHillClimbSolution bestSoFar = current; // Keeps the best state found over all restarts
        current.setScore(this.getProblem().scoreSolution(current));

        // Keep Track of The Number Of Iterations That Have Occurred
        int iterations = 0;

        do {

            // Update BestSoFar If Current Is Better
            if (this.getProblem().firstSolutionBetterThanOther(current, bestSoFar))
                bestSoFar = current;

            // Generate Next Solutions
            List<IHillClimbSolution> nextSolutions = this.getProblem().generateNextSolutions(current);

            // Score Each Next Solution
            for (IHillClimbSolution solution : nextSolutions)
                solution.setScore(this.getProblem().scoreSolution(solution));

            // Get The Best Next Solution
            IHillClimbSolution bestNextSolution = this.getProblem().getBestSolution(nextSolutions);

            // Check If We Hit Valley/Peak or Plateau If So Perform Random Restart Else Continue The Search
            if (this.getProblem().atPeakOrPlateau(current, bestNextSolution)) {
                current = this.generator.randomSolution();
                current.setScore(this.getProblem().scoreSolution(current));

            } else {
                current = bestNextSolution;
            }

            // Update Number Of Iterations
            iterations++;

        } while (bestSoFar.getScore() != this.getParams().getGoalScore() && iterations < this.getParams().getMaxIterations());

        return bestSoFar;
    }

    public static void main(String args[]) {
        HillClimbParams params = new HillClimbParams();
        params.setGoalScore(0);
        params.setMaxIterations(100000);

        Random random = new Random(0);

        NQueensSolnGenerator generator = new NQueensSolnGenerator(24, random);

        IHillClimbSolution initialState = generator.randomSolution();

        NQueensProblem problem = new NQueensProblem(initialState);

        HillClimbRandRestart climber = new HillClimbRandRestart(problem, params, generator);
        IHillClimbSolution optimal = climber.optimize();

        System.out.println(optimal);
        System.out.println("Score: " + optimal.getScore());
    }

}
