package com.example.config;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: wzy
 * @time: 2022/4/2 14:14
 */
@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    @Resource
    private UserDetailsService myUserDetailsService;

    @Override

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if(!requestURI.contains("login")){
            String jwtToken = request.getHeader("Authorization");
            if (!StringUtils.isEmpty(jwtToken)) {
                String username = new JwtTokenUtil().getUsernameFromToken(jwtToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}
