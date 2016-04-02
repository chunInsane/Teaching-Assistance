package cn.edu.nuc.acmicpc.dto;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/13
 */
public class ContestProblemDto {

    private Long contestProblemId;
    private Long contestId;
    private Long problemId;
    private Integer order;

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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }
}
