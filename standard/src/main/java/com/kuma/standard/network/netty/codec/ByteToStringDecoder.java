package com.kuma.standard.network.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * ByteToStringDecoder
 *
 * @author kuma 2021-02-24
 */
public class ByteToStringDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        // 取消息头
        // 设置回滚点，将消息攒着，直到能完整解析
        in.markReaderIndex();
        // 读指针移动了
        int length = in.readInt();
        // 取 Body 部分
        // 如果消息体长度不够，重置到消息头的起始位置
        if (in.readableBytes() < length) {
            in.resetReaderIndex();
            return;
        }
        // 可以读取消息体了
        byte[] bytes = new byte[length];
        // 从读指针开始读 length 长度的字节
        in.readBytes(bytes, 0, length);
        out.add(new String(bytes, StandardCharsets.UTF_8));
    }
}
