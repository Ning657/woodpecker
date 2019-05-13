package com.woodpecker;

import java.util.Map;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    entityManagerFactoryRef = "autotestEntityManagerFactory",
    transactionManagerRef = "autotestTransactionManager",
    basePackages = {"com.woodpecker.dao.autotest"})
public class AutotestJpaConfig {


  @Autowired
  @Qualifier("autotestDataSource")
  private DataSource dataSource;

  @Autowired
  private JpaProperties jpaProperties;


  private Map<String, String> getVendorProperties(DataSource dataSource) {
    return jpaProperties.getHibernateProperties(dataSource);
  }


  @Bean(name = "autotestEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      EntityManagerFactoryBuilder builder) {
    return builder.dataSource(dataSource).properties(getVendorProperties(dataSource))
        .packages("com.woodpecker.entity.autotest")
        .persistenceUnit("autotestPersistenceUnit")
        .build();
  }


  @Bean(name = "autotestEntityManager")
  public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
    return entityManagerFactory(builder).getObject().createEntityManager();
  }


  @Bean(name = "autotestTransactionManager")
  public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
    return new JpaTransactionManager(entityManagerFactory(builder).getObject());
  }


}