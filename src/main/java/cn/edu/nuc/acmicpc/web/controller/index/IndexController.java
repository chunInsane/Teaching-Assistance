package cn.edu.nuc.acmicpc.web.controller.index;

import cn.edu.nuc.acmicpc.common.constant.StatusConstant;
import cn.edu.nuc.acmicpc.common.enums.ContestType;
import cn.edu.nuc.acmicpc.common.enums.GenderType;
import cn.edu.nuc.acmicpc.common.enums.JudgeResultType;
import cn.edu.nuc.acmicpc.common.util.SessionUtil;
import cn.edu.nuc.acmicpc.dto.UserDto;
import cn.edu.nuc.acmicpc.form.dto.other.ResultDto;
import cn.edu.nuc.acmicpc.service.DepartmentService;
import cn.edu.nuc.acmicpc.service.LanguageService;
import cn.edu.nuc.acmicpc.service.RecentContestService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/5
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private RecentContestService recentContestService;


    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * redirect the page when use IE.
     * @return
     */
    @RequestMapping("nonsupport")
    public String nonsupport() {
        return "nonsupport";
    }

    @RequestMapping("data")
    public @ResponseBody String data(HttpSession session) {
        ResultDto resultDto = new ResultDto();
        UserDto currentUser = SessionUtil.getCurrentLoginUser(session);
        if (currentUser == null) {
            resultDto.setStatus(StatusConstant.UNAUTHERIZED);
            return JSON.toJSONString(resultDto);
        }
        return JSON.toJSONString(resultDto);
    }

    @RequestMapping("globalData")
    public @ResponseBody String globalData() {
        ResultDto resultDto = new ResultDto();
        Map<String, Object> result = new HashMap<>();
        result.put("departmentList", departmentService.getDepartments());
        result.put("languageList", languageService.getLanguageList());
        result.put("resultTypeList", JudgeResultType.values());
        result.put("contestTypeList", ContestType.values());
        result.put("genderTypeList", GenderType.values());
        resultDto.setResult(result);
        return JSON.toJSONString(resultDto);
    }
}
