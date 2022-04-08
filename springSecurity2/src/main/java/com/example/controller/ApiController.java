package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: wzy
 * @time: 2022/3/31 9:50
 */
@RestController
@RequestMapping("api")
public class ApiController {

    @GetMapping
    @RequestMapping("test1")
    public String test1(){

        return "test1";
    }

    @GetMapping
    @RequestMapping("test2")
    public String test2(){

        return "test2";
    }

}
