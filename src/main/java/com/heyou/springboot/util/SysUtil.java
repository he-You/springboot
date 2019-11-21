package com.heyou.springboot.util;

/**
 * @author heyou
 * @date 2019-11-21 17:03
 */
public class SysUtil {
    /**
     * 是否为空
     *
     * @param str 要判断的字符串
     * @date
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }
}
