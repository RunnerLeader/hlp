package com.note.nettyDemo.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class EchoClient {
    public static void main(String[] args) {
        new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf buff = msg instanceof ByteBuf? (ByteBuf) msg : null;
                                System.out.println(buff.toString(Charset.defaultCharset()));
                            }

                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                ByteBuf buffer = ctx.alloc().buffer(20);
                                //首次建立链接 发送消息
                                buffer.writeBytes("hi~".getBytes());
                                ctx.writeAndFlush(buffer);

                                //思考  是否需要释放buffer
                            }
                        });
                    }
                })
                .connect(new InetSocketAddress("localhost", 9999));
    }
}
