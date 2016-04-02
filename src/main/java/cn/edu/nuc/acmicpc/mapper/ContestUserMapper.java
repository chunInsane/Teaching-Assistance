package cn.edu.nuc.acmicpc.mapper;

import cn.edu.nuc.acmicpc.dto.ContestUserDto;

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
}
