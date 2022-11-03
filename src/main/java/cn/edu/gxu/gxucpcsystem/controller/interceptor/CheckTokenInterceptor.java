package cn.edu.gxu.gxucpcsystem.controller.interceptor;

import cn.edu.gxu.gxucpcsystem.service.AdminService;
import cn.edu.gxu.gxucpcsystem.domain.Admin;
import cn.edu.gxu.gxucpcsystem.utils.JwtUtil;
import cn.edu.gxu.gxucpcsystem.exception.TokenException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static cn.edu.gxu.gxucpcsystem.controller.Code.JWT_KEY;
import static cn.edu.gxu.gxucpcsystem.controller.Code.TOKEN_ERROR;

/**
 * @author Sct
 * @date 2022/7/1
 */

/**
 * 请求Token拦截器
 */
@Component
public class CheckTokenInterceptor implements HandlerInterceptor {
    @Autowired
    AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 跨域的OPTIONS请求是不带token的，会导致所有合法请求都被拦截
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }

        // 所有管理员操作都需要验证token

        String token = request.getHeader("token");
        if (token == null) {
            throw new TokenException(TOKEN_ERROR, "token失效");
        }
        // 比较token的有效时间
        Claims claims = JwtUtil.parseJWT(JWT_KEY, token);
        if (claims.get("ip") == null || claims.get("username") == null || claims.get("userType") == null || claims.get("password") == null) {
            throw new TokenException(TOKEN_ERROR, "token失效");
        }
        Date jwtTime = claims.getExpiration();
        Long nowMillis = System.currentTimeMillis();
        Date nowTime = new Date(nowMillis);
        if (nowTime.after(jwtTime)) {
            throw new TokenException(TOKEN_ERROR, "token失效");
        }

        // 比较发送IP是否与token记录IP一致
        String nowIP = request.getRemoteAddr();
        String jwtIP = (String) claims.get("ip");
        if (!nowIP.equals(jwtIP)) {
            throw new TokenException(TOKEN_ERROR, "token失效");
        }
        Admin admin = adminService.getByUsername((String) claims.get("username"));
        if (admin == null) {
            throw new TokenException(TOKEN_ERROR, "token失效");
        }
        if (request.getHeader("password") == null) {
            throw new TokenException(TOKEN_ERROR, "token失效");
        }
        if (!request.getHeader("password").equals(admin.getPassword())) {
            throw new TokenException(TOKEN_ERROR, "密码失效");
        }
        if (request.getRequestURI().contains("user")) {
            int userType = (int) admin.getUserType();
            if (userType == 1 && !claims.get("userType").equals("Super Admin") || userType == 2 && !claims.get("userType").equals("Admin")) {
                throw new TokenException(TOKEN_ERROR, "token失效");
            }
            if ((int) admin.getUserType() == 2) {
                throw new TokenException(TOKEN_ERROR, "权限不足");
            }
        }
        // token续租
        response.setHeader("token", JwtUtil.reletJWT(token));
        // 前后端分离的项目，默认只能接受基础的五个返回头，如返回自定义头，需要添加以下内容：https://www.cnblogs.com/liuxianbin/p/13035809.html

        response.addHeader("Access-Control-Expose-Headers", "token");

        // 放行
        return true;
    }

}
