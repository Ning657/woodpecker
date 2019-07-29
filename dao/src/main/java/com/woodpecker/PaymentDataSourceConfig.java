package com.woodpecker;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;


/**
 * 类描述:〈数据源配置〉
 *
 * @version 1.0
 * @author: xujinjian
 */
@Configuration
public class PaymentDataSourceConfig {


  @Bean("paymentDataSource")
  //@Primary
  @ConfigurationProperties(prefix = "spring.datasource.payment-db")
  public DataSource paymentDataSource() {
    return DataSourceBuilder.create().build();
  }


  @Bean("paymentJdbcTemplate")
  //@Primary
  public JdbcTemplate paymentJdbcTemplate(
      @Qualifier("paymentDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }


  @Bean("paymentTransaction")
  //@Primary
  public PlatformTransactionManager paymentTransactionManager(
      @Qualifier("paymentDataSource") DataSource
          dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }


}
