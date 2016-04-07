package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.common.enums.AuthenticationType;
import cn.edu.nuc.acmicpc.common.enums.JudgeResultType;
import cn.edu.nuc.acmicpc.dto.StatusDto;
import cn.edu.nuc.acmicpc.mapper.StatusMapper;
import cn.edu.nuc.acmicpc.service.StatusService;
import static com.google.common.base.Preconditions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
@Transactional(rollbackFor = Exception.class)
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
        Map<String, Object> params = new HashMap<>();
        if (isFirstTime) {
            params.put("result", JudgeResultType.JUDGE_WAIT.ordinal());
        } else {
            List<Integer> results = new ArrayList<>();
            results.add(JudgeResultType.JUDGE_WAIT.ordinal());
            results.add(JudgeResultType.JUDGE_JUDGING.ordinal());
            params.put("results", results);
        }
        return statusMapper.getStatusList(params);
    }

    @Override
    public void updateStatus(StatusDto statusDto) {
        checkNotNull(statusDto);
        checkArgument(statusDto.getStatusId() != null);
        statusMapper.updateStatus(statusDto);
    }

    @Override
    public Long createStatus(StatusDto statusDto) {
        return statusMapper.createStatus(checkNotNull(statusDto));
    }

    private List<Long> findAllProblemIdsThatUser(Boolean solved, Long userId, Boolean isAdmin) {
        Map<String, Object> params = new HashMap<>();
        if (solved) {
            params.put("resultType", JudgeResultType.JUDGE_AC.ordinal());
        }
        if (!isAdmin) {
            params.put("adminType", AuthenticationType.ADMIN.ordinal());
        }
        params.put("userId", userId);
        return statusMapper.findAllProblemIdsThatUser(params);
    }

    private Long countProblemsThatUser(boolean solved, Long userId, boolean isAdmin) {
        Map<String, Object> params = new HashMap<>();
        if (solved) {
            params.put("resultType", JudgeResultType.JUDGE_AC.ordinal());
        }
        if (!isAdmin) {
            params.put("adminType", AuthenticationType.ADMIN.ordinal());
        }
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
