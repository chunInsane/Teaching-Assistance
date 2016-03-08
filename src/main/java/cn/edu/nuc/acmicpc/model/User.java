package cn.edu.nuc.acmicpc.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * account information
 */
public class User implements Serializable {

    //account basic info
    private Integer userId;
    private String userName; //user email
    private String password;
    private String nickname;
    private Timestamp createTime;

    //user profile
    private String studentId;
    private String school;
    private Integer departmentId;
    private String mobilePhone;
    private String motto;
    private Integer sex;
    private Timestamp lastLogin;

    private Integer tried;
    private Integer solved;
    private Integer type;

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", createTime=" + createTime +
                ", studentId='" + studentId + '\'' +
                ", school='" + school + '\'' +
                ", departmentId=" + departmentId +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", motto='" + motto + '\'' +
                ", sex=" + sex +
                ", lastLogin=" + lastLogin +
                ", tried=" + tried +
                ", solved=" + solved +
                ", type=" + type +
                '}';
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setTried(Integer tried) {
        this.tried = tried;
    }

    public void setSolved(Integer solved) {
        this.solved = solved;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getSchool() {
        return school;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getMotto() {
        return motto;
    }

    public Integer getSex() {
        return sex;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public Integer getTried() {
        return tried;
    }

    public Integer getSolved() {
        return solved;
    }

    public Integer getType() {
        return type;
    }
}
