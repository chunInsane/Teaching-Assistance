package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.form.dto.other.SettingDto;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * Setting service interface
 */
public interface SettingService {

    /**
     * Get settings detail by settings name
     *
     * @param settingId
     * @return
     */
    public SettingDto getSettingDto(Long settingId);

    /**
     * update setting
     *
     * @param settingDto
     */
    public void updateSetting(SettingDto settingDto);
}
