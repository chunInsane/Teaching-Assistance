package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.dto.CodeDto;
import cn.edu.nuc.acmicpc.model.Code;

import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/13
 * Code service interface
 */
public interface CodeService {

    /**
     * Get code by code id
     * @param codeId
     * @return
     */
    public Code getCodeByCodeId(Long codeId);

    /**
     * Create a new code entity by codeDto
     * @param code
     * @return
     */
    public Long createCode(Code code);

}
