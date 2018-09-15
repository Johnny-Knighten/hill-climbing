import java.util.List;

/**
 * An implementation of the Hill Climbing algorithm to perform optimization/search. This is the standard implementation
 * with no restarts, so it is unlikely the most optimal state will be found. Can be used for minimization or maximization
 * and also has the ability for early termination when a specific value is found.
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
     * @return the most optimal IHillClimbProblem found
     */
    public IHillClimbProblem optimize() {
        // Make current The Initial State
        IHillClimbProblem current = this.initialState;
        current.setScore(current.scoreState());

        // Used To Mark a Valley or Peak
        boolean end = false;

        do {
            // Generate Next States and Score Them
            List<IHillClimbProblem> nextStates = current.generateNextStates();
            for(IHillClimbProblem state: nextStates)
                state.setScore(state.scoreState());

            IHillClimbProblem bestNextState = getBestNextState(nextStates);

            // Check If We Hit Valley/Peak Otherwise Update Current And Continue
            if(!params.isDescend())
                if(bestNextState.getScore() > current.getScore())
                    current = bestNextState;
                else
                    end = true;
            else
                if(bestNextState.getScore() < current.getScore())
                    current = bestNextState;
                else
                    end = true;

        } while(current.getScore() != params.getGoalScore() &&  !end);

        return current;
    }

    // Linear Run Over Next Possible States To Find The One With The Best Score
    private IHillClimbProblem getBestNextState(List<IHillClimbProblem> nextStates) {
        IHillClimbProblem best = nextStates.get(0);
        for(int nextState=1; nextState<nextStates.size(); nextState++) {
            // If Ascending Check If Current IHillClimbProblems Has Higher Score The Current Best
            if (!params.isDescend()) {
                if (best.getScore() < nextStates.get(nextState).getScore())
                    best = nextStates.get(nextState);
            } else {
                if (best.getScore() > nextStates.get(nextState).getScore())
                    best = nextStates.get(nextState);
            }
        }

        return best;
    }


    public static void main(String args[]) {
        HillClimbParams params = new HillClimbParams();
        params.setDescend(false);
        params.setGoalScore(0);

        NQueensProblem initialState = new NQueensProblem(new int[]{1,0,2,3});
        HillClimbing climber = new HillClimbing(initialState, params);
        IHillClimbProblem optimal = climber.optimize();
        System.out.println(optimal);
        System.out.println("Score: " + optimal.getScore());
    }

}
