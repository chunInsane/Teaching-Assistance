package cn.edu.nuc.acmicpc.model;

import java.io.Serializable;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * project setting
 */
public class Setting implements Serializable {

    private Integer settingId;
    private String name;
    private String description;
    private String value;

    @Override
    public String toString() {
        return "Setting{" +
                "settingId=" + settingId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
