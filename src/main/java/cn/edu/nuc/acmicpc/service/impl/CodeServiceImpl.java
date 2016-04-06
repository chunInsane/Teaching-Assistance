package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.dto.CodeDto;
import cn.edu.nuc.acmicpc.mapper.CodeMapper;
import cn.edu.nuc.acmicpc.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.google.common.base.Preconditions.*;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/13
 * Code service implement.
 */
@Service("codeService")
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeMapper codeMapper;

    @Override
    public CodeDto getCodeByCodeId(Long codeId) {
        return codeMapper.getCodeByCodeId(checkNotNull(codeId));
    }

    @Override
    public Long createCode(CodeDto codeDto) {
        return codeMapper.createCode(checkNotNull(codeDto));
    }
}
