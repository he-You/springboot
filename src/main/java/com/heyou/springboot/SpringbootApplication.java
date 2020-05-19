package com.heyou.springboot;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author heyou
 */
@SpringBootApplication
@MapperScan("com.heyou.springboot.dao")
@EnableEncryptableProperties
@EnableRetry
public class SpringbootApplication {

    public static void main(String[] args) {
        System.out.println("启动中....");
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
