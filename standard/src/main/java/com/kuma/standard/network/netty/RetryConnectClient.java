package com.kuma.standard.network.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.val;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author kuma 2021-02-25
 */
public class RetryConnectClient {

    private static final int MAX_RETRY = 5;

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(loopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {

                    }
                });
        val connectFuture = retryConnect(bootstrap, MAX_RETRY);
//         // 强制同步会报错，但不影响重试，保持异步重试比较好
//        connectFuture.sync();

        connectFuture.channel().closeFuture().sync();
    }


    private static ChannelFuture retryConnect(Bootstrap bootstrap, int retry) {
        return bootstrap.connect("localhost", 8080)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println("连接成功 ～");
                    } else if (retry == 0) {
                        System.out.println("重连次数用完了，放弃连接");
                    } else {
                        // 从 1 开始的重连次数，重连 5 次
                        int times = MAX_RETRY - retry + 1;
                        int delay = 1 << times;
                        System.out.println(new Date() + ": 连接失败，第" + times + "次重连...");
                        bootstrap.config().group().schedule(() -> retryConnect(bootstrap, retry - 1), delay, TimeUnit.SECONDS);
                    }
                });
    }
}
