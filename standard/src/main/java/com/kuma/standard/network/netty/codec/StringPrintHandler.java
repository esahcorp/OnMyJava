package com.kuma.standard.network.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 打印字符串
 *
 * @author kuma 2021-02-24
 */
public class StringPrintHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String s = (String) msg;
        System.out.println("输出一个字符串 >> " + s);
    }
}
