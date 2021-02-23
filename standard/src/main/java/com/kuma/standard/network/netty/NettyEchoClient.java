package com.kuma.standard.network.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.java.Log;
import lombok.val;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Netty echo client
 *
 * @author kuma 2021-02-23
 */
@Log
public class NettyEchoClient {
    private final int port;
    private final String ip;

    public NettyEchoClient(String ip, int port) {
        this.port = port;
        this.ip = ip;
    }

    public void startClient() {
        val loopGroup = new NioEventLoopGroup();
        try {
            val bootstrap = new Bootstrap();
            bootstrap.group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.ALLOCATOR, ByteBufAllocator.DEFAULT)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(NettyEchoClientHandler.INSTANCE);
                        }
                    });
            val connectFuture = bootstrap.connect(ip, port);
            connectFuture.addListener(l -> {
                if (l.isSuccess()) {
                    log.info("连接成功");
                } else {
                    log.info("连接失败");
                }
            });
            connectFuture.sync();
            val channel = connectFuture.channel();
            val scanner = new Scanner(System.in);
            System.out.println("请输入发送内容: ");
            while (scanner.hasNext()) {
                val next = scanner.next();
                val bytes = (LocalDateTime.now() + " >> " + next).getBytes(StandardCharsets.UTF_8);
                val buffer = channel.alloc().buffer();
                buffer.writeBytes(bytes);
                channel.writeAndFlush(buffer);
                System.out.println("请输入发送内容: ");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            loopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyEchoClient("localhost", 8080).startClient();
    }
}
