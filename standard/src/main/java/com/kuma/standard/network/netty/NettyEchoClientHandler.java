package com.kuma.standard.network.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.java.Log;
import lombok.val;

import java.nio.charset.StandardCharsets;

/**
 * @author kuma 2021-02-23
 */
@Log
@ChannelHandler.Sharable
public class NettyEchoClientHandler extends ChannelInboundHandlerAdapter {

    public static final NettyEchoClientHandler INSTANCE = new NettyEchoClientHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        int length = buf.readableBytes();
        val array = new byte[length];
        buf.getBytes(0, array);
        log.info("client received: " + new String(array, StandardCharsets.UTF_8));
        super.channelRead(ctx, msg);
    }
}
