package cn.edu.nuc.acmicpc.form.dto.user;

import cn.edu.nuc.acmicpc.dto.UserDto;

import java.sql.Timestamp;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/14
 * Profile user dto.
 */
public class ProfileUserDto {

    private Long userId;
    private String username; //user email
    private String nickname;
    private Timestamp createTime;
    private String school;
    private Integer departmentId;
    private String mobilePhone;
    private String motto;
    private Integer sex;
    private Timestamp lastLogin;
    private Integer tried;
    private Integer solved;

    public ProfileUserDto() {
    }

    public ProfileUserDto(UserDto userDto) {
        this.userId = userDto.getUserId();
        this.username = userDto.getUsername();
        this.nickname = userDto.getNickname();
        this.createTime = userDto.getCreateTime();
        this.school = userDto.getSchool();
        this.departmentId = userDto.getDepartmentId();
        this.mobilePhone = userDto.getMobilePhone();
        this.motto = userDto.getMotto();
        this.sex = userDto.getSex();
        this.lastLogin = userDto.getLastLogin();
        this.tried = userDto.getTried();
        this.solved = userDto.getSolved();
    }

    @Override
    public String toString() {
        return "ProfileUserDto{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", createTime=" + createTime +
                ", school='" + school + '\'' +
                ", departmentId=" + departmentId +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", motto='" + motto + '\'' +
                ", sex=" + sex +
                ", lastLogin=" + lastLogin +
                ", tried=" + tried +
                ", solved=" + solved +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getTried() {
        return tried;
    }

    public void setTried(Integer tried) {
        this.tried = tried;
    }

    public Integer getSolved() {
        return solved;
    }

    public void setSolved(Integer solved) {
        this.solved = solved;
    }
}
