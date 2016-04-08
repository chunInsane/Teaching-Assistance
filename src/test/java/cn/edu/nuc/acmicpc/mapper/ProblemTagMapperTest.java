package cn.edu.nuc.acmicpc.mapper;

import cn.edu.nuc.acmicpc.common.BasicTest;
import cn.edu.nuc.acmicpc.model.Problem;
import cn.edu.nuc.acmicpc.model.ProblemTag;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/7
 */
public class ProblemTagMapperTest extends BasicTest {

    @Autowired
    private ProblemTagMapper problemTagMapper;

    @Test
    public void testAddProblemTag() {
        ProblemTag problemTag = new ProblemTag();
        problemTag.setProblemId(1L);
        problemTag.setTagId(3L);
        problemTagMapper.addProblemTag(problemTag);
    }

    @Test
    public void testDeleteProblemTag() {
        problemTagMapper.deleteProblemTag(1L);
        List<ProblemTag> problemTags = problemTagMapper.getProblemTagsByProblemId(1L);
        Assert.assertEquals(0, problemTags.size());
    }

    @Test
    public void testGetProblemTagsByProblemId() {
        List<ProblemTag> problemTags = problemTagMapper.getProblemTagsByProblemId(1L);
        Assert.assertEquals(0, problemTags.size());
    }
}
