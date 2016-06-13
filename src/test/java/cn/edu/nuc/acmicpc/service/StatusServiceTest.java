package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.common.BasicTest;
import cn.edu.nuc.acmicpc.common.util.ObjectUtil;
import cn.edu.nuc.acmicpc.dto.StatusDto;
import cn.edu.nuc.acmicpc.model.Status;
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
public class StatusServiceTest extends BasicTest {

    @Autowired
    private StatusService statusService;

    @Test
    public void test() {
        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("userType", 2);
        Long count = statusService.count(conditionMap);
        PageInfo pageInfo = PageInfo.buildPageInfo(count, 1L, 15L, null);
        List<StatusDto> statusDtos = statusService.getShowStatusList(conditionMap, pageInfo);
        System.out.println(statusDtos);
    }

    @Test
    public void test1() {
        StatusDto statusDto = new StatusDto();
        statusDto.setProblemId(1L);
        statusDto.setUserId(1L);
        statusDto.setLanguageId(1L);
        statusDto.setCodeId(1L);
        statusDto.setCodeId(1L);
        statusService.createStatus(statusDto);
    }

    @Test
    public void test2() {
        Assert.assertEquals((Long) 0l, statusService.countProblemsThatUserSolved(1L, false));
    }

    @Test
    public void test3() {
        Assert.assertEquals((Long) 1l, statusService.countProblemsThatUserTried(1L, false));
    }

    @Test
    public void test4() {
        Assert.assertEquals((Long)1l, statusService.countUsersThatTriedProblem(1L));
    }

    @Test
    public void test5() {
        Assert.assertEquals((Long)0l, statusService.countUserThatSolvedProblem(1L));
    }

    @Test
    public void test6() {
        List<Long> problemIds = statusService.findAllProblemIdsThatUserSolved(1L, false);
        Assert.assertEquals(0, problemIds.size());
    }

    @Test
    public void test7() {
        List<Long> problems = statusService.findAllProblemIdsThatUserTried(1L, false);
        Assert.assertEquals(1, problems.size());
    }

    @Test
    public void test8() {
        List<StatusDto> statusDtos = statusService.getQueuingStatus(true);
        System.out.println(statusDtos.size());
        Assert.assertEquals(1, statusDtos.size());
        statusDtos = statusService.getQueuingStatus(false);
        System.out.println(statusDtos.size());
        Assert.assertEquals(1, statusDtos.size());
    }

    @Test
    public void  test9() {
        StatusDto statusDto = new StatusDto();
        statusDto.setStatusId(1L);
        statusDto.setLength(50);
        statusService.updateStatus(statusDto);
    }
}
