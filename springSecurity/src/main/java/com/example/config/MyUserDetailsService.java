package com.example.config;

import com.example.dto.RoleDto;
import com.example.entity.UserEntity;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 验证用户，返回用户信息，权限的信息
 * 创建自定义的UserDetailsService服务类，实现UserDetailsService接口，根据前端传过来的账号和数据库中的账号密码进行比较
 * 并返回UserDetails对象信息
 * @author: wzy
 * @time: 2022/3/30 17:07
 */
@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private PasswordEncoder pw;
    @Resource
    private UserService userService;

    /**
     * 1、登录校验
     * 2、根据用户名获取用用户的角色、权限等信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("=================进入登录验证方法===============================");
        //1.查询数据库中用户的信息
        UserEntity userEntity = userService.selectUserByUsername(username);
        log.info("user => {}",userEntity);
        if(userEntity == null){
            throw new UsernameNotFoundException("用户不存在，请检查");
        }
        //2.查询用户角色列表
        List<RoleDto> roleList = userService.findRoleByUserId(userEntity.getId());
        //3.查询用户的资源权限列表（通过用户角色列表进行查询）
        List<String> authority = userService.findAuthorityByRoleCodes(roleList);
        //3.1角色是一个特殊的权限,也要添加到查出来的权限列表中,Security规定角色的权限必须有ROLE_前缀，所以在查出的角色名称前面要再加上 ROLE_
        roleList.stream().forEach(roleDto -> {
            //4.将修改后的角色权限添加到资源权限列表authority中
            authority.add("ROLE_" + roleDto.getName());
        });
        //5.创建一个GrantedAuthority对象权限数组
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(
                //List转字符串,逗号分隔
                String.join(",", authority));
        //6.把对象权限数组赋值给UserDetails
        UserDetails userDetails = new User(userEntity.getUserName(), userEntity.getPassword(), grantedAuthorities);
        return userDetails;
    }
}
