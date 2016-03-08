package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.dto.other.SettingDto;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 */
public interface SettingService {

    /**
     * Get settings detail by settings name
     * @param settingId
     * @return
     */
    SettingDto getSettingDto(Integer settingId);

    /**
     * update setting
     * @param settingDto
     */
    void updateSetting(SettingDto settingDto);
}
