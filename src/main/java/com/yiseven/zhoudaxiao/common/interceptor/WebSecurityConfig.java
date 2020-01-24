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
    private static final List<String> EXCLUDE_APIS = Arrays.asList(
            "/login", "/person/add",
            "/category/queryAll",
            "/image/queryCarousels",
            "/image/queryListByProduct",
            "/product/query",
            "/product/queryProductList"
    );

    @Autowired
    LimitRequestInterceptor limitRequestInterceptor;
    @Autowired
    PersonValidInterceptor personValidInterceptor;
    @Autowired
    AddRecordValidInterceptor addRecordValidInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration limitInterceptor = registry.addInterceptor(limitRequestInterceptor);
        limitInterceptor.addPathPatterns("/**");

        InterceptorRegistration registration1 = registry.addInterceptor(personValidInterceptor);
        registration1.addPathPatterns("/**");
        registration1.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**")
                .excludePathPatterns(EXCLUDE_APIS);

//        InterceptorRegistration registration2 = registry.addInterceptor(addRecordValidInterceptor);
//        registration2.addPathPatterns("/record/add");
    }

}
