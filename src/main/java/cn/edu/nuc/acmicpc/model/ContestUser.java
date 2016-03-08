package cn.edu.nuc.acmicpc.model;

import java.io.Serializable;

/**
 * Map between contest and user
 */
public class ContestUser implements Serializable {

    private Integer contestUserId;
    private Integer contestId;
    private Integer userId;
    private String comment;

    @Override
    public String toString() {
        return "ContestUser{" +
                "contestUserId=" + contestUserId +
                ", contestId=" + contestId +
                ", userId=" + userId +
                ", comment='" + comment + '\'' +
                '}';
    }

    public Integer getContestUserId() {
        return contestUserId;
    }

    public void setContestUserId(Integer contestUserId) {
        this.contestUserId = contestUserId;
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
