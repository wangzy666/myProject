package com.example.service;

import com.example.dto.RoleDto;
import com.example.entity.PermissionEntity;
import com.example.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: wzy
 * @time: 2022/3/31 15:18
 */
public interface UserService {

    public UserEntity selectUserByUsername(String userName);

    public List<PermissionEntity> selectPermissionByUser(Long userId);

    public List<RoleDto> findRoleByUserId(Long userId);

    public List<String> findAuthorityByRoleCodes(List<RoleDto> roleList);
}
