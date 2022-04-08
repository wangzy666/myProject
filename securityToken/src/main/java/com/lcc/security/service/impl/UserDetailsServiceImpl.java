package com.lcc.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lcc.security.dao.MenuMapper;
import com.lcc.security.dao.UserMapper;
import com.lcc.security.entity.LoginUser;
import com.lcc.security.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: wzy
 * @time: 2022/4/6 15:31
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("========================================进入登录，校验用户密码的loadUserByUsername方法========================================");
        //1.查询用户信息
        LambdaQueryWrapper<UserEntity> lqw = new LambdaQueryWrapper();
        lqw.eq(UserEntity::getUserName,username);
        UserEntity userEntity = userMapper.selectOne(lqw);
        if(Objects.isNull(userEntity)){
            throw new RuntimeException("系统中没有查到此用户");
        }
        //2.查询对应的权限信息
        List<String> permsList = menuMapper.selectMenuList(userEntity.getId());
        //3.把数据封装成UserDetails返回
        return new LoginUser(userEntity,permsList);
    }
}
