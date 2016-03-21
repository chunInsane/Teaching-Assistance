package cn.edu.nuc.acmicpc.web.controller.user;

import cn.edu.nuc.acmicpc.common.constant.SessionConstant;
import cn.edu.nuc.acmicpc.common.constant.StatusConstant;
import cn.edu.nuc.acmicpc.common.util.ValidateUtil;
import cn.edu.nuc.acmicpc.dto.UserDto;
import cn.edu.nuc.acmicpc.form.dto.other.ResultDto;
import cn.edu.nuc.acmicpc.form.dto.user.LoginUserDto;
import cn.edu.nuc.acmicpc.form.dto.user.RegisterUserDto;
import cn.edu.nuc.acmicpc.model.Setting;
import cn.edu.nuc.acmicpc.service.*;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProblemService problemService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private UserSerialKeyService userSerialKeyService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private Setting setting;

    @RequestMapping("/login")
    public @ResponseBody String login(HttpSession session, @RequestBody @Valid LoginUserDto loginUserDto, BindingResult validateResult) {
        ResultDto resultDto = new ResultDto();
        if (validateResult.hasErrors()) {
            resultDto.setStatus(StatusConstant.SERVER_ERROR);
            resultDto.setErrors(ValidateUtil.fieldErrorsToMap(validateResult.getFieldErrors()));
        } else {
            UserDto userDto = userService.getUserByUsername(loginUserDto.getUserName());
            if (userDto == null || Objects.equals(userDto.getPassword(), loginUserDto.getPassword())) {
                Map<String, String> errors = new HashMap<>();
                errors.put("password", "username and password do not match!");
                resultDto.setStatus(StatusConstant.SERVER_ERROR);
                resultDto.setErrors(errors);
            }
            userDto.setLastLogin(new Timestamp(new Date().getTime() / 1000 * 1000));
            userService.updateUser(userDto);
            session.setAttribute(SessionConstant.CURRENT_LOGIN_USER_KEY, userDto);
        }
        return JSON.toJSONString(resultDto);
    }

    @RequestMapping("/logout")
    public @ResponseBody String logout(HttpSession session) {
        session.invalidate();
        ResultDto resultDto = new ResultDto();
        return JSON.toJSONString(resultDto);
    }

    @RequestMapping("/register")
    public @ResponseBody String register(HttpSession session, @RequestBody @Valid RegisterUserDto registerUserDto, BindingResult validateResult) {
        ResultDto resultDto = new ResultDto();
        if (validateResult.hasErrors()) {
            resultDto.setStatus(StatusConstant.SERVER_ERROR);
            resultDto.setErrors(ValidateUtil.fieldErrorsToMap(validateResult.getFieldErrors()));
        } else {
            Map<String, String> errors = new HashMap<>();
            UserDto userDto = userService.getUserByUsername(registerUserDto.getUserName());
            resultDto.setStatus(StatusConstant.SERVER_ERROR);
            if (null != userDto) {
                errors.put("username", "this username already used!");
                resultDto.setErrors(errors);
            } else if (validateResult.hasErrors()) {
                resultDto.setErrors(ValidateUtil.fieldErrorsToMap(validateResult.getFieldErrors()));
            } else if (Objects.equals(registerUserDto.getPassword(), registerUserDto.getPasswordConfirm())) {
                errors.put("passwordConfirm", "password and passwordConfirm is not same!");
                resultDto.setErrors(errors);
            } else {
                resultDto.setStatus(StatusConstant.SUCCESS);
                UserDto newUser = new UserDto();
                Timestamp currentTime = new Timestamp(new Date().getTime() / 1000 * 1000);
                newUser.setUserName(registerUserDto.getUserName());
                newUser.setPassword(registerUserDto.getPassword());
                userService.createUser(newUser);
            }
        }
        return JSON.toJSONString(resultDto);
    }


}
