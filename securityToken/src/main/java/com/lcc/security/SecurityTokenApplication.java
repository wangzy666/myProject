package com.lcc.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan("com.lcc.security.dao")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityTokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityTokenApplication.class, args);
    }

}
