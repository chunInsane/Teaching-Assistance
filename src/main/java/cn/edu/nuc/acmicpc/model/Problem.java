package cn.edu.nuc.acmicpc.model;

import java.io.Serializable;

/**
 * problem information
 */
public class Problem implements Serializable {

    private Integer problemId;
    private String title;
    private String description;
    private String standardInput;
    private String standardOutput;
    private String sampleInput;
    private String sampleOutput;
    private String hint;
    private String source;
    private Integer solved;
    private Integer tried;
    private Boolean isVisiable;
    private Integer dataCount;
    //limit
    private Integer timeLimit;
    private Integer memoryLimit;
    private Integer javaTimeLimit;
    private Integer javaMemoryLimit;
    private Integer outputLimit;

    @Override
    public String toString() {
        return "Problem{" +
                "problemId=" + problemId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", standardInput='" + standardInput + '\'' +
                ", standardOutput='" + standardOutput + '\'' +
                ", sampleInput='" + sampleInput + '\'' +
                ", sampleOutput='" + sampleOutput + '\'' +
                ", hint='" + hint + '\'' +
                ", source='" + source + '\'' +
                ", solved=" + solved +
                ", tried=" + tried +
                ", isVisiable=" + isVisiable +
                ", dataCount=" + dataCount +
                ", timeLimit=" + timeLimit +
                ", memoryLimit=" + memoryLimit +
                ", javaTimeLimit=" + javaTimeLimit +
                ", javaMemoryLimit=" + javaMemoryLimit +
                ", outputLimit=" + outputLimit +
                '}';
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStandardInput(String standardInput) {
        this.standardInput = standardInput;
    }

    public void setStandardOutput(String standardOutput) {
        this.standardOutput = standardOutput;
    }

    public void setSampleInput(String sampleInput) {
        this.sampleInput = sampleInput;
    }

    public void setSampleOutput(String sampleOutput) {
        this.sampleOutput = sampleOutput;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setSolved(Integer solved) {
        this.solved = solved;
    }

    public void setTried(Integer tried) {
        this.tried = tried;
    }

    public void setVisiable(Boolean visiable) {
        isVisiable = visiable;
    }

    public void setDataCount(Integer dataCount) {
        this.dataCount = dataCount;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public void setMemoryLimit(Integer memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public void setJavaTimeLimit(Integer javaTimeLimit) {
        this.javaTimeLimit = javaTimeLimit;
    }

    public void setJavaMemoryLimit(Integer javaMemoryLimit) {
        this.javaMemoryLimit = javaMemoryLimit;
    }

    public void setOutputLimit(Integer outputLimit) {
        this.outputLimit = outputLimit;
    }

    public Integer getProblemId() {

        return problemId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStandardInput() {
        return standardInput;
    }

    public String getStandardOutput() {
        return standardOutput;
    }

    public String getSampleInput() {
        return sampleInput;
    }

    public String getSampleOutput() {
        return sampleOutput;
    }

    public String getHint() {
        return hint;
    }

    public String getSource() {
        return source;
    }

    public Integer getSolved() {
        return solved;
    }

    public Integer getTried() {
        return tried;
    }

    public Boolean getVisiable() {
        return isVisiable;
    }

    public Integer getDataCount() {
        return dataCount;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    public Integer getJavaTimeLimit() {
        return javaTimeLimit;
    }

    public Integer getJavaMemoryLimit() {
        return javaMemoryLimit;
    }

    public Integer getOutputLimit() {
        return outputLimit;
    }
}
