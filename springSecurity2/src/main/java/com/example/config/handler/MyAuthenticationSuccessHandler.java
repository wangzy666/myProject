package com.example.config.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 自定义登录成功的处理器
 * @author: wzy
 * @time: 2022/3/31 16:02
 */
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private String url;

    public MyAuthenticationSuccessHandler(String url) {
        this.url = url;
    }
    @Resource
    JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        log.info("用户名：{}",user.getUsername());
        log.info("密码：{}",user.getPassword());//密码处于安全考虑，只打印null
        log.info("权限：{}",user.getAuthorities());
        String token = jwtTokenUtil.generateToken(user);
//        response.sendRedirect(url);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(token);
    }
}
