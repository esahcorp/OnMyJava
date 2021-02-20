package com.kuma.standard.nio;

import lombok.val;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * NIO UDP client
 *
 * @author kuma 2021-02-20
 */
public class UdpClient {
    public void send() throws IOException {
        try (val channel = DatagramChannel.open()) {
            channel.configureBlocking(false);
            val buffer = ByteBuffer.allocate(1 << 10);
            val scanner = new Scanner(System.in);
            System.out.println("请输入待发送数据：");
            while (scanner.hasNext()) {
                String next = scanner.next();
                buffer.put((LocalDateTime.now().toString() + ">>" + next).getBytes(StandardCharsets.UTF_8));
                buffer.flip();
                channel.send(buffer, new InetSocketAddress("localhost", 19200));
                buffer.clear();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new UdpClient().send();
    }
}
