package com.kuma.standard.concurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * @author kuma 2021-03-03
 */
public class WaitProducerConsumer {

    private static final int MAX_SIZE = 10;

    public static void main(String[] args) throws IOException {

        Queue<Integer> queue = new ArrayDeque<>();

        Thread producer = new Thread(() -> {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == MAX_SIZE) {
                        try {
                            System.out.println("Producer waiting");
                            // 进入等待并释放 synchronized queue 的锁
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.add(1);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queue.notifyAll();
                }
            }
        });
        producer.start();

        Thread consumer = new Thread(() -> {
            int sum = 0;
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 0) {
                        try {
                            System.out.println("Consumer waiting");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    sum += queue.poll();
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queue.notifyAll();
                }
                System.out.println(sum);
            }
        });
        consumer.start();
        System.out.println("Press Shit/Enter to exit\n");
        new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
}
