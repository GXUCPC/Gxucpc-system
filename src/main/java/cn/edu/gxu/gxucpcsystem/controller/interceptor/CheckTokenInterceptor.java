package cn.edu.gxu.gxucpcsystem.controller.interceptor;

import cn.edu.gxu.gxucpcsystem.controller.Code;
import cn.edu.gxu.gxucpcsystem.domain.utils.JwtUtil;
import cn.edu.gxu.gxucpcsystem.exception.TokenException;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Sct
 * @date 2022/7/1
 */

// 请求Token拦截器
@Component
public class CheckTokenInterceptor implements HandlerInterceptor {
    private final Code code = new Code();
    private final JwtUtil jwtUtil = new JwtUtil();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(request.getRequestURI().contains("admin")) {
            String token = request.getHeader("token");
            if(token.isEmpty()) {
                throw new TokenException(code.TOKEN_ERROR, "token失效");
            }
            // 比较token的有效时间
            Claims claims = jwtUtil.parseJWT(code.JWT_KEY, token);
            Date jwtTime = claims.getExpiration();
            long nowMillis = System.currentTimeMillis();
            Date nowTime = new Date(nowMillis);
            if(nowTime.after(jwtTime)) {
                throw new TokenException(code.TOKEN_ERROR, "token失效");
            }

            // 比较发送IP是否与token记录IP一致
            String nowIP = request.getRemoteAddr();
            String jwtIP = (String) claims.get("ip");
            if(!nowIP.equals(jwtIP)) {
                throw new TokenException(code.TOKEN_ERROR, "token失效");
            }
            if(request.getRequestURI().contains("user")) {
                //TODO 比较用户权限是否为Super Admin
            }

        }
        // 放行
        return true;
    }
}
