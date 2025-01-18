package com.note.nettyDemo.粘包;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ShortClient {
    public static void main(String[] args) {
        //for (int i = 0; i < 10; i++) {
            send();
        //}
    }

    private static void send() {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ChannelFuture channelFuture = new Bootstrap()
                    .group(worker)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {

                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ByteBuf buf = ctx.alloc().buffer();
//                                    char c = '0';
//                                    for (int i = 0; i < 10; i++) {
//                                        StringBuilder builder = makeStr(c++, (int) (Math.random() * 101));
//                                        buf.writeBytes(builder.toString().getBytes());
//                                    }
                                    send(buf, "hello, world!");
                                    send(buf, "hello!");
                                    send(buf, "hi!");
                                    //buf.writeBytes(new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18});
                                    ctx.writeAndFlush(buf);
                                    ctx.channel().close();
                                }
                            });
                        }
                    })
                    .connect("localhost", 9999)
                    .sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }

    private static void send(ByteBuf buf, String content) {
        byte[] bytes = content.getBytes();
        int len = bytes.length;
        buf.writeByte(0);
        buf.writeInt(len);
        buf.writeByte(1);
        buf.writeBytes(bytes);
    }

    public static StringBuilder makeStr(char c, int len){
        StringBuilder builder = new StringBuilder(len+2);
        for (int i = 0; i < len; i++) {
            builder.append(c);
        }
        builder.append("\n");
        return builder;
    }
}
