package com.kuma.standard.network.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.java.Log;
import lombok.val;

import java.nio.charset.StandardCharsets;

/**
 * Netty Echo 处理器
 *
 * @author kuma 2021-02-23
 */
@Log
@ChannelHandler.Sharable
public class NettyEchoServerHandler extends ChannelInboundHandlerAdapter {

    public static final NettyEchoServerHandler INSTANCE = new NettyEchoServerHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        val buf = (ByteBuf) msg;
        log.info("msg type: " + (buf.isDirect() ? "堆外内存" : "堆内存"));
        val length = buf.readableBytes();
        val array = new byte[length];
        buf.getBytes(0, array);
        log.info("received: " + new String(array, StandardCharsets.UTF_8));
        log.info("准备写回数据，msg 引用计数: " + buf.refCnt());
        val channelFuture = ctx.writeAndFlush(msg);
        // 没有注册自定义的出站处理器，会走默认流程，HeadHandler 清除引用
        channelFuture.addListener(l -> log.info("写回后，msg 的引用计数: " + buf.refCnt()));
    }
}
