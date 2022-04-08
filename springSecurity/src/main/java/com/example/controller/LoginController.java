package com.example.controller;

import com.example.config.JwtTokenUtil;
import com.example.config.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: wzy
 * @time: 2022/4/2 15:00
 */
@RestController
public class LoginController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
      * 登录方法
      *
      * @param username 用户名
      * @param password 密码
      * @return 结果
      */
    @PostMapping({"/login", "/"})
        public Map login(String username, String password) {
        Map result = new HashMap();
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
         // 生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        result.put("token", token);
        return result;
    }
}
