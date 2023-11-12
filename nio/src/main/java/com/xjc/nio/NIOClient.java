package com.xjc.nio;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author JC
 * @create 2023/11/11
 */
public class NIOClient {
    @SneakyThrows
    public static void main(String[] args) {
        SocketChannel socketChannel = SocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8888);
        socketChannel.configureBlocking(false);
        boolean connect = socketChannel.connect(inetSocketAddress);
        // 等待连接
        if (!connect) {
            while (!socketChannel.finishConnect()) {
                System.out.println("client connecting…………");
            }
        }
        String msg = "hello this is client message";
        ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
        socketChannel.write(byteBuffer);
        // 程序卡在这个位置
        System.in.read();
    }
}
