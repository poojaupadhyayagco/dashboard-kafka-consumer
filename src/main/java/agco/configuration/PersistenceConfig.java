//package agco.configuration;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.Properties;
//
//import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;
//
//
///**
// * Spring configuration for the persistence layer of the application. Performs proper wire up of
// * Hibernate into Spring.
// */
//@Configuration
//@EnableTransactionManagement
//public class PersistenceConfig {
//
//  private static final Logger logger = LogManager.getLogger(PersistenceConfig.class);
//  private static LocalContainerEntityManagerFactoryBean localEntityManagerFactoryBean;
//
//  @Value("${datasource.dialect}")
//  private String dbDialect;
//
//  @Value("${datasource.driver}")
//  private String dbDriver;
//
//  @Value("${datasource.url}")
//  private String dbUrl;
//
//  @Value("${datasource.user}")
//  private String dbUserName;
//
//  @Value("${datasource.password}")
//  private String dbPassword;
//
//  /**
//   * Creates a {@link LocalContainerEntityManagerFactoryBean} used by Spring for
//   * persistence activities.
//   *
//   * @return A {@link LocalContainerEntityManagerFactoryBean}.
//   */
//  @Bean
//  public EntityManagerFactory entityManagerFactory() {
//    if (localEntityManagerFactoryBean != null) {
//      throw new IllegalStateException("EntityManagerFactory has already been configured!");
//    }
//
//    logger.debug("Configuring EntityManagerFactory");
//
//    localEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//    localEntityManagerFactoryBean.setDataSource(dataSource());
//    localEntityManagerFactoryBean.setPersistenceUnitName("prod");
//
//    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//    localEntityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
//    localEntityManagerFactoryBean.setJpaProperties(createDynamicHibernateProperties());
//    localEntityManagerFactoryBean.afterPropertiesSet();
//
//    logger.info("Done configuring EntityManagerFactory");
//
//    return localEntityManagerFactoryBean.getObject();
//  }
//
//  /**
//   * Configures the {@link DataSource} for this application.
//   *
//   * @return A {@link DataSource}.
//   */
//  @Bean
//  public DataSource dataSource() {
//    DriverManagerDataSource dataSource = new DriverManagerDataSource();
//    dataSource.setDriverClassName(dbDriver);
//    dataSource.setUrl(dbUrl);
//    dataSource.setUsername(dbUserName);
//    dataSource.setPassword(dbPassword);
//    return dataSource;
//  }
//
//  /**
//   * Enables ability to invoke post-processors when a persistence exception occurs.
//   *
//   * @return A {@link PersistenceExceptionTranslationPostProcessor}.
//   */
//  @Bean
//  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//    return new PersistenceExceptionTranslationPostProcessor();
//  }
//
//  /**
//   * Configures the appropriate transaction manager for the persistence layer.
//   *
//   * @return A {@link PlatformTransactionManager}.
//   */
//  @Bean
//  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//    JpaTransactionManager transactionManager = new JpaTransactionManager();
//    transactionManager.setEntityManagerFactory(entityManagerFactory);
//
//    return transactionManager;
//  }
//
//  /**
//   * Returns a {@link DAO} which is typed to the appropriate class and leverages
//   * the {@link EntityManagerFactory} singleton.
//   *
//   * @return A properly configured {@link DAO}
//   */
//  @Bean
//  @Scope(SCOPE_PROTOTYPE)
//  public DAO dao() {
//    return new DAOImpl(entityManagerFactory(), logger);
//  }
//
//  private Properties createDynamicHibernateProperties() {
//    Properties databaseProps = new Properties();
//    databaseProps.put("hibernate.dialect", dbDialect);
//    return databaseProps;
//  }
//}