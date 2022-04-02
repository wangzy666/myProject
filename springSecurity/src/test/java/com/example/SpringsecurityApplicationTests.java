package com.example;

import com.example.dao.UserMapper;
import com.example.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringsecurityApplicationTests {
    @Resource
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        userMapper.insert(new UserEntity(null,"张三","123456"));
    }

}
