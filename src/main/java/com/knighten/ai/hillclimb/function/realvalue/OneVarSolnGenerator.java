package com.knighten.ai.hillclimb.function.realvalue;

import com.knighten.ai.hillclimb.interfaces.IHillClimbSolnGenerator;
import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;

import java.util.Random;

public class OneVarSolnGenerator implements IHillClimbSolnGenerator {

    private double minDomain;
    private double maxDomain;
    private double stepSize;
    private Random random;

    public OneVarSolnGenerator(double minDomain, double maxDomain, double stepSize, Random random) {

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

        if(!Double.isFinite(stepSize))
            throw new IllegalArgumentException("Step Size Cannot Be Infinity Or NaN");

        if(stepSize == 0)
            throw new IllegalArgumentException("Step Size Cannot Be 0");

        if(random == null)
            throw new IllegalArgumentException("Random Object Cannot Be Null");

        if(minDomain >= maxDomain)
            throw new IllegalArgumentException("Min Domain Must Be less That Max Domain");

        this.minDomain = minDomain;
        this.maxDomain = maxDomain;
        this.stepSize = stepSize;
        this.random = random;
    }

    // TODO - Control Rounding With Param
    @Override
    public IHillClimbSolution randomSolution() {
        double randomNotRounded = this.random.nextDouble() * (this.maxDomain - this.minDomain);
        double roundedRandom = this.minDomain + Math.round(randomNotRounded * 100.0) / 100.0;

        return new OneVarSolution(roundedRandom, minDomain, maxDomain, stepSize);
    }

}
