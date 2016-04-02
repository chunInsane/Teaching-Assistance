package cn.edu.nuc.acmicpc.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/1
 * Redis client.
 */
public class RedisClient {

    private static final Logger logger = LoggerFactory.getLogger(RedisClient.class);

    private static ConcurrentHashMap<String, JedisCommands> serverPool;

    static {
        serverPool = new ConcurrentHashMap<>(100);
        registerProxy(RedisServer.COMMON);
    }

    private static enum RedisServer {
        COMMON("redis.service.com", 6379, "chuninsane");
        private String host;
        private int port;
        private String password;

        public String getHost() {
            return host;
        }
        public int getPort() {
            return port;
        }
        public String getPassword() {
            return password;
        }
        RedisServer() {
        }
        RedisServer(final String host, final int port, final String password) {
            this.host = host;
            this.port = port;
            this.password = password;
        }
    }

    private static void registerProxy(RedisServer redisServer) {
        registerProxy(redisServer.getHost(), redisServer.getPort(), redisServer.getPassword());
    }

    private static void registerProxy(String host, int port, String password) {
        String key = host + ":" + port;
        registerProxy(key, newInstance(host, port, password));
    }

    private static void registerProxy(String key, JedisCommands jedisCommands) {
        serverPool.put(key, jedisCommands);
    }

    private static JedisCommands newInstance(final String host, final int port, final String password) {
        ClassLoader classLoader = RedisClient.class.getClassLoader();
        return (JedisCommands)Proxy.newProxyInstance(classLoader, new Class[]{JedisCommands.class}, new InvocationHandler() {
            JedisPool jedisPool = new InnerRedisClient(host, port, password).getPool();
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                try (Jedis jedis = jedisPool.getResource()) {
                    return invoke(jedis, method, args);
                }
            }
        });
    }

    public static JedisCommands getProxy() {
        return getProxy(RedisServer.COMMON);
    }

    public static JedisCommands getProxy(RedisServer common) {
        return getProxy(common.getHost(), common.getPort(), common.getPassword());
    }

    public static JedisCommands getProxy(String host, int port, String password) {
        String key = host + ":" + port;
        if (serverPool.containsKey(key)) {
            return serverPool.get(key);
        } else {
            synchronized (serverPool) {
                if (serverPool.containsKey(key)) {
                    return serverPool.get(key);
                }
                final JedisCommands jedisCommand = newInstance(host, port, password);
                serverPool.put(key, jedisCommand);
                return jedisCommand;
            }
        }
    }

    private static final class InnerRedisClient {

        public static final int MAX_TOTAL = 100;

        public JedisPool getPool() {
            logger.trace("get pool of a jedis client");
            return jedisPool;
        }

        private final JedisPool jedisPool;

        InnerRedisClient(final String host, final int port, final String password) {
            final JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_TOTAL);
            config.setMaxIdle(50);
            config.setMaxWaitMillis((long) (6 * 1000));

            config.setTestOnBorrow(true);
            jedisPool = new JedisPool(config, host, port, 6 * 1000, password);
        }

        @Override
        public String toString() {
            return "InnerRedisClient{" +
                    "jedisPool=" + jedisPool +
                    '}';
        }
    }

}
