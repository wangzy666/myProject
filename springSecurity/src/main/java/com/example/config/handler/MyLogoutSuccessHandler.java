package com.example.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:退出成功处理器
 * @author: wzy
 * @time: 2022/4/1 17:28
 */
@Slf4j
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    /**
     * 退出登陆url
     *      可以在yml或properties文件里通过nrsc.security.browser.signOutUrl 进行指定
     *      我指定的默认值为"/" --- 因为如果不指定一个默认的url时，配置授权那一块会报错
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    private String url;

    public MyLogoutSuccessHandler(String url) {
        this.url = url;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        log.info("退出成功");
        //如果没有指定退出成功的页面则返回前端一个字符串
        if (StringUtils.equalsIgnoreCase("/",url) || null == url) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString("退出成功"));
        } else {
            //重定向到退出成功登陆页面
            response.sendRedirect(url);
        }
    }
}
