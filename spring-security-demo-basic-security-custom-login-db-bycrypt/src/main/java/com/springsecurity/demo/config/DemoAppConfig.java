package com.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan("com.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {

	// setup a varaible to hold properties
	// import org.springframework.core.env.Environment;
	@Autowired
	private Environment env;

	// setup a logger
	// import java.util.logging.Logger;
	private Logger logger = Logger.getLogger(getClass().getName());

	// define a bean for view resolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	// define a bean for security data source
	// import javax.sql.DataSource;
	@Bean
	public DataSource securityDataSource() {
		// create a connection pool
		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		// set the JDBC driver class
		try {
			dataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// log some connection props
		logger.info("JDBC URL ==>" + env.getProperty("jdbc.url"));
		logger.info("JDBC USER ==>" + env.getProperty("jdbc.user"));

		// set db connection props
		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		dataSource.setUser(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.password"));

		// set connection pool props
		dataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		dataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		dataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		dataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

		return dataSource;
	}

	// need helper method to read Env property and convert it to int
	private int getIntProperty(String propName) {

		String propVal = env.getProperty(propName);

		// now convert it to int
		int intVal = Integer.parseInt(propVal);

		return intVal;
	}
}
