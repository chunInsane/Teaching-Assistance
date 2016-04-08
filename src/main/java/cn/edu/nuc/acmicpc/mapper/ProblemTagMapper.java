package cn.edu.nuc.acmicpc.mapper;

import cn.edu.nuc.acmicpc.model.ProblemTag;

import java.util.List;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/7
 * Problem tag mapper.
 */
public interface ProblemTagMapper {

    /**
     * Add new problemTag record.
     * @param problemTag
     * @return
     */
    public Long addProblemTag(ProblemTag problemTag);

    /**
     * Get problemTag by problem id.
     * @param problemId
     * @return
     */
        public List<ProblemTag> getProblemTagsByProblemId(Long problemId);

    /**
     * Delete problemTag by problem id.
     * @param problemId
     */
    public void deleteProblemTag(Long problemId);
}
