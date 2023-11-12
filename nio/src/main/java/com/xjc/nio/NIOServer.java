package com.xjc.nio;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author JC
 * @create 2023/11/11
 */
public class NIOServer {
    @SneakyThrows
    public static void main(String[] args) {
        SocketAddress socketAddress = new InetSocketAddress("localhost", 8888);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(socketAddress);
        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        // 注册选择器，监听连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            // 等待3秒，返回0 相当于没有时间，跳过
            if (selector.select(3000) == 0) {
                System.out.println("服务器等待3秒，无连接");
                continue;
            }
            // 如果有事件，selector.select(3000)>0的情况，获取时间
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
            while (selectionKeyIterator.hasNext()) {
                // 获取事件
                SelectionKey selectionKey = selectionKeyIterator.next();
                if (selectionKey.isAcceptable()) { // 连接事件
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    // 把socketChannel注册到selector，监听读事件，并绑定一个缓冲区
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(10240));
                }
                if (selectionKey.isReadable()) { // 读事件
                    // 获取通道
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    // 获取关联的bytebuffer
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    socketChannel.read(byteBuffer);
                    System.out.println("from client : " + new String(byteBuffer.array()));
                }
                selectionKeyIterator.remove();
            }
        }
    }
}