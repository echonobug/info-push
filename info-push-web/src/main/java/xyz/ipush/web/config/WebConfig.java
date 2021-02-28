package xyz.ipush.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.ipush.web.config.interceptor.AccessLogInterceptor;

/**
 * web 配置
 *
 * @author jwei
 * @date 2020/12/18
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AccessLogInterceptor accessLogInterceptor;

    public WebConfig(AccessLogInterceptor accessLogInterceptor) {
        this.accessLogInterceptor = accessLogInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessLogInterceptor);
    }

}
