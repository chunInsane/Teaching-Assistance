package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.dto.ContestProblemDto;
import cn.edu.nuc.acmicpc.dto.contest.ContestProblemDetailDto;

import java.util.List;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/13
 * Contest problem service interface.
 */
public interface ContestProblemService {

    /**
     * Get contestProblemDto by contestProblem id.
     * @param contestProblemId
     * @return
     */
    public ContestProblemDto getContestProblemDto(Long contestProblemId);

    /**
     * Get contestProblemDetailDto by contest id.
     * @param contestId
     * @return
     */
    public List<ContestProblemDetailDto> getContestProblemDetailDtoByContestId(Long contestId);

    /**
     * Remove contest problems by contest id.
     * @param contestId
     */
    public void removeContestProblemByContestId(Long contestId);

    /**
     * Create a new record by contestProblemDto entity
     * @param contestProblemDto
     * @return
     */
    public Long createContestProblem(ContestProblemDto contestProblemDto);

    /**
     * Check whether exist one problem in specified contest.
     * @param problemId
     * @param contestId
     * @return
     */
    public Boolean isExistContestProblem(Long problemId, Long contestId);
}
