package com.example.config.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description: 自定义权限不足的处理器
 * @author: wzy
 * @time: 2022/3/31 16:53
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //设置响应状态码
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        //设置响应数据格式
        response.setContentType("application/json;charset=utf-8");
        //输入响应内容
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            String json="{\"status\":\"403\",\"msg\":\"权限不足，拒绝访问\"}";
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}
