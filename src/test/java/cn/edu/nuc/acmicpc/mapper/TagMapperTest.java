package cn.edu.nuc.acmicpc.mapper;

import cn.edu.nuc.acmicpc.common.BasicTest;
import cn.edu.nuc.acmicpc.model.Tag;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/7
 */
public class TagMapperTest extends BasicTest {

    @Autowired
    private TagMapper tagMapper;

    @Test
    public void testAddTag() {
        Tag tag = new Tag();
        tag.setName("动态规划");
        tagMapper.addTag(tag);
    }

    @Test
    public void testGetTagByTagId() {
        Tag tag = tagMapper.getTagByTagId(3L);
        Assert.assertEquals((Long) 3l, tag.getTagId());
    }

    @Test
    public void testGetTags() {
        List<Tag> tagList = tagMapper.getTags();
        Assert.assertEquals(3, tagList.size());
    }

    @Test
    public void testDeleteTag() {
        tagMapper.deleteTag(4L);
    }
}

