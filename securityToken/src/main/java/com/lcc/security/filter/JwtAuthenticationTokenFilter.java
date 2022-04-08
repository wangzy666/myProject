package com.lcc.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.lcc.security.entity.LoginUser;
import com.lcc.security.utils.JwtUtils;
import com.lcc.security.utils.RedisUtil;
import com.lcc.security.utils.RsaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.PublicKey;
import java.util.Map;
import java.util.Objects;

/**
 * @description: 自定义JWT认证过滤器，过滤一切请求
 * @author: wzy
 * @time: 2022/4/7 9:37
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("========================================进入自定义JWT认证过滤器========================================");
        //1.在请求头中获取token
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){ //如果token为空，则不用去认证，直接放行，后续中的过滤器也会判断这个请求的权限，如果不合规就返回具体信息了
            log.info("======从请求头中没有获取到token======");
            filterChain.doFilter(request,response);
            return; //必须要加return，如果不加的话，执行完后续的过滤器后，还会再回来执行这个过滤器中下面的代码
        }
        //2.解析token中的用户id
        Map<String, Object> userInfo = null;
        try {
            PublicKey publicKey = RsaUtils.getPublicKey("D:/cjp/key.pub");
            userInfo = JwtUtils.getInfoFromToken(token, publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(Objects.isNull(userInfo)){
            throw new RuntimeException("解析token获取用户信息userInfo为空");
        }
        String userId = userInfo.get("userId") == null ? null : userInfo.get("userId").toString();
        log.info("从token中获取到的userId--->{}",userId);
        if(Objects.isNull(userId)){
            throw new RuntimeException("从token中获取的userId为空");
        }
        //3.通过用户id从redis中获取用户信息
        String str = (String)redisUtil.get("login:" + userId);
        LoginUser loginUser = JSONObject.parseObject(str, LoginUser.class);
        //4.将认证后的用户信息存入SecurityContextHolder
        //获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //5.继续执行过滤器
        filterChain.doFilter(request,response);
    }
}
