package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.dto.ContestUserDto;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/13
 * Contest user service interface
 */
public interface ContestUserService {

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
