package com.xjc.nio;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author JC
 * @create 2023/11/11
 */
public class MySocketChannel {
    @SneakyThrows
    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8888);
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.bind(inetSocketAddress);
            SocketChannel socketChannel = serverSocketChannel.accept();
            ByteBuffer byteBuffer = ByteBuffer.allocate(10240);
            while (socketChannel.read(byteBuffer) != -1) {
                System.out.println(new String(byteBuffer.array()));
            }
            byteBuffer.clear();
        }
    }
}
