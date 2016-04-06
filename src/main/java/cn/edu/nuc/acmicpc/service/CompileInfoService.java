package cn.edu.nuc.acmicpc.service;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/9
 * Compile information service interface.
 */
public interface CompileInfoService {

    /**
     * Create a new compile info record.
     * @param content
     * @return
     */
    public Long createCompileInfo(String content);

    /**
     * Get compile info by compile info id.
     * @param compileInfoId
     * @return
     */
    public String getCompileInfo(Long compileInfoId);

    /**
     * Update compile info.
     * @param compileInfoId
     * @param content
     */
    public void updateCompileInfoContent(Long compileInfoId, String content);

}
