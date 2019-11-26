package com.heyou.springboot.entity.base;



import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Jwt负载
 * @author heyou
 * @date 2019-11-26 20:24
 */
@Data
public class JwtPayload {
    //发布者
    private String issuer;
    //主题
    private String subject;

    //签名的观众 也可以理解谁接受签名的
    private List<String> audience;
    //发布时间
    private Date issuedAt;
    //过期时间
    private Date expiresAt;
    //开始使用时间
    private Date notBefore;
    //自定义签名
    private Map<String,String> claims;
}
