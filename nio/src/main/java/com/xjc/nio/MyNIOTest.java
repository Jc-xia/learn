package com.xjc.nio;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author JC
 * @create 2023/11/11
 */
public class MyNIOTest {
    public static void main(String[] args) {
        NIOServer.main(new String[]{});
        NIOClient.main(new String[]{});

    }


}




