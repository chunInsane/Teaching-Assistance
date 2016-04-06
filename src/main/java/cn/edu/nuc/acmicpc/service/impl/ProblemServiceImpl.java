package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.common.exception.AppException;
import cn.edu.nuc.acmicpc.dto.ProblemDto;
import cn.edu.nuc.acmicpc.mapper.ProblemMapper;
import cn.edu.nuc.acmicpc.model.Problem;
import cn.edu.nuc.acmicpc.service.ProblemService;
import cn.edu.nuc.acmicpc.web.common.PageInfo;
import static com.google.common.base.Preconditions.checkNotNull;
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
    public List<Long> getAllProblemIds(boolean isVisible) {
        return problemMapper.getAllProblemIds(isVisible);
    }

    @Override
    public ProblemDto getProblemDtoByProblemId(Long problemId) {
        return problemMapper.getProblemDtoByProblemId(checkNotNull(problemId));
    }

    @Override
    public Long count(Map<String, Object> condition) {
        return problemMapper.count(checkNotNull(condition));
    }

    @Override
    public List<ProblemDto> getProblemDtoList(Map<String, Object> condition, PageInfo pageInfo) {
        checkNotNull(condition).put("firstNo", pageInfo.getFirstNo());
        condition.put("pageSize", pageInfo.getCountPerPage());
        return problemMapper.getProblemDtoList(condition);
    }

    @Override
    public void operator(List<Long> ids, Map<String, Object> values) {
        //TODO
    }

    @Override
    public List<Object> query(Map<String, Object> values) {
        //TODO
        return null;
    }

    @Override
    public Long createProblem() {
        ProblemDto problemDto = new ProblemDto();
        problemDto.setProblemId(null);
        problemDto.setTitle("");
        problemDto.setDescription("");
        problemDto.setInput("");
        problemDto.setOutput("");
        problemDto.setSampleInput("");
        problemDto.setSampleOutput("");
        problemDto.setHint("");
        problemDto.setSource("");
        problemDto.setTimeLimit(1000);
        problemDto.setMemoryLimit(65535);
        problemDto.setSolved(0);
        problemDto.setTried(0);
        problemDto.setIsSpj(false);
        problemDto.setIsVisible(false);
        problemDto.setOutputLimit(8192);
        problemDto.setJavaTimeLimit(3000);
        problemDto.setJavaMemoryLimit(65535);
        problemDto.setDataCount(0);
        problemDto.setDifficulty(1);
        return problemMapper.createProblem(problemDto);
    }

    @Override
    public List<ProblemDto> createProblems(List<ProblemDto> problemDtos) {
        for (ProblemDto problemDto : problemDtos) {
            if (problemDto.getProblemId() != null) {
                if (this.checkProblemExists(problemDto.getProblemId())) {
                    throw new AppException("不存在该问题!");
                }
            } else {
                Long problemId = this.createProblem();
                problemDto.setProblemId(problemId);
                updateProblem(problemDto);
            }
        }
        return problemDtos;
    }

    @Override
    public void updateProblem(ProblemDto problemDto) {
        problemMapper.updateProblem(problemDto);
    }

    @Override
    public Boolean checkProblemExists(Long problemId) {
        return problemMapper.checkProblemExists(problemId) > 0;
    }

    @Override
    public void updateProblemByProblemId(Long problemId, Map<String, Object> params) {

    }

}
