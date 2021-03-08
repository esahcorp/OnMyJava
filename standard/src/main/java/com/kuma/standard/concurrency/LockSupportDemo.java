package com.kuma.standard.concurrency;

import java.util.concurrent.locks.LockSupport;

/**
 * @author kuma 2021-03-08
 */
public class LockSupportDemo {

    public static void main(String[] args) {
        LockSupport.unpark(Thread.currentThread());
        System.out.println("Unpark");
        LockSupport.park();
        System.out.println("First park");
        LockSupport.park();
        System.out.println("Second park");
    }
}
