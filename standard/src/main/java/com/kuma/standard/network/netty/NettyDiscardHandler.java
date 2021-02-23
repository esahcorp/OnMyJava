package com.kuma.standard.network.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.java.Log;
import lombok.val;

/**
 * Netty IO 处理器
 * 仅仅负责读事件
 *
 * @author kuma 2021-02-23
 */
@Log
public class NettyDiscardHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        val byteBuf = (ByteBuf) msg;
        try {
            log.info("收到消息：");
            while (byteBuf.isReadable()) {
                System.out.print((char) byteBuf.readByte());
            }
            System.out.println();
            ctx.write(msg);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
