package com.example.springbootmongo.config.datasource;

import com.example.springbootmongo.config.datasource.loadBalance.WeightRoundRobin;
import com.example.springbootmongo.entity.User;
import com.example.springbootmongo.entity.UserLog;
import com.example.springbootmongo.service.UserLogService;
import com.example.springbootmongo.service.jpa.UserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

@Aspect
//@Order(-10)//保证该AOP在@Transactional之前执行
@Component
@Slf4j
public class DynamicDataSourceAspect {

    @Autowired
    private UserService userService;

    @Before("execution(* com.example.springbootmongo.service.jpa..*.find*(..)) || execution(* com.example.springbootmongo.service.jpa..*.get*(..)) || execution(* com.example.springbootmongo.service.jpa..*.select*(..))")
    public void setSlaveDataSourceType() {
        String slaveDataSource = WeightRoundRobin.getDataSource();
        DynamicDataSourceContextHolder.setDataSourceType(slaveDataSource);
        log.info("Use DataSource : {} > {}" + slaveDataSource);
    }

    @Before("execution(* com.example.springbootmongo.service.jpa..*.add*(..)) || execution(* com.example.springbootmongo.service.jpa..*.delete*(..)) || execution(* com.example.springbootmongo.service.jpa..*.edit*(..))")
    public void setMasterDataSourceType() {
        DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.master.getType());
        log.info(Thread.currentThread().getId() + "-------Use DataSource : {} > {}" + DataSourceType.master.getType());
    }

    @After("execution(* com.example.springbootmongo.service.jpa.*.*(..))")
    public void restoreDataSource() {
        DynamicDataSourceContextHolder.clearDataSourceType();
        log.info(Thread.currentThread().getId() + "-------Use DataSource : {} > {}" + "clear");
    }
    @Around("execution(* com.example.springbootmongo.service.jpa..*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
       /* System.out.println("111111111111111111111111111");
        System.out.println("----------------------userService:"+userService);
        userService.testAOP();
        System.out.println("2222222222222222222222");*/
        Object result = pjp.proceed();//执行方法
        DynamicDataSourceContextHolder.clearDataSourceType();
        System.out.println("33333333333333333333");

        return result;
    }
}
