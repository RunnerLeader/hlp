package com.note.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufDemo {
    public static void main(String[] args) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        PrintUtil.log(buf);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            builder.append("a");
        }
        buf.writeBytes(builder.toString().getBytes());
        PrintUtil.log(buf);

        //堆内存
        ByteBuf buf1 = ByteBufAllocator.DEFAULT.heapBuffer();
        //直接内存
        ByteBuf buf2 = ByteBufAllocator.DEFAULT.directBuffer();

        //netty默认是开启池化功能的  可以复用ByteBuf

        //ByteBuf组成
        //读指针  写指针   容量  最大容量

        //内存释放
        //heapBuffer  GC回收
        //directBuffer  GC间接回收
        //池化回收


        System.out.println("================================================================================");

        //slice  零copy  分片引用
        ByteBuf buff = ByteBufAllocator.DEFAULT.buffer(10);
        buff.writeBytes(new byte[]{'a','b','c','d','e','f','g','h','i','j','k'});

        ByteBuf slice1 = buff.slice(0, 5);
        ByteBuf slice2 = buff.slice(5, 5);
        slice1.retain();//引用+1
        //用完以后
        slice1.release();//释放引用

        slice1.setByte(0, '0');
        PrintUtil.log(slice1);
        PrintUtil.log(slice2);

        //buff.release();//释放引用
        PrintUtil.log(buff);

        //也是零拷贝  全量
        //ByteBuf duplicate = buff.duplicate();

        //深copy
        //buff.copy();

        //组合
        ByteBuf sliceA = ByteBufAllocator.DEFAULT.buffer();
        sliceA.writeBytes(new byte[]{'a','b','c','d','e'});
        ByteBuf sliceB = ByteBufAllocator.DEFAULT.buffer();
        sliceB.writeBytes(new byte[]{'f','g','h','i','j'});

        CompositeByteBuf compositeByteBuf = ByteBufAllocator.DEFAULT.compositeBuffer();
        compositeByteBuf.addComponents(true, sliceA, sliceB);
        compositeByteBuf.retain();
        PrintUtil.log(compositeByteBuf);
        compositeByteBuf.release();

        ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(sliceA, sliceB);
        wrappedBuffer.retain();
        PrintUtil.log(wrappedBuffer);
        wrappedBuffer.release();



    }


}
