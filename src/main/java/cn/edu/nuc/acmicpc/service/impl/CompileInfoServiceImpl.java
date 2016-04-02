package cn.edu.nuc.acmicpc.service.impl;

import cn.edu.nuc.acmicpc.mapper.CompileInfoMapper;
import cn.edu.nuc.acmicpc.model.CompileInfo;
import cn.edu.nuc.acmicpc.service.CompileInfoService;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * CompileInfo service implement.
 */
@Service("compileInfoService")
public class CompileInfoServiceImpl implements CompileInfoService {

    @Autowired
    private CompileInfoMapper compileInfoMapper;

    @Override
    public Long createCompileInfo(String content) {
        checkArgument(StringUtils.isNotBlank(content), "content为空!");
        return compileInfoMapper.createCompileInfo(content);
    }

    @Override
    public String getCompileInfo(Long compileInfoId) {
        return compileInfoMapper.getCompileInfo(checkNotNull(compileInfoId));
    }

    @Override
    public void updateCompileInfoContent(Long compileInfoId, String content) {
        CompileInfo compileInfo = new CompileInfo();
        compileInfo.setCompileInfoId(checkNotNull(compileInfoId));
        compileInfo.setContent(content);
        compileInfoMapper.updateCompileInfoContent(compileInfo);
    }
}
