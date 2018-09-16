package com.example.springbootmongo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@Slf4j
@Configuration
public class SpringBootMongoApplication {

    public static void main(String[] args) {
        log.info("------启动ing-----");
        SpringApplication.run(SpringBootMongoApplication.class, args);
        log.info("------启动成功-----");
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        log.info("------设置文件大小-----");

        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize("50MB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("50MB");
        return factory.createMultipartConfig();
    }
}
