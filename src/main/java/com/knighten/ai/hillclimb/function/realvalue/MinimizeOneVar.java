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

    @Override
    public boolean currentBetterThanBest(IHillClimbSolution current, IHillClimbSolution best) {
        return current.getScore() < best.getScore();
    }

    public static void main(String[] args) {
        HillClimbParams params = new HillClimbParams();
        params.setMaxIterations(1000000);

        IOneVariableFunction function = (x) -> 4*Math.pow(x, 6) - 5*Math.pow(x, 2) + x - 1;

        IHillClimbSolution initialState = new OneVarSolution(2, -2, 2, .01);

        IHillClimbSolnGenerator generator = new OneVarSolnGenerator(-2, 2, .01, new Random());
        IHillClimbProblem problem = new MinimizeOneVar(initialState, function);

        HillClimbRandRestart climber = new HillClimbRandRestart(problem, params, generator);
        IHillClimbSolution optimal = climber.optimize();

        System.out.println(optimal);
        System.out.println("Score: " + optimal.getScore());

    }

}