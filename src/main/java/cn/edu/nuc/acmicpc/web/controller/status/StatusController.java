package cn.edu.nuc.acmicpc.web.controller.status;

import cn.edu.nuc.acmicpc.common.constant.StatusConstant;
import cn.edu.nuc.acmicpc.common.enums.JudgeResultType;
import cn.edu.nuc.acmicpc.common.enums.JudgeReturnType;
import cn.edu.nuc.acmicpc.common.exception.AppException;
import cn.edu.nuc.acmicpc.common.settings.Settings;
import cn.edu.nuc.acmicpc.common.util.DateUtil;
import cn.edu.nuc.acmicpc.common.util.SessionUtil;
import cn.edu.nuc.acmicpc.common.util.ValidateUtil;
import cn.edu.nuc.acmicpc.dto.*;
import cn.edu.nuc.acmicpc.form.condition.StatusCondition;
import cn.edu.nuc.acmicpc.form.dto.other.ResultDto;
import cn.edu.nuc.acmicpc.form.dto.status.ShowStatusDto;
import cn.edu.nuc.acmicpc.form.dto.status.SubmitStatusDto;
import cn.edu.nuc.acmicpc.service.*;
import cn.edu.nuc.acmicpc.web.common.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/5
 * Status controller.
 */
@Controller
@RequestMapping("/status")
public class StatusController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusController.class);

    @Autowired
    private StatusService statusService;
    @Autowired
    private ProblemService problemService;
    @Autowired
    private CodeService codeService;
    @Autowired
    private CompileInfoService compileInfoService;
    @Autowired
    private ContestService contestService;
    @Autowired
    private ContestProblemService contestProblemService;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private UserService userService;
    @Autowired
    private Settings settings;

    @RequestMapping("search")
    public @ResponseBody ResultDto search(HttpSession session, @RequestBody StatusCondition condition) {
        ResultDto resultDto = new ResultDto();
        if (condition.contestId == null) {
            condition.contestId = -1L;
        }
        if (condition.result == null) {
            condition.result = JudgeResultType.JUDGE_ALL;
        }
        if (!SessionUtil.isAdmin(session)) {
            condition.isForAdmin = false;
            if (condition.contestId != -1) {
                ContestDto contestDto = contestService.getContestDtoByContestId(condition.contestId);
                if (contestDto == null) {
                    LOGGER.error(String.format("不存在该比赛, contestId = %s", condition.contestId));
                    throw new AppException("不存在该比赛!");
                }
                //check permission
                if (SessionUtil.checkContestPermission(session, condition.contestId)) {
                    resultDto.setStatus(StatusConstant.UNAUTHORIZED);
                    return resultDto;
                }
            } else {
                condition.isProblemVisible = true;
                //TODO
            }
        } else {
            if (condition.contestId != -1) {
                ContestDto contestDto = contestService.getContestDtoByContestId(condition.contestId);
                if (contestDto == null) {
                    throw new AppException("不存在该比赛!");
                }
            }
        }

        Map<String, Object> conditionMap = condition.toConditionMap();
        Long count = statusService.count(conditionMap);
        PageInfo pageInfo = PageInfo.buildPageInfo(count, condition.currentPage, settings.RECORD_PER_PAGE, null);
        List<StatusDto> statusDtos = statusService.getShowStatusList(conditionMap, pageInfo);

        //convert statusDto list to showStatusDto list
        List<ShowStatusDto> showStatusDtos = new ArrayList<>();
        if (statusDtos != null && statusDtos.size() > 0) {
            for (StatusDto statusDto : statusDtos) {
                ShowStatusDto showStatusDto = new ShowStatusDto(statusDto);
                String returnType = JudgeReturnType.getReturnType(statusDto.getResultId()).getDescription();
                if (JudgeResultType.JUDGE_NOT_AC.getResults().contains(statusDto.getResultId())) {
                    returnType = returnType.replace("$case", statusDto.getCaseNumber().toString());
                }
                showStatusDto.setReturnType(returnType);
                showStatusDtos.add(showStatusDto);
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("pageInfo", pageInfo);
        result.put("list", showStatusDtos);
        resultDto.setResult(result);
        return resultDto;
    }

    /**
     * submit code.
     * @param session
     * @param submitDto
     * @param validateResult
     * @return
     */
    @RequestMapping("/submit")
    public @ResponseBody ResultDto submit(HttpSession session, @RequestBody @Valid SubmitStatusDto submitDto,
                                       BindingResult validateResult) {
        ResultDto resultDto = new ResultDto();
        if (validateResult.hasErrors()) {
            resultDto.setStatus(StatusConstant.SERVER_ERROR);
            resultDto.setResult(ValidateUtil.fieldErrorsToMap(validateResult.getFieldErrors()));
            return resultDto;
        }

        UserDto currentUser = SessionUtil.getCurrentLoginUser(session);

        //check language
        if (submitDto.getLanguageId() == null) {
            throw new AppException("请选择语言!");
        }
        if (languageService.getLanguageName(submitDto.getLanguageId()) == null) {
            throw new AppException("不存在该语言!");
        }

        //check problem
        if (submitDto.getProblemId() == null) {
            throw new AppException("不存在该题目!");
        }
        ProblemDto problemDto = problemService.getProblemDtoByProblemId(submitDto.getProblemId());
        if (problemDto == null) {
            throw new AppException("不存在该题目!");
        }

        if (submitDto.getContestId() != null) { //status in contest
            ContestDto contestDto = contestService.getContestDtoByContestId(submitDto.getContestId());
            if (contestDto == null) {
                throw new AppException("不存在该比赛!");
            }
            if (!contestProblemService.isExistContestProblem(submitDto.getProblemId(),
                    submitDto.getContestId())) {
                throw new AppException("错误的题目id.");
            }
            if (!SessionUtil.isAdmin(session)) {
                Timestamp currentTime = DateUtil.getCurrentTime();
                if (currentTime.before(contestDto.getStartTime()) || currentTime.after(contestDto.getEndTime())) {
                    throw new AppException("比赛已经结束!");
                }
                if (!SessionUtil.checkContestPermission(session, submitDto.getContestId())) {
                    throw new AppException("尚未注册该比赛!");
                }
            }
        } else { //status not in contest
            if (!SessionUtil.isAdmin(session)) {
                if (!problemDto.getIsVisible()) {
                    throw new AppException("不存在该比赛!");
                }
            }
        }

        //save code
        CodeDto codeDto = new CodeDto();
        codeDto.setContent(submitDto.getCodeContent());
        codeDto.setShare(false);
        Long codeId = codeService.createCode(codeDto);
        if (codeId == null) {
            throw new AppException("保存代码时出现错误!");
        }

        //save status
        StatusDto statusDto = new StatusDto();
        statusDto.setResultId(JudgeReturnType.JUDGE_WAIT.ordinal());
        statusDto.setCodeId(codeId);
        statusDto.setContestId(submitDto.getContestId());
        statusDto.setLanguageId(submitDto.getLanguageId());
        statusDto.setProblemId(submitDto.getProblemId());
        statusDto.setTime(DateUtil.getCurrentTime());
        statusDto.setUserId(currentUser.getUserId());
        statusDto.setLength(submitDto.getCodeContent().length());
        statusService.createStatus(statusDto);
        return resultDto;
    }
}
