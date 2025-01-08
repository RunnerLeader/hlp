package com.note.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class BufferBase {

    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(9999));
        ssc.configureBlocking(false);
        ssc.register(selector, SelectionKey.OP_ACCEPT);


        while (true){
            //监听事件
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()) {
                    System.out.println("建立链接");
                    //链接事件
                    ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssChannel.accept();
                    sc.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(8);
                    sc.register(selector, SelectionKey.OP_READ, buffer);
                }else if (key.isReadable()){
                    //read事件
                    try {
                        SocketChannel sc = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        int content = sc.read(buffer);
                        if (content > 0){
                            buffer.flip();
                            //todo 这里要考虑粘包半包和buffer扩容
                            System.out.println(StandardCharsets.UTF_8.decode(buffer).toString());
                            buffer.clear();
                        }else if (content < 0){
                            System.out.println("客户端退出链接, 事件取消");
                            key.cancel();
                        }
                    }catch (Exception e){
                        System.out.println("客户端异常, 事件取消");
                        key.cancel();
                    }
                }else if (key.isWritable()){
                    //write事件
                }
            }

        }
    }

    static class Client{
        public static void main(String[] args) throws IOException {
            SocketChannel sc = SocketChannel.open();
            sc.connect(new InetSocketAddress("localhost", 9999));
        }


    }

}
