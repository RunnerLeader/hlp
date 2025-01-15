package com.note.netty.note;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class FutureNote {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        EventLoop eventLoop = group.next();

        Future<String> future = eventLoop.submit(() -> {
            System.out.println("task is running...");
            return "task is finish";
        });

        //同步
//        future.sync();
//        future.get();

        //异步
        future.addListener(new GenericFutureListener<Future<? super String>>() {
            @Override
            public void operationComplete(Future<? super String> future) throws Exception {
                System.out.println(future.getNow());
            }
        });
    }
}
