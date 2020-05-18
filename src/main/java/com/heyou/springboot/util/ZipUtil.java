package com.heyou.springboot.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    public static Map<String,InputStream> upZipFile(MultipartFile multipartFile) throws IOException {
        Map<String,InputStream> map = new HashMap<>();
        File file =readFile(multipartFile);
        ZipFile zipFile = new ZipFile(file, StandardCharsets.UTF_8);
        FileInputStream input = new FileInputStream(file);
        ZipInputStream zipInputStream = new ZipInputStream(input, StandardCharsets.UTF_8);
        ZipEntry ze = null;
        // 循环遍历
        while ((ze = zipInputStream.getNextEntry()) != null) {
            String path = ze.getName();
            // 过滤 MacOS 解压出现的脏文件
            if(!(path.startsWith("__MACOSX/")|| path.endsWith(".DS_Store"))){
                if (!ze.isDirectory()) {
                    InputStream inputStream = zipFile.getInputStream(ze);
                    map.put(path,inputStream);
                }
            }
        }
        // 一定记得关闭流
        return map;
    }

    public static File readFile(MultipartFile multipartFile) throws IOException {
        File toFile = null;
        if (multipartFile.equals(StringUtils.EMPTY) || multipartFile.getSize() <= 0) {
            multipartFile = null;
        } else {
            InputStream ins = null;
            ins = multipartFile.getInputStream();
            toFile = new File(multipartFile.getOriginalFilename());
            inputStreamToFile(ins, toFile);
        }
        return toFile;
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断文件是否图片
     * @param fileName 文件的名称
     * @return boolean
     */
    private static boolean isImage(String fileName){
        String reg = ".+(.JPEG|.jpeg|.JPG|.jpg)$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(fileName);
        return matcher.find();
    }

    /**
     * 图片文件流转 Base64存储
     * @param inputStream
     * @return
     * @throws IOException
     */
    private String InputStreamTobase64(InputStream inputStream) throws IOException {
        byte[] bytes = IOUtils.toByteArray(inputStream);
        String encoded = Base64.getEncoder().encodeToString(bytes);
        return encoded;
    }

    /**
     * 解密
     * @param base64Str base64 字符串
     */
    private void base64ToOutputStream(String base64Str){
        byte[] decodedBytes = Base64.getDecoder().decode(base64Str);
    }
}
