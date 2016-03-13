package cn.edu.nuc.acmicpc.mapper;

import cn.edu.nuc.acmicpc.model.Code;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/13
 */
public interface CodeMapper {

    public Code getCodeByCodeId(Integer codeId);

    public Long createNewCode(Code code);

}
