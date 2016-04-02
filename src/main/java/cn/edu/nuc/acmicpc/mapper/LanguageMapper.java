package cn.edu.nuc.acmicpc.mapper;

import cn.edu.nuc.acmicpc.dto.LanguageDto;

import java.util.List;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * Language mapper.
 */
public interface LanguageMapper {

    /**
     * Get all languageDto entity
     * @return
     */
    public List<LanguageDto> getLanguageList();

    /**
     * Get language extension by language id
     * @param languageId
     * @return
     */
    public String getExtension(Long languageId);

    /**
     * Get language name by language id
     * @param languageId
     * @return
     */
    public String getLanguageName(Long languageId);
}
