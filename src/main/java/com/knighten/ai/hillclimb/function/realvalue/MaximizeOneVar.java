package com.knighten.ai.hillclimb.function.realvalue;

import com.knighten.ai.hillclimb.HillClimbParams;
import com.knighten.ai.hillclimb.HillClimbRandRestart;
import com.knighten.ai.hillclimb.interfaces.IHillClimbProblem;
import com.knighten.ai.hillclimb.interfaces.IHillClimbSolnGenerator;
import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Represents the maximization of a one var real valued function. Used with the hill climbing framework.
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
     * The smallest value in the search range.
     */
    private double minDomain;

    /**
     * The largest value in the search range.
     */
    private double maxDomain;

    /**
     * The value used to generate the next possible solutions. This is added/subtracted from xValue.
     */
    private double stepSize;

    /**
     * Creates a one var real valued function maximization problem.
     *
     * @param initialGuess the initial guess at the solution of the minimization
     * @param function     the function being minimized
     * @param minDomain    the smallest number in the search range
     * @param maxDomain    the largest number in the search range
     * @param stepSize     the unit used to generate next solutions
     */
    public MaximizeOneVar(IHillClimbSolution initialGuess, IOneVariableFunction function, double minDomain,
                          double maxDomain, double stepSize) {

        if (initialGuess == null)
            throw new IllegalArgumentException("Initial Guess Cannot Be Null");

        if (function == null)
            throw new IllegalArgumentException("Function Being Optimized Cannot Be Null");

        if (!Double.isFinite(stepSize))
            throw new IllegalArgumentException("Step Size Cannot Be Infinity Or NaN");

        if (stepSize == 0)
            throw new IllegalArgumentException("Step Size Cannot Be 0");

        if (stepSize == Double.MIN_VALUE)
            throw new IllegalArgumentException("Step Size Cannot Double.MIN_VALUE");

        if (stepSize == Double.MAX_VALUE)
            throw new IllegalArgumentException("Step Size Cannot Double.MAX_VALUE");

        if (!Double.isFinite(minDomain))
            throw new IllegalArgumentException("minDomain Cannot Be NaN or Infinite: " + minDomain + " was found");

        if (minDomain == Double.MIN_VALUE)
            throw new IllegalArgumentException("minDomain Cannot Double.MIN_VALUE");

        if (minDomain == Double.MAX_VALUE)
            throw new IllegalArgumentException("minDomain Cannot Double.MAX_VALUE");

        if (!Double.isFinite(maxDomain))
            throw new IllegalArgumentException("maxDomain Cannot Be NaN or Infinite: " + maxDomain + " was found");

        if (maxDomain == Double.MIN_VALUE)
            throw new IllegalArgumentException("maxDomain Cannot Double.MIN_VALUE");

        if (maxDomain == Double.MAX_VALUE)
            throw new IllegalArgumentException("maxDomain Cannot Double.MAX_VALUE");

        if (minDomain >= maxDomain)
            throw new IllegalArgumentException("Min Domain Must Be less That Max Domain");

        this.initialGuess = initialGuess;
        this.function = function;
        this.minDomain = minDomain;
        this.maxDomain = maxDomain;
        this.stepSize = stepSize;
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
     * @param newSolution     a solution generated from the current solution
     * @return true is a peak/valley or plateau is found, false otherwise
     */
    @Override
    public boolean atPeakOrPlateau(IHillClimbSolution currentSolution, IHillClimbSolution newSolution) {
        return newSolution.getScore() <= currentSolution.getScore();
    }

    /**
     * Determines if solution1 is better than solution2. Since we are maximizing the current is better if it has a
     * higher score.
     *
     * @param solution1 determining if this solution is better compared to other
     * @param solution2 solution to compare solution1 with
     * @return true if current is solution1 is better than solution2 else false
     */
    @Override
    public boolean firstSolutionBetterThanOther(IHillClimbSolution solution1, IHillClimbSolution solution2) {
        return solution1.getScore() > solution2.getScore();
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

    /**
     * Generates the next set of solutions. This is the solutions's x value plus the set value and minus the x value.
     *
     * @param solution solution used to generate next solutions
     * @return the list of next solutions
     */
    @Override
    public List<IHillClimbSolution> generateNextSolutions(IHillClimbSolution solution) {
        OneVarSolution solutionAsOneVar = (OneVarSolution) solution;
        List<IHillClimbSolution> list = new ArrayList<>();

        double largerValue = solutionAsOneVar.getXValue() + this.stepSize;
        if (largerValue <= this.maxDomain)
            list.add(new OneVarSolution(largerValue));

        double smallerValue = solutionAsOneVar.getXValue() - this.stepSize;
        if (smallerValue >= this.minDomain)
            list.add(new OneVarSolution(smallerValue));

        return list;
    }

    public static void main(String[] args) {
        HillClimbParams params = new HillClimbParams();
        params.setMaxIterations(1000000);

        IOneVariableFunction function = (x) -> -4 * Math.pow(x, 6) + 5 * Math.pow(x, 2) - x + 1;

        IHillClimbSolution initialState = new OneVarSolution(2);

        IHillClimbSolnGenerator generator = new OneVarSolnGenerator(-2, 2, .0001, new Random());
        IHillClimbProblem problem = new MaximizeOneVar(initialState, function, -2, 2, .0001);

        HillClimbRandRestart climber = new HillClimbRandRestart(problem, params, generator);
        IHillClimbSolution optimal = climber.optimize();

        System.out.println(optimal);
        System.out.println("Score: " + optimal.getScore());

    }

}
