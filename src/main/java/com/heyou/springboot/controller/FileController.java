package com.heyou.springboot.controller;

import com.heyou.springboot.util.ZipUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Map;

/**
 * @author heyou(heyou_0423 @ 163.com)
 * @date 2020/5/16 23:00
 */
@RestController
@RequestMapping(value = "/upload")
public class FileController {

    @PostMapping(value = "/zipFile")
    public void uploadFile(MultipartFile multipartFile) throws IOException {
        Map<String, InputStream> map = ZipUtil.upZipFile(multipartFile);
        for(Map.Entry<String,InputStream> entry : map.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            String suffix = entry.getKey().substring(entry.getKey().lastIndexOf("."));
            if(suffix.equals(".png")){
                byte[] bytes = IOUtils.toByteArray(entry.getValue());

                String encoded = Base64.getEncoder().encodeToString(bytes);
                String inputStreamStr = inputStreamToStr(entry.getValue());
                strToFile(inputStreamStr);
            }

        }
    }

    private String inputStreamToStr(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[0];
        bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String str = new String(bytes);
        return str;
    }

    private OutputStream strToFile(String str) throws IOException {
        OutputStream os = null;
        os.write(str.getBytes());
        return os;
    }
}
