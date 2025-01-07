package com.note.io;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class TestBufferBase {

    @Test
    public void bufferRead(){
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()){
            //分配内存
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true){
                //将数据读到buffer中
                int len = channel.read(buffer);
                if (len == -1){
                    break;
                }
                //切换到读模式
                buffer.flip();
                while (buffer.hasRemaining()){
                    System.out.print((char) buffer.get());
                }
                //切换到写模式
                buffer.clear();
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void bufferWrite(){
        //分配内存
        ByteBuffer buffer = ByteBuffer.allocate(20);
        //向buffer中写入数据
        buffer.put("abc".getBytes());
        //切换到读模式
        buffer.flip();
        //从buffer中读取数据
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        //重置position
        buffer.rewind();
        buffer.get();
        //标记
        buffer.mark();
        buffer.get();
        //跳转到标记
        buffer.reset();
        buffer.get();
        //切换到写模式
        buffer.clear();
    }

    //转字符串
    @Test
    public void buffer2String(){
        //1 字符串转buffer  需要手动切换读模式
        ByteBuffer buffer1 = ByteBuffer.allocate(20);
        buffer1.put("abc".getBytes());
        //2 charset 自动切换读模式
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("hello");
        //3 wrap
        ByteBuffer buffer3 = ByteBuffer.wrap("world".getBytes());

        String s1 = StandardCharsets.UTF_8.decode(buffer1).toString();
        String s2 = StandardCharsets.UTF_8.decode(buffer2).toString();
        String s3 = StandardCharsets.UTF_8.decode(buffer3).toString();
    }

    //分散读集中写
    @Test
    public void splitReadFocusWrite(){
        try (FileChannel channel = new RandomAccessFile("data.txt", "r").getChannel();
                FileChannel channel1 = new RandomAccessFile("data1.txt", "rw").getChannel()
        ){
            //分散读
            ByteBuffer b1 = ByteBuffer.allocate(3);
            ByteBuffer b2 = ByteBuffer.allocate(3);
            ByteBuffer b3 = ByteBuffer.allocate(3);
            channel.read(new ByteBuffer[]{b1,b2,b3});
            //集中写
            ByteBuffer bb1 = StandardCharsets.UTF_8.encode("hello");
            ByteBuffer bb2 = StandardCharsets.UTF_8.encode("world");
            ByteBuffer bb3 = StandardCharsets.UTF_8.encode("你好");
            channel1.write(new ByteBuffer[]{bb1,bb2,bb3});
            System.out.println("111");
        }catch (Exception e){

        }
    }
}
