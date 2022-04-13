package com.example.lambda.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description:
 * @author: wzy
 * @time: 2022/4/10 13:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode //用于后期的去重使用
public class Author {

    private Long id;

    private String name;

    private Integer age;

    private String info;

    private List<Book> books;
}
