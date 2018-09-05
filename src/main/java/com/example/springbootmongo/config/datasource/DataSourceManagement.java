package com.example.springbootmongo.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@AutoConfigureAfter({DataSourceConfig.class})
@Slf4j
public class DataSourceManagement {
    @Value("${spring.datasource.readSize}")
    private String dataSourceSize;

    @Autowired
    @Qualifier("writeDataSource")
    private DataSource writeDataSource;
    @Autowired
    @Qualifier("readDataSource1")
    private DataSource readDataSource1;
    @Autowired
    @Qualifier("readDataSource2")
    private DataSource readDataSource2;


    @Bean(name = "entityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        String dataSouceType = DataSourceContextHolder.getJdbcType();
        DataSource dataSourceNew = null;
        int random = (int) (Math.random() * 100);
        if ("read".equals(dataSouceType)) {
            log.info("-----------------------random-----"+random);
            if (random % 2 == 0) {
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
                .persistenceUnit("PersistenceUnit")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, Object> getVendorProperties() {
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }

}
