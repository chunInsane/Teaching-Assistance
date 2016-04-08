package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.common.BasicTest;
import cn.edu.nuc.acmicpc.common.util.UUIDUtil;
import cn.edu.nuc.acmicpc.model.UserSerialKey;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/8
 */
public class UserKeySerialServiceTest extends BasicTest {

    @Autowired
    private UserSerialKeyService userSerialKeyService;

    @Test
    public void test1() {
        UserSerialKey userSerialKey = new UserSerialKey();
        userSerialKey.setKey(UUIDUtil.generateUuid());
        userSerialKey.setUsername("chuninsane@163.com");
        userSerialKey.setStatus(0);
        userSerialKeyService.addUserSerialKey(userSerialKey);
    }

    @Test
    public void test2() {
        UserSerialKey userSerialKey = userSerialKeyService.getUserSerialKey(1L);
        Assert.assertNotNull(userSerialKey);
    }

    @Test
    public void test3() {
        UserSerialKey userSerialKey = userSerialKeyService.getUserSerialKeyByUsername("chuninsane@163.com");
        Assert.assertNotNull(userSerialKey);
    }

    @Test
    public void test4() {
        UserSerialKey userSerialKey = userSerialKeyService.getUserSerialKeyByUsername("chuninsane@163.com");
        String key = UUIDUtil.generateUuid();
        System.out.println(key);
        userSerialKey.setKey(key);
        userSerialKeyService.updateSerialKey(userSerialKey);
    }
}
