package com.example.springaop.aoplog;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: wzy
 * @time: 2022/3/16 16:41
 */
@RestController
public class LogTestController {

//    @LogFilter("保存请求日志")
    @RequestMapping("/saveRequestLog")
    public String saveRequestLog (@RequestParam("name") String name){
        return "success："+name ;
    }

//    @LogFilter("保存异常日志")
    @RequestMapping("/saveExceptionLog")
    public String saveExceptionLog (@RequestParam("name") String name){
        int error = 100 / 0 ;
        System.out.println(error);
        return "success："+name ;
    }
}
