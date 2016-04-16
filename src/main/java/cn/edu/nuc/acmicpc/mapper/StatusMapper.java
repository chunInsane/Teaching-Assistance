package cn.edu.nuc.acmicpc.mapper;

import cn.edu.nuc.acmicpc.dto.StatusDto;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * Status mapper.
 */
public interface StatusMapper {

    /**
     * Get problem ids
     * @param params
     * @return
     */
    public Long countProblems(Map<String, Object> params);

    /**
     * Get user ids
     * @param params
     * @return
     */
    public Long countUsers(Map<String, Object> params);

    /**
     * Get problem ids
     * @param params
     * @return
     */
    public List<Long> findAllProblemIdsThatUser(Map<String, Object> params);

    /**
     * Create new status record
     * @param status
     * @return
     */
    public Long createStatus(StatusDto status);

    /**
     * Get Statuses
     * @param params
     * @return
     */
    public List<StatusDto> getStatusList(Map<String, Object> params);

    /**
     * Update status information
     * @param statusDto
     */
    public void updateStatus(StatusDto statusDto);

    /**
     * Get the number of status fit in condition.
     * @param condition
     * @return
     */
    public Long count(Map<String, Object> condition);

    /**
     * Get these status fit in condition and page range.
     * @param condition
     * @return
     */
    public List<StatusDto> getShowStatusList(Map<String, Object> condition);
}
