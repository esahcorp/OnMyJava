package com.kuma.standard.network.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import lombok.val;

import java.nio.charset.StandardCharsets;

/**
 * @author kuma 2021-02-23
 */
public class BufferTypeTest {

    public static void main(String[] args) {
        val test = new BufferTypeTest();
        test.testHeapBuf();
        test.testDirectBuf();
    }

    private void testHeapBuf() {
        // 默认使用池堆外内存 ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer()
        val buffer = ByteBufAllocator.DEFAULT.heapBuffer();
        buffer.writeBytes("我在这里藏了一句话".getBytes(StandardCharsets.UTF_8));
        if (!buffer.isDirect()) {
            byte[] array = buffer.array();
            val offset = buffer.arrayOffset() + buffer.readerIndex();
            val length = buffer.readableBytes();
            System.out.println(new String(array, offset, length, StandardCharsets.UTF_8));
        }
        buffer.release();
    }

    private void testDirectBuf() {
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.directBuffer();
        buffer.writeBytes("我在这里藏了另一句话".getBytes(StandardCharsets.UTF_8));
        if (!buffer.hasArray()) {
            val length = buffer.readableBytes();
            val array = new byte[length];
            buffer.getBytes(buffer.readerIndex(), array);
            System.out.println(new String(array, StandardCharsets.UTF_8));
        }
        buffer.release();
    }
}
