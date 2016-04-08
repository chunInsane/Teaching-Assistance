package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.common.BasicTest;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/7
 */
public class CompileInfoServiceTest extends BasicTest {

    @Autowired
    private CompileInfoService compileInfoService;

    @Test
    public void testCreateCompileInfo() {
        String content = "compile error!";
        compileInfoService.createCompileInfo(content);
    }

    @Test
    public void testGetCompileInfo() {
        String compileInfoContent = compileInfoService.getCompileInfo(1L);
        Assert.assertNotNull(compileInfoContent);
    }

    @Test
    public void testUpdateCompileInfoContent() {
        compileInfoService.updateCompileInfoContent(1L, "new compile error!");
    }
}
