package com.lcc.security.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lcc.security.dto.UserDto;
import com.lcc.security.entity.LoginUser;
import com.lcc.security.entity.UserEntity;
import com.lcc.security.service.LoginService;
import com.lcc.security.utils.JwtUtils;
import com.lcc.security.utils.RedisUtil;
import com.lcc.security.utils.ResponseResult;
import com.lcc.security.utils.RsaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @description:
 * @author: wzy
 * @time: 2022/4/6 16:09
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ResponseResult login(UserDto userDto) {
        log.info("========================================进入登录校验，生成token，存储用户信息的方法========================================");
        //AuthenticationManager authenticate 进行用户认证
        //1.会调用UserDetailsServiceImpl.loadUserByUsername方法进行登录校验
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDto.getUserName(),userDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //如果没有认证通过，则给出具体的提示信息
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登陆失败");
        }
        //2.如果认证通过，使用userId生成一个jwt,jwt存入ResponseResult返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long userId = loginUser.getId();
        PrivateKey privateKey = null;
        Map<String,Object> userMap = new HashMap<String,Object>();
        userMap.put("userId",userId);
        try {
            privateKey = RsaUtils.getPrivateKey("D:/cjp/key.pri");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer expireMinutes = 60 * 2;//过期时间分钟
        String token = "";
        try{
            token = JwtUtils.generateToken(userMap,privateKey,null);
        }catch (Exception e){
            e.printStackTrace();
        }
        //3.把完整的用户信息存储redis，userId作为key
        redisUtil.set("login:" + userId, JSONObject.toJSONString(loginUser));
        //4.返回信息拼装
        Map<String,Object> reMap = new HashMap<String,Object>();
        reMap.put("token",token);
        return new ResponseResult(200,"登录成功",reMap);
    }
}
