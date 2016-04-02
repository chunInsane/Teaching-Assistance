package cn.edu.nuc.acmicpc.mapper;

import cn.edu.nuc.acmicpc.form.dto.other.SettingDto;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * Setting mapper.
 */
public interface SettingMapper {

    /**
     * Get settings detail by settings name
     * @param settingId
     * @return
     */
    SettingDto getSettingDto(Long settingId);

    /**
     * update setting
     * @param settingDto
     */
    void updateSetting(SettingDto settingDto);
}
