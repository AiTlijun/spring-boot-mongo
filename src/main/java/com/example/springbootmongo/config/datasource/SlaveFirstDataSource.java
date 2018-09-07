package com.example.springbootmongo.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@Slf4j
public class SlaveFirstDataSource {

    @Autowired
    @Qualifier("slave1DataSource")
    private DataSource slave1DataSource;

    @Bean(name = "entityManagerSlave1")
    public EntityManager entityManagerSlave1(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySlave1(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactorySlave1")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySlave1(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(slave1DataSource)
                .properties(getVendorProperties())
                .packages("com.example.springbootmongo") //设置实体类所在位置
                .persistenceUnit("slave1PersistenceUnit")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, Object> getVendorProperties() {
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }

    @Bean(name = "transactionManagerSlave1")
    public PlatformTransactionManager transactionManagerSlave1(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactorySlave1(builder).getObject());
    }
}
