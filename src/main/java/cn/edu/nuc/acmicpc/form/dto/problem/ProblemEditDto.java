package cn.edu.nuc.acmicpc.form.dto.problem;

import com.google.common.base.Preconditions;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import cn.edu.nuc.acmicpc.dto.ProblemDto;
import javax.validation.constraints.NotNull;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/14
 * Problem edit dto.
 */
public class ProblemEditDto {

    private String action;
    private Long problemId;
    private String description;
    @NotNull(message = "Please enter a validate title.")
    @Length(min = 1, max = 50, message = "Please enter a validate title.")
    private String title;
    @NotNull(message = "Time limit should between 0 and 60000")
    @Range(min = 0, max = 60000, message = "Time limit should between 0 and 60000")
    private Integer timeLimit;
    @NotNull(message = "Time limit should between 0 and 60000")
    @Range(min = 0, max = 60000, message = "Time limit should between 0 and 60000")
    private Integer javaTimeLimit;
    @NotNull(message = "Memory limit should between 0 and 262144")
    @Range(min = 0, max = 262144, message = "Memory limit should between 0 and 262144")
    private Integer memoryLimit;
    @NotNull(message = "Memory limit should between 0 and 262144")
    @Range(min = 0, max = 262144, message = "Memory limit should between 0 and 262144")
    private Integer javaMemoryLimit;
    @NotNull(message = "Output limit should between 0 and 262144")
    @Range(min = 0, max = 262144, message = "Output limit should between 0 and 262144")
    private Integer outputLimit;
    private Boolean isSpj;
    private Boolean isVisible;
    private String input;
    private String output;
    private String sampleInput;
    private String sampleOutput;
    private String hint;
    private String source;

    @Override
    public String toString() {
        return "ProblemEditDto{" +
                "action='" + action + '\'' +
                ", problemId=" + problemId +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", isSpj=" + isSpj +
                ", timeLimit=" + timeLimit +
                ", javaTimeLimit=" + javaTimeLimit +
                ", memoryLimit=" + memoryLimit +
                ", javaMemoryLimit=" + javaMemoryLimit +
                ", outputLimit=" + outputLimit +
                ", isVisible=" + isVisible +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                ", sampleInput='" + sampleInput + '\'' +
                ", sampleOutput='" + sampleOutput + '\'' +
                ", hint='" + hint + '\'' +
                ", source='" + source + '\'' +
                '}';
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsSpj() {
        return isSpj;
    }

    public void setIsSpj(Boolean isSpj) {
        this.isSpj = isSpj;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getJavaTimeLimit() {
        return javaTimeLimit;
    }

    public void setJavaTimeLimit(Integer javaTimeLimit) {
        this.javaTimeLimit = javaTimeLimit;
    }

    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(Integer memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public Integer getJavaMemoryLimit() {
        return javaMemoryLimit;
    }

    public void setJavaMemoryLimit(Integer javaMemoryLimit) {
        this.javaMemoryLimit = javaMemoryLimit;
    }

    public Integer getOutputLimit() {
        return outputLimit;
    }

    public void setOutputLimit(Integer outputLimit) {
        this.outputLimit = outputLimit;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getSampleInput() {
        return sampleInput;
    }

    public void setSampleInput(String sampleInput) {
        this.sampleInput = sampleInput;
    }

    public String getSampleOutput() {
        return sampleOutput;
    }

    public void setSampleOutput(String sampleOutput) {
        this.sampleOutput = sampleOutput;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public ProblemDto toProblemDto(ProblemDto problemDto) {
        Preconditions.checkNotNull(problemDto);
        problemDto.setTitle(title);
        problemDto.setDescription(description);
        problemDto.setInput(input);
        problemDto.setOutput(output);
        problemDto.setSampleInput(sampleInput);
        problemDto.setSampleOutput(sampleOutput);
        problemDto.setHint(hint);
        problemDto.setSource(source);
        problemDto.setTimeLimit(timeLimit);
        problemDto.setJavaTimeLimit(javaTimeLimit);
        problemDto.setMemoryLimit(memoryLimit);
        problemDto.setJavaMemoryLimit(javaMemoryLimit);
        problemDto.setOutputLimit(outputLimit);
        problemDto.setIsSpj(isSpj);
        problemDto.setIsVisible(isVisible);
        return problemDto;
    }
}
