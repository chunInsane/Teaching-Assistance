package cn.edu.nuc.acmicpc.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/10
 */
public class Status implements Serializable {

    private Integer statusId;
    private Integer userId;
    private Integer problemId;
    private Integer result;
    private Integer memoryCost;
    private Integer timeCost;
    private Integer languageId;
    private Integer length;
    private Timestamp time;
    private Integer contestId;
    private Integer caseNumber;
    private Integer codeId;
    private Integer compileInfoId;

    @Override
    public String toString() {
        return "Status{" +
                "statusId=" + statusId +
                ", userId=" + userId +
                ", problemId=" + problemId +
                ", result=" + result +
                ", memoryCost=" + memoryCost +
                ", timeCost=" + timeCost +
                ", languageId=" + languageId +
                ", length=" + length +
                ", time=" + time +
                ", contestId=" + contestId +
                ", caseNumber=" + caseNumber +
                ", codeId=" + codeId +
                ", compileInfoId=" + compileInfoId +
                '}';
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
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

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
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

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public Integer getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(Integer caseNumber) {
        this.caseNumber = caseNumber;
    }

    public Integer getCodeId() {
        return codeId;
    }

    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

    public Integer getCompileInfoId() {
        return compileInfoId;
    }

    public void setCompileInfoId(Integer compileInfoId) {
        this.compileInfoId = compileInfoId;
    }
}
