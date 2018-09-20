import interfaces.IHillClimbSolution;
import nqueens.NQueensSoln;

import java.util.List;

/**
 * An implementation of the Hill Climbing algorithm to perform optimization. This is the standard implementation with no
 * restarts, so it is unlikely the most optimal state will be found. Can be used for minimization or maximization. Also
 * has the ability for early termination when a specific value is found and can terminate when a set number of
 * iterations are executed.
 */
public class HillClimb {

    private HillClimbParams params;
    private IHillClimbSolution initialSolution;

    public HillClimb(IHillClimbSolution initialSolution, HillClimbParams params) {
        this.initialSolution = initialSolution;
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
        current.setScore(current.scoreState());

        // Used To Mark a Valley or Peak
        boolean isValleyOrPeak = false;

        int iterations = 0;

        do {
            // Generate Next States and Score Them
            List<IHillClimbSolution> nextStates = current.generateNextSolns();
            for(IHillClimbSolution state: nextStates)
                state.setScore(state.scoreState());

            IHillClimbSolution bestNextState = getBestSolution(nextStates);

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
    private IHillClimbSolution getBestSolution(List<IHillClimbSolution> nextStates) {
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
        params.setMaxIterations(25);

        NQueensSoln initialState = new NQueensSoln(new int[]{0,1,2,3,4,5,6,7});
        HillClimb climber = new HillClimb(initialState, params);
        IHillClimbSolution optimal = climber.optimize();

        System.out.println(optimal);
        System.out.println("Score: " + optimal.getScore());
    }

}
