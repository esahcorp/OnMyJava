package com.kuma.standard.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author kuma 2021-03-11
 */
public class ClhSpinLock implements Lock {

    private ThreadLocal<Node> currentContainer = ThreadLocal.withInitial(Node::new);
    private AtomicReference<Node> tail = new AtomicReference<>(new Node());

    private static class Node {
        private volatile boolean locked;
    }

    @Override
    public void lock() {
        Node node = currentContainer.get();
        node.locked = true;
        Node prev = tail.getAndSet(node);
        while (prev.locked) {}
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
        Node node = currentContainer.get();
        node.locked = false;
        currentContainer.set(new Node());
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
