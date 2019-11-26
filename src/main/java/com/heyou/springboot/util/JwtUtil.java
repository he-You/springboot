package com.heyou.springboot.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.heyou.springboot.entity.base.JwtPayload;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 简要说明:Jwt工具类:生成token和解析token
 *
 * @author heyou
 * @date 2019-11-26 20:27
 */
@Data
@Component
public class JwtUtil {
    private String secret = "secret";
    //发布者
    private String issuer = "USERSERVICE";
    //主题
    private String subject = "userLoginToken";

    //签名的观众 也可以理解谁接受签名的
    private String audience = "APP";
    //自定义签名
    private Map<String,String> claims;

    /**
     * 创建 hour小时后过期的Token
     */
    public String createToken(Map<String,String> claims,int hour) {
        JwtPayload createPayload = this.createPayload(1);
        createPayload.setClaims(claims);
        Algorithm hmac256 = Algorithm.HMAC256(this.getSecret());
        return createToken(createPayload,hmac256);
    }
    /**
     * 根据负载和算法创建Token
     */
    private String createToken(JwtPayload payload, Algorithm algorithm) {

        Builder headBuilder = createHeaderBuilder(algorithm);
        Builder publicClaimbuilder = addPublicClaimBuilder(headBuilder,payload);
        Builder privateClaimbuilder = addPrivateClaimbuilder(publicClaimbuilder,payload);
        return privateClaimbuilder.sign(algorithm);
    }
    /**
     * 创建自定小时后过期的负载
     */
    private JwtPayload createPayload(int hour) {
        List<String> audienceList = new ArrayList<>();
        JwtPayload payload = new JwtPayload();
        payload.setIssuer(this.getIssuer());
        payload.setSubject(this.getSubject());
        audienceList.add(this.getAudience());
        payload.setAudience(audienceList);
        this.setIssuedAtAndExpiresAt(new Date(), hour, payload);
        return payload;
    }
    /**
     * 创建自定小时后过期的负载
     * @param hour
     * @return
     */
    public JwtPayload createPayload(String issuer, String subject, String audience, Date date,int hour) {
        JwtPayload payload = new JwtPayload();
        List<String> audienceList = new ArrayList<>();
        payload.setIssuer(issuer);
        payload.setSubject(subject);
        audienceList.add(audience);
        payload.setAudience(audienceList);
        this.setIssuedAtAndExpiresAt(date, hour, payload);
        return payload;
    }
    /**
     * 添加私有声明
     */
    private Builder addPrivateClaimbuilder(Builder builder, JwtPayload payload) {
        Map<String, String> claims = payload.getClaims();
        if(!CollectionUtils.isEmpty(claims)) {
            claims.forEach(builder::withClaim);
        }
        return builder;
    }
    /**
     * 添加公共声明
     * @param builder
     * @param payload
     * @return
     */
    private Builder addPublicClaimBuilder(Builder builder, JwtPayload payload) {
        if(!StringUtils.isEmpty(payload.getIssuer())) {
            builder.withIssuer(payload.getIssuer());
        }

        if(!StringUtils.isEmpty(payload.getSubject())) {
            builder.withSubject(payload.getSubject());
        }

        if(payload.getIssuedAt() != null) {
            //生成签名的时间
            builder .withIssuedAt(payload.getIssuedAt());
        }

        if(payload.getExpiresAt() != null) {
            //签名过期的时间
            builder .withExpiresAt(payload.getExpiresAt());
        }

        if(CollectionUtils.isEmpty(payload.getAudience())) {
            payload.getAudience().forEach(builder::withAudience);
        }

        return builder;
    }
    /**
     * 创建JWT 头部信息
     */
    private Builder createHeaderBuilder(Algorithm algorithm) {
        return JWT.create().withHeader(buildJwtHeader(algorithm));
    }
    /**
     * 校验Token
     */
    public  JwtPayload verifyToken(String token) {
        DecodedJWT jwt;
        JwtPayload payload;
        try {
            jwt = getDecodedJwt(token);
            payload = getPublicClaim(jwt);
            payload = getPrivateClaim(jwt, payload);
        } catch (Exception e) {
            throw e;
        }
        return payload;
    }
    /**
     * 获取JWT 私有声明
     */
    private JwtPayload getPrivateClaim(DecodedJWT jwt, JwtPayload payload) {
        Map<String, String> claims = new HashMap<>();
        jwt.getClaims().forEach((k,v)->{
            String asString = v.asString();
            claims.put(k, asString);
        });
        payload.setClaims(claims);
        return payload;
    }
    /**
     * 获取JWT 公共声明
     */
    private JwtPayload getPublicClaim(DecodedJWT jwt) {
        JwtPayload payload = new JwtPayload();
        payload.setIssuer(jwt.getIssuer());
        payload.setSubject(jwt.getSubject());
        payload.setAudience(jwt.getAudience());
        payload.setIssuedAt(jwt.getIssuedAt());
        payload.setExpiresAt(jwt.getExpiresAt());
        return payload;
    }
    /**
     * 获取 DecodedJWT
     */
    private DecodedJWT getDecodedJwt(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(this.getSecret())).build();
        return verifier.verify(token);
    }
    /**
     * 构建JWT头部Map信息
     */
    private Map<String, Object> buildJwtHeader(Algorithm algorithm) {
        Map<String, Object> map = new HashMap<>();
        map.put("alg", algorithm.getName());
        map.put("typ", "JWT");
        return map;
    }

    /**
     * 根据发布时间设置过期时间
     */
    private void setIssuedAtAndExpiresAt(Date issuedAt, Integer hour, JwtPayload payload) {
        payload.setIssuedAt(issuedAt);
        payload.setExpiresAt(getAfterDateByHour(issuedAt,hour));
    }
    /**
     * 返回一定时间后的日期
     */
    private Date getAfterDateByHour(Date date, int hour){
        if(date == null){
            date = new Date();
        }
        return getAfterDate(date,0,0,0,hour,0,0);
    }
    public  Date getAfterDateByMinute(Date date, int minute){
        if(date == null){
            date = new Date();
        }
        return getAfterDate(date,0,0,0,0,minute,0);
    }
    /**
     * 返回一定时间后的日期
     * @param date 开始计时的时间
     * @param year 增加的年
     * @param month 增加的月
     * @param day 增加的日
     * @param hour 增加的小时
     * @param minute 增加的分钟
     * @param second 增加的秒
     * @return
     */
    private Date getAfterDate(Date date, int year, int month, int day, int hour, int minute, int second){
        if(date == null){
            date = new Date();
        }

        Calendar cal = new GregorianCalendar();

        cal.setTime(date);
        if(year != 0){
            cal.add(Calendar.YEAR, year);
        }
        if(month != 0){
            cal.add(Calendar.MONTH, month);
        }
        if(day != 0){
            cal.add(Calendar.DATE, day);
        }
        if(hour != 0){
            cal.add(Calendar.HOUR_OF_DAY, hour);
        }
        if(minute != 0){
            cal.add(Calendar.MINUTE, minute);
        }
        if(second != 0){
            cal.add(Calendar.SECOND, second);
        }
        return cal.getTime();
    }



}
