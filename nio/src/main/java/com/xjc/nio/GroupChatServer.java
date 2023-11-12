package com.xjc.nio;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author JC
 * @create 2023/11/11
 */
public class GroupChatServer {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    public static final int PORT = 8888;

    @SneakyThrows
    public GroupChatServer() {
        this.selector = Selector.open();
        this.serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost",PORT));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }
    @SneakyThrows
    public void listen(){
        while (true) {
            int count = selector.select(2000);
            if (count>0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
                while (selectionKeyIterator.hasNext()) {
                    SelectionKey selectionKey = selectionKeyIterator.next();
                    if (selectionKey.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println(socketChannel.getRemoteAddress() + "connected:::");
                    }
                    if (selectionKey.isReadable()) {
                        readData(selectionKey);
                    }
                    selectionKeyIterator.remove();
                }
            }
        }
    }

    @SneakyThrows
    private void readData(SelectionKey selectionKey) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int count = socketChannel.read(byteBuffer);
            if (count>0) {
                String msg = new String(byteBuffer.array());
                System.out.println("form client:::"+ msg);
                notifyAllClient(msg,socketChannel);
            }
        }catch (Exception e){
            try {
                System.out.println(socketChannel.getRemoteAddress() + ":::disconnected");
                selectionKey.cancel();
                socketChannel.close();
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }

    @SneakyThrows
    private void notifyAllClient(String msg, SocketChannel noNotifyChannel) {
        System.out.println("服务器转发消息");
        for (SelectionKey key : selector.keys()) {
            Channel channel = key.channel();
            if (channel instanceof SocketChannel && channel != noNotifyChannel) {
                SocketChannel socketChannel = (SocketChannel) channel;
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                socketChannel.write(byteBuffer);
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
