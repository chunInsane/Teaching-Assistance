package cn.edu.nuc.acmicpc.web.controller.problem;

import cn.edu.nuc.acmicpc.common.constant.StatusConstant;
import cn.edu.nuc.acmicpc.common.enums.JudgeResultType;
import cn.edu.nuc.acmicpc.common.exception.AppException;
import cn.edu.nuc.acmicpc.common.settings.Settings;
import cn.edu.nuc.acmicpc.common.util.DateUtil;
import cn.edu.nuc.acmicpc.common.util.SessionUtil;
import cn.edu.nuc.acmicpc.common.util.ValidateUtil;
import cn.edu.nuc.acmicpc.dto.*;
import cn.edu.nuc.acmicpc.form.dto.other.ResultDto;
import cn.edu.nuc.acmicpc.form.dto.problem.ProblemCondition;
import cn.edu.nuc.acmicpc.service.*;
import com.alibaba.fastjson.JSON;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/3/21
 * Problem controller
 */
@Controller
@RequestMapping("/problem")
public class ProblemController {

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

    @RequestMapping("/data/{problemId}")
    public @ResponseBody String data(@PathVariable("problemId") Long problemId, HttpSession session) {
        ProblemDto problemDto = problemService.getProblemDtoByProblemId(problemId);
        if (null == problemDto) {
            throw new AppException("不存在该题目!");
        }
        if (!SessionUtil.isAdmin(session)) {
            if (!problemDto.getIsVisible()) {
                throw new AppException("不存在该题目!");
            }
        }
        ResultDto resultDto = new ResultDto();
        resultDto.setStatus(StatusConstant.SUCCESS);
        Map<String, Object> result = new HashMap<>();
        result.put("problem", problemDto);
        resultDto.setResult(result);
        return JSON.toJSONString(resultDto);
    }



    @RequestMapping("/search")
    public @ResponseBody String search(ProblemCondition problemCondition, HttpSession session) {
        if (!SessionUtil.isAdmin(session)) {
            problemCondition.isVisible = true;
        }
        //TODO
        return "";
    }


}
