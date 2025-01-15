package com.note.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class FutureClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        //1 启动类
        ChannelFuture channelFuture = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel nsc) throws Exception {
                                nsc.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                                //添加编码器
                                nsc.pipeline().addLast(new StringEncoder());
                            }
                        })
                // nio 的connect是异步非阻塞  由nio线程执行链接动作  主线程会继续往下执行
                .connect(new InetSocketAddress("localhost", 9999));

        //这里sync阻塞主线程 等待链接建立完成
         channelFuture.sync();
         //链接建立好后  再往下执行
         Channel channel = channelFuture.channel();

//        channelFuture.addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture channelFuture) throws Exception {
//                Channel channel = channelFuture.channel();
//                channel.writeAndFlush("hello world");
//            }
//        });

        new Thread(()->{
            Scanner scanner = new Scanner(System.in);
            while (true){
                String line = scanner.nextLine();
                if ("q".equals(line)){
                    //close 是异步的
                    channel.close();
                    break;
                }
                channel.writeAndFlush(line);
            }
        }, "input").start();


        ChannelFuture closeFuture = channel.closeFuture();
//        //这里sync阻塞主线程 等待链接关闭之后处理
//        closeFuture.sync();
//        System.out.println("关闭之后处理");

        closeFuture.addListener((ChannelFutureListener) channelFuture1 -> {
            System.out.println("关闭之后处理");
            //关闭客户端
            group.shutdownGracefully();
        });



    }
}
