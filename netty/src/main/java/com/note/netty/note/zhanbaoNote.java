package com.note.netty.note;

public class zhanbaoNote {
    public static void main(String[] args) {

    }

    //粘包
    //现象  发送 abc def   接受 abcdef
    //原因 应用层: 接收方byteBuf设置太大(默认1024)
    //滑动窗口: 滑动窗口内缓冲了多个报文的时候就会粘包
    //Nagle算法: 会造成粘包   ip报头 20字节 tcp报头 20字节  Nagle算法会缓冲一批数据再发送

    //半包
    //现象 发送 abcdef  接受abc def
    //原因  应用层: 接收方ByteBuf小于实际发送数据量
    // 滑动窗口 滑动窗口内缓冲区不足 会先发送一部分数据 等待ack后才能发送剩下的部分  这就造成半包
    //mss限制  发送数据超过mss限制 会将数据切分发送 就会造成半包

    //本质上是因为tcp是流式协议  消息无边界


    //解决方案
    // 短链接  可以解决粘包 解决不了半包
    // 定长解码器
    // 行解码器
    // 基于长度字段的解码器
        //int maxFrameLength,
    // int lengthFieldOffset,  从头偏移几个字节
    // int lengthFieldLength,   长度字段占几个字节
    // int lengthAdjustment,    长度字段之后额外有几个字节
    // int initialBytesToStrip  从头跳过几个字节
}
