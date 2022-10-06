package cn.edu.gxu.gxucpcsystem.utils;

import cn.edu.gxu.gxucpcsystem.exception.TokenException;
import io.jsonwebtoken.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static cn.edu.gxu.gxucpcsystem.controller.Code.*;

/**
 * @Author Sct
 * @Date 2022/7/1 23:22
 * @Version 1.0
 */
public class JwtUtil {
    /**
     * 生成jwt
     * 使用Hs256算法
     *
     * @param jwtSec    jwt秘钥
     * @param ttlMillis jwt过期时间(毫秒)
     * @param username  用户名
     * @param ip        访问的IP地址
     * @param userType  用户权限
     * @return 信息加密后的Token
     */
    public static String createJWT(String jwtSec, Long ttlMillis, String username, String ip, String userType, String password) {
        // 签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成JWT的时间
        Long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //TODO 添加clientNo信息

        // 创建payload的私有声明
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("username", username);
        claims.put("ip", ip);
        claims.put("userType", userType);
        claims.put("password", password);

        // 添加payload声明
        // 设置jwt的body
        JwtBuilder builder = Jwts.builder()
                // 设置私有声明
                .setClaims(claims)
                // 设置JWT ID：是JWT的唯一标识
                .setId(UUID.randomUUID().toString())
                // jwt的签发时间
                .setIssuedAt(now)
                .setSubject(username)
                .setSubject(ip)
                .setSubject(userType)
                .setSubject(password)
                // 设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, jwtSec.getBytes(StandardCharsets.UTF_8));
        if (ttlMillis < 0) {
            ttlMillis = 0L;
        }
        Long expMillis = nowMillis + ttlMillis;
        Date exp = new Date(expMillis);
        // 设置过期时间
        builder.setExpiration(exp);
        return builder.compact();
    }


    /**
     * Token的解密
     *
     * @param jwtSec jwt秘钥
     * @param token  加密后的token
     * @return
     */
    public static Claims parseJWT(String jwtSec, String token) {
        // 得到DefaultJwtParser
        try {
            Claims claims = Jwts.parser()
                    // 设置签名的秘钥
                    .setSigningKey(jwtSec.getBytes(StandardCharsets.UTF_8))
                    // 设置需要解析的jwt
                    .parseClaimsJws(token).getBody();
            return claims;
        } catch (ExpiredJwtException e) {
            throw new TokenException(TOKEN_ERROR, "jwt过期");
        } catch (UnsupportedJwtException e) {
            throw new TokenException(TOKEN_ERROR, "不支持的JWT");
        } catch (MalformedJwtException e) {
            throw new TokenException(TOKEN_ERROR, "JWT格式错误");
        } catch (SignatureException e) {
            throw new TokenException(TOKEN_ERROR, "签名异常");
        } catch (IllegalArgumentException e) {
            throw new TokenException(TOKEN_ERROR, "非法请求");
        } catch (Exception e) {
            throw new TokenException(TOKEN_ERROR, "解析异常");
        }
    }


    /**
     * Token的续租(为了减少服务器压力，只给失效不到一天的token进行续租)
     *
     * @param token 旧的token
     * @return 新的token
     */
    public static String reletJWT(String token) {
        Claims claims = parseJWT(JWT_KEY, token);
        Date jwtTime = claims.getExpiration();
        // 对于不到一天到期的Token自动续租
        Long nowMillis = System.currentTimeMillis() + 86400000;
        Date nowTime = new Date(nowMillis);
        if (nowTime.after(jwtTime)) {
            token = createJWT(JWT_KEY, TTL_MILLIS, (String) claims.get("username"), (String) claims.get("ip"), (String) claims.get("power"), (String) claims.get("password"));
        }
        return token;
    }

}
