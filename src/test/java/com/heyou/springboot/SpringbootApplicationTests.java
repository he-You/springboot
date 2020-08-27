package com.heyou.springboot;

import com.heyou.springboot.service.AsynService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {
    @Autowired
    private AsynService asynService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void write() {
        for(int i=0;i<100;i++){
            asynService.writeData("this is "+String.valueOf(i));
        }

    }
}
