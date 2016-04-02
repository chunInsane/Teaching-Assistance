package cn.edu.nuc.acmicpc.mapper;

import cn.edu.nuc.acmicpc.model.Code;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/13
 * Code mapper.
 */
public interface CodeMapper {

    /**
     * Get code by code id
     * @param codeId
     * @return
     */
    public Code getCodeByCodeId(Long codeId);

    /**
     * Create new code record.
     * @param code
     * @return
     */
    public Long createCode(Code code);

}
