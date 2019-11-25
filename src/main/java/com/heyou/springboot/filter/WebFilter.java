package com.heyou.springboot.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author heyou
 * @date 2019-11-25 23:35
 */
@Slf4j
@Component
public class WebFilter implements Filter {
    private  Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //初始化
        logger.info("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //链路 直接传给下一个过滤器
        logger.info("过滤器进行请求过滤");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info("过滤器销毁");
    }
}
