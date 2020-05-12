package com.heyou.springboot.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * 读取zip包中的文件
 *
 * @author heyou(heyou_0423 @ 163.com)
 * @date 2020/5/12 22:04
 */
public class ZipUtil {
    public static void upZipFile(String filePath) throws IOException {
        //String filePath = "/Users/heyou-macbook/Desktop/test2.zip";
        File file = new File(filePath);
        ZipFile zipFile = new ZipFile(file, StandardCharsets.UTF_8);
        FileInputStream input = new FileInputStream(file);
        ZipInputStream zipInputStream = new ZipInputStream(input, StandardCharsets.UTF_8);
        ZipEntry ze = null;
        // 循环遍历
        while ((ze = zipInputStream.getNextEntry()) != null) {
            String path = ze.getName();
            // 过滤 MacOS 解压出现的脏文件
            if(!(path.startsWith("__MACOSX/")|| path.endsWith(".DS_Store"))){
                System.out.println(path);
                if (!ze.isDirectory() && ze.toString().endsWith("txt")) {
                    System.out.println("文件内容:");
                    InputStream inputStream = zipFile.getInputStream(ze);
                    List<String> texts = IOUtils.readLines(inputStream);
                    for (String text : texts) {
                        System.out.println(text);
                    }
                    inputStream.close();
                }
            }
        }
        // 一定记得关闭流
        zipInputStream.closeEntry();
        input.close();
    }
}
