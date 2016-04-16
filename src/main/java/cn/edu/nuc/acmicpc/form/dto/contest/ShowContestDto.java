package cn.edu.nuc.acmicpc.form.dto.contest;

import cn.edu.nuc.acmicpc.dto.ContestDto;

import java.sql.Timestamp;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/15
 * Show contest dto.
 */
public class ShowContestDto {

    private Long contestId;
    private Integer length;
    private String title;
    private Byte type;
    private String typeName;
    private String status;
    private Boolean isVisible;
    private Timestamp time;

    public ShowContestDto() {
    }

    public ShowContestDto(ContestDto contestDto) {
        this.contestId = contestDto.getContestId();
        this.length = contestDto.getLength();
        this.title = contestDto.getTitle();
        this.type = contestDto.getType();
        this.typeName = contestDto.getTypeName();
        this.status = contestDto.getStatus();
        this.isVisible = contestDto.isVisible();
        this.time = contestDto.getTime();
    }

    @Override
    public String toString() {
        return "ShowContestDto{" +
                "contestId=" + contestId +
                ", length=" + length +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                ", status='" + status + '\'' +
                ", isVisible=" + isVisible +
                ", time=" + time +
                '}';
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
