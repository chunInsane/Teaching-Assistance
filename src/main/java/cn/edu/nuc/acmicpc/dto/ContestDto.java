package cn.edu.nuc.acmicpc.dto;

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
    private Integer frozenTime;
    private Integer lengthDays;
    private Integer lengthHours;
    private Integer lengthMinutes;
    private String problemList;
    private List<UserDto> userDtos;
    private String typeName;
    private String status;
    private Timestamp startTime;
    private Timestamp endTime;

    @Override
    public String toString() {
        return "ContestDto{" +
                "endTime=" + endTime +
                ", startTime=" + startTime +
                ", status='" + status + '\'' +
                ", typeName='" + typeName + '\'' +
                ", userDtos=" + userDtos +
                ", problemList='" + problemList + '\'' +
                ", lengthMinutes=" + lengthMinutes +
                ", lengthHours=" + lengthHours +
                ", lengthDays=" + lengthDays +
                ", frozenTime=" + frozenTime +
                ", password='" + password + '\'' +
                ", isVisible=" + isVisible +
                ", length=" + length +
                ", type=" + type +
                ", time=" + time +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", contestId=" + contestId +
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

    public Integer getFrozenTime() {
        return frozenTime;
    }

    public void setFrozenTime(Integer frozenTime) {
        this.frozenTime = frozenTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getStartTime() {

        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
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
}
