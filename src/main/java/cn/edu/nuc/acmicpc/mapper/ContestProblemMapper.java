package cn.edu.nuc.acmicpc.mapper;

import cn.edu.nuc.acmicpc.dto.ContestProblemDto;
import cn.edu.nuc.acmicpc.dto.contest.ContestProblemDetailDto;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * Contest problem mapper.
 */
public interface ContestProblemMapper {

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
     * @param params
     * @return
     */
    public Long isExistContestProblem(Map<String, Object> params);
}
