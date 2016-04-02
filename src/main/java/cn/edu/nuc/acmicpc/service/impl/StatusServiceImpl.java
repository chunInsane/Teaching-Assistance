package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.dto.StatusDto;
import cn.edu.nuc.acmicpc.mapper.StatusMapper;
import cn.edu.nuc.acmicpc.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }

    @Override
    public List<Long> findAllProblemIdsThatUserSolved(Long userId, boolean isAdmin) {
        return null;
    }

    @Override
    public Long countProblemsThatUserTried(Long userId, boolean isAdmin) {
        return null;
    }

    @Override
    public Long countProblemsThatUserSolved(Long userId, boolean isAdmin) {
        return null;
    }

    @Override
    public Long countUsersThatTriedProblem(Long problemId) {
        return null;
    }

    @Override
    public Long countUserThatSolvedProblem(Long problemId) {
        return null;
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
}
