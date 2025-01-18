package com.note.nettyDemo.粘包;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestLengthFieldDecoder {
    public static void main(String[] args) {
        EmbeddedChannel channel = new EmbeddedChannel(
                new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4)
                        ,new LoggingHandler(LogLevel.DEBUG)
        );
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        send(buf, "hello, world");
        send(buf, "hi~");
        channel.writeInbound(buf);
    }

    private static void send(ByteBuf buf, String content) {
        byte[] bytes = content.getBytes();
        int len = bytes.length;
        buf.writeInt(len);
        buf.writeBytes(bytes);
    }


}
