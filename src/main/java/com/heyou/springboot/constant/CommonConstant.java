package com.heyou.springboot.constant;

import java.math.BigDecimal;

/**
 * 静态变量信息
 * @author heyou
 * @date 2019-11-21 16:33
 */
public class CommonConstant {
    //默认经销商代码
    public final static String DEFAULT_DEALER = "00000";
    //Controller的搜索路径
    public final static String controller_pattern = "classpath*:/com/daoyun/controller/**/*.class";
    //接口Controller的搜索路径，用于构建接口文档
    public final static String api_controller_pattern = "classpath*:/com/daoyun/controller/erp/**/*.class";
    //系统无session的时候添加更新信息，默认使用该用户名
    public final static String USERNAME_SYSTEM = "system";

    public final static String UPLOAD_FILE_PATH = "UPLOAD_FILE_PATH";

    public final static String UPLOAD_FILE_PAHT_URL = "https://daoyunkj.oss-cn-shanghai.aliyuncs.com/";

    public final static String DEFAULT_PRODUCT_IMG = UPLOAD_FILE_PAHT_URL + "default/default_product.png";
    //默认定金比例
    public final static BigDecimal DEFAULT_DEPOSITE_RATE = new BigDecimal(0.1);
    //支付宝回调地址
    public final static String PAY_NOTIFY_URL_ZFB = "https://www.yunstore.vip/pay/alipay/notify";
    //微信回调地址
    public final static String PAY_NOTIFY_URL_WEIXIN = "https://www.yunstore.vip/pay/wxpay/qcodeMode2Notify";
    //我方分配给贵司的的公司编号, 请参考邮件《快递100-企业版快递查询接口（API）——授权key及相关工具》
    public final static String KUAIDI100_CUSTOMER = "";
    //我方分配给贵司的的公司编号, 请参考邮件《快递100-企业版快递查询接口（API）——授权key及相关工具》
    public final static String KUAIDI100_KEY = "";

    public final static String NATURAL_123456 = "123456";

    //
    public final static String ALL_MENU = "ALL_MENU";
    public final static String ALL_BUTTON = "ALL_BUTTON";

    //    public static final String NO_INTERCEPTOR_PATH = ".*/((/sys/code)|(logout)|(code)|(app)|(weixin)|(static)|(main)|(websocket)|(uploadImgs)).*"; // 不对匹配该值的访问路径拦截（正则）
    public static final String NO_LOGIN_PATH =".*/((sys/login)|(sys/code)|(weixin)|(static)|(main)|(uploadImgs)|(api/doc)|(sys/sendCode)|(sys/checkCode)|(app/dealer/weChat)).*";
    public static final String NO_JURISDICTION_PATH = ".*/((sys/userMenuList)|(sys/sysFile)|(api/doc)|(sys/pub)|(app/sys)).*";
    // session用的用户
    public static final String SESSION_USER = "sessionUser";
    // 验证码
    public static final String SESSION_SECURITY_CODE = "sessionSecCode";
    // 用户名
    public static final String SESSION_USERNAME = "USERNAME";
    //默认显示记录数
    public static final String PAGE = "10";
    public static final String FWATERM = "no,DY Admin,20,1,1"; // 文字水印配置
    public static final String IWATERM = "no,watermark.png,1,1"; // 图片水印配置
    public static final String FILEPATHIMG = "uploadFiles/uploadImgs/"; // 图片上传路径
    public static final String LOGIN = "/sys/tologin"; // 登录地址
    public static final String SLASH = "/";
    public final static String AES_KEY = "Da0YunERP";  //AES加密
    public final static String TOKE_PARAM = "token";  //token

    public final static String DEFAULT_GIFT = "DEFAULT_GIFT";  //分类key
    public final static String DEFAULT_COMBINE = "DEFAULT_COMBINE";  //分类key
    public final static String DEFAULT_OTHER ="DEFAULT_OTHER";//分类key：未分类
    public final static String AUTONAVI_KEY = "fe362912dae73423f41b95eace569a15";  //高德地图key
    public final static String ERP = "erp";  //路径
    public final static String IMPORT_RESULT = "importResult";  //导入结果文件路径
    public final static String EXPORT = "export";  //导出文件路径
    public final static boolean SMS_ON = true;  //发送短信开关
    public final static String DEFAULT_MOBILE = "18261712655;13151645485;17625348966;18362615891;18506176432;18512101965;18262015601;18516152019;17372654618;18506176431"; //小程序不发送真实短信验证码
    public final static String DEFAULT_UNIT_NAME = "个";
}
