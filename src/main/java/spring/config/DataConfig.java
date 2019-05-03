package spring.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import spring.DAO.Model.Bank;
import spring.DAO.Service.BankService;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;


//@EnableAutoConfiguration
@Configuration
@Primary
@EnableTransactionManagement
@PropertySource("classpath:app.properties")
@ComponentScan(basePackages = "spring.*")
@EnableJpaRepositories( basePackages = {"spring.DAO.Repository"}, entityManagerFactoryRef = "entityManagerFactory")
//@EntityScan("by.patrik.springmvc.DAO.Model")
public class DataConfig{
	
	/*
	//Плейсхолдеры для подключения бд
	//-------------------
	@Value("${jdbc.driver}")
	private String driverName;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String userName;
	
	@Value("${jdbc.password}")
	private String password;
	//-------------------
	
	//Плейсхолдеры hibernate
	//-------------------
	@Value("${hibernate.dialect}")
	private String hibernateDialect;
	
	@Value("${hibernate.show_sql}")
	private String showsql;
	
	@Value("${hibernate.format_sql}")
	private String formatsql;
	
	@Value("${hibernate.creation_policy}")
	private String creationPolicy;
	//-------------------
	
    private static final String PROP_DATABASE_DRIVER = "jdbc.driver";
    private static final String PROP_DATABASE_URL = "jdbc.url";
    private static final String PROP_DATABASE_USERNAME = "jdbc.username";
    private static final String PROP_DATABASE_PASSWORD = "jdbc.password";
    private static final String PROP_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROP_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROP_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROP_HIBERNATE_CREATION_POLICY = "hibernate.creation_policy";
     
    
    //private static final String PROP_ENTITYMANAGER_PACKAGES_TO_SCAN = "db.entitymanager.packages.to.scan";
    //private static final String PROP_HIBERNATE_HBM2DDL_AUTO = "db.hibernate.hbm2ddl.auto";

    //@Resource
    //private Environment env;
	
	//Отвечает за подключение к бд
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/testdb?useLegacyDatetimeCode=false&serverTimezone=UTC");
		dataSource.setUsername("root");		
		dataSource.setPassword("12345");
		return dataSource;		
	}
	
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("by.patrik.srpingmvc.DAO.Model");
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;	
	}
	
	@Bean
	public Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");	
		properties.setProperty("hibernate.show_sql", "true");	
		properties.setProperty("hibernate.format_sql", "true");	
		properties.setProperty("hibernate.creation_policy", "create");	
		properties.setProperty("hibernate.hbm2ddl.auto", "create");
		
	    //properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
	    //properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		
        //properties.put(PROP_HIBERNATE_DIALECT, env.getRequiredProperty(PROP_HIBERNATE_DIALECT));
        //properties.put(PROP_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL));
        //properties.put(PROP_HIBERNATE_FORMAT_SQL, env.getRequiredProperty(PROP_HIBERNATE_FORMAT_SQL));
        //properties.put(PROP_HIBERNATE_CREATION_POLICY, env.getRequiredProperty(PROP_HIBERNATE_CREATION_POLICY));
		return properties;		
	}
	
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;		
	}
	*/
	//Отвечает за плейсхолдеры необходим что бы спринг их воспринимал(их обработка)
	//@Bean
	//public PropertyPlaceholderConfigurer placeholderConfig() {
	//	return new PropertyPlaceholderConfigurer();		
	//}

    @Resource
    private Environment environment;


    @Bean
    @Primary
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/testdb?useLegacyDatetimeCode=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("12345");
        return dataSource;
    }

    @Bean
    @Primary
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(getHibernateProperties());
        sessionFactory.setPackagesToScan("spring.DAO.Model");
        return sessionFactory;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter hibernateJPA = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceUnitName("JpaMySqlPersistance");
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setJpaVendorAdapter(hibernateJPA);
        entityManagerFactoryBean.setPackagesToScan("spring.DAO.Model");
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

        return entityManagerFactoryBean;
    }

    @Bean
    @Primary
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();

        properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));

        return properties;
    }


    
	
}
