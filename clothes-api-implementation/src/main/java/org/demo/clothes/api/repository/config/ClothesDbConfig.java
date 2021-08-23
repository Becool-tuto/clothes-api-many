package org.demo.clothes.api.repository.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "clothesEntityManagerFactory",
                        transactionManagerRef = "clothesTransactionManager",
                        basePackages = {"org.demo.clothes.api.repository"})
public class ClothesDbConfig {
    private static final Logger logger = LoggerFactory.getLogger(ClothesDbConfig.class);

    @Primary
    @Bean(name = "clothesDataSource")
    @ConfigurationProperties(prefix = "db.clothes.datasource")
    public DataSource dataSource(){  DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:clothestest;MODE=MYSQL;INIT=RUNSCRIPT FROM 'classpath:init_clothesdb.sql';");
        return dataSource;}

    @Primary
    @Bean(name="clothesEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean clothesEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("clothesDataSource") DataSource dataSource){
        return builder.dataSource(dataSource).packages("org.demo.clothes.api.model").persistenceUnit("clothes").build();
    }

    @Primary
    @Bean(name = "clothesTransactionManager")
    public PlatformTransactionManager clothesTransactionManager(@Qualifier("clothesEntityManagerFactory")EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
}
