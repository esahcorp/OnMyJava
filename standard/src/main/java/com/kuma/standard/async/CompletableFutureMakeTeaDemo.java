package com.kuma.standard.async;

import lombok.val;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * CompletableFuture 实现的异步泡茶 demo
 * 与 FutureTaskDemo 相似
 *
 * @author kuma 2021-02-22
 */
public class CompletableFutureMakeTeaDemo extends BaseAsyncMakeTeaDemo {

    public static void main(String[] args) {
        new CompletableFutureMakeTeaDemo().startMainProcess();
    }

    @Override
    protected boolean prepareWaterAndCup() {
        val threadPool = Executors.newFixedThreadPool(2);
        val washFuture = CompletableFuture.supplyAsync(() -> {
            try {
                washProcess();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
            return true;
        }, threadPool);

        val boilFuture = CompletableFuture.supplyAsync(() -> {
            try {
                boilWaterProcess();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
            return true;
        }, threadPool);

        try {
            CompletableFuture.allOf(washFuture, boilFuture).get();
        } catch (ExecutionException e) {
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        return true;
    }
}
