package com.note.nettyDemo.粘包;

import com.note.netty.PrintUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class ShortServer {
    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ChannelFuture channelFuture = new ServerBootstrap()
                    .group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    // 调整系统接收缓冲区(滑动窗口)
                    //.option(ChannelOption.SO_RCVBUF, 10)
                    // 调整netty的接收缓冲区(ByteBuf)
                    .childOption(ChannelOption.RCVBUF_ALLOCATOR, new AdaptiveRecvByteBufAllocator(16,16,16))
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            //定长解码器
                            //ch.pipeline().addLast(new FixedLengthFrameDecoder(10));
                            //行解码器
                            //ch.pipeline().addLast(new LineBasedFrameDecoder(256));
                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 1, 4, 1, 4));
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    ByteBuf buf = (ByteBuf) msg;
                                    PrintUtil.log(buf);
                                }
                            });
                        }
                    })
                    .bind(9999).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
