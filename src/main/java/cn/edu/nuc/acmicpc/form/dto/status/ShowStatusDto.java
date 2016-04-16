package cn.edu.nuc.acmicpc.form.dto.status;

import cn.edu.nuc.acmicpc.dto.StatusDto;

import java.sql.Timestamp;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/15
 * Show status dto.
 */
public class ShowStatusDto {

    private Long statusId;
    private Integer caseNumber;
    private String nickname;
    private String username;
    private Long userId;
    private Long problemId;
    private Long contestId;
    private String returnType;
    private Integer returnTypeId;
    private Timestamp time;
    private Integer timeCost;
    private Integer memoryCost;
    private String language;
    private Integer length;

    public ShowStatusDto() {
    }

    public ShowStatusDto(StatusDto statusDto) {
        this.statusId = statusDto.getStatusId();
        this.caseNumber = statusDto.getCaseNumber();
        this.nickname = statusDto.getNickname();
        this.username = statusDto.getUsername();
        this.userId = statusDto.getUserId();
        this.problemId = statusDto.getProblemId();
        this.contestId = statusDto.getContestId();
        this.returnTypeId = statusDto.getResultId();
        this.returnType = statusDto.getResult();
        this.time = statusDto.getTime();
        this.timeCost = statusDto.getTimeCost();
        this.memoryCost = statusDto.getMemoryCost();
        this.language = statusDto.getLanguage();
        this.length = statusDto.getLength();
    }

    @Override
    public String toString() {
        return "ShowStatusDto{" +
                "time=" + time +
                ", returnTypeId='" + returnTypeId + '\'' +
                ", returnType='" + returnType + '\'' +
                ", problemId=" + problemId +
                ", contestId=" + contestId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", caseNumber=" + caseNumber +
                ", statusId=" + statusId +
                '}';
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Integer getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(Integer caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public Integer getReturnTypeId() {
        return returnTypeId;
    }

    public void setReturnTypeId(Integer returnTypeId) {
        this.returnTypeId = returnTypeId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
