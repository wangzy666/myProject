package com.lcc.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: wzy
 * @time: 2022/4/6 14:26
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
//    @PreAuthorize("hasAnyAuthority('sys:hello')")
    public String hello(){
        return "hello";
    }
}
