package cn.edu.nuc.acmicpc.model;

import java.io.Serializable;

/**
 * Map between contest and problem
 */
public class ContestProblem implements Serializable {

    private Long contestProblemId;
    private Long contestId;
    private Long problemId;
    private Integer order;

    @Override
    public String toString() {
        return "ContestProblem{" +
                "contestProblemId=" + contestProblemId +
                ", contestId=" + contestId +
                ", problemId=" + problemId +
                ", order=" + order +
                '}';
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public Long getContestProblemId() {
        return contestProblemId;
    }

    public void setContestProblemId(Long contestProblemId) {
        this.contestProblemId = contestProblemId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
