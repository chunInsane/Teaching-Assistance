package cn.edu.nuc.acmicpc.redis;

import cn.edu.nuc.acmicpc.common.util.RedisClient;
import junit.framework.Assert;
import org.junit.Test;
import redis.clients.jedis.JedisCommands;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/1
 */
public class RedisClientTest {

    @Test
    public void testRedisClient() {
        JedisCommands jedisCommands = RedisClient.getProxy();
        System.out.println(jedisCommands.get("zhang"));
        Assert.assertNotNull(jedisCommands);
    }
}
