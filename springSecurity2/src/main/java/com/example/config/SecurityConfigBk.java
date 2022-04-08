//package com.example.config;
//
//import com.example.handler.MyAccessDeniedHandler;
//import com.example.handler.MyAuthenticationFailureHandler;
//import com.example.handler.MyAuthenticationSuccessHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.annotation.Resource;
//
///**
// * @description:
// * @author: wzy
// * @time: 2022/3/30 17:05
// */
////@Configuration
//public class SecurityConfigBk extends WebSecurityConfigurerAdapter {
//
//    @Resource
//    protected MyAccessDeniedHandler myAccessDeniedHandler;
//
//    /**
//     * 强散列哈希加密实现
//     */
//    @Bean
//    public PasswordEncoder getPw(){
//        return new BCryptPasswordEncoder();
//    }
//
//    /**
//     */
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
////        httpSecurity
////                .formLogin() //允许用户进行基于表单的认证
////                .loginPage("/login.html") //设置自定义的登录页面
////                .loginProcessingUrl("/login") //当发现是/login时候，系统会认为是登录，这里的地址必须和登录页面login.html中表单提交的地址一致，然后默认会去执行UserDetailsServiceImpl
//////                .successForwardUrl("/toMain") //登录成功后，跳转的地址,必须是Post请求，需要通过controller进行跳转（适用前后端不分离的项目）
////                .failureForwardUrl("/toError") //登录失败后，跳转的地址,必须是Post请求，需要通过controller进行跳转（适用前后端不分离的项目）
//////                .successHandler(new MyAuthenticationSuccessHandler("https://www.baidu.com/")) //登录成功后的处理器，不能和successForwardUrl共存，适用前后端分离的项目
//////                .failureHandler(new MyAuthenticationFailureHandler("/error.html")) //登录失败后的处理器，不能和successForwardUrl共存，适用前后端分离的项目
//////                .successHandler(new MyAuthenticationSuccessHandler("/main.html")) //登录成功后的处理器，不能和successForwardUrl共存，适用前后端分离的项目
//////                .failureHandler(new MyAuthenticationFailureHandler("/error.html")) //登录失败后的处理器，不能和successForwardUrl共存，适用前后端分离的项目
////                .and() //使用and()方法相当于XML标签的关闭。 这样允许我们继续配置父类节点
////                .csrf().disable() //关闭csrf防护
////                .authorizeRequests() //确保我们应用中的所有请求都需要用户被认证
////                .antMatchers("/index").authenticated() //首页是只要登录了就可以访问
////                //使用权限表达式规则 将自定义权限规则传入,所有url必须走我们写的权限规则方法,才能访问
////                //其他的资源的访问我们通过权限规则表达式实现，表达式规则中使用了rbacService，
////                //这个类我们自定义实现。该类服务hasPermission从内存(或数据库)动态加载资源匹配规则，进行资源访问鉴权
////                .anyRequest().access("@myRBACService.hasPermission(request,authentication)");
////                /**
////                 * 以下请求是允许访问的
////                 * antPathMatcher用法：
////                 *
////                 * 使用通配符进行匹配
////                 *
////                 *      ？匹配一个字符(matches one character)。
////                 *      *  匹配0个或者多个字符 ( matches zero or more characters)。
////                 *      ** 匹配url中的0个或多个子目录 (matches zero or more directories in a path)
////                 *      {spring:[a-z]+} 匹配满足正则表达式[a-z]+的路径，这些路径赋值给变量"spring" (matches the regexp [a-z]+ as a path variable named "spring"）
////                 * 用例：
////                 *
////                 * 1. /erb/contract/?est.json 匹配/erb/contract/test.json、/erb/contract/aest.json、/erb/contract/best.json, 但不匹配/erb/contract/est.json ；
////                 *
////                 * 2. /erb/contract/*.json 匹配以.json的路径，/erb/contract/a.json、/erb/contract/ab.json、/erb/contract/abcXXX.json
////                 *
////                 * 3. /erb/contract/*  匹配以 /erb/contract/开始的路径，但只能是一级,/erb/contract/a、/erb/contract/abXXX、/erb/contract/a.json、/erb/contract/abXX.json、/erb/contract/a.do、/erb/contract/abXX.do 等
////                 **/
//                 // 4.  /**/contract/** ，只要路径含有contract就可以匹配，比如：/erb/XXX/contract/a/b/XX、/erb/XXX/contract/a/b/XX.XX
//                //  5.  /**/{contract:[a-z,A-Z]+}/**, 比如：/erb/XXX/contract/a/b/XX、/erb/XXX/contractXXX/a/b/XX.XX
////                .antMatchers("/api/test1").permitAll() //放行
////                .antMatchers("/login.html").permitAll() //访问此地址就不需要进行身份认证了，防止重定向死循环
////                .antMatchers("/error.html").permitAll() //放行
////                .antMatchers("/api/test2").hasRole("aaa") //拥有aaa角色的用户才能访问/api/test2的地址，角色需要在UserDetailsServiceImpl中进行配置，格式是ROLE_aaa
////                .antMatchers("/main1.html").hasAuthority("admin") //给admin用户设置访问main1.html的权限
//                // 除上面外的所有请求全部需要鉴权认证，不能和access同时使用
////                .anyRequest().authenticated();
//    }
//}
