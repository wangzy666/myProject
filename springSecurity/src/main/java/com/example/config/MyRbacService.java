//package com.example.config;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
///**
// * @description: 动态资源鉴权规则
// * @author: wzy
// * @time: 2022/4/1 13:59
// *
// * 首先通过登录用户名加载用户的urls（即资源访问路径、资源唯一标识）。
// * 如果urls列表中任何一个元素，能够和request.getRequestURI()请求资源路径相匹配，则表示该用户具有访问该资源的权限。
// * urls.stream().anyMatch是java8的语法，可以遍历数组，返回一个boolean类型。
// * hasPermission有两个参数，第一个参数是HttpServletRequest ,第二个参数是Authentication认证主体
// * 用户每一次访问系统资源的时候，都会执行这个方法，判断该用户是否具有访问该资源的权限。
// */
//
//@Component("myRbacService") //给这个bean取名
//public class MyRbacService {
//
////
////    @Resource
////    private MyRBACServiceMapper rbacServiceMapper;
////
////    //security提供的工具类
////    private AntPathMatcher antPathMatcher = new AntPathMatcher();
//
//    /**
//     * 判断某用户是否有该请求资源的访问权限
//     *
//     * @param request
//     * @param authentication
//     * @return
//     */
//    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
//        //从security中拿出用户主体,实际上是我们之前封装的UserDetials,
//        //但是又被封了一层
//        Object principal = authentication.getPrincipal();
//        //如果取出的principal是我们放进去的UserDetails类,并且已经登录
//        if (principal instanceof UserDetails) {
//            //1.强转获取UserDetails
//            UserDetails userDetails = (UserDetails) principal;
//            //2.从内存中获取权限(因为已经登录),放入security容器中,如果有的话返回true
//            List<GrantedAuthority> authorityList =
//                    AuthorityUtils.commaSeparatedStringToAuthorityList(request.getRequestURI());
//            return userDetails.getAuthorities().contains(authorityList.get(0));
//            //2.通过用户名获取用户资源(用户找角色,角色找资源)(这里拿url做的标识,所以是url)
//            //           List<String> urlByUserName = rbacServiceMapper.findUrlByUserName(username);
//            //3.遍历urls,然后通过antPathMatcher判断是否匹配,匹配的上返回true
//            //     return urlByUserName.stream().anyMatch(
//            //              url -> antPathMatcher.match(url, request.getRequestURI())
//            //     );
//        }
//        return false;
//    }
//
//}