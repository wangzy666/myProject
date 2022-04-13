package com.example.lambda.optional;

import com.example.lambda.stream.Author;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @description:
 * @author: wzy
 * @time: 2022/4/11 15:53
 */
public class OptionalTest {

    public static void main(String[] args) {
        Author author = getAuthor();
        Optional<Author> author1 = Optional.ofNullable(author);
        author1.ifPresent(author2 -> System.out.println(author2.getName()));
    }

    public static Author getAuthor(){
        Author author = new Author(1L, "张三", 8, "哈哈", null);
        return null;
    };
}
