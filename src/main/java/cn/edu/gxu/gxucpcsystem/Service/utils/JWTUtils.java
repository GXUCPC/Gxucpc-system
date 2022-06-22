package cn.edu.gxu.gxucpcsystem.Service.utils;

/**
 * @author MaoMao
 * @Description jwt 工具类
 * @create 2022-06-22 7:46 PM
 */


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    private static final String SING = "MaoMaoGod";
    /**
     * 生成Token  header.payload.sign
     */
    public static String getToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7); //默认7天过期
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SING));
        return token;
    }
    /**
     *  验证token的合法性
     */
    public static void verify(String token){
        JWT.require(Algorithm.HMAC256(SING)).build().verify(token);  //这一行代码就可以起到验证的作用，因为在验证不匹配时它自动会抛出异常
    }
    /**
     * 获取token的信息方法
     */
    public static DecodedJWT getTokenInfo(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        return verify;
    }
}
