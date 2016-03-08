package cn.edu.nuc.acmicpc.model;

import java.io.Serializable;

/**
 * problem tag information
 */
public class Tag implements Serializable {

    private Integer tagId;
    private String name;

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", name='" + name + '\'' +
                '}';
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTagId() {

        return tagId;
    }

    public String getName() {
        return name;
    }
}
