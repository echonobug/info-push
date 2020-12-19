package fun.jwei.ipush.web.config;

import fun.jwei.ipush.web.config.log.access.AccessLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfigurer 实现类
 *
 * @author jwei
 * @date 2020/12/18
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AccessLogInterceptor accessLogInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessLogInterceptor);
    }
}
