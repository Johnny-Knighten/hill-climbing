package com.knighten.ai.hillclimb.function.realvalue;

import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;

import java.util.ArrayList;
import java.util.List;

public class OneVarSolution implements IHillClimbSolution {

    private double minDomain;
    private double maxDomain;
    private double score;
    private double xValue;
    private double stepSize;


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

    public double getXValue() {
        return xValue;
    }

    @Override
    public double getScore() {
        return this.score;
    }

    @Override
    public void setScore(double score) {

        if(!Double.isFinite(score))
            throw new IllegalArgumentException("A OneVarSolution's Score Cannot Be Infinity Or NaN");

        this.score = score;
    }

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

    @Override
    public String toString() {
        return "X Value - " + this.getXValue();
    }

}
