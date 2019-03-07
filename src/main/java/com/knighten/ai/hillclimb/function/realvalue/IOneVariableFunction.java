package com.knighten.ai.hillclimb.function.realvalue;

/**
 * A functional interface used to represent a real valued function with one variable.
 * <p>
 * Example Lambdas:
 * 1. (x) arrow Math.pow(x, 2) - f(x)=x^2
 * 2. (x) arrow Math.log(x) - f(x)=log(x)
 * 3. (x) arrow Math.log(1/x) - f(x)=log(1/x)
 * 4. (x) arrow Math.exp(x) - f(x)=e^x
 */
@FunctionalInterface
public interface IOneVariableFunction {

    /**
     * Find the output of the function using the supplied x value.
     *
     * @param xValue the value of x
     * @return the about of the function using the supplied x
     */
    double getFuncValue(double xValue);
}
