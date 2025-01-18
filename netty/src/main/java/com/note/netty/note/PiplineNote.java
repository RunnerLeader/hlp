package com.note.netty.note;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Data;

import java.nio.charset.Charset;

public class PiplineNote {
    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("handler1", new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println(Thread.currentThread().getName() + "-handler1");
                                ByteBuf buf = (ByteBuf) msg;
                                super.channelRead(ctx, buf.toString(Charset.defaultCharset()));
                            }
                        });
                        pipeline.addLast("handler2", new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println(Thread.currentThread().getName() + "-handler2");
                                User user = new User((String) msg);
                                super.channelRead(ctx, user);
                            }
                        });
                        pipeline.addLast("handler3", new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println(Thread.currentThread().getName() + "-handler3");
                                System.out.println(msg + ": " + msg.getClass());
//                                ctx.writeAndFlush(ctx.alloc().buffer().writeBytes("hello".getBytes()));
                                ch.writeAndFlush(ctx.alloc().buffer().writeBytes("nihao".getBytes()));
                            }
                        });
                        pipeline.addLast("handler4", new ChannelOutboundHandlerAdapter(){
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                System.out.println(Thread.currentThread().getName() + "-handler4");
                                super.write(ctx, msg, promise);
                            }
                        });
                        pipeline.addLast("handler5", new ChannelOutboundHandlerAdapter(){
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                System.out.println(Thread.currentThread().getName() + "-handler5");
                                super.write(ctx, msg, promise);
                            }
                        });
                    }
                })
                .bind(9999);
    }

    @Data
    static class User{
        private String name;

        public User(String name) {
            this.name = name;
        }
    }
}
