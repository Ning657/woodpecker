package com.woodpecker;

import java.util.Map;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;


/**
 * 类描述:〈JPA配置〉
 *
 * @version 1.0
 * @author: xujinjian
 */
@Configuration
@EnableJpaRepositories(
    entityManagerFactoryRef = "loandbEntityManagerFactory",
    transactionManagerRef = "loandbTransactionManager",
    basePackages = {"com.woodpecker.dao.loandb"})
public class LoandbJpaConfig {
  

  @Autowired
  @Qualifier("loandbDataSource")
  private DataSource dataSource;

  @Autowired
  private JpaProperties jpaProperties;
  
  @Autowired
  private HibernateProperties hibernateProperties;


  private Map<String, Object> getVendorProperties() {
    return hibernateProperties
        .determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
  }


  @Bean(name = "loandbEntityManagerFactory")
  @Primary
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      EntityManagerFactoryBuilder builder) {
    return builder.dataSource(dataSource).properties(getVendorProperties())
        .packages("com.woodpecker.entity.loandb")
        .persistenceUnit("loandbPersistenceUnit")
        .build();
  }


  @Bean(name = "loandbEntityManager")
  @Primary
  public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
    return entityManagerFactory(builder).getObject().createEntityManager();
  }


  @Bean(name = "loandbTransactionManager")
  @Primary
  public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
    return new JpaTransactionManager(entityManagerFactory(builder).getObject());
  }


}
