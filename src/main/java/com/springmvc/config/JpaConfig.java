package com.springmvc.config;

import jakarta.persistence.EntityManagerFactory;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author BAO 6/21/2023
 */

/**
 * EntityManagerFactory không hỗ trợ tạo talbe từ entity SessionFactory hỗ trợ tạo table từ entity
 * Nhưng entityManager sử dụng tiêu chuâẩn JPA hỡ tợ JPQL dễ truy vấn vào database ho
 */
@Configuration
@PropertySource("classpath:database.properties")
@EnableTransactionManagement
public class JpaConfig {

  private final Environment environment;

  public JpaConfig(Environment environment) {
    this.environment = environment;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
    LocalContainerEntityManagerFactoryBean factoryBean =
        new LocalContainerEntityManagerFactoryBean();
    factoryBean.setDataSource(dataSource());
    factoryBean.setPackagesToScan("com.springmvc");
    /**
     * cấu hình Hibernate JPA, nếu dùng sessionFactory thì không cần. Nhưng vì EntityManagerFactory
     * hoạt động theo chuẩn JPA nên cần cấu hình adapter dùng JPA thì bắt buộc phải có adapter mới dùng các hibernate.dialect được
     * nói cho nó biết mình đang sử dụng cấu hình properties gì !
     * Khi dùng sessionFactory không cần khai báo adapter vì SessionFactory là 1 thành phần của Hibernate core. Còn JPA có nhiều adapter được cung cấp
     */
    factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    factoryBean.setJpaProperties(hibernateProperties());
    return factoryBean;
  }



  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
    dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
    dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
    dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
    return dataSource;
  }

  private Properties hibernateProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
    properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
    properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
    properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
    return properties;
  }

  /**
   * Cấu hình Transaction Manager để quản lý các transaction khi dùng @Transactional
   *
   * <p>Nếu không có Transaction Manager thì @Transactional sẽ bị vô hiệu và phải rollback và commit
   * = bay
   *
   * @return
   */
  @Bean
  public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory);
    return transactionManager;
  }
}
