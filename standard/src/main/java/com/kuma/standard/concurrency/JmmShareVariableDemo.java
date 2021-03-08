package com.kuma.standard.concurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * 线程共享变量并发问题演示
 * 如果想模拟单核 CPU 下的正常效果，可以添加 -XX:ActiveProcessorCount=1 选项
 *
 * @author kuma 2021-03-08
 */
public class JmmShareVariableDemo {

    static
//    volatile
    boolean prepared = false;

    public static void main(String[] args) throws IOException, InterruptedException {
        new Thread(() -> {
            System.out.println("Waiting for data...");
            while (!prepared) {

            }
            System.out.println("=========== success");
        }).start();
        // 必须，保证线程执行的顺序
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            System.out.println("Start prepare data");
            prepared = true;
            System.out.println("Stop prepare data");
        }).start();
        new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
}
