package com.lcc.security.handler;

import com.alibaba.fastjson.JSON;
import com.lcc.security.utils.ResponseResult;
import com.lcc.security.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 自定义登录认证失败的处理器
 * @author: wzy
 * @time: 2022/4/8 9:49
 */
@Component
@Slf4j
public class MyAuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("========================================进入自定义登录认证失败的处理器========================================");
        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"抱歉啊，用户登录认证失败");
        String s = JSON.toJSONString(result);
        WebUtil.renderString(response,s);
    }
}
