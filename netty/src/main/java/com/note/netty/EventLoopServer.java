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
    public static void main(String[] args) {
        ChannelHandler handle1 = new ChannelInboundHandlerAdapter(){
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                ByteBuf buf = (ByteBuf) msg;
                String content = buf.toString(Charset.defaultCharset());
                System.out.println("["+Thread.currentThread().getName() + "] ==> " + content);
                ctx.fireChannelRead(msg);
            }
        };

        ChannelHandler handle2 = new ChannelInboundHandlerAdapter(){
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                ByteBuf buf = (ByteBuf) msg;
                String content = buf.toString(Charset.defaultCharset());
                TimeUnit.MILLISECONDS.sleep(3000);
                System.out.println("["+Thread.currentThread().getName() + "] ==> " + content);
            }
        };

        //DefaultEventLoopGroup 可执行普通任务 定时任务
        DefaultEventLoopGroup group = new DefaultEventLoopGroup();

        new ServerBootstrap()
                .group(new NioEventLoopGroup(), new NioEventLoopGroup(2))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast("handler-1", handle1)
                                .addLast(group, "handler-2", handle2);
                    }
                }).bind(9999);
    }


}
