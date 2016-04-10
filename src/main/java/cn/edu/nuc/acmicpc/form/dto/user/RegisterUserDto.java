package cn.edu.nuc.acmicpc.form.dto.user;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * Register user dto.
 */
public class RegisterUserDto {

    @NotNull(message = "请输入用户名!")
    @Email(message = "格式不合法!")
    private String username;

    private String validateCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
