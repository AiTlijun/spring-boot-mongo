package com.example.springbootmongo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringBootMongoApplication {

    public static void main(String[] args) {
        log.info("------启动ing-----");
        SpringApplication.run(SpringBootMongoApplication.class, args);
        log.info("------启动成功-----");
    }
}
