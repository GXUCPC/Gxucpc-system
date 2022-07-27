package cn.edu.gxu.gxucpcsystem.config;

import cn.edu.gxu.gxucpcsystem.controller.interceptor.CheckTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Sct
 * @Date 2022/7/1 22:14
 * @Version 1.0
 */
@Configuration
@ComponentScan("cn.edu.gxu.gxucpcsystem.controller")
@EnableWebMvc
public class SpringMVCConfig implements WebMvcConfigurer {
    @Autowired
    private CheckTokenInterceptor checkTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 对所有请求域名为/api/admin的请求进行鉴权检查
//        registry.addInterceptor(checkTokenInterceptor).addPathPatterns("/api/admin/**");
        registry.addInterceptor(checkTokenInterceptor).addPathPatterns("/api/**");
    }
}
