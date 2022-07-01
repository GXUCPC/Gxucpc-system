package cn.edu.gxu.gxucpcsystem.interceptor;

import cn.edu.gxu.gxucpcsystem.domain.utils.Re;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Sct
 * @date 2022/7/1
 */

// 请求Token拦截器
public class CheckToken implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getRequestURI().contains("admin")) {

            return false;
        } else {
            return true;
        }
    }
}
