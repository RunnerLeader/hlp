package com.note.netty;

import io.netty.buffer.ByteBuf;

import static io.netty.buffer.ByteBufUtil.appendPrettyHexDump;
import static io.netty.util.internal.StringUtil.NEWLINE;

public class PrintUtil {
    public static void log(ByteBuf buf) {
        int len = buf.readableBytes();
        int rows = len / 16 + (len % 15 == 0 ? 0 : 1) + 4;
        StringBuilder builder = new StringBuilder(rows * 80 * 2)
                .append("read index: ").append(buf.readerIndex())
                .append(", write index: ").append(buf.writerIndex())
                .append(", capacity: ").append(buf.capacity())
                .append(NEWLINE);
        appendPrettyHexDump(builder, buf);
        System.out.println(builder);
    }
}
