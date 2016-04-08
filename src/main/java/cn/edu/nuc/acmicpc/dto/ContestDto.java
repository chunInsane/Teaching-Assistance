package cn.edu.nuc.acmicpc.dto;

import com.sun.tools.corba.se.idl.constExpr.Times;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/13
 */
public class ContestDto {

    private Long contestId;
    private String title;
    private String description;
    private Timestamp time;
    private Byte type;
    private Integer length;
    private boolean isVisible;
    private String password;
    private String passwordRepeat;
    private Integer frozenTime;
    private String action;
    private Integer lengthDays;
    private Integer lengthHours;
    private Integer lengthMinutes;
    private String problemList;
    private Boolean needFrozen;
    private Integer frozenLengthMinutes;
    private Integer frozenLengthHours;
    private Integer frozenLengthDays;
    private List<UserDto> userDtos;
    private String typeName;
    private String status;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp currentTime;
    private Long timeLeft;

    @Override
    public String toString() {
        return "ContestDto{" +
                "contestId=" + contestId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", time=" + time +
                ", type=" + type +
                ", length=" + length +
                ", isVisible=" + isVisible +
                ", password='" + password + '\'' +
                ", passwordRepeat='" + passwordRepeat + '\'' +
                ", frozenTime=" + frozenTime +
                ", action='" + action + '\'' +
                ", lengthDays=" + lengthDays +
                ", lengthHours=" + lengthHours +
                ", lengthMinutes=" + lengthMinutes +
                ", problemList='" + problemList + '\'' +
                ", needFrozen=" + needFrozen +
                ", frozenLengthMinutes=" + frozenLengthMinutes +
                ", frozenLengthHours=" + frozenLengthHours +
                ", frozenLengthDays=" + frozenLengthDays +
                ", userDtos=" + userDtos +
                ", typeName='" + typeName + '\'' +
                ", status='" + status + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", currentTime=" + currentTime +
                ", timeLeft=" + timeLeft +
                '}';
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public Integer getFrozenTime() {
        return frozenTime;
    }

    public void setFrozenTime(Integer frozenTime) {
        this.frozenTime = frozenTime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getLengthDays() {
        return lengthDays;
    }

    public void setLengthDays(Integer lengthDays) {
        this.lengthDays = lengthDays;
    }

    public Integer getLengthHours() {
        return lengthHours;
    }

    public void setLengthHours(Integer lengthHours) {
        this.lengthHours = lengthHours;
    }

    public Integer getLengthMinutes() {
        return lengthMinutes;
    }

    public void setLengthMinutes(Integer lengthMinutes) {
        this.lengthMinutes = lengthMinutes;
    }

    public String getProblemList() {
        return problemList;
    }

    public void setProblemList(String problemList) {
        this.problemList = problemList;
    }

    public Boolean getNeedFrozen() {
        return needFrozen;
    }

    public void setNeedFrozen(Boolean needFrozen) {
        this.needFrozen = needFrozen;
    }

    public Integer getFrozenLengthMinutes() {
        return frozenLengthMinutes;
    }

    public void setFrozenLengthMinutes(Integer frozenLengthMinutes) {
        this.frozenLengthMinutes = frozenLengthMinutes;
    }

    public Integer getFrozenLengthHours() {
        return frozenLengthHours;
    }

    public void setFrozenLengthHours(Integer frozenLengthHours) {
        this.frozenLengthHours = frozenLengthHours;
    }

    public Integer getFrozenLengthDays() {
        return frozenLengthDays;
    }

    public void setFrozenLengthDays(Integer frozenLengthDays) {
        this.frozenLengthDays = frozenLengthDays;
    }

    public List<UserDto> getUserDtos() {
        return userDtos;
    }

    public void setUserDtos(List<UserDto> userDtos) {
        this.userDtos = userDtos;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Timestamp currentTime) {
        this.currentTime = currentTime;
    }

    public Long getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(Long timeLeft) {
        this.timeLeft = timeLeft;
    }
}
