package com.kuma.standard.nio;

import lombok.val;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * New IO 实现 HTTP 服务端
 *
 * @author kuma 2021-02-20
 */
public class HttpServer {

    public static void main(String[] args) {
        try (val selector = Selector.open()) {
            try (val serverChannel = ServerSocketChannel.open()) {
                serverChannel.bind(new InetSocketAddress(8080));
                serverChannel.configureBlocking(false);

                serverChannel.register(selector, SelectionKey.OP_ACCEPT);
                while (selector.select() > 0) {
                    val keyIterator = selector.selectedKeys().iterator();
                    while (keyIterator.hasNext()) {
                        val key = keyIterator.next();
                        new HttpHandler(key).handKey();
                        keyIterator.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class HttpHandler {
        private final SelectionKey key;

        public HttpHandler(SelectionKey key) {
            this.key = key;
        }

        public void handleAccept() throws IOException {
            val clientChannel = ((ServerSocketChannel) key.channel()).accept();
            clientChannel.configureBlocking(false);
            clientChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(1 << 10));
        }

        public void handleRead() throws IOException {
            try (SocketChannel sc = (SocketChannel) key.channel()) {
                ByteBuffer buffer = (ByteBuffer) key.attachment();
                buffer.clear();

                if (sc.read(buffer) != -1) {
                    buffer.flip();
                    String receivedString = Charset.forName(StandardCharsets.UTF_8.name()).decode(buffer).toString();
                    String[] requestMsg = receivedString.split("\r\n");
                    for (String s : requestMsg) {
                        System.out.println(s);
                        // 遇到空行代表报文头打印完了
                        if (s.isEmpty()) {
                            break;
                        }
                    }

                    // 打印首行信息
                    String[] firstLine = requestMsg[0].split(" ");
                    System.out.println();
                    System.out.println("Method:\t" + firstLine[0]);
                    System.out.println("Url:\t" + firstLine[1]);
                    System.out.println("HTTP Version:\t" + firstLine[2]);
                    System.out.println();

                    // 返回客户端
                    StringBuilder sendString = new StringBuilder();
                    sendString.append("HTTP/1.1 200 OK\r\n");
                    sendString.append("Content-Type:text/html;charset=").append(StandardCharsets.UTF_8.name()).append("\r\n");
                    sendString.append("\r\n");

                    sendString.append("<html><head><title>显示报文</title></head><body>");
                    sendString.append("接收到请求报文是:<br/>");
                    for (String s : requestMsg) {
                        sendString.append(s).append("<br/>");
                    }
                    sendString.append("</body></html>");
                    ByteBuffer wrap = ByteBuffer.wrap(sendString.toString().getBytes(StandardCharsets.UTF_8));
                    sc.write(wrap);
                }
            }
        }

        public void handKey() throws IOException {
            if (key.isAcceptable()) {
                handleAccept();
            }

            if (key.isReadable()) {
                handleRead();
            }
        }
    }
}
