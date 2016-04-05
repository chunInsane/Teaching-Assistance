package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.dto.SettingDto;
import cn.edu.nuc.acmicpc.mapper.SettingMapper;
import cn.edu.nuc.acmicpc.service.SettingService;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * Setting service implement.
 */
@Service("settingService")
public class SettingServiceImpl implements SettingService {

    @Autowired
    private SettingMapper settingMapper;

    public SettingMapper getSettingMapper() {
        return settingMapper;
    }

    public void setSettingMapper(SettingMapper settingMapper) {
        this.settingMapper = settingMapper;
    }

    @Override
    public SettingDto getSettingDto(Long settingId) {
        return settingMapper.getSettingDto(checkNotNull(settingId));
    }

    @Override
    public void updateSetting(SettingDto settingDto) {
        checkArgument(checkNotNull(settingDto).getSettingId() != null, "settingId为空!");
        settingMapper.updateSetting(settingDto);
    }
}
