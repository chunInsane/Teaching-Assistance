package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.common.BasicTest;
import cn.edu.nuc.acmicpc.form.condition.ContestCondition;
import cn.edu.nuc.acmicpc.web.common.PageInfo;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/8
 */
public class ContestServiceTest extends BasicTest {

    @Autowired
    private ContestService contestService;

    @Test
    public void test1() {
        Assert.assertEquals(0, contestService.getAllisVisibleContestIds().size());
    }

    @Test
    public void test2() {
        ContestCondition condition = new ContestCondition();
        condition.isVisible = false;
        Map<String, Object> conditionMap = condition.toConditionMap();
        Long count = contestService.count(conditionMap);
        PageInfo pageInfo = PageInfo.buildPageInfo(count, 1L, 15L, null);
        Assert.assertEquals(1, contestService.getContestList(conditionMap, pageInfo).size());
    }

    @Test
    public void test3() {
        ContestCondition condition = new ContestCondition();
        condition.isVisible = false;
        Map<String, Object> conditionMap = condition.toConditionMap();
        Long count = contestService.count(conditionMap);
        Assert.assertEquals(count, (Long)1L);
    }

    @Test
    public void test4() {

    }
}
