package cn.edu.nuc.acmicpc.dto;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/13
 */
public class ContestProblemDto {

    private Long contestProblemId;
    private Long contestId;
    private Long order;
    private Long problemId;

    @Override
    public String toString() {
        return "ContestProblemDto{" +
                "contestProblemId=" + contestProblemId +
                ", contestId=" + contestId +
                ", order=" + order +
                ", problemId=" + problemId +
                '}';
    }

    public Long getContestProblemId() {
        return contestProblemId;
    }

    public void setContestProblemId(Long contestProblemId) {
        this.contestProblemId = contestProblemId;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }
}
