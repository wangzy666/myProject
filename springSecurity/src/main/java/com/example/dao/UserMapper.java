package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dto.RoleDto;
import com.example.entity.PermissionEntity;
import com.example.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description:
 * @author: wzy
 * @time: 2022/4/1 9:25
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    public List<PermissionEntity> selectPermissionByUserId(Long userId);


    //根据userID查询用户角色
    List<RoleDto> findRoleByUserId(@Param("userId") Long userId);


    //根据用户角色查询用户权限
    List<String> findAuthorityByRoleCodes(@Param("roleList") List<RoleDto> roleList);
}
