package com.kuma.standard.nio;

import lombok.val;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author kuma 2021-02-20
 */
public class NioDiscardClient {

    public static void startClient() {
        // Selector 是用来支持高并发的，客户端没意义

        try (val channel = SocketChannel.open(new InetSocketAddress("localhost", 8080))) {
            channel.configureBlocking(false);
            while (!channel.finishConnect()) {
                // 等待连接
            }
            System.out.println("连接成功");
            val buffer = ByteBuffer.allocate(1 << 10);
            buffer.put("Hello World!".getBytes(StandardCharsets.UTF_8));
            buffer.flip();

            channel.write(buffer);
            channel.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        startClient();
    }
}
