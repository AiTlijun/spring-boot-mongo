package com.example.springbootmongo.config.datasource;

import com.example.springbootmongo.config.datasource.loadBalance.WeightRoundRobin;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-10)//保证该AOP在@Transactional之前执行
@Component
@Slf4j
public class DynamicDataSourceAspect {

    @Before("execution(* com.example.springbootmongo.service..*.find*(..)) || execution(* com.example.springbootmongo.service..*.get*(..)) || execution(* com.example.springbootmongo.service..*.select*(..))")
    public void setSlaveDataSourceType() {
       String slaveDataSource =  WeightRoundRobin.getDataSource();
        DynamicDataSourceContextHolder.setDataSourceType( slaveDataSource);
        log.info("Use DataSource : {} > {}" + slaveDataSource );
    }

    @Before("execution(* com.example.springbootmongo.service..*.add*(..)) || execution(* com.example.springbootmongo.service..*.delete*(..)) || execution(* com.example.springbootmongo.service..*.edit*(..))")
    public void setMasterDataSourceType() {
        DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.master.getType());
        log.info("Use DataSource : {} > {}" + DataSourceType.master.getType() );
    }

    @After("execution(* com.example.springbootmongo.service..*.*(..)) ")
    public void restoreDataSource() {
        DynamicDataSourceContextHolder.clearDataSourceType();
        log.info("Use DataSource : {} > {}" + "clear" );
    }
    /*@Around("execution(public * com.example.springbootmongo.service..*.*(..))")
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
    }*/
}
