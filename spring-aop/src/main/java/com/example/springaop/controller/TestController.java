package com.example.springaop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: wzy
 * @time: 2022/3/16 9:10
 */
@RestController
public class TestController {

    @GetMapping(value = "/test")
    public String test(){
        System.out.println("---test---");
        return "hello,aop";
    }
    @GetMapping(value = "/test2")
    public String test2(@RequestParam("name") String name){
        System.out.println("---name:" + name);
        System.out.println("---test2---");
        return "hello,aop";
    }
}
