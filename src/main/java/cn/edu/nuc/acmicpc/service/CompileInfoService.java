package cn.edu.nuc.acmicpc.service;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/9
 * Compile information service
 */
public interface CompileInfoService {

    /**
     * Get compile info by compile info id.
     *
     * @param compileInfoId
     * @return
     */
    public String getCompileInfo(Integer compileInfoId);

    /**
     * Update compile info.
     *
     * @param compileInfoId
     * @param content
     */
    public void updateCompileInfoContent(Long compileInfoId, String content);

    /**
     * Create a new compile info record.
     *
     * @param content
     * @return
     */
    public Long createCompileInfo(String content);
}
