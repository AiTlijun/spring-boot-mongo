package com.example.springbootmongo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@Slf4j
@Configuration
@MapperScan("com.example.springbootmongo.mapper")
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

    @Bean
    public Object testBean(PlatformTransactionManager platformTransactionManager) {
        log.info("事务管理器:{}" + platformTransactionManager.getClass().getName());
        return new Object();
    }
}
