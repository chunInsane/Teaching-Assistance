package cn.edu.nuc.acmicpc.mapper;

import cn.edu.nuc.acmicpc.model.CompileInfo;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/2
 * Compile information mapper.
 */
public interface CompileInfoMapper {

    /**
     * Create a new compile info record.
     *
     * @param content
     * @return
     */
    public Long createCompileInfo(String content);

    /**
     * Get compile info by compile info id.
     *
     * @param compileInfoId
     * @return
     */
    public String getCompileInfo(Long compileInfoId);

    /**
     * Update compile info.
     *
     * @param compileInfo
     */
    public void updateCompileInfoContent(CompileInfo compileInfo);
}
