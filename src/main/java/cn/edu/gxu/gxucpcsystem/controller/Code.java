package cn.edu.gxu.gxucpcsystem.controller;


/**
 * @author Sct
 * @date 2022/7/1
 */
public class Code {
    // 运行正常
    public static final Integer STATUS_OK = 50000;
    // 访问资源无效
    public static final Integer RESOURCE_DISABLE = 50001;
    // token无效
    public static final Integer TOKEN_ERROR = 50002;
    // 数据库异常
    public static final Integer DATABASE_ERROR = 50003;
    // JWT 密钥
    public static final String JWT_KEY = "GxucpcFromGxucaAndGxuAndDXAndICPCAndCCPC";
    // Token 失效时间(7D)
    public static final Long TTL_MILLIS = 604800000L;
}
