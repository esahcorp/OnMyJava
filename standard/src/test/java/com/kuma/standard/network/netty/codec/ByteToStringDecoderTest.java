package com.kuma.standard.network.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author kuma 2021-02-24
 */
public class ByteToStringDecoderTest {

    @Test
    public void testByteToStringDecoder() {
        val initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new ByteToStringDecoder()).addLast(new StringPrintHandler());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        byte[] bytes = "从前有座山 ".getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < 100; i++) {
            // 构建 buf
            int random = new Random().nextInt(3);
            ByteBuf buf = Unpooled.buffer();
            buf.writeInt(random * bytes.length);
            for (int j = 0; j < random; j++) {
                buf.writeBytes(bytes);
            }
            // 模拟发送数据
            channel.writeInbound(buf);
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Assert.assertTrue(true);
    }

}