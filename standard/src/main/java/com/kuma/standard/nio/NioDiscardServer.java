package com.kuma.standard.nio;

import lombok.val;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author kuma 2021-02-20
 */
public class NioDiscardServer {

    public static void startServer() throws IOException {
        try (val selector = Selector.open()) {

            // 1. 构建 Server
            try (val serverChannel = ServerSocketChannel.open()) {
                serverChannel.configureBlocking(false);
                serverChannel.bind(new InetSocketAddress("localhost", 8080));

                // 2. 注册
                serverChannel.register(selector, SelectionKey.OP_ACCEPT);

                System.out.println("服务器启动成功");
                // 3. 处理 Key
                while (selector.select() > 0) {
                    val keyIterator = selector.selectedKeys().iterator();
                    while (keyIterator.hasNext()) {
                        val key = keyIterator.next();

                        if (key.isAcceptable()) {
                            // 3.1 处理 ACCEPT 就绪事件
                            // 不能在此关闭 SocketChannel
                            val socket = serverChannel.accept();
                            socket.configureBlocking(false);
                            // 注册当前 SocketChannel 的 Read 就绪事件
                            socket.register(selector, SelectionKey.OP_READ);
                        } else if (key.isReadable()) {
                            try (val socketChannel = ((SocketChannel) key.channel())) {
                                val buffer = ByteBuffer.allocate(1 << 10);
                                for (int length; (length = socketChannel.read(buffer)) > 0; ) {
                                    buffer.flip();
                                    System.out.println(new String(buffer.array(), 0, length));
                                    buffer.clear();
                                }
                            }
                        }
                        keyIterator.remove();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        startServer();
    }
}
