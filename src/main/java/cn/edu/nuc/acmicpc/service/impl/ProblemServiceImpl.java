package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.dto.ProblemDto;
import cn.edu.nuc.acmicpc.mapper.ProblemMapper;
import cn.edu.nuc.acmicpc.service.ProblemService;
import cn.edu.nuc.acmicpc.web.common.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * Problem service implement.
 */
@Service("problemService")
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public List<Integer> getAllProblemIds(boolean isVisible) {
        return null;
    }

    @Override
    public ProblemDto getProblemDtoByProblemId(Long problemId) {
        return null;
    }

    @Override
    public Integer count(Map<String, Object> condition) {
        return null;
    }

    @Override
    public List<ProblemDto> getProblemDtoList(Map<String, Object> condition, PageInfo pageInfo) {
        return null;
    }

    @Override
    public void operator(List<Long> ids, Map<String, Object> values) {

    }

    @Override
    public List<Object> query(Map<String, Object> values) {
        return null;
    }

    @Override
    public Long createProblem() {
        return null;
    }

    @Override
    public List<ProblemDto> createProblems(List<ProblemDto> problemDtos) {
        return null;
    }

    @Override
    public void updateProblem(ProblemDto problemDto) {

    }

    @Override
    public Boolean checkProblemExists(Long problemId) {
        return null;
    }

    @Override
    public void updateProblemByProblemId(Long problemId, Map<String, Object> params) {

    }
}
