package com.kuma.standard.concurrency;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 通过反射获取 Unsafe 实例
 *
 * @author kuma 2021-03-04
 */
public class UnsafeDemo {

    static Unsafe UNSAFE = null;
    static long STATE_OFFSET = 0;

    private volatile long state = 0;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
            STATE_OFFSET = UNSAFE.objectFieldOffset(UnsafeDemo.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UnsafeDemo demo = new UnsafeDemo();
        boolean success = UNSAFE.compareAndSwapLong(demo, STATE_OFFSET, 0, 1);
        System.out.println(success);
        System.out.println(demo.state);
    }
}
