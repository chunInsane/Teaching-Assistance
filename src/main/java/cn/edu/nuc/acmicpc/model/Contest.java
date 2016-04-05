package cn.edu.nuc.acmicpc.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Contest information
 */
public class Contest implements Serializable {

    /** tow hours*/
    private static final Integer DEFAULT_CONTEST_TIME_LENGTH = 2 * 60 * 60;

    private Long contestId;
    private String title;
    private String description;
    private Timestamp time;
    private Byte type;
    private Integer length = DEFAULT_CONTEST_TIME_LENGTH;
    private boolean isVisible;
    private String password;
    private Integer frozenTime;

    @Override
    public String toString() {
        return "Contest{" +
                "frozenTime=" + frozenTime +
                ", password='" + password + '\'' +
                ", isVisible=" + isVisible +
                ", length=" + length +
                ", type=" + type +
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
}
