package com.lcc.security.config;

import com.lcc.security.filter.JwtAuthenticationTokenFilter;
import com.lcc.security.handler.MyAccessDeniedHandlerImpl;
import com.lcc.security.handler.MyAuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @description:
 * @author: wzy
 * @time: 2022/3/30 17:05
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


    /**
     * 强散列哈希 密码加密实现
     */
    @Bean
    public PasswordEncoder getPw(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private MyAuthenticationEntryPointImpl myAuthenticationEntryPoint;

    @Autowired
    private MyAccessDeniedHandlerImpl myAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //取消跨站防护
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests() //所有请求都需要被认证
                .antMatchers("/user/login").anonymous() //指定匿名用户允许访问登录接口
                .anyRequest() //所有的请求
                //使用权限表达式规则 将自定义权限规则传入,所有url必须走我们写的权限规则方法,才能访问
                //这个类是我们自定义实现，该类中的方法hasPermission从内存或数据库中动态加载资源匹配规则，进行资源访问鉴权
                .access("@myRbacService.hasPermission(request,authentication)");


        //在哪个过滤器之前添加过滤器：在登录校验过滤器之前添加 自定义JWT认证过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);


        //配置异常处理器
        http.exceptionHandling()
                .authenticationEntryPoint(myAuthenticationEntryPoint) //认证失败的自定义处理器
                .accessDeniedHandler(myAccessDeniedHandler); //权限不足的自定义处理器

        //允许跨域配置
        http.cors();
    }
}
