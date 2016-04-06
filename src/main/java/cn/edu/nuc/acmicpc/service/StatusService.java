package cn.edu.nuc.acmicpc.service;


import cn.edu.nuc.acmicpc.dto.StatusDto;

import java.util.List;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * status service interface
 */
public interface StatusService {

    /**
     * list user's tried problem id
     * @param userId
     * @param isAdmin
     * @return
     */
    public List<Long> findAllProblemIdsThatUserTried(Long userId, boolean isAdmin);

    /**
     * list user's accepted problem id
     * @param userId
     * @param isAdmin
     * @return
     */
    public List<Long> findAllProblemIdsThatUserSolved(Long userId, boolean isAdmin);

    /**
     * count user's tried visible normal problems
     * @param userId
     * @param isAdmin
     * @return
     */
    public Long countProblemsThatUserTried(Long userId, boolean isAdmin);

    /**
     * count user's solved visible normal problems
     * @param userId
     * @param isAdmin
     * @return
     */
    public Long countProblemsThatUserSolved(Long userId, boolean isAdmin);

    /**
     * count users that tried specified problem
     * @param problemId
     * @return
     */
    public Long countUsersThatTriedProblem(Long problemId);

    /**
     * count users that solve specified problem
     * @param problemId
     * @return
     */
    public Long countUserThatSolvedProblem(Long problemId);

    /**
     * Get the status that pending to judge
     * @param isFirstTime
     * @return
     */
    public List<StatusDto> getQueuingStatus(boolean isFirstTime);

    /**
     * updates status
     * @param status
     */
    public void updateStatus(StatusDto status);

    /**
     * create a new status
     * @param status
     * @return
     */
    public Long createStatus(StatusDto status);
}
