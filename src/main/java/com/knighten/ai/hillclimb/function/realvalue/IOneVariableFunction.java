package com.knighten.ai.hillclimb.function.realvalue;

/**
 * A functional interface used to represent a real valued function with one variable.
 *
 * Example Lambdas:
 * (x) -> Math.pow(x, 2) - f(x)=x^2
 * (x) -> Math.log(x) - f(x)=log(x)
 * (x) -> Math.log(1/x) - f(x)=log(1/x)
 * (x) -> Math.exp(x) - f(x)=e^x
 */
public interface IOneVariableFunction {

    /**
     * Find the output of the function using the supplied x value.
     *
     * @param xValue the value of x
     * @return the about of the function using the supplied x
     */
    double getFuncValue(double xValue);
}
