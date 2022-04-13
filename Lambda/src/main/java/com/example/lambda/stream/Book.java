package com.example.lambda.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: wzy
 * @time: 2022/4/10 13:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode //用于后期的去重使用
public class Book {
    private Long id;

    private String name;

    private String category;//分类，"小说,军事,爱情"

    private Integer score;//评分

    private String info;//简介
}
