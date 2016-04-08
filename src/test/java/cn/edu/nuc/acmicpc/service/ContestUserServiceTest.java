package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.common.BasicTest;
import cn.edu.nuc.acmicpc.dto.ContestUserDto;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/8
 */
public class ContestUserServiceTest extends BasicTest {

    @Autowired
    private ContestUserService contestUserService;

    @Test
    public void test1() {
        ContestUserDto contestUserDto = new ContestUserDto();
        contestUserDto.setContestId(1L);
        contestUserDto.setUserId(1L);
        contestUserDto.setStatus((byte)1);
        contestUserDto.setComment("我要参加比赛!");
        contestUserService.createContestUser(contestUserDto);
        Assert.assertFalse(contestUserService.checkUserCanRegisterInContest(1L, 1L));
    }

    @Test
    public void test2() {
        Assert.assertFalse(contestUserService.checkUserCanRegisterInContest(1L, 1L));
    }

    @Test
    public void test3() {
        contestUserService.removeContestUsersByContestId(2L);
    }

}
