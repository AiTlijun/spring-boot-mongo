package com.example.springbootmongo.config.datasource.jpa;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 *
 * 用户操作日志数据源配置类
 *
 * @author lijun
 * @create 2018-09-04 10:26
 **/
@Configuration
@MapperScan(basePackages = "com.example.springbootmongo.mapper.*", sqlSessionFactoryRef = "mybatisSqlSessionFactory")
@EnableTransactionManagement
public class MybatisEntityManager {

    @Value("${mybatis.typeAliasesPackage}")
    private String typeAliasesPackage;
    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;

    @Autowired
    @Qualifier("mybatisDataSource")
    private DataSource mybatisDataSource;


    @Bean(name = "mybatisTransactionManager")
    public DataSourceTransactionManager mybatisTransactionManager() {
        return new DataSourceTransactionManager(mybatisDataSource);
    }

    @Bean(name = "mybatisSqlSessionFactory")
    public SqlSessionFactory mybatisSqlSessionFactory(@Qualifier("mybatisDataSource") DataSource mybatisDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mybatisDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mapperLocations));
        return sessionFactory.getObject();
    }
}