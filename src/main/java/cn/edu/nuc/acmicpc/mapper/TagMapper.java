package cn.edu.nuc.acmicpc.mapper;

import cn.edu.nuc.acmicpc.model.Tag;

import java.util.List;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/7
 * Tag mapper.
 */
public interface TagMapper {

    /**
     * Add new tag record.
     * @param tag
     * @return
     */
    public Long addTag(Tag tag);

    /**
     * Get tag record by tag id.
     * @param tagId
     * @return
     */
    public Tag getTagByTagId(Long tagId);

    /**
     * Delete specific tag.
     * @param tagId
     */
    public void deleteTag(Long tagId);

    /**
     * Get all tag.
     * @return
     */
    public List<Tag> getTags();
}
