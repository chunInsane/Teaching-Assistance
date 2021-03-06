package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.dto.SettingDto;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * Setting service interface
 */
public interface SettingService {

    /**
     * Get settings detail by settings id.
     * @param settingId
     * @return
     */
    public SettingDto getSettingDto(Long settingId);

    /**
     * Update setting.
     * @param settingDto
     */
    public void updateSetting(SettingDto settingDto);
}
