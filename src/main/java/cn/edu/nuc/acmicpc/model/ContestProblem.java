package cn.edu.nuc.acmicpc.model;

import java.io.Serializable;

/**
 * Map between contest and problem
 */
public class ContestProblem implements Serializable {

    private Integer contestProblemId;
    private Integer contestId;
    private Integer problemId;
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

    public Integer getContestProblemId() {
        return contestProblemId;
    }

    public void setContestProblemId(Integer contestProblemId) {
        this.contestProblemId = contestProblemId;
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
