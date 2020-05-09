package com.yiseven.zhoudaxiao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class ZhoudaxiaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhoudaxiaoApplication.class, args);
    }

    @Value("${swagger.prefix}")
    private String prefix;

    @Bean
    public Docket createRestApi() {
        ParameterBuilder headers = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        headers.name("zhoudaxiao_auth").description("auth token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的phone参数非必填，传空也可以
        //根据每个方法名也知道当前方法在设置什么参数
        pars.add(headers.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yiseven.zhoudaxiao.web"))
                .paths(PathSelectors.any())
                .build()
                //下面这个设置就是在接口的path前加上project-name
                .pathMapping(prefix)
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Zhoudaxiao_API")
                .description("")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
