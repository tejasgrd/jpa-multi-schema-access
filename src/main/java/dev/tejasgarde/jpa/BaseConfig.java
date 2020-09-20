package dev.tejasgarde.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.HashMap;

public class BaseConfig {
  @Value("${spring.jpa.database-platform}")
  private String dbPlatform;
  @Value("${spring.jpa.show-sql}")
  private String showSql;
  @Value("${spring.jpa.hibernate.ddl-auto}")
  private String hbm2DDLAuto;

  @Autowired(required = false)
  private PersistenceUnitManager persistenceUnitManager;


  public JpaProperties getJpaProperties() {
    JpaProperties jpaProperties = new JpaProperties();
    HashMap<String, String> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto", hbm2DDLAuto);
    properties.put("show-sql", showSql);
    properties.put("database-platform", dbPlatform);

    properties.put("hibernate.ejb.naming_strategy",
        "org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy");
    properties.put("hibernate.physical_naming_strategy",
        "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
    properties.put("hibernate.implicit_naming_strategy",
        "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");

    jpaProperties.setProperties(properties);
    jpaProperties.setDatabase(Database.MYSQL);
    jpaProperties.setShowSql(Boolean.parseBoolean(showSql));
    return jpaProperties;
  }

  public EntityManagerFactoryBuilder createEntityManagerFactory(
      JpaProperties customerJpaProperties) {
    JpaVendorAdapter jpaVendorAdapter = createAdaptor(customerJpaProperties);
    return new EntityManagerFactoryBuilder(jpaVendorAdapter, customerJpaProperties.getProperties(),
        this.persistenceUnitManager);
  }

  public JpaVendorAdapter createAdaptor(JpaProperties jpaProperties) {
    AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    adapter.setShowSql(jpaProperties.isShowSql());
    adapter.setDatabase(jpaProperties.getDatabase());
    adapter.setDatabasePlatform(jpaProperties.getDatabasePlatform());
    adapter.setGenerateDdl(jpaProperties.isGenerateDdl());
    return adapter;
  }
}



