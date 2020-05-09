package com.yiseven.zhoudaxiao.common.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @author hdeng
 */
@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {
    public static final List<String> EXCLUDE_APIS = Arrays.asList(
            "/category/queryAll",
            "/image/queryCarousels",
            "/image/queryListByProduct",
            "/product/query",
            "/product/queryProductList"
    );

    @Autowired
    LimitRequestInterceptor limitRequestInterceptor;
    @Autowired
    AuthValidInterceptor authValidInterceptor;
    @Autowired
    AddRecordValidInterceptor addRecordValidInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration limitInterceptor = registry.addInterceptor(limitRequestInterceptor);
        limitInterceptor.addPathPatterns("/**");

        InterceptorRegistration registration1 = registry.addInterceptor(authValidInterceptor);
        registration1.addPathPatterns("/**");
        registration1.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**")
                .excludePathPatterns(Arrays.asList("/login", "/person/add"));

//        InterceptorRegistration registration2 = registry.addInterceptor(addRecordValidInterceptor);
//        registration2.addPathPatterns("/record/add");
    }
}
