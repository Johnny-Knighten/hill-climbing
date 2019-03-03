package com.knighten.ai.hillclimb.function.realvalue;

import com.knighten.ai.hillclimb.HillClimb;
import com.knighten.ai.hillclimb.HillClimbParams;
import com.knighten.ai.hillclimb.HillClimbRandRestart;
import com.knighten.ai.hillclimb.interfaces.IHillClimbProblem;
import com.knighten.ai.hillclimb.interfaces.IHillClimbSolnGenerator;
import com.knighten.ai.hillclimb.interfaces.IHillClimbSolution;

import java.util.List;
import java.util.Random;

public class MinimizeOneVar implements IHillClimbProblem {

    private IHillClimbSolution initialGuess;
    private IOneVariableFunction function;

    public MinimizeOneVar(IHillClimbSolution initialGuess, IOneVariableFunction function) {

        if(initialGuess == null)
            throw new IllegalArgumentException("Initial Guess Cannot Be Null");

        if(function == null)
            throw new IllegalArgumentException("Function Being Optimized Cannot Be Null");

        this.initialGuess = initialGuess;
        this.function = function;
    }

    @Override
    public IHillClimbSolution getInitialGuess() {
        return this.initialGuess;
    }

    @Override
    public IHillClimbSolution getBestSolution(List<IHillClimbSolution> possibleSolns) {
        IHillClimbSolution min = possibleSolns.get(0);
        for(int nextSoln=1; nextSoln < possibleSolns.size(); nextSoln++) {
            if (min.getScore() > possibleSolns.get(nextSoln).getScore()) {
                min = possibleSolns.get(nextSoln);
            }
        }

        return min;
    }

    @Override
    public boolean isPeakOrPlateau(IHillClimbSolution currentSolution, IHillClimbSolution newSolution) {
        return newSolution.getScore() >= currentSolution.getScore();
    }

    @Override
    public double scoreSolution(IHillClimbSolution solution) {
        return this.function.getFuncValue(((OneVarSolution) solution).getXValue());
    }

    /**
     * Determines if the current solutions is better than the best. Since we are minimizing the current is better if it
     * has a lower score.
     *
     * @param current the current solution being operated on
     * @param best the best solution found so far
     * @return true if current has a lower score than best else false
     */
    @Override
    public boolean currentBetterThanBest(IHillClimbSolution current, IHillClimbSolution best) {
        return current.getScore() < best.getScore();
    }

    public static void main(String[] args) {
        HillClimbParams params = new HillClimbParams();
        params.setMaxIterations(1000000);

        IOneVariableFunction function = (x) -> 4*Math.pow(x, 6) - 5*Math.pow(x, 2) + x - 1;

        IHillClimbSolution initialState = new OneVarSolution(2, -2, 2, .0001);

        IHillClimbSolnGenerator generator = new OneVarSolnGenerator(-2, 2, .0001, new Random());
        IHillClimbProblem problem = new MinimizeOneVar(initialState, function);

        HillClimbRandRestart climber = new HillClimbRandRestart(problem, params, generator);
        IHillClimbSolution optimal = climber.optimize();

        System.out.println(optimal);
        System.out.println("Score: " + optimal.getScore());

    }

}
