package com.lcc.security.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @description:
 * @author: wzy
 * @time: 2022/4/6 14:53
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {
    //返回数据
    private T data;
    //返回码
    private Integer code;
    //返回描述
    private String msg;

    public ResponseResult(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(T data){
        this.code = code;
        this.data = data;
    }
    public ResponseResult(Integer code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
