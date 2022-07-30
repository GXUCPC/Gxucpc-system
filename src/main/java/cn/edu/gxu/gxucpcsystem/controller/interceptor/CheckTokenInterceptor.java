package cn.edu.gxu.gxucpcsystem.controller.interceptor;

import cn.edu.gxu.gxucpcsystem.Service.AdminService;
import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.Admin;
import cn.edu.gxu.gxucpcsystem.utils.JwtUtil;
import cn.edu.gxu.gxucpcsystem.exception.TokenException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
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

// 请求Token拦截器
@Component
public class CheckTokenInterceptor implements HandlerInterceptor {
    @Autowired
    AdminService adminService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        if(request.getRequestURI().contains("admin")) {
//            String token = request.getHeader("token");
//            if(token == null) {
//                throw new TokenException(TOKEN_ERROR, "token失效");
//            }
//            // 比较token的有效时间
//            Claims claims = JwtUtil.parseJWT(JWT_KEY, token);
//            if(claims.get("ip") == null || claims.get("username") == null || claims.get("userType") == null || claims.get("password") == null)
//                throw new TokenException(TOKEN_ERROR, "token失效");
//            Date jwtTime = claims.getExpiration();
//            long nowMillis = System.currentTimeMillis();
//            Date nowTime = new Date(nowMillis);
//            if(nowTime.after(jwtTime)) {
//                throw new TokenException(TOKEN_ERROR, "token失效");
//            }
//
//            // 比较发送IP是否与token记录IP一致
//            String nowIP = request.getRemoteAddr();
//            String jwtIP = (String) claims.get("ip");
//            if(!nowIP.equals(jwtIP)) {
//                throw new TokenException(TOKEN_ERROR, "token失效");
//            }
//            Admin admin = adminService.getByUsername((String) claims.get("username"));
//            if(admin == null) throw new TokenException(TOKEN_ERROR, "token失效");
//            if(request.getHeader("password") == null) throw new TokenException(TOKEN_ERROR, "token失效");
//            if(!request.getHeader("password").equals(admin.getPassword())) throw new TokenException(TOKEN_ERROR, "密码失效");
//            if(request.getRequestURI().contains("user")) {
//                //TODO 查询数据库，比较用户权限是否为Super Admin
//                int userType = (int)admin.getUserType();
//                if(userType == 1 && !claims.get("userType").equals("Super Admin") || userType == 2 && !claims.get("userType").equals("Admin")) throw new TokenException(TOKEN_ERROR, "token失效");
//                if((int)admin.getUserType() == 2) throw new TokenException(TOKEN_ERROR, "权限不足");
//            }
//            // token续租
//            request.setAttribute("token", JwtUtil.reletJWT(token));
//        }
        // 放行
        return true;
    }

}
