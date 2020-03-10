package com.mihai.project.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        List<Parameter> parameterList = new ArrayList<>();
        ParameterBuilder usernameParameterBuilder = new ParameterBuilder();
        ParameterBuilder passwordParameterBuilder = new ParameterBuilder();
        usernameParameterBuilder.name("username")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .defaultValue("username")
                .required(true)
                .build();
        passwordParameterBuilder.name("password")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .defaultValue("password")
                .required(true)
                .build();
        parameterList.add(usernameParameterBuilder.build());
        parameterList.add(passwordParameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("")
                .globalOperationParameters(parameterList);
    }
}
