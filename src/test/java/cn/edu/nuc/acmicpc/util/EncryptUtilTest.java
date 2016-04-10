package cn.edu.nuc.acmicpc.util;

import cn.edu.nuc.acmicpc.common.util.EncryptUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/4
 */
public class EncryptUtilTest {

    @Test
    public void testEncodeByMd5() {
        String encodeStr = EncryptUtil.encoderByMd5("111111");
        Assert.assertNotNull(encodeStr);
    }

    @Test
    public void test() {
        String originalStr = "111111";
        String encodeStr = EncryptUtil.encoderByMd5(originalStr);
        System.out.println("encodeStr = " + encodeStr);
        Assert.assertTrue(EncryptUtil.checkPassword(originalStr, EncryptUtil.encoderByMd5(originalStr)));
    }

    @Test
    public void test1() {
        String str = "111111";
        System.out.println(EncryptUtil.encoderByMd5(str));
    }
}
