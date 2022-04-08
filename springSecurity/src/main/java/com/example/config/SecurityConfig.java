package com.example.config;

import com.example.config.handler.MyAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.annotation.Resource;

/**
 * @description:
 * @author: wzy
 * @time: 2022/3/30 17:05
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    protected MyAccessDeniedHandler myAccessDeniedHandler;

    /**
     * 强散列哈希 密码加密实现
     */
    @Bean
    public PasswordEncoder getPw(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**","/css/**","/images/**"); //这个是用来忽略一些url地址，对其不进行校验，通常用在一些静态文件中。
    }

    /**
     * 基础配置
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
             // 认证失败处理类
             //.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
             // 基于token，所以不需要session
             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
             //配置权限
             .authorizeRequests()
             // 对于登录login 验证码captchaImage 允许匿名访问
             .antMatchers("/login").permitAll()
             .antMatchers("/order") //需要对外暴露的资源路径
             .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN") //user角色和admin角色都可以访问
             .antMatchers("/system/user", "/system/role", "/system/menu")
             .hasAnyRole("ADMIN") //admin角色可以访问
             // 除上面外的所有请求全部需要鉴权认证
//             .anyRequest().access("@myRbacService.hasPermission(request,authentication)").and()//authenticated()要求在执行该请求时，必须已经登录了应用
             // CRSF禁用，因为不使用session
             .and().csrf().disable() ;//禁用跨站csrf攻击防御，否则无法登陆成功
             //登出功能
             httpSecurity.logout().logoutUrl("/logout");
//             // 添加JWT filter
//             httpSecurity.addFilterBefore(new JwtAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
