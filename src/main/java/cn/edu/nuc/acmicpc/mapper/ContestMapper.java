package cn.edu.nuc.acmicpc.mapper;

import cn.edu.nuc.acmicpc.dto.ContestDto;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/6
 * Contest mapper.
 */
public interface ContestMapper {

    /**
     * Create new contest record
     * @param contestDto
     * @return
     */
    public Long createContest(ContestDto contestDto);

    /**
     * Get all visible contest id
     * @return
     */
    public List<Long> getAllisVisibleContestIds();

    /**
     * Get contestDto by id
     * @param contestId
     * @return
     */
    public ContestDto getContestDtoByContestId(Long contestId);

    /**
     * Get the number of contest
     * @param contestId
     * @return
     */
    public Long isExistsContest(Long contestId);

    /**
     * Update contest information.
     * @param contestDto
     */
    public void updateContest(ContestDto contestDto);

    /**
     * Get the number of contest fit in condition
     * @param condition
     * @return
     */
    public Long count(Map<String, Object> condition);

    /**
     * Get these contest fit in condition
     * @param conditions
     * @return
     */
    public List<ContestDto> getContestList(Map<String, Object> conditions);
}
