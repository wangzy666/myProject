package com.lcc.security.controller;

import com.lcc.security.dto.UserDto;
import com.lcc.security.service.LoginService;
import com.lcc.security.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: wzy
 * @time: 2022/4/6 16:05
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/user/login")
    public ResponseResult login(@RequestBody UserDto userDto){

        return loginService.login(userDto);
    }
}
