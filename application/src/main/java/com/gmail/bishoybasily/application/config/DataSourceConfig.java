package com.gmail.bishoybasily.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
//@EnableJpaRepositories(basePackages = {"com"})
//@EnableTransactionManagement
//@EnableConfigurationProperties(JpaProperties.class)
public class DataSourceConfig {

    @Lazy
    @Bean
    @Primary
    public DataSource dataSource() {
        return new TenantBasedDataSource();
    }

//    @Lazy
//    @Bean
//    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory);
//        return transactionManager;
//    }
//
//    @Lazy
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaProperties jpaProperties) {
//        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactoryBean.setDataSource(dataSource);
//        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        entityManagerFactoryBean.setPackagesToScan("com");
//        entityManagerFactoryBean.getJpaPropertyMap().putAll(jpaProperties.getProperties());
//        return entityManagerFactoryBean;
//    }

}
