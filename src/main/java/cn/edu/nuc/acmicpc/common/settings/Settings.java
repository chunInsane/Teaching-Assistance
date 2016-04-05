package cn.edu.nuc.acmicpc.common.settings;

import cn.edu.nuc.acmicpc.common.exception.AppException;
import cn.edu.nuc.acmicpc.common.settings.entity.EmailSetting;
import cn.edu.nuc.acmicpc.common.settings.entity.JudgeSetting;
import cn.edu.nuc.acmicpc.common.util.FileUtil;
import cn.edu.nuc.acmicpc.dto.SettingDto;
import cn.edu.nuc.acmicpc.service.SettingService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * Global settings
 */
@Repository
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Lazy(false)
@PropertySource("classpath:resources.properties")
public class Settings {

    private static final Logger LOGGER = LoggerFactory.getLogger(Settings.class);

    @Autowired
    private Environment environment;

    @Autowired
    private SettingService settingService;

    /**host*/
    public String HOST;
    /**global encoding*/
    public String ENCODING;
    /**upload files store directory*/
    public String UPLOAD_FOLDER;
    /**picture files store directory*/
    public String PICTURE_FOLDER;
    /**judge core's name*/
    public String JUDGE_CORE;
    /**data directory*/
    public String DATA_PATH;
    /**work directory*/
    public String WORK_PATH;
    /**judge list*/
    public List<JudgeSetting> JUDGES;
    /**email setting*/
    public EmailSetting EMAIL_SETTING;
    /**record count in list*/
    public Long RECORD_PER_PAGE;

    /**
     * initialize settings
     */
    @PostConstruct
    public void init() {
        try {
            //loading from db
            HOST = getStringValueSettingByType(SettingType.HOST);
            ENCODING = getStringValueSettingByType(SettingType.ENCODING);
            RECORD_PER_PAGE = getLongValueSettingByType(SettingType.RECORD_PER_PAGE);
            JUDGE_CORE = getStringValueSettingByType(SettingType.JUDGE_CORE);
            JUDGES = JSON.parseArray(getStringValueSettingByType(SettingType.JUDGES), JudgeSetting.class);
            EMAIL_SETTING = JSON.parseObject(getStringValueSettingByType(SettingType.EMAIL), EmailSetting.class);

            //loading from resources.properties
            PICTURE_FOLDER = environment.getProperty("images.path") + "/";
            DATA_PATH = environment.getProperty("data.path") + "/";
            UPLOAD_FOLDER = environment.getProperty("upload.path") + "/";
            WORK_PATH = environment.getProperty("judge.workPath") + "/";

            //initialize folders
            FileUtil.createDirectoryIfNotExists(PICTURE_FOLDER);
            FileUtil.createDirectoryIfNotExists(DATA_PATH);
            FileUtil.createDirectoryIfNotExists(UPLOAD_FOLDER);
            FileUtil.createDirectoryIfNotExists(WORK_PATH);

        } catch (AppException e) {
            LOGGER.error("initialize settings fail!", e);
        }
    }

    private String getStringValueSettingByType(SettingType settingType) {
        SettingDto settingDto = settingService.getSettingDto(settingType.getTypeId());
        return settingDto.getValue();
    }

    private Long getLongValueSettingByType(SettingType settingType) {
        SettingDto settingDto = settingService.getSettingDto(settingType.getTypeId());
        return Long.valueOf(settingDto.getValue());
    }

}
