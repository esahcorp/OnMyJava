package com.kuma.standard.async;

import lombok.val;

import java.util.concurrent.TimeUnit;

/**
 * 用 join 演示的华罗庚泡茶的例子
 * 子线程异步执行，但是主线程会阻塞
 *
 * @author kuma 2021-02-21
 */
public class JoinThreadDemo {
    public static void main(String[] args) {
        /*
          主线程：
          1. 启动清洗线程
          2. 启动烧水线程
          3. 放茶叶
          4. 倒热水
         */

        Thread.currentThread().setName("主线程");
        val washThread = new WashThread();
        val boilWaterThread = new BoilWaterThread();
        washThread.start();
        boilWaterThread.start();
        try {
            washThread.join();
            boilWaterThread.join();
        } catch (InterruptedException e) {
            System.out.println(getCurrentThreadName() + " 异常中断");
            Thread.currentThread().interrupt();
        }

        System.out.println("放茶叶");
        System.out.println("泡茶");
    }

    static String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    static class WashThread extends Thread {


        public WashThread() {
            super("清洗线程");
        }
        @Override
        public void run() {
            try {
                System.out.println(getCurrentThreadName() + " 洗茶具");
                TimeUnit.SECONDS.sleep(2);
                System.out.println(getCurrentThreadName() + " 洗完了");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

    }
    static class BoilWaterThread extends Thread {


        public BoilWaterThread() {
            super("烧水线程");
        }
        @Override
        public void run() {
            try {
                System.out.println(getCurrentThreadName() + " 洗水壶");
                TimeUnit.SECONDS.sleep(2);
                System.out.println(getCurrentThreadName() + " 装水");
                TimeUnit.SECONDS.sleep(1);
                System.out.println(getCurrentThreadName() + " 烧水");
                TimeUnit.SECONDS.sleep(5);
                System.out.println(getCurrentThreadName() + " 水烧开了");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

    }
}
