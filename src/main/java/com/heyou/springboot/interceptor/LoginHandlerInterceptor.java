package com.heyou.springboot.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.heyou.springboot.constant.Enum.CodeEnum;
import com.heyou.springboot.controller.exception.SysException;
import com.heyou.springboot.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 拦截器
 * @author heyou
 * @date 2019-11-21 16:27
 */
@Slf4j
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Resource
    JwtUtil jwtUtil;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 目标方法执行之前
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Token Checkout processing");
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            return true;
        }
//        if (StringUtils.isEmpty(token)) {
//            throw new SysException(CodeEnum.TOKEN_ISEMPTY);
//        }
        String tokenInServletContext = (String)request.getServletContext().getAttribute(token);
        if(StringUtils.isEmpty(tokenInServletContext)) {
            throw new SysException(CodeEnum.ILLEGAL_TOKEN);
        }
        try {
            jwtUtil.verifyToken(token);
        } catch (AlgorithmMismatchException e) {
            log.error("Token Checkout processing AlgorithmMismatchException 异常！"+e.getLocalizedMessage());
            throw new SysException(CodeEnum.ILLEGAL_TOKEN);
        }catch (TokenExpiredException e) {
            log.info("token已经过期");
            throw new SysException(CodeEnum.EXPIRE_TOKEN);
        }catch (SignatureVerificationException e) {
            log.error("Token Checkout processing SignatureVerificationException 异常！"+e.getLocalizedMessage());
            throw new SysException(CodeEnum.ILLEGAL_TOKEN);
        }catch (Exception e) {
            log.error("Token Checkout processing 未知异常！"+e.getLocalizedMessage());
            throw e;
        }
        return true;
    }

    /**
     * controller 调用之后被调用，如果有异常则不调用
     */

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("postHandle");

        long startTime = (long) request.getAttribute("startTime");
        System.out.println("时间拦截器耗时:"+(System.currentTimeMillis() -startTime));
    }

    /**
     * //controller 调用之后被调用，有没有异常都会被调用,Exception 参数里放着异常信息
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}

    /*@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        logger.info("在请求处理之前进行调用（Controller方法调用之前）");
        request.setAttribute("startTime", System.currentTimeMillis());
        logger.info("已收到请求："+request);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        return true;
    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后），如果异常发生，则该方法不会被调用");
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String path = request.getServletPath();
        if(path.contains("/app/dealer/weChat")){
            //允许所有来源访问
            response.setHeader("Access-Control-Allow-Origin","*");
            //允许访问的方式
            response.setHeader("Access-Control-Allow-Method","POST,GET");
        }
        if (!path.matches(CommonConstant.NO_LOGIN_PATH)) {
            //请求结束将客户请求清除
            String token = request.getHeader(CommonConstant.TOKE_PARAM);
            if (SysUtil.isEmpty(token)) {
                token = request.getParameter(CommonConstant.TOKE_PARAM);
            }
            //验证token
            //String tokenId = TokenUtil.getClaims(token).getId();
            //拼接redis中登录信息的key
            //String reqKey = SysEnum.TOKEN_MAP.REQUEST_TOKEN.name + tokenId + path;
            //清除redis数据
            //RedisUtil.redisTemplateStatic.delete(reqKey);
        }
    }

    */
    /**
     * 	处理未登录,或登录失效
     * @param response
     * @param obj
     *//*
    private void dealNotLoginReturn(HttpServletRequest request,HttpServletResponse response, Object obj) {
        String json = JsonUtil.objectToJson(obj);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpStatus.OK.value());
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException ex) {
            logger.error("response error", ex);
        } finally {
            if (writer != null){
                writer.close();
            }
        }
    }

}*/
