package com.heyou.springboot.entity.requestInfo.ReqUser;

import lombok.Data;

/**
 * 登录请求体
 * @author heyou
 * @date 2019-11-25 23:56
 */
@Data
public class ReqLogin {
    private String userName;

    private String password;

    private String token;
}
