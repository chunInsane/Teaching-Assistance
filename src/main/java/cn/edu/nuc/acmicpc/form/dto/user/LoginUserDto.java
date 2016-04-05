package cn.edu.nuc.acmicpc.form.dto.user;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * Dto post from user login from
 */
public class LoginUserDto {

    @NotNull(message = "请输入用户名!")
    @Email(message = "格式不合法!")
    private String userName;

    @NotNull(message = "请输入密码!")
    @Length(min = 6, max = 14, message = "请输入6-14个字符!")
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

    @Override
    public String toString() {
        return "LoginUserDto{" +
                "password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
