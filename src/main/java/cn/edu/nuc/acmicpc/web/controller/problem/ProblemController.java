package cn.edu.nuc.acmicpc.web.controller.problem;

import cn.edu.nuc.acmicpc.common.constant.StatusConstant;
import cn.edu.nuc.acmicpc.common.exception.AppException;
import cn.edu.nuc.acmicpc.common.settings.Settings;
import cn.edu.nuc.acmicpc.common.util.SessionUtil;
import cn.edu.nuc.acmicpc.dto.ProblemDto;
import cn.edu.nuc.acmicpc.form.dto.other.ResultDto;
import cn.edu.nuc.acmicpc.form.dto.problem.ProblemCondition;
import cn.edu.nuc.acmicpc.service.FileService;
import cn.edu.nuc.acmicpc.service.ProblemService;
import cn.edu.nuc.acmicpc.service.StatusService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
    private StatusService statusService;
    @Autowired
    private FileService fileService;
    @Autowired
    private Settings settings;

    @RequestMapping("/data/{problemId}")
    public @ResponseBody String data(@PathVariable("problemId") Long problemId, HttpSession session) {
        ProblemDto problemDto = problemService.getProblemDtoByProblemId(problemId);
        if (null == problemDto) {
            throw new AppException("No such problem.");
        }
        if (!SessionUtil.isAdmin(session)) {
            if (!problemDto.getIsVisible()) {
                throw new AppException("No such problem.");
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

    public @ResponseBody String uploadProblemDataFile(@PathVariable Long problemId, @RequestBody(value = "uploadFiles", required = true, ))


}
