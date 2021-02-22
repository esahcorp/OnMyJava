package com.kuma.standard.async;

import java.util.concurrent.TimeUnit;

/**
 * 泡茶的抽象类
 *
 * @author kuma 2021-02-22
 */
public abstract class BaseAsyncMakeTeaDemo {

    /*
      主线程：
      1. 启动清洗线程
      2. 启动烧水线程
      3. 放茶叶
      4. 倒热水
     */

    /**
     * 泡茶主流程
     */
    public void startMainProcess() {
        Thread.currentThread().setName("主流程");
        System.out.println("泡茶主流程 >>  开始");
        if (!prepareWaterAndCup()) {
            System.out.println("辅助流程失败，喝不上茶了");
            return;
        }
        System.out.println("放茶叶");
        System.out.println("泡茶");
        System.out.println("泡茶成功");
    }

    /**
     * 准备开水和茶具
     */
    protected abstract boolean prepareWaterAndCup();

    static String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    /**
     * 洗茶具流程
     *
     * @throws InterruptedException thread interrupted when sleep
     */
    static void washProcess() throws InterruptedException {
        System.out.println(getCurrentThreadName() + " 洗茶具");
        TimeUnit.SECONDS.sleep(2);
        System.out.println(getCurrentThreadName() + " 洗完了");
    }

    /**
     * 烧水流程
     *
     * @throws InterruptedException thread interrupted when sleep
     */
    static void boilWaterProcess() throws InterruptedException {
        System.out.println(getCurrentThreadName() + " 洗水壶");
        TimeUnit.SECONDS.sleep(2);
        System.out.println(getCurrentThreadName() + " 装水");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(getCurrentThreadName() + " 烧水");
        TimeUnit.SECONDS.sleep(5);
        System.out.println(getCurrentThreadName() + " 水烧开了");
    }
}
