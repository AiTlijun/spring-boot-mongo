package com.example.springbootmongo.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class DynamicDataSourceAspect {

    @Before("execution(* com.example.springbootmongo.service..*.find*(..)) || execution(* com.example.springbootmongo.service..*.get*(..)) || execution(* com.example.springbootmongo.service..*.select*(..))")
    public void setReadDataSourceType() {
        DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.read.getType());
        log.info("Use DataSource : {} > {}" + DataSourceType.read.getType() );
    }

    @Before("execution(* com.example.springbootmongo.service..*.add*(..)) || execution(* com.example.springbootmongo.service..*.delete*(..)) || execution(* com.example.springbootmongo.service..*.edit*(..))")
    public void setWriteDataSourceType() {
        DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.write.getType());
        log.info("Use DataSource : {} > {}" + DataSourceType.write.getType() );
    }

    @After("execution(* com.example.springbootmongo.service..*.*(..)) ")
    public void restoreDataSource() {
        DynamicDataSourceContextHolder.clearDataSourceType();
        log.info("Use DataSource : {} > {}" + "clear" );
    }
    @Around("execution(public * com.example.springbootmongo.service..*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method targetMethod = methodSignature.getMethod();
        if (targetMethod.isAnnotationPresent(TargetDataSource.class)) {
            String targetDataSource = targetMethod.getAnnotation(TargetDataSource.class).value();
            System.out.println("----------数据源是:" + targetDataSource + "------");
            DynamicDataSourceContextHolder.setDataSourceType(targetDataSource);
        }
        Object result = pjp.proceed();//执行方法
        DynamicDataSourceContextHolder.clearDataSourceType();
        return result;
    }
}
