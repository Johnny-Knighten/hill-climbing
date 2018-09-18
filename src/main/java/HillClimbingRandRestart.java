import interfaces.IHillClimbProbHelper;
import interfaces.IHillClimbProblem;
import nqueens.NQueensProblemHelper;

import java.util.List;

/**
 * An implementation of the Hill Climbing With Random Restarts algorithm to perform optimization/search. The true
 * optimal state is more likely to be found if the algorithm is ran for many iterations. Can be used for minimization or
 * maximization. Also has the ability for early termination when a specific value is found and can terminate when a set
 * number of iterations are executed.
 */
public class HillClimbingRandRestart {

    private HillClimbParams params;
    private IHillClimbProblem initialState;
    private IHillClimbProbHelper helper;

    public HillClimbingRandRestart(IHillClimbProblem initialState, IHillClimbProbHelper helper, HillClimbParams params) {
        this.initialState = initialState;
        this.helper = helper;
        this.params = params;
    }

    /**
     * Starts the hill climbing process to find an optimal state.
     *
     * @return the most optimal interfaces.IHillClimbProblem found
     */
    public IHillClimbProblem optimize() {
        // Make current The Initial State
        IHillClimbProblem current = this.initialState;
        IHillClimbProblem bestSoFar =  this.initialState; // Keeps the best state found over all restarts
        current.setScore(current.scoreState());

        int iterations = 0;

        do {
            // Generate Next States and Score Them
            List<IHillClimbProblem> nextStates = current.generateNextStates();
            for(IHillClimbProblem state: nextStates)
                state.setScore(state.scoreState());

            IHillClimbProblem bestNextState = getBestNextState(nextStates);

            // Check If We Hit Valley/Peak then Random Restart Otherwise Update Current And Continue
            if(!params.isMinimization())
                if(bestNextState.getScore() > current.getScore()) {
                    current = bestNextState;
                    bestSoFar = current;
                } else {
                    current = helper.randomState();
                    if(current.getScore() > bestSoFar.getScore())
                        bestSoFar = current;
                }
            else
                if(bestNextState.getScore() < current.getScore()) {
                    current = bestNextState;
                    bestSoFar = current;
                } else {
                    current = helper.randomState();
                    if(current.getScore() < bestSoFar.getScore())
                        bestSoFar = current;
                }

            iterations++;
        } while(!isGoalScore(current) && iterations < params.getMaxIterations());

        return bestSoFar;
    }

    // Linear Run Over Next Possible States To Find The One With The Best Score
    private IHillClimbProblem getBestNextState(List<IHillClimbProblem> nextStates) {
        IHillClimbProblem best = nextStates.get(0);
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
    private boolean isGoalScore(IHillClimbProblem current) {
        if(!params.isMinimization())
            return current.getScore() >= params.getGoalScore();

        return current.getScore() <= params.getGoalScore();
    }

    public static void main(String args[]) {
        HillClimbParams params = new HillClimbParams();
        params.setMinimization(true);
        params.setGoalScore(0);
        params.setMaxIterations(1000);

        NQueensProblemHelper helper = new NQueensProblemHelper(8, 0);

        IHillClimbProblem initialState = helper.randomState();
        HillClimbingRandRestart climber = new HillClimbingRandRestart(initialState, helper, params);
        IHillClimbProblem optimal = climber.optimize();
        System.out.println(optimal);
        System.out.println("Score: " + optimal.getScore());
    }

}
