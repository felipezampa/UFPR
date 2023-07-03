package com.bantads.conta.repository.CUD;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.bantads.conta.repository.CUD", 
    entityManagerFactoryRef = "cudEntityManager", 
    transactionManagerRef = "cudTransactionManager"
)
public class CUDRepositoryConfiguration {
    @Autowired
    private Environment env;

    public CUDRepositoryConfiguration() {
        super();
    }
    
    @Bean(name = "cudDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource cudDataSource() {
        return DataSourceBuilder.create().build();
    }
   
    @Bean(name = "cudEntityManager")
    public LocalContainerEntityManagerFactoryBean cudEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(cudDataSource());
        em.setPackagesToScan("com.bantads.conta.model.CUD");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean(name = "cudTransactionManager")
    public PlatformTransactionManager cudTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(cudEntityManager().getObject());
        return transactionManager;
    }
}
