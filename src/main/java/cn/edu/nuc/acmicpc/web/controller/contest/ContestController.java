package cn.edu.nuc.acmicpc.web.controller.contest;

import cn.edu.nuc.acmicpc.common.constant.StatusConstant;
import cn.edu.nuc.acmicpc.common.exception.AppException;
import cn.edu.nuc.acmicpc.common.settings.Settings;
import cn.edu.nuc.acmicpc.common.util.DateUtil;
import cn.edu.nuc.acmicpc.common.util.SessionUtil;
import cn.edu.nuc.acmicpc.dto.ContestDto;
import cn.edu.nuc.acmicpc.dto.ContestProblemDto;
import cn.edu.nuc.acmicpc.dto.ContestUserDto;
import cn.edu.nuc.acmicpc.dto.UserDto;
import cn.edu.nuc.acmicpc.dto.contest.ContestProblemDetailDto;
import cn.edu.nuc.acmicpc.form.condition.ContestCondition;
import cn.edu.nuc.acmicpc.form.dto.other.ResultDto;
import cn.edu.nuc.acmicpc.service.ContestProblemService;
import cn.edu.nuc.acmicpc.service.ContestService;
import cn.edu.nuc.acmicpc.service.ContestUserService;
import cn.edu.nuc.acmicpc.service.UserService;
import cn.edu.nuc.acmicpc.web.common.PageInfo;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/5
 * Contest Controller.
 */
@Controller
@RequestMapping("/contest")
public class ContestController {

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
     * @param session
     * @return
     */
    @RequestMapping("register/{userId}/{contestId}")
    public @ResponseBody String register(@PathVariable("userId") Long userId, @PathVariable("contestId") Long contestId,
                                         HttpSession session) {
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

        if (!contestService.checkUserCanRegisterInContest(userId, contestId)) {
            throw new AppException("已经注册过该比赛!");
        }

        ContestUserDto contestUserDto = new ContestUserDto();
        contestUserDto.setUserId(userId);
        contestUserDto.setContestId(contestId);
        Long contestUserId = contestUserService.createContestUser(contestUserDto);
        if (contestUserId == null) {
            throw new AppException("注册比赛出现错误!");
        }

        return JSON.toJSONString(resultDto);
    }

    @RequestMapping("search")
    public @ResponseBody String search(HttpSession session, @RequestBody(required = false) ContestCondition condition) {
        ResultDto resultDto = new ResultDto();
        if (condition == null) {
            condition = new ContestCondition();
        }
        if (!SessionUtil.isAdmin(session)) {
            condition.isVisible = true;
        }
        Map<String, Object> conditionMap = condition.buildConditionMap();
        Long count = contestService.count(conditionMap);
        PageInfo pageInfo = PageInfo.buildPageInfo(count, condition.currentPage, settings.RECORD_PER_PAGE, null);
        List<ContestDto> contestDtos = contestService.getContestList(conditionMap, pageInfo);

        Map<String, Object> result = new HashMap<>();
        result.put("pageInfo", pageInfo);
        result.put("list", contestDtos);
        resultDto.setResult(result);
        return JSON.toJSONString(resultDto);
    }

    @RequestMapping("data/{contestId}")
    public @ResponseBody String data(@PathVariable("contestId") Long contestId, HttpSession session) {
        ResultDto resultDto = new ResultDto();
        ContestDto contestDto = contestService.getContestDtoByContestId(contestId);
        if (contestDto == null) {
            throw new AppException("不存在该比赛!");
        }

        if (!contestDto.isVisible() && !SessionUtil.isAdmin(session)) {
            throw new AppException("不存在该比赛!");
        }
        if (SessionUtil.checkContestPermission(session, contestId)) {
            resultDto.setStatus(StatusConstant.UNAUTHORIZED);
            return JSON.toJSONString(resultDto);
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
        return JSON.toJSONString(resultDto);
    }
}
