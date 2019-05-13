package com.woodpecker;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
public class AutoTestDataSourceConfig {


  @Bean("autotestDataSource")
  //@Primary
  @ConfigurationProperties(prefix = "spring.datasource.auto-test-db")
  public DataSource autotestDataSource() {
    return DataSourceBuilder.create().build();
  }


  @Bean("autotestJdbcTemplate")
  //@Primary
  public JdbcTemplate autotestJdbcTemplate(
      @Qualifier("autotestDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }


  @Bean("autotestTransaction")
  //@Primary
  public PlatformTransactionManager autotestTransactionManager(
      @Qualifier("autotestDataSource") DataSource
          dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }


}
