package im.dadoo.auth.biz.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * @author codekitten
 *
 */
@Configuration
@ComponentScan("im.dadoo.auth.biz")
@EnableTransactionManagement
public class BizContext {
  
  @Bean(initMethod = "init", destroyMethod = "close")
  public DataSource dataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUrl("jdbc:mysql://202.114.18.242:33066/dadooauth?characterEncoding=utf8&autoReconnect=true");
    dataSource.setUsername("root");
    dataSource.setPassword("dadoo2012dadoo");
    return dataSource;
  }
  
  @Bean
  public NamedParameterJdbcTemplate jdbcTemplate() {
    NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource());
    return jdbcTemplate;
  }

  @Bean
  public PlatformTransactionManager txManager() {
    DataSourceTransactionManager txManager = new DataSourceTransactionManager(this.dataSource());
    return txManager;
  }
}
