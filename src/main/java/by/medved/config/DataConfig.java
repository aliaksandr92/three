package by.medved.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("by.medved.model")
@PropertySource("classpath:/app.properties")
@EnableJpaRepositories("by.medved.repository")
public class DataConfig
{
	@Value("${db.driver:org.postgresql.Driver}")
	private String dbDriver;
	@Value("${db.password:stalker}")
	private String dbPass;
	@Value("${db.url:jdbc:postgresql://localhost:5432/database}")
	private String dbUrl;
	@Value("${db.username:postgres}")
	private String dbName;
	@Value("${db.hibernate.dialect:org.hibernate.dialect.PostgreSQLDialect}")
	private String dbDialect;
	@Value("${db.hibernate.show_sql:true}")
	private String dbShow;
	@Value("${db.entitymanager.packages.to.scan:by.medved.model}")
	private String dbPackages;
	@Value("${db.hibernate.hbm2ddl.auto:create}")
	private String dbHibernate;

	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(dbDriver);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(dbName);
		dataSource.setPassword(dbPass);

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory()
	{
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan(dbPackages);

		entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

		return entityManagerFactoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager()
	{
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

		return transactionManager;
	}

	private Properties getHibernateProperties()
	{
		Properties properties = new Properties();
		properties.put(dbDialect, dbDialect);
		properties.put(dbShow, dbShow);
		properties.put(dbHibernate, dbHibernate);

		return properties;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer()
	{
		return new PropertySourcesPlaceholderConfigurer();
	}
}
