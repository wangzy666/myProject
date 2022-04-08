package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description:
 * @author: wzy
 * @time: 2022/3/31 15:11
 */
@Data
@TableName("sys_user")
public class UserEntity {
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    private String userName;

    private String password;

    public UserEntity(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
}
