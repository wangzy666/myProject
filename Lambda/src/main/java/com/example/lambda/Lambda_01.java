package com.example.lambda;

/**
 * @description:
 * @author: wzy
 * @time: 2022/4/10 10:03
 */
public class Lambda_01 {

    public static void main(String[] args) {
        new Thread(()-> {
                System.out.println();
        }).start();
    }

    private static void run() {

    }
}
