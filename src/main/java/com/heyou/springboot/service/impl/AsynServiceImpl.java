package com.heyou.springboot.service.impl;

import com.heyou.springboot.service.AsynService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author heyou(heyou_0423 @ 163.com)
 * @date 2020/7/16 22:42
 */
@Service
public class AsynServiceImpl implements AsynService {
    private static Logger logger = LogManager.getLogger(AsynServiceImpl.class.getName());

    @Async("asyncServiceExecutor")
    @Override
    public void writeData(String data) {
        logger.info("线程-" + Thread.currentThread().getId() + "在执行写入:"+data);
    }

}
