package com.note.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

public class HelloServer {
    public static void main(String[] args) {
        //1 创建启动器 组装netty组件
        new ServerBootstrap()
                //2 bossEventLoop WorkerEventLoop(selector, thread) group 组
                .group(new NioEventLoopGroup())
                //3 服务器用NioServerSocketChannel实现
                .channel(NioServerSocketChannel.class)
                //4 boss 负责处理链接   worker(child)负责处理读写
                .childHandler(
                        //5 channel代表和客户端进行数据读写的通道   Initializer初始化, 负责添加别的handler
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel nsc) throws Exception {
                                //6 添加具体的handler
                                nsc.pipeline().addLast(new StringDecoder());//编码器
                                //
                                nsc.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                                    //处理读事件
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println(msg);
                                    }
                                });

                            }
                        })
                //6 绑定端口
                .bind(9999);
    }
}
