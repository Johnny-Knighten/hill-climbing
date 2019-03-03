package com.knighten.ai.hillclimb.function.realvalue;

import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;

import java.util.ArrayList;
import java.util.List;

public class OneVarSolution implements IHillClimbSolution {

    private double score;
    private double xValue;
    private double stepSize;


    public OneVarSolution(double xValue, double stepSize) {

        if(!Double.isFinite(xValue))
            throw new IllegalArgumentException("X Value Cannot Be Infinity Or NaN");

        if(!Double.isFinite(stepSize))
            throw new IllegalArgumentException("Step Size Cannot Be Infinity Or NaN");

        if(stepSize == 0)
            throw new IllegalArgumentException("Step Size Cannot Be 0");

        this.xValue = xValue;
        this.stepSize = stepSize;
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
        list.add(new OneVarSolution(xValue - stepSize, stepSize));
        list.add(new OneVarSolution(xValue + stepSize, stepSize));
        return list;
    }
}
