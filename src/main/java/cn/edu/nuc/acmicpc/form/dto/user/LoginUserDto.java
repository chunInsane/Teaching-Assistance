package cn.edu.nuc.acmicpc.form.dto.user;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * Dto post from user login from
 */
public class LoginUserDto {

    private String userName;
    private String password;

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
