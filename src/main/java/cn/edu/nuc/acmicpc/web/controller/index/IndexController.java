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
import org.apache.shiro.authz.annotation.RequiresAuthentication;
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
 * Index controller.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private LanguageService languageService;

    /**
     * redirect the page when use IE.
     * @return
     */
    @RequestMapping("nonsupport")
    public String nonsupport() {
        return "nonsupport";
    }

    @RequiresAuthentication
    @RequestMapping("data")
    public @ResponseBody ResultDto data(HttpSession session) {
        ResultDto resultDto = new ResultDto();
        UserDto currentUser = SessionUtil.getCurrentLoginUser();
        if (currentUser == null) {
            resultDto.setStatus(StatusConstant.UNAUTHERIZED);
            return resultDto;
        }
        return resultDto;
    }

    @RequestMapping("globalData")
    public @ResponseBody ResultDto globalData() {
        ResultDto resultDto = new ResultDto();
        Map<String, Object> result = new HashMap<>();
        result.put("departmentList", departmentService.getDepartments());
        result.put("languageList", languageService.getLanguageList());
        result.put("resultTypeList", JudgeResultType.values());
        result.put("contestTypeList", ContestType.values());
        result.put("genderTypeList", GenderType.values());
        resultDto.setResult(result);
        return resultDto;
    }
}
