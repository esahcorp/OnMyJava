package com.kuma.standard.nio;

import lombok.val;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/**
 * New IO UDP Server
 *
 * @author kuma 2021-02-20
 */
public class UdpServer {
    public void receive() throws IOException {
        try (val channel = DatagramChannel.open()) {
            channel.configureBlocking(false);
            channel.bind(new InetSocketAddress("localhost", 19200));
            System.out.println("UDP 服务器启动成功");

            try (val selector = Selector.open()) {
                channel.register(selector, SelectionKey.OP_READ);
                while (selector.select() > 0) {
                    val buffer = ByteBuffer.allocate(1 << 10);
                    for (SelectionKey key : selector.selectedKeys()) {
                        if (key.isReadable()) {
                            val client = channel.receive(buffer);
                            buffer.flip();
                            System.out.println(new String(buffer.array(), 0, buffer.limit()));
                            buffer.clear();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new UdpServer().receive();
    }
}
