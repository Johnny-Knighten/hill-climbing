import interfaces.IHillClimbProblem;
import nqueens.NQueensProblem;

import java.util.List;

/**
 * An implementation of the Hill Climbing algorithm to perform optimization/search. This is the standard implementation
 * with no restarts, so it is unlikely the most optimal state will be found. Can be used for minimization or
 * maximization. Also has the ability for early termination when a specific value is found and can terminate when a set
 * number of iterations are executed.
 */
public class HillClimbing {

    private HillClimbParams params;
    private IHillClimbProblem initialState;

    public HillClimbing(IHillClimbProblem initialState, HillClimbParams params) {
        this.initialState = initialState;
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
        current.setScore(current.scoreState());

        // Used To Mark a Valley or Peak
        boolean isValleyOrPeak = false;

        int iterations = 0;

        do {
            // Generate Next States and Score Them
            List<IHillClimbProblem> nextStates = current.generateNextStates();
            for(IHillClimbProblem state: nextStates)
                state.setScore(state.scoreState());

            IHillClimbProblem bestNextState = getBestNextState(nextStates);

            // Check If We Hit Valley/Peak Otherwise Update Current And Continue
            if(!params.isMinimization())
                if(bestNextState.getScore() > current.getScore())
                    current = bestNextState;
                else
                    isValleyOrPeak = true;
            else
                if(bestNextState.getScore() < current.getScore())
                    current = bestNextState;
                else
                    isValleyOrPeak = true;

            iterations++;
        } while(!isGoalScore(current) && !isValleyOrPeak && iterations < params.getMaxIterations());

        return current;
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
        params.setMaxIterations(5);

        NQueensProblem initialState = new NQueensProblem(new int[]{0,1,2,3});
        HillClimbing climber = new HillClimbing(initialState, params);
        IHillClimbProblem optimal = climber.optimize();
        System.out.println(optimal);
        System.out.println("Score: " + optimal.getScore());
    }

}
