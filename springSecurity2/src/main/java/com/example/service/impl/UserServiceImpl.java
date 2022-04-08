package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.dao.UserMapper;
import com.example.dto.RoleDto;
import com.example.entity.PermissionEntity;
import com.example.entity.UserEntity;
import com.example.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: wzy
 * @time: 2022/3/31 15:18
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private PasswordEncoder pw;

    @Resource
    private UserMapper userMapper;

    @Override
    public UserEntity selectUserByUsername(String userName) {
        String password = pw.encode("123456");//这个密码实际是存储在数据库中的，而数据库中存储的密码是用户注册的时候设置的密码
        System.out.println("加密后的密码：" + password);
        //        UserEntity user = new UserEntity(1,"admin",password);
        LambdaQueryWrapper<UserEntity> lqw = new LambdaQueryWrapper();
        lqw.eq(UserEntity::getUserName, userName);
        return userMapper.selectOne(lqw);
    }

    @Override
    public List<PermissionEntity> selectPermissionByUser(Long userId) {
        List<PermissionEntity> list = userMapper.selectPermissionByUserId(userId);
        return list;
    }

    @Override
    public List<RoleDto> findRoleByUserId(Long userId) {
        return userMapper.findRoleByUserId(userId);
    }

    @Override
    public List<String> findAuthorityByRoleCodes(List<RoleDto> roleList) {
        return userMapper.findAuthorityByRoleCodes(roleList);
    }
}
