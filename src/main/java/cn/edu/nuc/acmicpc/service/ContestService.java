package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.dto.ContestDto;
import cn.edu.nuc.acmicpc.web.common.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/13
 * Contest Service interface
 */
public interface ContestService {

    /**
     * Create a new contest record
     * @return
     */
    public Long createContest();

    /**
     * Get all visible contest's id
     * @return
     */
    public List<Long> getAllisVisibleContestIds();

    /**
     * Get contestDto entity by contest id
     * @param contestId
     * @return
     */
    public ContestDto getContestDtoByContestId(Long contestId);

    /**
     * Check specified contest exists
     * @param contestId
     * @return
     */
    public Boolean isExistsContest(Long contestId);

    /**
     * Update contest record
     * @param contestDto
     */
    public void updateContest(ContestDto contestDto);

    /**
     * Count the number of contests fit in condition
     * @param conditions
     * @return
     */
    public Long count(Map<String, Object> conditions);

    /**
     * Get the contests fit in condition and page range
     * @param conditions
     * @param pageInfo
     * @return
     */
    public List<ContestDto> getContestList(Map<String, Object> conditions, PageInfo pageInfo);

    /**
     * Check whether a user can register specific contest.
     * @param userId
     * @param contestId
     * @return
     */
    public Boolean checkUserCanRegisterInContest(Long userId, Long contestId);

}
