package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;


/**
 * @author 14431
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 在这每一个bean就代表着一个携程开发的用户
     * @param environment
     * @return
     */
    @Bean
    public Docket docket(Environment environment){
        //获取项目目前的运行环境，看是否为开发环境，而且我们需要让swagger只在开发环境下运行
        Profiles profiles= Profiles.of("dev");
        //然后我们需要获取springboot当时的环境并且判断是否为dev环境
        boolean flag = environment.acceptsProfiles(profiles);
        //返回给spring一个Docket对象，其中包含着Swagger的信息
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("崔震云")
                .apiInfo(apiInfo())
                //设置swagger是否启动
                .enable(flag)
                //所有的扫描配置都要写在 .select()和.build()之间
                .select()
                //指定包扫描的方式
                .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                //指定方法上注解扫描的方式需要给一个注解的.class类型
                .apis(RequestHandlerSelectors.withMethodAnnotation(RequestMapping.class))
                .build();
    }
    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("a");
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("b");
    }
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact=new Contact("Planifolia_Van","Planifolia.Van.net","zhenyuncui@gmail.com");
        //项目的一些描述
        return new ApiInfo(
            "Spring-Shiro演示",
                "这个项目是用于演示Spring-shiro的用法",
                "β-1.0",
                "",
                //这个参数是一个Contact对象，其中包括了作者的名字，官网，邮箱
                contact,
                "",
                "",
                new ArrayList<>()
        );
    }
}
