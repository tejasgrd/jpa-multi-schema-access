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
    basePackages = {"dev.tejasgarde.jpa.respository.reviews"},
    entityManagerFactoryRef = "reviewsEntityManager",
    transactionManagerRef = "reviewsTransactionManager")
public class ReviewsDBConfig extends BaseConfig{
  @Bean
  public JpaProperties reviewsJpaProperties() {
    return getJpaProperties();
  }

  @Bean
  @ConfigurationProperties("datasources.reviews")
  public DataSourceProperties reviewsDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean reviewsEntityManager(
      final JpaProperties reviewsJpaProperties) {
    EntityManagerFactoryBuilder builder = createEntityManagerFactory(reviewsJpaProperties);
    return builder.dataSource(reviewsDataSource()).packages("dev.tejasgarde.jpa.domain.reviews")
        .persistenceUnit("reviewsDataSource").build();
  }

  @Bean
  @ConfigurationProperties(prefix = "datasources.reviews.configurations")
  public DataSource reviewsDataSource() {
    DataSource dataSource = reviewsDataSourceProperties()
        .initializeDataSourceBuilder()
        .type(BasicDataSource.class).build();
    return dataSource;
  }

  @Bean
  public JpaTransactionManager reviewsTransactionManager(final JpaProperties reviewsJpaProperties) {
    return new JpaTransactionManager(reviewsEntityManager(reviewsJpaProperties).getObject());
  }
}
