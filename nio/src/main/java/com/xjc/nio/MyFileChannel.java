package com.xjc.nio;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author JC
 * @create 2023/11/11
 */
public class MyFileChannel {
    @SneakyThrows
    public static void main(String[] args) {
        try (FileInputStream fileInputStream = new FileInputStream("pom.xml")) {
            // 文件输入流通道
            FileChannel fileInputStreamChannel = fileInputStream.getChannel();
            try (FileOutputStream fileOutputStream = new FileOutputStream("pom-channel.xml")) {
                // 文件输出流通道
                FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(10240);
                /**
                 * 普通模式
                 */
                // 输入流通道的数据读到缓冲区
//                fileInputStreamChannel.read(byteBuffer);
//                // 切换成读模式
//                byteBuffer.flip();
//                // 从缓冲区写入到输出流
//                fileOutputStreamChannel.write(byteBuffer);

                /**
                 * transfer 方式
                 */
//                fileInputStreamChannel.transferTo(0,byteBuffer.limit(),fileOutputStreamChannel);
                fileOutputStreamChannel.transferFrom(fileInputStreamChannel,0,byteBuffer.limit());

            }
        }
    }
}
