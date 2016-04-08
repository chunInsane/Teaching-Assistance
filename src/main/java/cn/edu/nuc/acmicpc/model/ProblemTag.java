package cn.edu.nuc.acmicpc.model;

import java.io.Serializable;

/**
 * mapping between tag and problem
 */
public class ProblemTag implements Serializable {

    private Long problemTagId;
    private Long problemId;
    private Long tagId;

    @Override
    public String toString() {
        return "ProblemTag{" +
                "problemTagId=" + problemTagId +
                ", problemId=" + problemId +
                ", tagId=" + tagId +
                '}';
    }

    public Long getProblemTagId() {
        return problemTagId;
    }

    public void setProblemTagId(Long problemTagId) {
        this.problemTagId = problemTagId;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}
