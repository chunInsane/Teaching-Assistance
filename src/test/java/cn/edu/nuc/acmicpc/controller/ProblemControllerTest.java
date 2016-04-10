package cn.edu.nuc.acmicpc.controller;

import cn.edu.nuc.acmicpc.common.BasicTest;
import cn.edu.nuc.acmicpc.dto.ProblemListDto;
import cn.edu.nuc.acmicpc.form.condition.ProblemCondition;
import cn.edu.nuc.acmicpc.form.dto.other.ResultDto;
import cn.edu.nuc.acmicpc.service.ProblemService;
import cn.edu.nuc.acmicpc.web.common.PageInfo;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/9
 */
public class ProblemControllerTest extends BasicTest {

    @Autowired
    private ProblemService problemService;

    @Test
    public void test1() {
        ResultDto resultDto = new ResultDto();
        ProblemCondition condition = new ProblemCondition();
        Map<String, Object> conditionMap = condition.toConditionMap();
        condition.keyword = "a + b";
        PageInfo pageInfo = PageInfo.buildPageInfo(problemService.count(conditionMap), 1L, 15L, null);
        List<ProblemListDto> problemDtoList = problemService.getProblemListDtos(conditionMap, pageInfo);
        Map<String, Object> result = new HashMap<>();
        result.put("list", problemDtoList);
        result.put("pageInfo", pageInfo);
        resultDto.setResult(result);
        String response = JSON.toJSONString(resultDto);
        System.out.println(response);
    }
}
