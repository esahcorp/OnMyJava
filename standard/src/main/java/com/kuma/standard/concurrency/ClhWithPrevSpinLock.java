package com.kuma.standard.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * CLH 的 Java 实现
 *
 * @author kuma 2021-03-10
 */
public class ClhWithPrevSpinLock implements Lock {

    private final ThreadLocal<QNode> prevNodeContainer;
    private final ThreadLocal<QNode> currentNodeContainer;
    private final AtomicReference<QNode> tail;

    public ClhWithPrevSpinLock() {
        prevNodeContainer = new ThreadLocal<>();
        currentNodeContainer = ThreadLocal.withInitial(QNode::new);
        // tail 初始值的 toLock 为 false, 第一个线程将自动获得锁
        tail = new AtomicReference<>(new QNode());
    }

    @Override
    public void lock() {
        QNode current = currentNodeContainer.get();
        current.toLock = true;
        QNode prev = tail.getAndSet(current);
        prevNodeContainer.set(prev);
        while (prev.toLock) { }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        QNode current = currentNodeContainer.get();
        current.toLock = false;
        /*
         * !!! 为了避免死锁，不能重用当前的 Node。
         * 为了避免重复生成新对象，复用 prevNode。等到当前线程结束，prevNode 会随着线程一起被回收。
         */
        currentNodeContainer.set(prevNodeContainer.get());
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    private static class QNode {
        // 默认为 false
        private volatile boolean toLock;
    }
}
