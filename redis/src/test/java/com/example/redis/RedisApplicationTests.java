package com.example.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class RedisApplicationTests {

    @Resource
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    /**
     * 获取redis的连接对象
     */
    @Test
    void contextLoads() {
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        System.out.println("测试是否连通:" + connection.ping());
    }

}
