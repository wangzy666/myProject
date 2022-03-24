package com.example.springaop.aoplog;

import java.lang.annotation.*;
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogFilter {
    String value() default "" ;
}
