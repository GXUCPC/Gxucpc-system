package cn.edu.gxu.gxucpcsystem.config;

import cn.edu.gxu.gxucpcsystem.controller.interceptor.CheckTokenInterceptor;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class SecurityConfig extends WebMvcConfigurationSupport {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CheckTokenInterceptor checkTokenInterceptor;
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder json = Jackson2ObjectMapperBuilder.json();
        json.applicationContext(this.applicationContext);
        json.serializationInclusion(JsonInclude.Include.NON_NULL);
        converters.removeIf(f-> f.getClass()==MappingJackson2HttpMessageConverter.class);
        converters.add(new MappingJackson2HttpMessageConverter(json.build()));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 对所有请求域名为/api/admin的请求进行鉴权检查
        registry.addInterceptor(checkTokenInterceptor).addPathPatterns("/api/admin/**");
//        registry.addInterceptor(checkTokenInterceptor).addPathPatterns("/api/**");
    }
}
