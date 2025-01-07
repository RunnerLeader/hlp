package com.note.nio;

import sun.nio.ch.DirectBuffer;

import java.net.ServerSocket;
import java.nio.*;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioBase {
    public static void main(String[] args) {
        //nio 的三大组件
        //1 channel
        //1.1文件数据传输
        FileChannel fileChannel;
        //1.2 UDP数据传输
        DatagramChannel datagramChannel;
        //1.3 TCP数据传输
        SocketChannel socketChannel;
        ServerSocketChannel serverSocketChannel;
        //2 buffer
        ByteBuffer byteBuffer;
            MappedByteBuffer mappedByteBuffer;
            DirectBuffer directBuffer;
            //HeapByteBuffer heapByteBuffer;
        ShortBuffer shortBuffer;
        IntBuffer intBuffer;
        LongBuffer longBuffer;
        FloatBuffer floatBuffer;
        DoubleBuffer doubleBuffer;
        CharBuffer charBuffer;
        //3 selector
        //selector主要是配合线程管理多个channel的, channel是工作在非阻塞模式下的
        //调用selector的select()方法会阻塞 直到有channel发生读写事件, selector会将这些事件发送给线程处理



        //阻塞
        //在没有数据可读时或数据复制过程中, 线程必须阻塞等待, 不占用cpu资源, 但也不能干其他事情
        //32位jvm一个线程320k, 64位jvm一个线程1024k, 为了减少线程数, 要采用线程池技术
        //即便用了线程池, 如果很多链接简历, 但长时间inactive, 也会阻塞线程池中所有线程
    }
}
