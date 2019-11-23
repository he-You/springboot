package com.heyou.springboot.util;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author heyou
 * @date 2019-11-23 23:05
 * 提供两种加盐加密方式:MD5加盐和SHA-1&MD5加盐
 * 返回结果为布尔值
 * 使用方法:
 * 1.获取加密后的密码串:String newPassword = Md5AndSalts.getSaltMd5(requstPassword) or Md5AndSalts.getSaltMd5AndSha(requstPassword)
 * 2.验证: Md5AndSalts.getSaltverifyMd5(requstPassword, newPassword) or Md5AndSalts.getSaltverifyMd5AndSha(requstPassword, newPassword)
 */
public class Md5AndSalts {
    /**
     * byte[]字节数组 转换成 十六进制字符串
     * @param arr 要转换的byte[]字节数组
     * @return String 返回十六进制字符串
     */
    private static String hex(byte[] arr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : arr) {
            sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString();
    }

    /**
     * MD5加密,并把结果由字节数组转换成十六进制字符串
     * @param str 要加密的内容
     * @return String 返回加密后的十六进制字符串
     */
    private static String md5Hex(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(str.getBytes());
            return hex(digest);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return "";
        }
    }
    /**
     * md5和sha-1混合加密
     * @param inputText 要加密的内容
     * @return String md5和sha-1混合加密之后的密码
     */
    private static String md5AndSha(String inputText) {
        return sha(md5(inputText));
    }
    /**
     * md5加密
     * @param inputText 要加密的内容
     * @return String md5加密之后的密码
     */
    private static String md5(String inputText) {
        return encrypt(inputText, "md5");
    }
    /**
     * sha-1加密
     * @param inputText 要加密的内容
     * @return sha-1加密之后的密码
     */
    private static String sha(String inputText) {
        return encrypt(inputText, "sha-1");
    }
    /**
     * md5或者sha-1加密
     * @param inputText 要加密的内容
     * @param algorithmName 加密算法名称：md5或者sha-1，不区分大小写
     *
     * @return String md5或者sha-1加密之后的结果
     */
    private static String encrypt(String inputText, String algorithmName) {
        if (inputText == null || "".equals(inputText.trim())) {
            throw new IllegalArgumentException("请输入要加密的内容");
        }
        if (algorithmName == null || "".equals(algorithmName.trim())) {
            algorithmName = "md5";
        }
        try {
            MessageDigest m = MessageDigest.getInstance(algorithmName);
            m.update(inputText.getBytes(StandardCharsets.UTF_8));
            byte[] s = m.digest();
            return hex(s);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 生成含有随机盐的密码
     * @param password 要加密的密码
     * @return String 含有随机盐的密码
     */
    private static String getSaltMd5(String password) {
        // 生成一个16位的随机数
        String salt = Md5AndSalts.getStrAfterMix();
        password = md5Hex(password + salt);

        return String.valueOf(Md5AndSalts.getPwdAfterMixSalt(password,salt));
    }
    /**
     * 验证加盐后是否和原密码一致
     * @param password 原密码
     * @param md5str 加密之后的密码
     *@return boolean true表示和原密码一致 false表示和原密码不一致
     */
    private static boolean getSaltverifyMd5(String password, String md5str) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        String salt = Md5AndSalts.getSalt(md5str,cs1,cs2);
        return md5Hex(password + salt).equals(String.valueOf(cs1));
    }
    /**
     * 生成含有随机盐的密码
     * @param password 要加密的密码
     * @return String 含有随机盐的密码
     */
    private static String getSaltMd5AndSha(String password) {
        // 生成最终的加密盐
        String salt = Md5AndSalts.getStrAfterMix();
        password = md5AndSha(password + salt);
        return String.valueOf(Md5AndSalts.getPwdAfterMixSalt(password,salt));
    }
    /**
     * 验证加盐后是否和原密码一致
     * @param password 原密码
     * @param md5str 加密之后的密码
     *@return boolean true表示和原密码一致 false表示和原密码不一致
     */
    private static boolean getSaltverifyMd5AndSha(String password, String md5str) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        String salt = Md5AndSalts.getSalt(md5str,cs1,cs2);
        String encrypPassword = md5AndSha(password + salt);
        // 加密密码去掉最后8位数(SHA-1加密方式加盐后长度是48)
        encrypPassword = encrypPassword.substring(0 , encrypPassword.length() - 8);
        return encrypPassword.equals(String.valueOf(cs1));
    }
    /**
     * 获取盐值
     */
    private static String getSalt(String md5str,char[] cs1,char[] cs2){
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5str.charAt(i);
            cs1[i / 3 * 2 + 1] = md5str.charAt(i + 2);
            cs2[i / 3] = md5str.charAt(i + 1);
        }
        return new String(cs2);
    }
    /**
     * 获取加密加盐混合后的字符串
     */
    private static String getStrAfterMix(){
        Random random = new Random();
        StringBuilder sBuilder = new StringBuilder(16);
        sBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = sBuilder.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sBuilder.append("0");
            }
        }
        // 生成最终的加密盐
        return sBuilder.toString();
    }
    private static char[] getPwdAfterMixSalt(String password,String salt){
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return cs;
    }
    public static void main(String[] args) {
        // 原密码
        String plaintext = "123456";
        // 获取加盐加密后的MD5密码串
        String ciphertext = Md5AndSalts.getSaltMd5(plaintext);
        System.out.println("加盐后MD5密码串：" + ciphertext);
        System.out.println("是否是同一字符串:" + Md5AndSalts.getSaltverifyMd5(plaintext, ciphertext));
        // 获取加盐加密后的MD5密码串
        String ciphertext2 = Md5AndSalts.getSaltMd5AndSha(plaintext);
        System.out.println("加盐后MD5：" + ciphertext2);
        System.out.println("是否是同一字符串:" + Md5AndSalts.getSaltverifyMd5AndSha(plaintext, ciphertext2));
    }
}
