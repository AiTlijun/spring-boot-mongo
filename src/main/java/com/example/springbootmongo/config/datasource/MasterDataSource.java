package com.example.springbootmongo.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@Slf4j
/*@EnableJpaRepositories(basePackages = "com.example.springbootmongo",
        entityManagerFactoryRef = "entityManagerFactoryMaster",
        transactionManagerRef = "entityManagerMaster")*/
@AutoConfigureAfter(name = "DataSourceConfigurer")
public class MasterDataSource {

    @Autowired
    @Qualifier("masterDataSource")
    private DataSource masterDataSource;

    @Primary
    @Bean(name = "entityManagerMaster")
    public EntityManager entityManagerMaster(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryMaster(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryMaster")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryMaster(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(masterDataSource)
                .properties(getVendorProperties())
                .packages("com.example.springbootmongo") //设置实体类所在位置
                .persistenceUnit("masterPersistenceUnit")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, Object> getVendorProperties() {
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }

    @Primary
    @Bean(name = "transactionManagerMaster")
    public PlatformTransactionManager transactionManagerMaster(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryMaster(builder).getObject());
    }

}
