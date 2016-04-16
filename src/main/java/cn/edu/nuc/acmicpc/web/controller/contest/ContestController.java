package cn.edu.nuc.acmicpc.web.controller.contest;

import cn.edu.nuc.acmicpc.common.constant.StatusConstant;
import cn.edu.nuc.acmicpc.common.enums.ContestType;
import cn.edu.nuc.acmicpc.common.exception.AppException;
import cn.edu.nuc.acmicpc.common.settings.Settings;
import cn.edu.nuc.acmicpc.common.util.DateUtil;
import cn.edu.nuc.acmicpc.common.util.EncryptUtil;
import cn.edu.nuc.acmicpc.common.util.SessionUtil;
import cn.edu.nuc.acmicpc.common.util.ValidateUtil;
import cn.edu.nuc.acmicpc.dto.ContestDto;
import cn.edu.nuc.acmicpc.dto.ContestUserDto;
import cn.edu.nuc.acmicpc.dto.UserDto;
import cn.edu.nuc.acmicpc.dto.contest.ContestProblemDetailDto;
import cn.edu.nuc.acmicpc.form.condition.ContestCondition;
import cn.edu.nuc.acmicpc.form.dto.contest.LoginContestDto;
import cn.edu.nuc.acmicpc.form.dto.contest.ShowContestDto;
import cn.edu.nuc.acmicpc.form.dto.other.ResultDto;
import cn.edu.nuc.acmicpc.model.Contest;
import cn.edu.nuc.acmicpc.service.ContestProblemService;
import cn.edu.nuc.acmicpc.service.ContestService;
import cn.edu.nuc.acmicpc.service.ContestUserService;
import cn.edu.nuc.acmicpc.service.UserService;
import cn.edu.nuc.acmicpc.web.common.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/5
 * Contest controller.
 */
