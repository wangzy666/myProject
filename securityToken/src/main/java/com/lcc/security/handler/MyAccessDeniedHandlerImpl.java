package com.lcc.security.handler;

import com.alibaba.fastjson.JSON;
import com.lcc.security.utils.ResponseResult;
import com.lcc.security.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 自定义权限验证失败的处理器
 * @author: wzy
 * @time: 2022/4/8 9:49
 */
@Component
@Slf4j
public class MyAccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("========================================进入自定义权限验证失败的处理器========================================");
        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(),"抱歉啊，您的权限不足，无法访问");
        String s = JSON.toJSONString(result);
        WebUtil.renderString(response,s);
    }
}
