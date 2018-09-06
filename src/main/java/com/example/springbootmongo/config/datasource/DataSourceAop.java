package com.example.springbootmongo.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
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

    @Before("execution(* com.example.springbootmongo.service..*.add*(..)) || execution(* com.example.springbootmongo.service..*.delete*(..)) || execution(* com.example.springbootmongo.service..*.edit*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.write();
        log.info("dataSource切换到：write");
    }

    @After("execution(* com.example.springbootmongo.service..*.*(..)) ")
    public void restoreDataSource() {
        DataSourceContextHolder.clearDataSourceType();
        log.info("dataSource 清除：clear");
    }
}
