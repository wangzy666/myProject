package com.example.lambda.stream;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: wzy
 * @time: 2022/4/10 13:30
 */
public class StreamTest {

    public static void main(String[] args) {
        //遍历年龄小于18，作家的名字
//        test1();

        //取出作家的所有书籍中的最低分和最高分
//        test2();

        //获取一个存放作家名字的List集合
//        test3();

        //获取一个Map集合,作家的名字作为key，List<Book>作为value
//        test4();

        //判断年龄是否有在20岁以上的作家
//        test5();

        Stream<Author> stream = getAuthors().stream();
        stream.map(author -> author.getAge())
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer age) {
                        return String.valueOf(age);
                    }
                });

    }

    private static void test5() {
        Stream<Author> stream = getAuthors().stream();
        boolean b = stream.anyMatch(author -> author.getAge() > 20);
        System.out.println(b);
    }

    private static void test4() {
        Stream<Author> stream = getAuthors().stream();
        Map<String, List<Book>> collect = stream.distinct()
                .collect(Collectors.toMap(author -> author.getName(), author -> author.getBooks()));
        System.out.println(collect);
    }

    private static void test3() {
        List<Author> authors = getAuthors();
        Stream<Author> stream = authors.stream();
        List<String> collect = stream.map(author -> author.getName())
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    private static void test1() {
        List<Author> authorList = getAuthors();
        authorList
                .stream() //创建流
                .distinct() //去重
                .filter(author -> author.getAge() < 18) //过滤年龄小于18岁的
                .forEach(author -> System.out.println(author.getName())); //打印输出作家的名字
    }

    private static void test2() {
        List<Author> authors = getAuthors();
        Stream<Author> stream = authors.stream();
        Optional<Integer> min = stream.flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .min((score1, score2) -> score1 - score2);
        System.out.println(min.get());
        Optional<Integer> max = stream.flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .max((score1, score2) -> score1 - score2);
        System.out.println(max.get());
    }


    private static List<Author> getAuthors(){
        //数据初始化
        Author author = new Author(1L, "张三", 8, "哈哈", null);
        Author author2 = new Author(2L, "李四", 25, "嘿嘿", null);
        Author author3 = new Author(3L, "王五", 16, "嘻嘻", null);
        Author author4 = new Author(3L, "王五", 16, "嘻嘻", null);
        //书籍列表
        List<Book> book1 = new ArrayList<>();
        List<Book> book2 = new ArrayList<>();
        List<Book> book3 = new ArrayList<>();

        book1.add(new Book(1L,"书名1","爱情，都市",90,"很不错哦"));
        book1.add(new Book(2L,"书名2","小说，个人传记",80,"不赖不赖"));

        book2.add(new Book(3L,"书名3","哲学",85,"很不错哦666"));
        book2.add(new Book(3L,"书名3","哲学",85,"不赖不赖666"));
        book2.add(new Book(4L,"书名4","个人传记",86,"不赖不赖123"));

        book3.add(new Book(5L,"书名5","爱情",97,"很不错哦88"));
        book3.add(new Book(6L,"书名6","小说",88,"不赖不赖99"));
        book3.add(new Book(7L,"书名7","小说",89,"不赖不赖00"));

        author.setBooks(book1);
        author2.setBooks(book2);
        author3.setBooks(book3);
        author4.setBooks(book3);

        ArrayList<Author> authors = new ArrayList<>(Arrays.asList(author, author2, author3, author4));
        return authors;
    };
}
