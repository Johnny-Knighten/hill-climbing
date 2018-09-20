import interfaces.IHillClimbSolnGenerator;
import interfaces.IHillClimbSolution;
import nqueens.NQueensSolnGenerator;

import java.util.List;

/**
 * An implementation of the Hill Climbing With Random Restarts algorithm to perform optimization. The true optimal state
 * is more likely to be found if the algorithm is ran for many iterations. Can be used for minimization or maximization.
 * Also has the ability for early termination when a specific value is found and can terminate when a set number of
 * iterations are executed.
 */
public class HillClimbRandRestart {

    private HillClimbParams params;
    private IHillClimbSolution initialSolution;
    private IHillClimbSolnGenerator generator;

    public HillClimbRandRestart(IHillClimbSolution initialSolution, IHillClimbSolnGenerator generator,
                                HillClimbParams params) {
        this.initialSolution = initialSolution;
        this.generator = generator;
        this.params = params;
    }

    /**
     * Starts the hill climbing process to find an optimal state.
     *
     * @return the most optimal state found
     */
    public IHillClimbSolution optimize() {
        // Make current The Initial State
        IHillClimbSolution current = this.initialSolution;
        IHillClimbSolution bestSoFar =  this.initialSolution; // Keeps the best state found over all restarts
        current.setScore(current.scoreState());

        int iterations = 0;

        do {
            // Generate Next States and Score Them
            List<IHillClimbSolution> nextStates = current.generateNextSolns();
            for(IHillClimbSolution state: nextStates)
                state.setScore(state.scoreState());

            IHillClimbSolution bestNextState = getBestNextState(nextStates);

            // Check If We Hit Valley/Peak then Random Restart Otherwise Update Current And Continue
            if(!params.isMinimization())
                if(bestNextState.getScore() > current.getScore()) {
                    current = bestNextState;
                    bestSoFar = current;
                } else {
                    current = generator.randomSolution();
                    if(current.getScore() > bestSoFar.getScore())
                        bestSoFar = current;
                }
            else
                if(bestNextState.getScore() < current.getScore()) {
                    current = bestNextState;
                    bestSoFar = current;
                } else {
                    current = generator.randomSolution();
                    if(current.getScore() < bestSoFar.getScore())
                        bestSoFar = current;
                }

            iterations++;
        } while(!isGoalScore(current) && iterations < params.getMaxIterations());

        return bestSoFar;
    }

    // Linear Run Over Next Possible States To Find The One With The Best Score
    private IHillClimbSolution getBestNextState(List<IHillClimbSolution> nextStates) {
        IHillClimbSolution best = nextStates.get(0);
        for(int nextState=1; nextState<nextStates.size(); nextState++) {
            // If Ascending Check If Current IHillClimbProblems Has Higher Score The Current Best
            if (!params.isMinimization()) {
                if (best.getScore() < nextStates.get(nextState).getScore())
                    best = nextStates.get(nextState);
            } else {
                if (best.getScore() > nextStates.get(nextState).getScore())
                    best = nextStates.get(nextState);
            }
        }

        return best;
    }

    // Check For Goal Score Depending on Minimization or Maximization
    private boolean isGoalScore(IHillClimbSolution current) {
        if(!params.isMinimization())
            return current.getScore() >= params.getGoalScore();

        return current.getScore() <= params.getGoalScore();
    }

    public static void main(String args[]) {
        HillClimbParams params = new HillClimbParams();
        params.setMinimization(true);
        params.setGoalScore(0);
        params.setMaxIterations(1000);

        NQueensSolnGenerator generator = new NQueensSolnGenerator(8, 0);

        IHillClimbSolution initialState = generator.randomSolution();
        HillClimbRandRestart climber = new HillClimbRandRestart(initialState, generator, params);
        IHillClimbSolution optimal = climber.optimize();

        System.out.println(optimal);
        System.out.println("Score: " + optimal.getScore());
    }

}
