package cn.edu.nuc.acmicpc.mapper;

import cn.edu.nuc.acmicpc.dto.ContestUserDto;

import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * Contest user mapper.
 */
public interface ContestUserMapper {

    /**
     * create new contest user record
     * @param contestUserDto
     * @return
     */
    public Long createContestUser(ContestUserDto contestUserDto);

    /**
     * Remove all contest users by contest id
     * @param contestId
     */
    public void removeContestUsersByContestId(Long contestId);

    /**
     * Check whether a user can register specific contest.
     * @param params
     * @return
     */
    public Long checkUserCanRegisterInContest(Map<String, Object> params);
}
