package com.example.springbootmongo.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@AutoConfigureAfter({DataSourceConfig.class})
@Slf4j
public class DataSourceManagement implements ImportBeanDefinitionRegistrar, EnvironmentAware {
    @Value("${spring.datasource.readSize}")
    private int dataSourceSize;

    @Autowired
    @Qualifier("writeDataSource")
    private DataSource writeDataSource;
    @Autowired
    @Qualifier("readDataSource1")
    private DataSource readDataSource1;
    @Autowired
    @Qualifier("readDataSource2")
    private DataSource readDataSource2;


    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return this.entityManagerFactoryBean(builder).getObject();
    }

    @Bean(name = "entityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
        String dataSouceType = DynamicDataSourceContextHolder.getDataSourceType();
        DataSource dataSourceNew = null;
        int random = (int) (Math.random() * 100);
        if ("read".equals(dataSouceType)) {
            log.info("entityManagerFactory -> dataSourceSize={}, random={},", dataSourceSize, random);
            if (random % dataSourceSize == 0) {
                dataSourceNew = readDataSource1;
            } else
                dataSourceNew = readDataSource2;
        } else {
            dataSourceNew = writeDataSource;
        }
        return builder
                .dataSource(dataSourceNew)
                .properties(getVendorProperties())
                .packages("com.example.springbootmongo.bean") //设置实体类所在位置
                .persistenceUnit(dataSouceType + "PersistenceUnit")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties() {
        return jpaProperties.getProperties();
        // return jpaProperties.getHibernateProperties(new HibernateSettings());
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory(builder));
    }

    @Override
    public void setEnvironment(Environment environment) {

    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

    }
}
