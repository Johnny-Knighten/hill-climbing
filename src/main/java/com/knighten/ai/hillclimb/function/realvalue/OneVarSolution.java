package com.knighten.ai.hillclimb.function.realvalue;

import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;


/**
 * Represents a solution of minimizing a one var real valued function.
 */
public class OneVarSolution implements IHillClimbSolution {

    /**
     * The value of the function being optimized when the solution's x value is plugged in.
     */
    private double score;

    /**
     * The solution's x value, the value used to score the solution.
     */
    private double xValue;

    /**
     * Creates a possible solution to the minimization of a one var real valued function.
     *
     * @param xValue the x value of the solution
     */
    public OneVarSolution(double xValue) {

        if (!Double.isFinite(xValue))
            throw new IllegalArgumentException("X Value Cannot Be Infinity Or NaN");

        this.xValue = xValue;
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

        if (!Double.isFinite(score))
            throw new IllegalArgumentException("A OneVarSolution's Score Cannot Be Infinity Or NaN");

        this.score = score;
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
