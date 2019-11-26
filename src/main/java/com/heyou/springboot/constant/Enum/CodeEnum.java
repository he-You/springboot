package com.heyou.springboot.constant.Enum;

/**
 * 简要说明:
 *
 * @author heyou
 * @date 2019-11-26 21:04
 */
public enum CodeEnum {
    /**
     * 用户名或者密码错误
     */
    LOGIN_NAMEANDPWD_ERROR(100000,"用户名或者密码错误！"),
    LOGIN_AGAIN(100001,"需要重新登录！"),
    ILLEGAL_TOKEN(110000,"非法token！"),
    EXPIRE_TOKEN(110001,"token已经过期！"),
    TOKEN_ISEMPTY(110002,"Token 不能为空");


    private CodeEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
