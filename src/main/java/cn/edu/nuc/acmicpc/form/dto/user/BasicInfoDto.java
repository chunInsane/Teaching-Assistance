package cn.edu.nuc.acmicpc.form.dto.user;

import cn.edu.nuc.acmicpc.dto.UserDto;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/8
 * Basic information.
 */
public class BasicInfoDto {

    @NotNull(message = "请输入用户名!")
    @Email(message = "格式不合法!")
    private String username;

    @NotNull(message = "请输入密码!")
    @Length(min = 6, max = 14, message = "请输入6-14个字符!")
    private String password;

    @NotNull(message = "请输入密码!")
    @Length(min = 6, max = 14, message = "请输入6-14个字符!")
    private String repeatPassword;

    private Integer sex;
    private String nickname;
    private String school;
    private Integer departmentId;
    private String mobilePhone;
    private String motto;

    public UserDto buildUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setSex(sex);
        userDto.setNickname(nickname);
        userDto.setSchool(school);
        userDto.setDepartmentId(departmentId);
        userDto.setMobilePhone(mobilePhone);
        userDto.setMotto(motto);
        return userDto;
    }

    @Override
    public String toString() {
        return "BasicInfoDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                ", sex=" + sex +
                ", nickname='" + nickname + '\'' +
                ", school='" + school + '\'' +
                ", departmentId=" + departmentId +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", motto='" + motto + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
}
