package com.note.nettyDemo.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.Charset;

public class EchoServer {
    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

                                ByteBuf readBuf = msg instanceof ByteBuf? (ByteBuf) msg : null;
                                System.out.println(readBuf.toString(Charset.defaultCharset()));
                                //readBuf是否需要释放

                                ByteBuf writeBuf = ctx.alloc().buffer(20);
                                writeBuf.writeBytes("hello".getBytes());
                                ctx.writeAndFlush(writeBuf);
                                //writeBuf是否需要释放
                            }
                        });
                    }
                })
                .bind(9999);
    }
}
