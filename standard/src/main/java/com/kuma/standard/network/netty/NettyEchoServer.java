package com.kuma.standard.network.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.java.Log;
import lombok.val;

/**
 * Netty Echo server
 *
 * @author kuma 2021-02-23
 */
@Log
public class NettyEchoServer {

    private final int port;

    public NettyEchoServer(int port) {
        this.port = port;
    }

    public void startServer() {
        val bossGroup = new NioEventLoopGroup(1);
        val workerGroup = new NioEventLoopGroup(1);
        try {
            val bootstrap = new ServerBootstrap();
            bootstrap.group(workerGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) {
                            ch.pipeline().addLast(NettyEchoServerHandler.INSTANCE);
                        }
                    });
            // 同步阻塞绑定端口
            val channelFuture = bootstrap.bind(port).sync();
            // 确定 bind() 方法执行完了之后，再确认端口
            log.info(" Server start, listening on port " + channelFuture.channel().localAddress());
            val closeFuture = channelFuture.channel().closeFuture();
            // 同步阻塞关闭通道
            closeFuture.sync();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyEchoServer(8080).startServer();
    }
}
