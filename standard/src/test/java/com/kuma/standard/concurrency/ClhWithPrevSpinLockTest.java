package com.kuma.standard.concurrency;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author kuma 2021-03-11
 */
public class ClhWithPrevSpinLockTest {

    int count = 0;

    @Test
    public void concurrency_between_100_thread() throws InterruptedException {
        final ClhWithPrevSpinLock lock = new ClhWithPrevSpinLock();

        CountDownLatch latch = new CountDownLatch(100);

        Thread[] array = new Thread[100];
        for (int i = 0; i < 100; i++) {
            array[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    lock.lock();
                    try {
                        count ++;
                    } finally {
                        lock.unlock();
                    }
                }
                latch.countDown();
            });
        }
        for (Thread thread : array) {
            thread.start();
        }
        latch.await();
        System.out.println(count);
        Assert.assertEquals(10000, count);
    }

}