package cn.edu.nuc.acmicpc.service;

import cn.edu.nuc.acmicpc.common.BasicTest;
import cn.edu.nuc.acmicpc.common.util.EncryptUtil;
import cn.edu.nuc.acmicpc.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/4
 * User service test.
 */
public class UserServiceTest extends BasicTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() {
        UserDto userDto = new UserDto();
        userDto.setUserName("chuninsane");
        userDto.setPassword(EncryptUtil.encoderByMd5("123"));
        userService.createUser(userDto);
    }

    @Test
    public void testGetUserById() {
        UserDto userDto = userService.getUserByUserId(1L);
        Assert.assertNotNull(userDto);
    }

    @Test
    public void testUpdateUser() {
        UserDto userDto = userService.getUserByUserId(2L);
        userDto.setMotto("Hello, World!");
        userService.updateUser(userDto);
    }

    @Test
    public void testGetUserByUsername() {
        UserDto userDto = userService.getUserByUsername("zhangliang");
        Assert.assertNull(userDto);
        userDto = userService.getUserByUsername("chuninsane");
        Assert.assertNotNull(userDto);
    }

    @Test
    public void testCount() {
        Map<String, Object> params = new HashMap<>();
        params.put("user_name", "chuninsane");
        Long count = userService.count(params);
        Assert.assertEquals(1, (long) count);
    }

    @Test
    public void testUpdateUserByUserId() {
        Map<String, Object> params = new HashMap<>();
        params.put("motto", "super admin!");
        userService.updateUserByUserId(1L, params);
    }

    @Test
    public void testIsExistById() {
        Assert.assertTrue(userService.isExistUserByUserId(1L));
        Assert.assertTrue(!userService.isExistUserByUserId(3L));
    }

    @Test
    public void testIsExistByUsername() {
        Assert.assertTrue(userService.isExistUserByUsername("chuninsane"));
        Assert.assertTrue(!userService.isExistUserByUsername("zhangliang"));
    }
}
