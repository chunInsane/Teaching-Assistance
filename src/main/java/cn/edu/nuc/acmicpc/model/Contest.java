package cn.edu.nuc.acmicpc.model;

import java.io.Serializable;

/**
 * Contest information
 */
public class Contest implements Serializable {

    /** tow hours*/
    private static final Integer DEFAULT_CONTEST_TIME_LENGTH = 2 * 60 * 60;

    private Integer contestId;
    private String title;
    private String description;
    private Integer type;
    private Integer length = DEFAULT_CONTEST_TIME_LENGTH;
    private boolean isVisible;
    private String password;
    private Integer parentId;
    private Integer frozenTime;

    @Override
    public String toString() {
        return "Contest{" +
                "frozenTime=" + frozenTime +
                ", parentId=" + parentId +
                ", password='" + password + '\'' +
                ", isVisible=" + isVisible +
                ", length=" + length +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", contestId=" + contestId +
                '}';
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getFrozenTime() {
        return frozenTime;
    }

    public void setFrozenTime(Integer frozenTime) {
        this.frozenTime = frozenTime;
    }
}
