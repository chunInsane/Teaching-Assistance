package cn.edu.nuc.acmicpc.web.controller.user;

import cn.edu.nuc.acmicpc.common.constant.StatusConstant;
import cn.edu.nuc.acmicpc.common.exception.AppException;
import cn.edu.nuc.acmicpc.common.settings.Settings;
import cn.edu.nuc.acmicpc.common.util.DateUtil;
import cn.edu.nuc.acmicpc.common.util.SessionUtil;
import cn.edu.nuc.acmicpc.common.util.UUIDUtil;
import cn.edu.nuc.acmicpc.common.util.ValidateUtil;
import cn.edu.nuc.acmicpc.dto.TypeAheadUserDto;
import cn.edu.nuc.acmicpc.dto.UserDto;
import cn.edu.nuc.acmicpc.form.condition.UserCondition;
import cn.edu.nuc.acmicpc.form.dto.other.ResultDto;
import cn.edu.nuc.acmicpc.form.dto.user.BasicInfoDto;
import cn.edu.nuc.acmicpc.form.dto.user.ProfileUserDto;
import cn.edu.nuc.acmicpc.form.dto.user.RegisterUserDto;
import cn.edu.nuc.acmicpc.model.UserSerialKey;
import cn.edu.nuc.acmicpc.service.*;
import cn.edu.nuc.acmicpc.web.common.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private static final String ACTIVATE_EMAIL_SUBJECT = "TEACH-ASSISTANCE账户激活邮件";

    @Autowired
    private UserService userService;
    @Autowired
    private UserSerialKeyService userSerialKeyService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private Settings settings;
    @Autowired
    private CaptchaService captchaService;

    @RequestMapping("/logout")
    public String logout() {
        SessionUtil.logout();
        return "redirect:/index.html";
    }

    @RequestMapping("/register")
    public @ResponseBody ResultDto register(@RequestBody @Valid RegisterUserDto registerUserDto,
                                         BindingResult validateResult, HttpSession session) {
        ResultDto resultDto = new ResultDto();

        //validate validateCode
        if (!captchaService.validate(session.getId(), registerUserDto.getValidateCode())) {
            Map<String, String> result = new HashMap<>();
            result.put("validateCode", "验证码错误!");
            resultDto.setStatus(StatusConstant.SERVER_ERROR);
            resultDto.setErrors(result);
            return resultDto;
        }

        if (validateResult.hasErrors()) {
            resultDto.setStatus(StatusConstant.SERVER_ERROR);
            resultDto.setErrors(ValidateUtil.fieldErrorsToMap(validateResult.getFieldErrors()));
        } else {
            boolean needEmail = false; //whether need send email
            String username = registerUserDto.getUsername();
            UserSerialKey userSerialKey = userSerialKeyService.getUserSerialKeyByUsername(username);
            if (userSerialKey != null) {
                if (Objects.equals(UserSerialKey.SUCCESS, userSerialKey.getStatus())) {
                    resultDto.setStatus(StatusConstant.SERVER_ERROR);
                    resultDto.setMessage("该邮箱已注册!");
                } else {
                    needEmail = true;
                }
            } else {
                needEmail = true;
                userSerialKey = new UserSerialKey();
                userSerialKey.setUsername(username);
                userSerialKey.setKey(UUIDUtil.generateUuid());
                userSerialKey.setStatus(UserSerialKey.INITIAL);
                userSerialKeyService.addUserSerialKey(userSerialKey);
            }

            //send activate email.
            if (needEmail) {
                String activateUrl = settings.HOST + "user/toActivate?key=" + userSerialKey.getKeyId()
                        + "&uid=" + userSerialKey.getKey();
                String emailContent = generateActivateEmailContent(activateUrl);
                LOGGER.info(String.format("send activate email, activateUrl = %s", activateUrl));
                emailService.send(username, ACTIVATE_EMAIL_SUBJECT, emailContent);
                resultDto.setMessage("激活邮件已发送，请注意查收!");
            }
        }
        return resultDto;
    }

    @Deprecated
    @RequestMapping("/register1")
    public @ResponseBody ResultDto register1(@RequestBody @Valid RegisterUserDto registerUserDto, BindingResult validateResult) {
        ResultDto resultDto = new ResultDto();
        if (validateResult.hasErrors()) {
            resultDto.setStatus(StatusConstant.SERVER_ERROR);
            resultDto.setErrors(ValidateUtil.fieldErrorsToMap(validateResult.getFieldErrors()));
        } else {
            Map<String, String> errors = new HashMap<>();
            UserDto userDto = userService.getUserByUsername(registerUserDto.getUsername());
            resultDto.setStatus(StatusConstant.SERVER_ERROR);
            if (null != userDto) {
                errors.put("username", "该用户邮箱已被使用!");
                resultDto.setErrors(errors);
            } else if (validateResult.hasErrors()) {
                resultDto.setErrors(ValidateUtil.fieldErrorsToMap(validateResult.getFieldErrors()));
            }  else {
                resultDto.setStatus(StatusConstant.SUCCESS);
                UserDto newUser = new UserDto();
                Timestamp currentTime = DateUtil.getCurrentTime();
                newUser.setUsername(registerUserDto.getUsername());
                newUser.setCreateTime(currentTime);
                userService.createUser(newUser);
            }
        }
        return resultDto;
    }

    @RequestMapping("/toActivate")
    public String toActivate(HttpServletRequest request, @RequestParam(value = "key", required = true) String key,
                                              @RequestParam(value = "uid", required = true) String uid) {
        Long userSerialId = Long.valueOf(key);
        UserSerialKey userSerialKey = userSerialKeyService.getUserSerialKey(userSerialId);
        if (userSerialKey == null) {
            LOGGER.error(String.format("不存在该序列号! key = %s, uid = %s!", key, uid));
            throw new AppException(String.format("不存在该序列号! key = %s, uid = %s!", key, uid));
        }
        if (!Objects.equals(uid, userSerialKey.getKey())) {
            LOGGER.error(String.format("uid不合法! key = %s, uid = %s!", key, uid));
            throw new AppException(String.format("uid不合法! key = %s, uid = %s!", key, uid));
        }
        request.setAttribute("username", userSerialKey.getUsername());
        request.setAttribute("key", userSerialKey.getKey());
        return "user/activate";
    }

    @RequestMapping("/activate")
    public @ResponseBody ResultDto activate(@RequestBody @Valid BasicInfoDto basicInfoDto, BindingResult validateResult) {
        ResultDto resultDto = new ResultDto();
        if (validateResult.hasErrors()) {
            resultDto.setStatus(StatusConstant.SERVER_ERROR);
            resultDto.setErrors(ValidateUtil.fieldErrorsToMap(validateResult.getFieldErrors()));
            return resultDto;
        }
        //twins input password inconsistent
        if (!Objects.equals(basicInfoDto.getPassword(), basicInfoDto.getRepeatPassword())) {
            Map<String, String> errors = new HashMap<>();
            errors.put("repeatPassword", "两次输入的密码不一致!");
            resultDto.setStatus(StatusConstant.SERVER_ERROR);
            resultDto.setErrors(errors);
            return resultDto;
        }

        String username = basicInfoDto.getUsername();
        UserDto userDto = userService.getUserByUsername(username);
        if (userDto == null) {
            throw new AppException("用户邮箱不合法!");
        }
        UserSerialKey userSerialKey = userSerialKeyService.getUserSerialKeyByUsername(username);
        userSerialKey.setStatus(UserSerialKey.SUCCESS);
        userSerialKeyService.updateSerialKey(userSerialKey);

        userService.createUser(basicInfoDto.buildUserDto());
        return resultDto;
    }

    @RequiresAuthentication
    @RequestMapping("/profile/{userId}")
    public @ResponseBody ResultDto profile(@PathVariable("userId") Long userId) {
        UserDto userDto = userService.getUserByUserId(userId);
        ProfileUserDto profileUserDto = new ProfileUserDto(userDto);
        ResultDto resultDto = new ResultDto();
        Map<String, Object> result = new HashMap<>();
        result.put("data", profileUserDto);
        resultDto.setResult(result);
        return resultDto;
    }

    @RequiresAuthentication
    @RequestMapping("/typeAheadItem/{username}")
    public @ResponseBody ResultDto typeAheadItem(@PathVariable("username") String username) {
        ResultDto resultDto = new ResultDto();
        UserDto userDto = userService.getUserByUsername(username);
        if (userDto == null) {
            resultDto.setStatus(StatusConstant.NOT_FOUND);
            resultDto.setMessage("不存在该用户!");
            return resultDto;
        }
        TypeAheadUserDto typeAheadUserDto = new TypeAheadUserDto();
        typeAheadUserDto.setUserId(userDto.getUserId());
        typeAheadUserDto.setUsername(userDto.getUsername());
        typeAheadUserDto.setNickname(userDto.getNickname());
        Map<String, Object> result = new HashMap<>();
        result.put("user", typeAheadUserDto);
        resultDto.setResult(result);
        return resultDto;
    }

    @RequiresAuthentication
    @RequestMapping("typeAheadList")
    public @ResponseBody ResultDto typeAheadList(@RequestBody UserCondition condition) {
        ResultDto resultDto = new ResultDto();
        Map<String, Object> conditionMap = condition.toConditionMap();
        Long count = userService.count(conditionMap);
        PageInfo pageInfo = PageInfo.buildPageInfo(count, 1L, settings.RECORD_PER_PAGE, null);
        List<TypeAheadUserDto> userDtos = userService.getTypeAheadUserDtos(conditionMap, pageInfo);
        Map<String, Object> result = new HashMap<>();
        result.put("pageInfo", pageInfo);
        result.put("list", userDtos);
        resultDto.setResult(result);
        return resultDto;
    }

    /**
     * Generate activate email content.
     * @param activateUrl
     * @return
     */
    private String generateActivateEmailContent(String activateUrl) {
        StringBuilder builder = new StringBuilder();
        builder.append("请将<a href='");
        builder.append(activateUrl);
        builder.append("'>");
        builder.append(activateUrl);
        builder.append("</a>");
        builder.append("激活链接复制到浏览器中,激活Teach-Assistance账户");
        return builder.toString();
    }
}