package cn.edu.nuc.acmicpc.form.dto.user;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 */
public class RegisterUserDto {

    private Integer userId;

    private String userName; //user email

    private String password;

    private String passwordConfirm;

    @Override
    public String toString() {
        return "RegisterUserDto{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                '}';
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
