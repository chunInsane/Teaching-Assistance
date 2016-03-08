package cn.edu.nuc.acmicpc.dto.other;

import cn.edu.nuc.acmicpc.dto.BaseDto;
import cn.edu.nuc.acmicpc.model.Setting;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 */
public class SettingDto implements BaseDto<Setting> {

    private Integer settingId;
    private String name;
    private String description;
    private String value;

    @Override
    public String toString() {
        return "SettingDto{" +
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
