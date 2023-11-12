package com.xjc.nio;

import java.nio.ByteBuffer;

/**
 * @author JC
 * @create 2023/11/9
 */
public class MyByteBuffer {

    public static void main(String[] args) {
        String msg = "my byteBuffer";
        // 创建固定大小的buffer，返回是heapByteBuffer
        ByteBuffer allocate = ByteBuffer.allocate(500);
        byte[] bytes = msg.getBytes();
        // 写入数据到buffer
        allocate.put(bytes);
        // 切换成读模式
        allocate.flip();
        // 创建临时数组，用于存储获取到的数据
        byte[] tmpByte = new byte[bytes.length];
        int i = 0;
        while (allocate.hasRemaining()) {
            tmpByte[i] = allocate.get();
            i++;
        }
//        tmpByte = allocate.array();
        System.out.println(new String(tmpByte));

    }

}
