package com.note.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BioServerClient {
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(8);

        //创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //设置ssc非阻塞模式
        ssc.configureBlocking(false);
        //绑定端口
        ssc.bind(new InetSocketAddress(9999));

        List<SocketChannel> scList = new ArrayList<>();

        while (true){
            //接受客户端链接请求 建立连接  非阻塞 无链接返回null
            SocketChannel sChannel = ssc.accept();
            if (sChannel != null){
                //设置sc非阻塞模式
                sChannel.configureBlocking(false);
                scList.add(sChannel);
            }

            for (SocketChannel sc : scList) {
                //读取数据 非阻塞  无数据返回0
                int content = sc.read(buffer);
                if (content > 0){
                    buffer.flip();
                    System.out.println(StandardCharsets.UTF_8.decode(buffer));
                    buffer.clear();
                }else if (content < 0){
                    System.out.println("客户端关闭链接: " + content);
                }
            }


        }


    }


    static class Client{
        public static void main(String[] args) throws IOException {
            SocketChannel sc = SocketChannel.open();
            sc.connect(new InetSocketAddress("localhost",9999));
            System.out.println(1111);
            sc.close();
        }
    }
}
