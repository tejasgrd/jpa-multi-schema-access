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
    basePackages = {"dev.tejasgarde.jpa.respository.users"},
    entityManagerFactoryRef = "usersEntityManager",
    transactionManagerRef = "usersTransactionManager")
public class UsersDBConfig extends BaseConfig{
  @Bean
  public JpaProperties usersJpaProperties() {
    return getJpaProperties();
  }

  @Bean
  @ConfigurationProperties("datasources.users")
  public DataSourceProperties usersDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean usersEntityManager(
      final JpaProperties usersJpaProperties) {
    EntityManagerFactoryBuilder builder = createEntityManagerFactory(usersJpaProperties);
    return builder.dataSource(usersDataSource()).packages("dev.tejasgarde.jpa.domain.users")
        .persistenceUnit("usersDataSource").build();
  }

  @Bean
  @ConfigurationProperties(prefix = "datasources.users.configurations")
  public DataSource usersDataSource() {
    DataSource dataSource = usersDataSourceProperties()
        .initializeDataSourceBuilder()
        .type(BasicDataSource.class).build();
    return dataSource;
  }

  @Bean
  public JpaTransactionManager usersTransactionManager(final JpaProperties usersJpaProperties) {
    return new JpaTransactionManager(usersEntityManager(usersJpaProperties).getObject());
  }
}
