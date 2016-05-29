package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.common.util.FileUtil;
import org.junit.Test;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/5/8
 */
public class FileUtilTest {

    @Test
    public void testSaveToFile() {
        FileUtil.saveToFile("hello", "/Users/zhangliang/Documents/workspace/oj/work/judge3/temp//Main.c");
    }
}
