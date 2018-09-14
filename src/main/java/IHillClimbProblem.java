import java.util.List;

public interface IHillClimbProblem {
    double scoreState();
    List<IHillClimbProblem> generateNextStates();
    double getScore();
    void setScore(double score);
}
