package com.example.config;

import com.example.config.handler.MyAccessDeniedHandler;
import com.example.config.handler.MyAuthenticationFailureHandler;
import com.example.config.handler.MyAuthenticationSuccessHandler;
import com.example.config.handler.MyLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
                .formLogin() //允许用户进行基于表单的认证
                .loginPage("/login.html") //设置自定义的登录页面，默认使用框架自带的登录页面
                .loginProcessingUrl("/login") //设置自定义的登录方法，默认是/login,系统在发现请求地址是/login的时候，系统会认为是登录请求，这里的地址必须和登录页面login.html中表单提交的地址一致，然后默认会去执行UserDetailsServiceImpl
                .successHandler(new MyAuthenticationSuccessHandler("/main.html")) //登录成功后的处理器，不能和successForwardUrl共存，适用前后端分离的项目
                .failureHandler(new MyAuthenticationFailureHandler("/error.html")) //登录失败后的处理器，不能和successForwardUrl共存，适用前后端分离的项目


                //关闭csrf防护
                .and()
                .csrf().disable()


                //访问资源权限的相关配置
                .authorizeRequests() //所有请求都需要被认证
                .antMatchers("/login.html").permitAll() //登录页放行，如果不加这一行则一直重定向死循环在登录页面
                .antMatchers("/main.html").permitAll() //首页放行，不登录默认也可以访问
                .antMatchers("/error.html").permitAll() //错误页放行
                .antMatchers("/logout").permitAll() //退出接口放行

                //使用权限表达式规则 将自定义权限规则传入,所有url必须走我们写的权限规则方法,才能访问
                //这个类是我们自定义实现，该类中的方法hasPermission从内存或数据库中动态加载资源匹配规则，进行资源访问鉴权
                .anyRequest().access("@myRbacService.hasPermission(request,authentication)")


                //退出登录的相关配置
                .and()
                .logout()
//                .logoutSuccessHandler(new MyLogoutSuccessHandler("/login.html")) //自定义退出成功处理器（如记录日志），和logoutSuccessUrl互斥
                .logoutSuccessHandler(new MyLogoutSuccessHandler(null)) //自定义退出成功处理器（如记录日志），和logoutSuccessUrl互斥
                .deleteCookies("JSESSIONID") //退出时要删除的Cookies的名字


                //异常处理的相关配置
                .and()
                .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);
    }
}
