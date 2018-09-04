package com.example.springbootmongo.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class DataSourceAop {

    @Before("execution(* com.example.springbootmongo.service..*.find*(..)) || execution(* com.example.springbootmongo.service..*.get*(..)) || execution(* com.example.springbootmongo.service..*.select*(..))")
    public void setReadDataSourceType() {
        DataSourceContextHolder.read();
        log.info("dataSource切换到：Read");
    }
}
