package cn.edu.nuc.acmicpc.web.controller.problem;

import cn.edu.nuc.acmicpc.common.constant.StatusConstant;
import cn.edu.nuc.acmicpc.common.enums.ProblemSolvedStatusType;
import cn.edu.nuc.acmicpc.common.exception.AppException;
import cn.edu.nuc.acmicpc.common.settings.Settings;
import cn.edu.nuc.acmicpc.common.util.SessionUtil;
import cn.edu.nuc.acmicpc.dto.ProblemDto;
import cn.edu.nuc.acmicpc.dto.ProblemListDto;
import cn.edu.nuc.acmicpc.dto.UserDto;
import cn.edu.nuc.acmicpc.form.condition.ProblemCondition;
import cn.edu.nuc.acmicpc.form.dto.other.ResultDto;
import cn.edu.nuc.acmicpc.service.*;
import cn.edu.nuc.acmicpc.web.common.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/3/21
 * Problem controller.
 */
@Controller
@RequestMapping("/problem")
public class ProblemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProblemController.class);

    @Autowired
    private ProblemService problemService;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private ContestService contestService;
    @Autowired
    private ContestProblemService contestProblemService;
    @Autowired
    private CodeService codeService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private Settings settings;

    @RequestMapping("/data/{problemId}")
    public @ResponseBody ResultDto data(@PathVariable("problemId") Long problemId, HttpSession session) {
        ProblemDto problemDto = problemService.getProblemDtoByProblemId(problemId);
        if (null == problemDto) {
            LOGGER.error(String.format("不存在该题目, problemId = %s!", problemId));
            throw new AppException("不存在该题目!");
        }
        if (!SessionUtil.isAdmin(session)) {
            if (!problemDto.getIsVisible()) {
                LOGGER.error(String.format("不存在该题目, problemId = %s!", problemId));
                throw new AppException("不存在该题目!");
            }
        }
        ResultDto resultDto = new ResultDto();
        resultDto.setStatus(StatusConstant.SUCCESS);
        Map<String, Object> result = new HashMap<>();
        result.put("problem", problemDto);
        resultDto.setResult(result);
        return resultDto;
    }

    @RequestMapping("/search")
    public @ResponseBody ResultDto search(HttpSession session, @RequestBody(required = false) ProblemCondition condition) {
        ResultDto resultDto = new ResultDto();
        if (!SessionUtil.isAdmin(session)) {
            condition.isVisible = true;
        }
        Map<String, Object> conditionMap = condition.toConditionMap();
        Long count = problemService.count(conditionMap);
        PageInfo pageInfo = PageInfo.buildPageInfo(count, condition.currentPage, settings.RECORD_PER_PAGE, null);
        List<ProblemListDto> problemListDtos = problemService.getProblemListDtos(conditionMap, pageInfo);

        Map<Long, ProblemSolvedStatusType> problemStatus = getProblemStatus(SessionUtil.getCurrentLoginUser(session), session);

        for (ProblemListDto problemListDto : problemListDtos) {
            ProblemSolvedStatusType problemSolvedStatusType = problemStatus.get(problemListDto.getProblemId());
            if (Objects.equals(problemSolvedStatusType, ProblemSolvedStatusType.PASS)) {
                problemListDto.setStatus(1);
            } else if (Objects.equals(problemSolvedStatusType, ProblemSolvedStatusType.FAIL)) {
                problemListDto.setStatus(2);
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("pageInfo", pageInfo);
        result.put("list", problemListDtos);
        resultDto.setResult(result);
        return resultDto;
    }

    /**
     * Get current login user problem status.
     * @param currentUser
     * @param session
     * @return
     */
    private Map<Long, ProblemSolvedStatusType> getProblemStatus(UserDto currentUser, HttpSession session) {
        Map<Long, ProblemSolvedStatusType> problemStatus = new HashMap<>();
        if (currentUser != null) {
            List<Long> triedProblemIds = statusService.findAllProblemIdsThatUserTried(
                    currentUser.getUserId(), SessionUtil.isAdmin(session));
            for (Long triedProblemId : triedProblemIds) {
                problemStatus.put(triedProblemId, ProblemSolvedStatusType.FAIL);
            }
            List<Long> acProblemIds = statusService.findAllProblemIdsThatUserSolved(
                    currentUser.getUserId(), SessionUtil.isAdmin(session));
            for (Long acProblemId : acProblemIds) {
                problemStatus.put(acProblemId, ProblemSolvedStatusType.PASS);
            }
        }
        return problemStatus;
    }
}
