package com.knighten.ai.hillclimb.function.realvalue;

import com.knighten.ai.hillclimb.interfaces.IHillClimbSolnGenerator;
import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;

import java.util.Random;

public class OneVarSolnGenerator implements IHillClimbSolnGenerator {

    private double minDomain;
    private double maxDomain;
    private double stepSize;
    private Random random;

    private int numberOfStepsInSearchRange;

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

        // We Will Get The Number Of Steps That Fit Into The SearchRanch To Be Used To Generate X Values
        this.numberOfStepsInSearchRange = (int) Math.floor((maxDomain-minDomain)/stepSize);
    }

    @Override
    public IHillClimbSolution randomSolution() {
        double randomValue = this.minDomain + this.stepSize * this.random.nextInt(numberOfStepsInSearchRange+1);
        return new OneVarSolution(randomValue, minDomain, maxDomain, stepSize);
    }

}
