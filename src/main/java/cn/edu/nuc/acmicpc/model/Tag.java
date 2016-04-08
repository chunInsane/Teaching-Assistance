package cn.edu.nuc.acmicpc.model;

import java.io.Serializable;

/**
 * problem tag information
 */
public class Tag implements Serializable {

    private Long tagId;
    private String name;

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", name='" + name + '\'' +
                '}';
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTagId() {
        return tagId;
    }

    public String getName() {
        return name;
    }
}
