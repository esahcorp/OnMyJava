package com.kuma.standard.async;

import lombok.val;

/**
 * 用 join 演示的华罗庚泡茶的例子
 * 子线程异步执行，但是主线程会阻塞
 *
 * @author kuma 2021-02-21
 */
public class JoinThreadMakeTeaDemo extends BaseAsyncMakeTeaDemo {

    public static void main(String[] args) {
        new JoinThreadMakeTeaDemo().startMainProcess();
    }

    @Override
    protected boolean prepareWaterAndCup() {
        try {
            val washThread = new WashThread();
            val boilWaterThread = new BoilWaterThread();
            washThread.start();
            boilWaterThread.start();
            washThread.join();
            boilWaterThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        return true;
    }

    private static class WashThread extends Thread {
        public WashThread() {
            super("清洗流程 >> ");
        }

        @Override
        public void run() {
            try {
                washProcess();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

    }

    private static class BoilWaterThread extends Thread {
        public BoilWaterThread() {
            super("烧水流程 >> ");
        }

        @Override
        public void run() {
            try {
                boilWaterProcess();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

    }
}
