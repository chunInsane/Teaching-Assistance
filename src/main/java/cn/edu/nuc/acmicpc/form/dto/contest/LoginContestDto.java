package cn.edu.nuc.acmicpc.form.dto.contest;

import javax.validation.constraints.NotNull;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/15
 * Login contest dto.
 */
public class LoginContestDto {

    @NotNull
    private Long contestId;
    private String password;

    @Override
    public String toString() {
        return "LoginContestDto{" +
                "contestId=" + contestId +
                ", password='" + password + '\'' +
                '}';
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
