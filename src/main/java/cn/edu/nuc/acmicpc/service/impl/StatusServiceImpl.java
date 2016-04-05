package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.common.enums.JudgeResultType;
import cn.edu.nuc.acmicpc.dto.StatusDto;
import cn.edu.nuc.acmicpc.mapper.StatusMapper;
import cn.edu.nuc.acmicpc.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * Status service implement.
 */
@Service("statusService")
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusMapper statusMapper;

    @Override
    public List<Long> findAllProblemIdsThatUserTried(Long userId, boolean isAdmin) {
        return findAllProblemIdsThatUser(false, userId, isAdmin);
    }

    @Override
    public List<Long> findAllProblemIdsThatUserSolved(Long userId, boolean isAdmin) {
        return findAllProblemIdsThatUser(true, userId, isAdmin);
    }

    @Override
    public Long countProblemsThatUserTried(Long userId, boolean isAdmin) {
        return countProblemsThatUser(false, userId, isAdmin);
    }

    @Override
    public Long countProblemsThatUserSolved(Long userId, boolean isAdmin) {
        return countProblemsThatUser(true, userId, isAdmin);
    }

    @Override
    public Long countUsersThatTriedProblem(Long problemId) {
        return countUserThatProblem(false, problemId);
    }

    @Override
    public Long countUserThatSolvedProblem(Long problemId) {
        return countUserThatProblem(true, problemId);
    }

    @Override
    public List<StatusDto> getQueuingStatus(boolean isFirstTime) {
        return null;
    }

    @Override
    public void updateStatus(StatusDto status) {

    }

    @Override
    public StatusDto createStatus(StatusDto status) {
        return null;
    }

    private List<Long> findAllProblemIdsThatUser(Boolean solved, Long userId, Boolean isAdmin) {
        Map<String, Object> params = new HashMap<>();
        if (solved) {
            params.put("resultType", JudgeResultType.JUDGE_AC.ordinal());
        }
        params.put("isAdmin", isAdmin);
        params.put("userId", userId);
        return statusMapper.getProblemIds(params);
    }

    private Long countProblemsThatUser(boolean solved, Long userId, boolean isAdmin) {
        Map<String, Object> params = new HashMap<>();
        if (solved) {
            params.put("resultType", JudgeResultType.JUDGE_AC.ordinal());
        }
        params.put("isAdmin", isAdmin);
        params.put("userId", userId);
        return statusMapper.countProblems(params);
    }

    private Long countUserThatProblem(boolean solve, Long problemId) {
        Map<String, Object> params = new HashMap<>();
        if (solve) {
            params.put("resultType", JudgeResultType.JUDGE_AC.ordinal());
        }
        params.put("problemId", problemId);
        return statusMapper.countUsers(params);
    }
}
