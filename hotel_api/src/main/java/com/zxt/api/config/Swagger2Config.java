package com.zxt.api.config;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/23 21:02
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Bean
    public Docket createRestApi() {
        log.warn("SWAGGER访问地址:/swagger-ui.html");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //.apis(RequestHandlerSelectors.basePackage("com.zxt.api.controller"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .globalOperationParameters(setHeaderToken());
    }


    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("token").description("Authorization").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("中信通微信小程序RESTfulAPIs")
                .description("hotel_api")
                .contact(new Contact("chenwenjun",null,null))
                .version("1.0")
                .build();
    }
}
