package com.kuma.standard.async;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.val;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 基于 Guava 增强版的异步模型实现的泡茶 demo
 *
 * 继承是为了复用 Callable 类
 *
 * @author kuma 2021-02-22
 */
public class GuavaMakeTeaDemo extends FutureTaskMakeTeaDemo {

    public static void main(String[] args) {
        new GuavaMakeTeaDemo().startMainProcess();
    }

    @Override
    public void startMainProcess() {
        val javaPool = Executors.newFixedThreadPool(2);
        val pool = MoreExecutors.listeningDecorator(javaPool);

        val mainJob = new MainJob();
        val thread = new Thread(mainJob, "主流程");
        thread.start();

        val washFuture = pool.submit(new WashJob());
        val boilFuture = pool.submit(new BoilWaterJob());
        Futures.addCallback(washFuture, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(@Nullable Boolean success) {
                if (Boolean.TRUE.equals(success)) {
                    mainJob.cupOk = true;
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(getCurrentThreadName() + " 洗茶具都没洗好，没有茶喝了");
            }
        }, pool);
        Futures.addCallback(boilFuture, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(@Nullable Boolean success) {
                if (Boolean.TRUE.equals(success)) {
                    mainJob.waterOk = true;
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(getCurrentThreadName() + " 烧水失败，没有茶喝了");
            }
        }, pool);
    }

    @Override
    protected boolean prepareWaterAndCup() {
        return false;
    }

    static class MainJob implements Runnable {

        boolean waterOk = false;
        boolean cupOk = false;

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println(getCurrentThreadName() + " 等着水开杯子洗完，读会书...");
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(getCurrentThreadName() + " 书飞走了，读不了书了");
                }
                if (waterOk && cupOk) {
                    System.out.println(getCurrentThreadName() + " 放茶叶");
                    System.out.println(getCurrentThreadName() + " 泡茶");
                    System.out.println(getCurrentThreadName() + " 喝茶 ~");
                    break;
                }
            }
        }
    }

}
