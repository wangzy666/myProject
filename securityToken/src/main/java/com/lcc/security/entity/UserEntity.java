package com.lcc.security.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description:
 * @author: wzy
 * @time: 2022/4/6 15:08
 */
@Data
@TableName("sys_user")
public class UserEntity {
    @TableId(value = "id")
    private Long id;

    private String userName;

    private String password;

    public UserEntity(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
}
