package cn.edu.nuc.acmicpc.web.controller.user;

import cn.edu.nuc.acmicpc.common.constant.SessionConstant;
import cn.edu.nuc.acmicpc.common.constant.StatusConstant;
import cn.edu.nuc.acmicpc.common.settings.Settings;
import cn.edu.nuc.acmicpc.common.util.DateUtil;
import cn.edu.nuc.acmicpc.common.util.EncryptUtil;
import cn.edu.nuc.acmicpc.common.util.ValidateUtil;
import cn.edu.nuc.acmicpc.dto.UserDto;
import cn.edu.nuc.acmicpc.form.condition.UserCondition;
import cn.edu.nuc.acmicpc.form.dto.other.ResultDto;
import cn.edu.nuc.acmicpc.form.dto.user.LoginUserDto;
import cn.edu.nuc.acmicpc.form.dto.user.RegisterUserDto;
import cn.edu.nuc.acmicpc.dto.TypeAheadUserDto;
import cn.edu.nuc.acmicpc.service.*;
import cn.edu.nuc.acmicpc.web.common.PageInfo;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * User controller.
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
    private Settings settings;

    @RequestMapping("/toLogin")
    public String toLogin(Model model) {
        return "user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody String login(HttpSession session, @RequestBody @Valid LoginUserDto loginUser,
                                      BindingResult validateResult) {
        ResultDto resultDto = new ResultDto();
        if (validateResult.hasErrors()) {
            resultDto.setStatus(StatusConstant.SERVER_ERROR);
            resultDto.setErrors(ValidateUtil.fieldErrorsToMap(validateResult.getFieldErrors()));
        } else {
            UserDto userDto = userService.getUserByUsername(loginUser.getUserName());
            if (userDto == null || EncryptUtil.checkPassword(loginUser.getPassword(), userDto.getPassword())) {
                Map<String, String> errors = new HashMap<>();
                errors.put("password", "用户名和密码不匹配!");
                resultDto.setStatus(StatusConstant.SERVER_ERROR);
                resultDto.setErrors(errors);
            }
            userDto.setLastLogin(DateUtil.getCurrentTime());
            userService.updateUser(userDto);
            session.setAttribute(SessionConstant.CURRENT_LOGIN_USER_KEY, userDto);
        }
        return JSON.toJSONString(resultDto);
    }

    @RequestMapping("/logout")
    public @ResponseBody String logout(HttpSession session) {
        session.removeAttribute(SessionConstant.CURRENT_LOGIN_USER_KEY);
        ResultDto resultDto = new ResultDto();
        return JSON.toJSONString(resultDto);
    }

    @RequestMapping("/register")
    public @ResponseBody String register(@RequestBody @Valid RegisterUserDto registerUserDto, BindingResult validateResult) {
        ResultDto resultDto = new ResultDto();
        if (validateResult.hasErrors()) {
            resultDto.setStatus(StatusConstant.SERVER_ERROR);
            resultDto.setErrors(ValidateUtil.fieldErrorsToMap(validateResult.getFieldErrors()));
        } else {
            Map<String, String> errors = new HashMap<>();
            UserDto userDto = userService.getUserByUsername(registerUserDto.getUserName());
            resultDto.setStatus(StatusConstant.SERVER_ERROR);
            if (null != userDto) {
                errors.put("username", "该用户名已被使用!");
                resultDto.setErrors(errors);
            } else if (validateResult.hasErrors()) {
                resultDto.setErrors(ValidateUtil.fieldErrorsToMap(validateResult.getFieldErrors()));
            } else if (Objects.equals(registerUserDto.getPassword(), registerUserDto.getPasswordConfirm())) {
                errors.put("passwordConfirm", "两次输入的密码不一致!");
                resultDto.setErrors(errors);
            } else {
                resultDto.setStatus(StatusConstant.SUCCESS);
                UserDto newUser = new UserDto();
                Timestamp currentTime = DateUtil.getCurrentTime();
                newUser.setUserName(registerUserDto.getUserName());
                newUser.setPassword(registerUserDto.getPassword());
                newUser.setCreateTime(currentTime);
                userService.createUser(newUser);
            }
        }
        return JSON.toJSONString(resultDto);
    }

    @RequestMapping("/typeAheadItem/{username}")
    public @ResponseBody String typeAheadItem(@PathVariable("username") String username) {
        ResultDto resultDto = new ResultDto();
        UserDto userDto = userService.getUserByUsername(username);
        if (userDto == null) {
            resultDto.setStatus(StatusConstant.NOT_FOUND);
            resultDto.setMessage("不存在该用户!");
            return JSON.toJSONString(resultDto);
        }
        TypeAheadUserDto typeAheadUserDto = new TypeAheadUserDto();
        typeAheadUserDto.setUserId(userDto.getUserId());
        typeAheadUserDto.setUsername(userDto.getUserName());
        typeAheadUserDto.setNickname(userDto.getNickname());
        Map<String, Object> result = new HashMap<>();
        result.put("user", typeAheadUserDto);
        resultDto.setResult(result);
        return JSON.toJSONString(resultDto);
    }

    @RequestMapping("typeAheadList")
    public @ResponseBody String typeAheadList(@RequestBody UserCondition condition) {
        ResultDto resultDto = new ResultDto();
        Map<String, Object> conditionMap = condition.toConditionMap();
        Long count = userService.count(conditionMap);
        PageInfo pageInfo = PageInfo.buildPageInfo(count, 1L, settings.RECORD_PER_PAGE, null);
        List<TypeAheadUserDto> userDtos = userService.getTypeAheadUserDtos(conditionMap, pageInfo);
        Map<String, Object> result = new HashMap<>();
        result.put("pageInfo", pageInfo);
        result.put("list", userDtos);
        resultDto.setResult(result);
        return JSON.toJSONString(resultDto);
    }

}
