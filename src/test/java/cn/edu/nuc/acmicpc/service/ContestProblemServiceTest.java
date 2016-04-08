package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.common.BasicTest;
import cn.edu.nuc.acmicpc.dto.ContestProblemDto;
import cn.edu.nuc.acmicpc.dto.contest.ContestProblemDetailDto;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/8
 */
public class ContestProblemServiceTest extends BasicTest {

    @Autowired
    private ContestProblemService contestProblemService;

    @Test
    public void test1() {
        Assert.assertFalse(contestProblemService.isExistContestProblem(1L, 1L));
    }

    @Test
    public void test2() {
        ContestProblemDto contestProblem = new ContestProblemDto();
        contestProblem.setProblemId(1L);
        contestProblem.setContestId(1L);
        contestProblem.setOrder(1);
        contestProblemService.createContestProblem(contestProblem);
        Assert.assertTrue(contestProblemService.isExistContestProblem(1L, 1L));
    }

    @Test
    public void test3() {
        Assert.assertNotNull(contestProblemService.getContestProblemDto(1L));
    }

    @Test
    public void test4() {
        ContestProblemDto contestProblemDto = new ContestProblemDto();
        contestProblemDto.setContestId(2L);
        contestProblemDto.setProblemId(1L);
        contestProblemDto.setOrder(2);
        contestProblemService.createContestProblem(contestProblemDto);
        contestProblemService.removeContestProblemByContestId(2L);
        Assert.assertFalse(contestProblemService.isExistContestProblem(1L, 2L));
    }

    @Test
    public void test5() {
        List<ContestProblemDetailDto> contestProblemDetailDtoList = contestProblemService.getContestProblemDetailDtoByContestId(1L);
        for (ContestProblemDetailDto detailDto : contestProblemDetailDtoList) {
            System.out.println(detailDto.toString());
        }
        Assert.assertNotNull(contestProblemDetailDtoList);
    }
}
