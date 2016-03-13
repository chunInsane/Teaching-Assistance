package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.dto.CodeDto;

import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/13
 * Code service interface
 */
public interface CodeService {

    /**
     * Get codeDto by code id
     * @param codeId
     * @return
     */
    public CodeDto getCodeDtoByCodeId(Integer codeId);

    /**
     * Create a new code entity by codeDto
     * @param codeDto
     * @return
     */
    public Long createNewCode(CodeDto codeDto);

}
