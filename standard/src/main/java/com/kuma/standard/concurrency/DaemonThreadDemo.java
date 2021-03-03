package com.kuma.standard.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * @author kuma 2021-03-03
 */
public class DaemonThreadDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main over");
    }
}
