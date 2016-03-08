package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.common.exception.AppException;
import cn.edu.nuc.acmicpc.dto.other.SettingDto;
import cn.edu.nuc.acmicpc.mapper.SettingMapper;
import cn.edu.nuc.acmicpc.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 */
@Service
public class SettingServiceImpl implements SettingService {

    @Autowired
    private SettingMapper settingMapper = null;

    @Override
    public SettingDto getSettingDto(Integer settingId) {
        if (settingId == null)
            throw new AppException("settingId is null");
        return settingMapper.getSettingDto(settingId);
    }

    @Override
    public void updateSetting(SettingDto settingDto) {
        if (settingDto == null)
            throw new AppException("settingDto is null");
        settingMapper.updateSetting(settingDto);
    }
}
