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
public class SlaveSecondDataSource {

    @Autowired
    @Qualifier("slave2DataSource")
    private DataSource slave2DataSource;

    @Bean(name = "entityManagerSlave2")
    public EntityManager entityManagerSlave2(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySlave2(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactorySlave2")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySlave2(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(slave2DataSource)
                .properties(getVendorProperties())
                .packages("com.example.springbootmongo") //设置实体类所在位置
                .persistenceUnit("slave2PersistenceUnit")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, Object> getVendorProperties() {
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }

    @Bean(name = "transactionManagerSlave2")
    public PlatformTransactionManager transactionManagerSlave2(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactorySlave2(builder).getObject());
    }
}
