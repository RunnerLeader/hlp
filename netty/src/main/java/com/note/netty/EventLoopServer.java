package com.note.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class EventLoopServer {

    //处理普通任务
    static EventLoopGroup defaultGroup = new DefaultEventLoopGroup(3);

    public static void main(String[] args) {
        new ServerBootstrap()
                //第一个group  只负责 ServerSocketChannel上的 accept事件
                //第二个group  只负责 SocketChannel上的 读写事件
                .group(new NioEventLoopGroup(), new NioEventLoopGroup(2))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast("handler-1", new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        ByteBuf buf = (ByteBuf) msg;
                                        System.out.println(Thread.currentThread().getName()
                                                + ":" + buf.toString(Charset.defaultCharset()));
                                        //将消息传递给下一个handler
                                        ctx.fireChannelRead(msg);
                                    }
                                })
                                .addLast(defaultGroup, "handler2", new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        ByteBuf buf = (ByteBuf) msg;
                                        System.out.println(Thread.currentThread().getName()
                                                + ":" + buf.toString(Charset.defaultCharset()));
                                    }
                                });
                    }
                })
                .bind(9999);
    }


}
