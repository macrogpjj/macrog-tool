package com.macrog.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author Administrator
 * @version 1.0
 * @description Knife4j配置
 * @date 2021/10/14 11:10
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Value("#{'prod'.equals('${spring.profiles.active}')}")
    private boolean disableSwagger;

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(!disableSwagger)
                .apiInfo(new ApiInfoBuilder()
                        //.title("swagger-bootstrap-ui-demo RESTful APIs")
                        .description("# swagger-bootstrap-ui-demo RESTful APIs")
                        .title("macrog接口API")
                        .termsOfServiceUrl("http://www.xx.com/")
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("2.X版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.macrog.web.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
