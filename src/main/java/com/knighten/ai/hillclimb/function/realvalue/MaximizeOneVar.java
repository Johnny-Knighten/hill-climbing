package com.knighten.ai.hillclimb.function.realvalue;

import com.knighten.ai.hillclimb.HillClimbParams;
import com.knighten.ai.hillclimb.HillClimbRandRestart;
import com.knighten.ai.hillclimb.interfaces.IHillClimbProblem;
import com.knighten.ai.hillclimb.interfaces.IHillClimbSolnGenerator;
import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 *  Represents the maximization of a one var real valued function. Used with the hill climbing framework.
 */
public class MaximizeOneVar implements IHillClimbProblem {

    /**
     * The initial guess at the solution to the minimization.
     */
    private IHillClimbSolution initialGuess;

    /**
     * The function being optimized.
     */
    private IOneVariableFunction function;

    /**
     * Creates a one var real valued function maximization problem.
     *
     * @param initialGuess the initial guess at the solution of the minimization
     * @param function the function being minimized
     */
    public MaximizeOneVar(IHillClimbSolution initialGuess, IOneVariableFunction function) {

        if(initialGuess == null)
            throw new IllegalArgumentException("Initial Guess Cannot Be Null");

        if(function == null)
            throw new IllegalArgumentException("Function Being Optimized Cannot Be Null");

        this.initialGuess = initialGuess;
        this.function = function;
    }

    /**
     * Gets the initial guess at solution of the problem.
     *
     * @return the initial solution the problem starts at
     */
    @Override
    public IHillClimbSolution getInitialGuess() {
        return this.initialGuess;
    }

    /**
     * Finds the solution with the highest score in the list.
     *
     * @param possibleSolns a list of possible solutions
     * @return the most optimal solution in the list
     */
    @Override
    public IHillClimbSolution getBestSolution(List<IHillClimbSolution> possibleSolns) {
        return possibleSolns.stream()
                .max(Comparator.comparing(IHillClimbSolution::getScore))
                .get();
    }

    /**
     * Checks if the algorithm has encountered a peak/valley or a plateau. A peak/valley or plateau is found when
     * the new solution is less than or equal to the current solution since we are performing maximization.
     *
     * @param currentSolution the current solution in the hill climb iteration
     * @param newSolution a solution generated from the current solution
     * @return true is a peak/valley or plateau is found, false otherwise
     */
    @Override
    public boolean isPeakOrPlateau(IHillClimbSolution currentSolution, IHillClimbSolution newSolution) {
        return newSolution.getScore() <= currentSolution.getScore();
    }

    /**
     * Determines if the current solution is better than the best. Since we are maximizing the current is better if it
     * has a higher score.
     *
     * @param current the current solution being operated on
     * @param best the best solution found so far
     * @return true if current has a lower score than best else false
     */
    @Override
    public boolean currentBetterThanBest(IHillClimbSolution current, IHillClimbSolution best) {
        return current.getScore() > best.getScore();
    }

    /**
     * Takes a solution's x value and plugs it into the function being maximized.
     *
     * @param solution the solution being scored.
     * @return the value of the function when the solution's x value is plugged in
     */
    @Override
    public double scoreSolution(IHillClimbSolution solution) {
        return this.function.getFuncValue(((OneVarSolution) solution).getXValue());
    }

    public static void main(String[] args) {
        HillClimbParams params = new HillClimbParams();
        params.setMaxIterations(1000000);

        IOneVariableFunction function = (x) -> -4*Math.pow(x, 6) + 5*Math.pow(x, 2) - x + 1;

        IHillClimbSolution initialState = new OneVarSolution(2, -2, 2, .0001);

        IHillClimbSolnGenerator generator = new OneVarSolnGenerator(-2, 2, .0001, new Random());
        IHillClimbProblem problem = new MaximizeOneVar(initialState, function);

        HillClimbRandRestart climber = new HillClimbRandRestart(problem, params, generator);
        IHillClimbSolution optimal = climber.optimize();

        System.out.println(optimal);
        System.out.println("Score: " + optimal.getScore());

    }

}
