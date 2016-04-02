package cn.edu.nuc.acmicpc.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/9
 */
public class StatusDto implements Serializable {

    private Long statusId;
    private Integer resultId;
    private String result;
    private Integer memoryCost = 0;
    private Integer timeCost = 0;
    private Integer length;
    private Timestamp time;
    private Integer caseNumber;
    private Long codeId;
    private Long compileInfoId;
    private Long contestId;
    private Long languageId;
    private Long problemId;
    private Long userId;
    private String username;
    private String nickname;
    private String language;
    private String extension;
    private String codeContent;
    private Integer dataCount;
    private Integer javaMemoryLimit;
    private Integer javaTimeLimit;
    private Integer memoryLimit;
    private Integer timeLimit;
    private Integer outputLimit;
    private boolean isSpj;

    @Override
    public String toString() {
        return "StatusDto{" +
                "statusId=" + statusId +
                ", resultId=" + resultId +
                ", result='" + result + '\'' +
                ", memoryCost=" + memoryCost +
                ", timeCost=" + timeCost +
                ", length=" + length +
                ", time=" + time +
                ", caseNumber=" + caseNumber +
                ", codeId=" + codeId +
                ", compileInfoId=" + compileInfoId +
                ", contestId=" + contestId +
                ", languageId=" + languageId +
                ", problemId=" + problemId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", language='" + language + '\'' +
                ", extension='" + extension + '\'' +
                ", codeContent='" + codeContent + '\'' +
                ", dataCount=" + dataCount +
                ", javaMemoryLimit=" + javaMemoryLimit +
                ", javaTimeLimit=" + javaTimeLimit +
                ", memoryLimit=" + memoryLimit +
                ", timeLimit=" + timeLimit +
                ", outputLimit=" + outputLimit +
                ", isSpj=" + isSpj +
                '}';
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getMemoryCost() {
        return memoryCost;
    }

    public void setMemoryCost(Integer memoryCost) {
        this.memoryCost = memoryCost;
    }

    public Integer getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(Integer timeCost) {
        this.timeCost = timeCost;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(Integer caseNumber) {
        this.caseNumber = caseNumber;
    }

    public Long getCodeId() {
        return codeId;
    }

    public void setCodeId(Long codeId) {
        this.codeId = codeId;
    }

    public Long getCompileInfoId() {
        return compileInfoId;
    }

    public void setCompileInfoId(Long compileInfoId) {
        this.compileInfoId = compileInfoId;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getCodeContent() {
        return codeContent;
    }

    public void setCodeContent(String codeContent) {
        this.codeContent = codeContent;
    }

    public Integer getDataCount() {
        return dataCount;
    }

    public void setDataCount(Integer dataCount) {
        this.dataCount = dataCount;
    }

    public Integer getJavaMemoryLimit() {
        return javaMemoryLimit;
    }

    public void setJavaMemoryLimit(Integer javaMemoryLimit) {
        this.javaMemoryLimit = javaMemoryLimit;
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

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getOutputLimit() {
        return outputLimit;
    }

    public void setOutputLimit(Integer outputLimit) {
        this.outputLimit = outputLimit;
    }

    public boolean getIsSpj() {
        return isSpj;
    }

    public void setIsSpj(boolean spj) {
        isSpj = spj;
    }
}