@Controller
@RequestMapping("/contest")
public class ContestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContestController.class);

    @Autowired
    private Settings settings;
    @Autowired
    private ContestService contestService;
    @Autowired
    private ContestProblemService contestProblemService;
    @Autowired
    private UserService userService;
    @Autowired
    private ContestUserService contestUserService;

    /**
     * user register contest
     * @param userId
     * @param contestId
     * @return
     */
    @RequestMapping("register/{userId}/{contestId}")
    public @ResponseBody ResultDto register(@PathVariable("userId") Long userId, @PathVariable("contestId") Long contestId) {
        ResultDto resultDto = new ResultDto();
        //check user
        UserDto userDto = userService.getUserByUserId(userId);
        if (userDto == null || !Objects.equals(userDto.getUserId(), userId)) {
            throw new AppException("用户不存在!");
        }

        //check contest
        ContestDto contestDto = contestService.getContestDtoByContestId(contestId);
        if (contestDto == null || !Objects.equals(contestDto.getContestId(), contestId)) {
            throw new AppException("比赛不存在!");
        }

        //stop register after contest stopped
        Timestamp currentTime = DateUtil.getCurrentTime();
        if (currentTime.after(contestDto.getEndTime())) {
            throw new AppException("比赛已经结束!");
        }

        if (!contestUserService.checkUserCanRegisterInContest(userId, contestId)) {
            throw new AppException("已经注册过该比赛!");
        }

        ContestUserDto contestUserDto = new ContestUserDto();
        contestUserDto.setUserId(userId);
        contestUserDto.setContestId(contestId);
        Long contestUserId = contestUserService.createContestUser(contestUserDto);
        if (contestUserId == null) {
            LOGGER.error("注册比赛出现错误!");
            throw new AppException("注册比赛出现错误!");
        }

        return resultDto;
    }

    @RequestMapping("search")
    public @ResponseBody ResultDto search(HttpSession session, @RequestBody(required = false) ContestCondition condition) {
        ResultDto resultDto = new ResultDto();
        if (condition == null) {
            condition = new ContestCondition();
        }
        if (!SessionUtil.isAdmin(session)) {
            condition.isVisible = true;
        }
        Map<String, Object> conditionMap = condition.toConditionMap();
        Long count = contestService.count(conditionMap);
        PageInfo pageInfo = PageInfo.buildPageInfo(count, condition.currentPage, settings.RECORD_PER_PAGE, null);
        List<ContestDto> contestDtos = contestService.getContestList(conditionMap, pageInfo);

        //convert contestDto list to showContestDto list
        List<ShowContestDto> showContestDtos = new ArrayList<>();
        if (contestDtos != null && contestDtos.size() > 0) {
            for (ContestDto contestDto : contestDtos) {
                ContestType contestType = ContestType.values()[contestDto.getType()];
                ShowContestDto showContestDto = new ShowContestDto(contestDto);
                showContestDto.setTypeName(contestType.getDescription());
                showContestDtos.add(showContestDto);
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("pageInfo", pageInfo);
        result.put("list", showContestDtos);
        resultDto.setResult(result);
        return resultDto;
    }

    @RequestMapping("data/{contestId}")
    public @ResponseBody ResultDto data(@PathVariable("contestId") Long contestId, HttpSession session) {
        ResultDto resultDto = new ResultDto();
        ContestDto contestDto = contestService.getContestDtoByContestId(contestId);
        if (contestDto == null) {
            LOGGER.error(String.format("不存在该比赛! contestId = %s", contestId));
            throw new AppException("不存在该比赛!");
        }

        if (!contestDto.isVisible() && !SessionUtil.isAdmin(session)) {
            LOGGER.error(String.format("不存在该比赛! contestId = %s", contestId));
            throw new AppException("不存在该比赛!");
        }
        if (SessionUtil.checkContestPermission(session, contestId)) {
            resultDto.setStatus(StatusConstant.UNAUTHORIZED);
            return resultDto;
        }

        List<ContestProblemDetailDto> contestProblemDetailDtos;
        contestDto.setType(SessionUtil.getContestType(session, contestId));
        if (contestDto.getStatus().equals("Pending") && !SessionUtil.isAdmin(session)) {
            contestProblemDetailDtos = Collections.EMPTY_LIST;
        } else {
            contestProblemDetailDtos = contestProblemService.getContestProblemDetailDtoByContestId(contestId);
            if (!SessionUtil.isAdmin(session)) {
                for (ContestProblemDetailDto contestProblemDetailDto : contestProblemDetailDtos) {
                    contestProblemDetailDto.setSource("");
                }
            }
        }
        Map<String, Object> result = new HashMap<>();
        result.put("contest", contestDto);
        result.put("problemList", contestProblemDetailDtos);
        resultDto.setResult(result);
        return resultDto;
    }

    @RequestMapping("/loginContest")
    public @ResponseBody ResultDto loginContest(HttpSession session,
                        @RequestBody @Valid LoginContestDto loginContestDto, BindingResult validateResult) {
        ResultDto resultDto = new ResultDto();
        if (validateResult.hasErrors()) {
            resultDto.setStatus(StatusConstant.SERVER_ERROR);
            resultDto.setErrors(ValidateUtil.fieldErrorsToMap(validateResult.getFieldErrors()));
        } else {
            Map<String, String> errors = checkLoginInfo(session, loginContestDto);
            if (errors != null && errors.size() > 0) {
                resultDto.setStatus(StatusConstant.SERVER_ERROR);
                resultDto.setErrors(errors);
            }
        }
        return resultDto;
    }

    /**
     * check login information when login contest.
     * @param session
     * @param loginContestDto
     * @return
     */
    private Map<String, String> checkLoginInfo(HttpSession session, LoginContestDto loginContestDto) {
        Map<String, String> errors = new HashMap<>();
        ContestDto contestDto = contestService.getContestDtoByContestId(loginContestDto.getContestId());
        if (contestDto == null || (!contestDto.isVisible() && !SessionUtil.isAdmin(session))) {
            throw new AppException("不存在该比赛!");
        }

        if (contestDto.getType() == ContestType.PUBLIC.ordinal()) {
            //public type, do nothing
        } else if (contestDto.getType() == ContestType.PRIVATE.ordinal()){
            //check password
            if (!SessionUtil.isAdmin(session)) {
                if (!EncryptUtil.checkPassword(loginContestDto.getPassword(), contestDto.getPassword())) {
                    errors.put("password", "密码不正确!");
                }
            }
        } else {
            //unexpected type
            LOGGER.error(String.format("unexpected type, type = %s", contestDto.getType()));
            throw new AppException("unexpected type!");
        }
        return errors;
    }
}
