package com.ese.config.spring;


import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration 
public class DataSource  {

	@Bean(destroyMethod="close")
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
		dataSource.setUsername("UBICAHN30G");
		dataSource.setPassword("UBICAHN30G");
		dataSource.setDefaultAutoCommit(false);
		return dataSource;
	}
	
}
