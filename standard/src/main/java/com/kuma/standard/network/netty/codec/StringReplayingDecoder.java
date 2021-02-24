package com.kuma.standard.network.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Replaying Decoder demo
 *
 * @author kuma 2021-02-24
 */
public class StringReplayingDecoder extends ReplayingDecoder<StringReplayingDecoder.Status> {
    private int length;

    public StringReplayingDecoder() {
        super(Status.PARSE_1);
    }

    /*
     * 读取过程中，长度不固定，需要依靠传输协议先获取内容长度，然后用读指针来区分各种不同的状态
     */

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        switch (state()) {
            case PARSE_1:
                length = in.readInt();
                checkpoint(Status.PARSE_2);
                break;
            case PARSE_2:
                byte[] bytes = new byte[length];
                in.readBytes(bytes, 0, length);
                out.add(new String(bytes, StandardCharsets.UTF_8));
                // 更新状态和读指针
                checkpoint(Status.PARSE_1);
                break;
            default:
                break;
        }
    }

    enum Status {
        PARSE_1, PARSE_2
    }
}
