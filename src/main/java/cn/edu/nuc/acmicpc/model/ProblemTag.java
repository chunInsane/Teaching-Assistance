package cn.edu.nuc.acmicpc.model;

import java.io.Serializable;

/**
 * mapping between tag and problem
 */
public class ProblemTag implements Serializable {

    private Integer problemTagId;
    private Integer problemId;
    private Integer tagId;

    @Override
    public String toString() {
        return "ProblemTag{" +
                "problemTagId=" + problemTagId +
                ", problemId=" + problemId +
                ", tagId=" + tagId +
                '}';
    }

    public void setProblemTagId(Integer problemTagId) {
        this.problemTagId = problemTagId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getProblemTagId() {

        return problemTagId;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public Integer getTagId() {
        return tagId;
    }
}
