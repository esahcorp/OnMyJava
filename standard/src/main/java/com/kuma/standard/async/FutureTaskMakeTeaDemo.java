package com.kuma.standard.async;

import lombok.val;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask 泡茶 demo
 * 异步阻塞
 *
 * @author kuma 2021-02-22
 */
public class FutureTaskMakeTeaDemo extends BaseAsyncMakeTeaDemo {

    public static void main(String[] args) {
        new FutureTaskMakeTeaDemo().startMainProcess();
    }

    @Override
    protected boolean prepareWaterAndCup() {
        val washTask = new FutureTask<Boolean>(new WashJob());
        val washThread = new Thread(washTask, "清洗流程 >> ");

        val boilWaterTask = new FutureTask<>(new BoilWaterJob());
        val boilThread = new Thread(boilWaterTask, "烧水流程 >> ");

        washThread.start();
        boilThread.start();

        try {
            if (!washTask.get()) {
                System.out.println("洗茶具失败，不喝了");
                return false;
            }
            if (!boilWaterTask.get()) {
                System.out.println("烧水失败，不喝了");
            }
        } catch (ExecutionException e) {
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        return true;
    }

    static class WashJob implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            try {
                washProcess();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
            return true;
        }
    }

    static class BoilWaterJob implements Callable<Boolean> {

        @Override
        public Boolean call() throws Exception {
            try {
                boilWaterProcess();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
            return true;
        }
    }
}
