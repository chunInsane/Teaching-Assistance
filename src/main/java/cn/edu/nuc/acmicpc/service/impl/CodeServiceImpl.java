package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.dto.CodeDto;
import cn.edu.nuc.acmicpc.mapper.CodeMapper;
import cn.edu.nuc.acmicpc.model.Code;
import cn.edu.nuc.acmicpc.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/13
 */
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeMapper codeMapper;

    @Override
    public CodeDto getCodeDtoByCodeId(Integer codeId) {
        Code code = codeMapper.getCodeByCodeId(codeId);
        CodeDto codeDto = new CodeDto(code);
        return codeDto;
    }

    @Override
    public Long createNewCode(CodeDto codeDto) {
        Code code = new Code(codeDto);
        return codeMapper.createNewCode(code);
    }
}
