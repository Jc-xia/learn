package com.xjc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author JC
 * @create 2024/3/27
 */
@Controller
@Slf4j
public class TestController {

    @PostMapping("/test/fileUpload")
    public void fileUploadTest(@RequestParam("file") MultipartFile file) throws Exception {
        File newFile = new File("C:\\Users\\JC\\Desktop\\testfile.txt");

        // 手动获取输入、输出流
        FileOutputStream outputStream = new FileOutputStream(newFile);
        InputStream inputStream = file.getInputStream();
        byte[] bytes1 = file.getBytes();
        byte[] bytes = new byte[1024 * 1024];
        int read = inputStream.read(bytes);
        while (read != -1) {
            outputStream.write(bytes, 0, read);
            read = inputStream.read(bytes);
        }
        outputStream.flush();
    }
}
