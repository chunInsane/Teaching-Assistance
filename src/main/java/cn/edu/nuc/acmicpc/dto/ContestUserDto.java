package cn.edu.nuc.acmicpc.dto;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/13
 */
public class ContestUserDto {

    private Long contestUserId;
    private Long contestId;
    private Long userId;
    private Byte status;
    private String comment;

    @Override
    public String toString() {
        return "ContestUserDto{" +
                "contestUserId=" + contestUserId +
                ", contestId=" + contestId +
                ", userId=" + userId +
                ", status=" + status +
                ", comment='" + comment + '\'' +
                '}';
    }

    public Long getContestUserId() {
        return contestUserId;
    }

    public void setContestUserId(Long contestUserId) {
        this.contestUserId = contestUserId;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
