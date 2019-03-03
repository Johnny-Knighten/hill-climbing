package com.knighten.ai.hillclimb.function.realvalue;

import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a solution of minimizing a one var real valued function.
 */
public class OneVarSolution implements IHillClimbSolution {

    /**
     * The smallest value in the search range.
     */
    private double minDomain;

    /**
     * The largest value in the search range.
     */
    private double maxDomain;

    /**
     * The value of the function being optimized when the solution's x value is plugged in.
     */
    private double score;

    /**
     * The solution's x value, the value used to score the solution.
     */
    private double xValue;

    /**
     * The value used to generate the next possible solutions. This is added/subtracted from xValue.
     */
    private double stepSize;

    /**
     * Creates a possible solution to the minimization of a one var real valued function.
     *
     * @param xValue the x value of the solution
     * @param minDomain the smallest number in the search range
     * @param maxDomain the largest number in the search range
     * @param stepSize the unit used to generate next solutions
     */
    public OneVarSolution(double xValue, double minDomain, double maxDomain, double stepSize) {

        if(!Double.isFinite(xValue))
            throw new IllegalArgumentException("X Value Cannot Be Infinity Or NaN");

        if(!Double.isFinite(stepSize))
            throw new IllegalArgumentException("Step Size Cannot Be Infinity Or NaN");

        if(stepSize == 0)
            throw new IllegalArgumentException("Step Size Cannot Be 0");

        if(!Double.isFinite(minDomain))
            throw new IllegalArgumentException("minDomain Cannot Be NaN or Infinite: " + minDomain + " was found");

        if(minDomain == Double.MIN_VALUE)
            throw new IllegalArgumentException("minDomain Cannot Double.MIN_VALUE");

        if(minDomain == Double.MAX_VALUE)
            throw new IllegalArgumentException("minDomain Cannot Double.MAX_VALUE");

        if(!Double.isFinite(maxDomain))
            throw new IllegalArgumentException("maxDomain Cannot Be NaN or Infinite: " + maxDomain + " was found");

        if(maxDomain == Double.MIN_VALUE)
            throw new IllegalArgumentException("maxDomain Cannot Double.MIN_VALUE");

        if(maxDomain == Double.MAX_VALUE)
            throw new IllegalArgumentException("maxDomain Cannot Double.MAX_VALUE");

        if(minDomain >= maxDomain)
            throw new IllegalArgumentException("Min Domain Must Be less That Max Domain");

        this.xValue = xValue;
        this.stepSize = stepSize;
        this.minDomain = minDomain;
        this.maxDomain = maxDomain;
    }

    /**
     * Gets the solution's x value.
     *
     * @return the solution's x value
     */
    public double getXValue() {
        return xValue;
    }

    /**
     * Gets the solution's value when its x value is plugged into the function being minimized.
     *
     * @return the value of f(x)
     */
    @Override
    public double getScore() {
        return this.score;
    }

    /**
     * Sets the solution's score, its value when its x value is plugged into the function being minimized.
     *
     * @param score the solutions value of f(x)
     */
    @Override
    public void setScore(double score) {

        if(!Double.isFinite(score))
            throw new IllegalArgumentException("A OneVarSolution's Score Cannot Be Infinity Or NaN");

        this.score = score;
    }

    /**
     * Generates the next set of solutions. This is the solutions's x value plus the set value and minus the x value.
     *
     * @return the list of next solutions
     */
    @Override
    public List<IHillClimbSolution> generateNextSolutions() {
        List<IHillClimbSolution> list = new ArrayList<>();

        double largerValue = this.xValue + this.stepSize;
        if(largerValue <= this.maxDomain)
            list.add(new OneVarSolution(largerValue, this.minDomain, this.maxDomain, this.stepSize));

        double smallerValue = this.xValue - this.stepSize;
        if(smallerValue >= this.minDomain)
            list.add(new OneVarSolution(smallerValue, this.minDomain, this.maxDomain, this.stepSize));

        return list;
    }

    /**
     * Creates a string containing the solution's x value and f(x) value.
     *
     * @return string representation of the solution.
     */
    @Override
    public String toString() {
        return "X Value - " + this.getXValue() + "\nf(x) Value - " + this.getScore();
    }

}
