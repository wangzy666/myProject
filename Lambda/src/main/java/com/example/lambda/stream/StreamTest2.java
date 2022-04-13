package com.example.lambda.stream;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @description:
 * @author: wzy
 * @time: 2022/4/10 13:30
 */
public class StreamTest2 {

    /**
     * 遍历集合 List
     * @param args
     */
//    public static void main(String[] args) {
//        //书籍列表
//        List<Book> book = new ArrayList<>();
//        book.add(new Book(1L,"书名1","爱情，都市",90,"很不错哦"));
//        book.add(new Book(2L,"书名2","小说，个人传记",80,"不赖不赖"));
//        book.add(new Book(3L,"书名3","哲学",85,"不赖不赖666"));
//        book.add(new Book(4L,"书名4","个人传记",86,"不赖不赖123"));
//
//        book.stream().forEach(book1 -> System.out.println(book1.getName()));
//    }


    /**
     * 遍历 Map
     * @param args
     */
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("v1",1);
        map.put("v2",2);
        map.put("v3",3);
        Stream<Map.Entry<String, Integer>> stream = map.entrySet().stream();
        stream.forEach((obj -> System.out.println("键：" + obj.getKey() + ",值：" + obj.getValue())));

    }



}
