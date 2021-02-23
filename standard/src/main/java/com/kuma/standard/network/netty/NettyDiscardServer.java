package com.kuma.standard.network.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.java.Log;
import lombok.val;

/**
 * Netty 版 Discard 服务器
 *
 * @author kuma 2021-02-23
 */
@Log
public class NettyDiscardServer {
    private final int port;
    public NettyDiscardServer(int port) {
        this.port = port;
    }

    public void startServer() {
        val acceptorLoopGroup = new NioEventLoopGroup(1);
        val workerLoopGroup = new NioEventLoopGroup(1);

        try {
            val bootstrap = new ServerBootstrap();
            bootstrap.group(acceptorLoopGroup, workerLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(port)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 像流水线中注册处理器
                            socketChannel.pipeline().addLast(new NettyDiscardHandler());
                        }
                    });

            // 同步阻塞绑定端口
            val channelFuture = bootstrap.bind().sync();
            // 确定 bind() 方法执行完了之后，再确认端口
            log.info(" Server start, listening on port " + channelFuture.channel().localAddress());
            val closeFuture = channelFuture.channel().closeFuture();
            // 同步阻塞关闭通道
            closeFuture.sync();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            acceptorLoopGroup.shutdownGracefully();
            workerLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyDiscardServer(8080).startServer();
    }
}
