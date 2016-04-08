package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.common.BasicTest;
import cn.edu.nuc.acmicpc.dto.ProblemDto;
import cn.edu.nuc.acmicpc.dto.ProblemListDto;
import cn.edu.nuc.acmicpc.form.condition.ProblemCondition;
import cn.edu.nuc.acmicpc.web.common.PageInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/8
 */
public class ProblemServiceTest extends BasicTest {

    @Autowired
    private ProblemService problemService;

    @Test
    public void test1() {
        problemService.createProblem();
    }

    @Test
    public void test2() {
        ProblemDto problemDto = new ProblemDto();
        problemDto.setProblemId(2L);
        problemDto.setTitle("a - b");
        problemService.updateProblem(problemDto);
    }

    @Test
    public void test3() {
        Assert.assertTrue(problemService.checkProblemExists(1L));
    }

    @Test
    public void test4() {
        ProblemCondition condition = new ProblemCondition();
        condition.isVisible = false;
        Long count = problemService.count(condition.toConditionMap());
        Assert.assertEquals((Long) 1L, count);
    }

    @Test
    public void test5() {
        ProblemCondition condition = new ProblemCondition();
        condition.isVisible = false;
        Long count = problemService.count(condition.toConditionMap());
        PageInfo pageInfo = PageInfo.buildPageInfo(count, 1L, 15L, null);
        List<ProblemListDto> problemListDtoList = problemService.getProblemListDtos(condition.toConditionMap(), pageInfo);
        Assert.assertEquals(1, problemListDtoList.size());
    }

    @Test
    public void test6() {
        ProblemDto problemDto = new ProblemDto();
        problemDto.setProblemId(2L);
        problemDto.setSource("中北大学校赛!");
        problemService.updateProblem(problemDto);
    }

    @Test
    public void test7() {
        Assert.assertTrue(problemService.checkProblemExists(1L));
        Assert.assertFalse(problemService.checkProblemExists(3L));
    }

    @Test
    public void test8() {
        Assert.assertEquals(1, problemService.getAllProblemIds(true).size());
        Assert.assertEquals(1, problemService.getAllProblemIds(false).size());
        Assert.assertEquals(2, problemService.getAllProblemIds(null).size());
    }

    @Test
    public void test9() {
        Assert.assertNotNull(problemService.getProblemDtoByProblemId(1L));
    }

    @Test
    public void test10() {
        Map<String, Object> params = new HashMap<>();
        params.put("description", "Calculate $a+b$");
        problemService.updateProblemByProblemId(2L, params);
    }
}
