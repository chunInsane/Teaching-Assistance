package cn.edu.nuc.acmicpc.common;

import cn.edu.nuc.acmicpc.common.util.UUIDUtil;
import org.junit.Test;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/7
 */
public class UUIDUtilTest {

    @Test
    public void testGenerateUUid() {
        System.out.println(UUIDUtil.generateUuid());
        System.out.println(UUIDUtil.generateUuid());
    }

}
