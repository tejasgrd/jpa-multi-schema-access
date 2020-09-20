package dev.tejasgarde.jpa;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
    basePackages = {"dev.tejasgarde.jpa.respository.products"},
    entityManagerFactoryRef = "productsEntityManager",
    transactionManagerRef = "productsTransactionManager")
public class ProductsDBConfig extends BaseConfig {

  @Bean
  public JpaProperties productsJpaProperties() {
    return getJpaProperties();
  }

  @Bean
  @ConfigurationProperties("datasources.products")
  public DataSourceProperties productsDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean productsEntityManager(
      final JpaProperties productsJpaProperties) {
    EntityManagerFactoryBuilder builder = createEntityManagerFactory(productsJpaProperties);
    return builder.dataSource(productsDataSource()).packages("dev.tejasgarde.jpa.domain.products")
        .persistenceUnit("productsDataSource").build();
  }

  @Bean
  @ConfigurationProperties(prefix = "datasources.products.configurations")
  public DataSource productsDataSource() {
    DataSource dataSource = productsDataSourceProperties()
        .initializeDataSourceBuilder()
        .type(BasicDataSource.class).build();
    return dataSource;
  }

  @Bean
  public JpaTransactionManager productsTransactionManager(final JpaProperties productsJpaProperties) {
    return new JpaTransactionManager(productsEntityManager(productsJpaProperties).getObject());
  }
}
