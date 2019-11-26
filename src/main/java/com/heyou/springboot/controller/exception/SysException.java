package com.heyou.springboot.controller.exception;

import com.heyou.springboot.constant.Enum.CodeEnum;

/**
 * 简要说明:
 *
 * @author heyou
 * @date 2019-11-26 21:05
 */
public class SysException extends RuntimeException{
    private static final long serialVersionUID = -8201518085425482189L;

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    private Integer code;

    public SysException(Integer code,String message) {
        super(message);
        this.code = code;
    }
    public SysException(CodeEnum codeEnum) {
        super(codeEnum.getMsg());
        this.code = codeEnum.getCode();
    }
}
