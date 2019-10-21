package com.heyou.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author heyou
 */
@SpringBootApplication
@MapperScan("com.heyou.springboot.dao")
public class SpringbootApplication {

    public static void main(String[] args) {
        System.out.println("启动中....");
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
