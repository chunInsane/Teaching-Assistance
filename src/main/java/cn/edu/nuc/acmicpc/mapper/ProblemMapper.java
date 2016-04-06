package cn.edu.nuc.acmicpc.mapper;

import cn.edu.nuc.acmicpc.dto.ProblemDto;
import cn.edu.nuc.acmicpc.model.Problem;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * Problem mapper.
 */
public interface ProblemMapper {

    /**
     * Gets all problems' id according to the parameters.
     * @param isVisible
     * @return
     */
    public List<Long> getAllProblemIds(Boolean isVisible);

    /**
     * Get ProblemDto entity by problem's ID.
     * @param problemId
     * @return
     */
    public ProblemDto getProblemDtoByProblemId(Long problemId);

    /**
     * Count the number of problem fit in condition
     * @param condition
     * @return
     */
    public Long count(Map<String, Object> condition);

    /**
     * Get all problem fit in condition.
     * @param condition
     * @return
     */
    public List<ProblemDto> getProblemDtoList(Map<String, Object> condition);

    /**
     * Create new problem record.
     * @param problemDto
     * @return
     */
    public Long createProblem(ProblemDto problemDto);

    /**
     * Update problem information.
     * @param problemDto
     */
    public void updateProblem(ProblemDto problemDto);

    /**
     * Count the number of problem.
     * @param problemId
     * @return
     */
    public Long checkProblemExists(Long problemId);
}
