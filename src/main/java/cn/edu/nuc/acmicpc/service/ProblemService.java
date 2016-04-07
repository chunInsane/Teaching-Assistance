package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.dto.ProblemDto;
import cn.edu.nuc.acmicpc.dto.ProblemListDto;
import cn.edu.nuc.acmicpc.web.common.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * Problem service interface
 */
public interface ProblemService {

    /**
     * Gets all problems' id according to the parameters.
     * @param isVisible
     * @return
     */
    public List<Long> getAllProblemIds(boolean isVisible);

    /**
     * Get ProblemDto entity by problem's ID.
     * @param problemId
     * @return
     */
    public ProblemDto getProblemDtoByProblemId(Long problemId);

    /**
     * Counts the number of problems fit in condition
     * @param condition
     * @return
     */
    public Long count(Map<String, Object> condition);

    /**
     * Get the problems fit in the condition and page range
     * @param condition
     * @param pageInfo
     * @return
     */
     public List<ProblemListDto> getProblemListDtos(Map<String, Object> condition, PageInfo pageInfo);

    /**
     * Modify one field of multiply entities as value.
     * @param ids
     * @param values
     * @throws
     */
    public void operator(List<Long> ids, Map<String, Object> values);

    /**
     * Query one field of multiply entities.
     * @param values
     * @return
     */
    public List<Object> query(Map<String, Object> values);

    /**
     * Creates a new problem record.
     * @return the new problem's id.
     */
    public Long createProblem();

    /**
     * Create problems by problemDtos.
     * @param problemDtos
     * @return
     */
    public List<ProblemDto> createProblems(List<ProblemDto> problemDtos);

    /**
     * update problem record
     * @param problemDto
     */
    public void updateProblem(ProblemDto problemDto);


    /**
     * Check whether a problem exists.
     * @param problemId
     * @return true if this problem exists.
     */
    public Boolean checkProblemExists(Long problemId);

    /**
     * update problem information by problem id
     * @param problemId
     * @param params
     */
    public void updateProblemByProblemId(Long problemId, Map<String, Object> params);

}
