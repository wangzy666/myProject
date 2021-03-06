//package com.example.config;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
///**
// * @description:
// * @author: wzy
// * @time: 2022/4/2 15:01
// */
//@Service
//public class JwtAuthService implements UserDetailsService {
//
//    @Resource
//    private AuthenticationManager authenticationManager;
//
//    @Resource
//    private JwtTokenUtil jwtTokenUtil;
//
//
//    /**
//      * 登录认证换取JWT令牌
//      * @param username 用户名
//      * @param password 密码
//      * @return 结果
//      */
//    public String login(String username, String password) {
//        // 用户验证
//        Authentication authentication = null;
//        try {
//            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
//            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (Exception e) {
//            throw new RuntimeException("用户名密码错误");
//        }
//        UserDetails loginUser = (UserDetails) authentication.getPrincipal();
//         // 生成token
//        return jwtTokenUtil.generateToken(loginUser);
//    }
//}
