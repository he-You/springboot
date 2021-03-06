package com.heyou.springboot.entity.requestInfo.ReqUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 登录请求体
 * @author heyou
 * @date 2019-11-25 23:56
 */
@Data
public class ReqLogin implements Serializable {
    @JsonProperty("user_name")
    private String userName;

    private String password;

    private String token;
}
