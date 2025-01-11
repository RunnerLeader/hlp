package com.note.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class FutureClient {
    public static void main(String[] args) throws InterruptedException {
        //1 启动类
        ChannelFuture channelFuture = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel nsc) throws Exception {
                                //添加编码器
                                nsc.pipeline().addLast(new StringEncoder());
                            }
                        })
                // nio 的connect是异步非阻塞  由nio线程执行链接动作  主线程会继续往下执行
                .connect(new InetSocketAddress("localhost", 9999));
        /**
         //这里sync阻塞主线程 等待链接建立完成
         channelFuture.sync();
         //链接建立好后  再往下执行
         Channel channel = channelFuture.channel();
         channel.writeAndFlush("hello world");
         */

        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                Channel channel = channelFuture.channel();
                channel.writeAndFlush("hello world");
            }
        });





    }
}
