package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.dto.LanguageDto;
import cn.edu.nuc.acmicpc.mapper.LanguageMapper;
import cn.edu.nuc.acmicpc.service.LanguageService;
import static com.google.common.base.Preconditions.checkNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * Language service implement.
 */
@Service("languageService")
@Transactional(rollbackFor = Exception.class)
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageMapper languageMapper;

    @Override
    public Long createLanguage(LanguageDto languageDto) {
        languageMapper.createLanguage(checkNotNull(languageDto));
        return languageDto.getLanguageId();
    }

    @Override
    public List<LanguageDto> getLanguageList() {
        return languageMapper.getLanguageList();
    }

    @Override
    public String getExtension(Long languageId) {
        return languageMapper.getExtension(checkNotNull(languageId));
    }

    @Override
    public String getLanguageName(Long languageId) {
        return languageMapper.getLanguageName(checkNotNull(languageId));
    }
}
