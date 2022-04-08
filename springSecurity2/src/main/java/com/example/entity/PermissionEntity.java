package com.example.entity;

import lombok.Data;

/**
 * @description:
 * @author: wzy
 * @time: 2022/4/1 9:20
 */
@Data
public class PermissionEntity {
    private int id;
    //权限名称
    private String name;

    //权限描述
    private String descritpion;

    //授权链接
    private String url;

    //父节点id
    private int pid;
}
