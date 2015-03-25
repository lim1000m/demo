package com.ese.config.spring;


import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value={"classpath:dwg/properties/application.properties"})
@MapperScan("com.ese.dwg.**.service.mapper")
public class DataSourceConfig {

	@Autowired
	private Environment env;
	
	@Value("${type-db}")
	private String initDateBaseType;
	
	/**
	 * define the connection information for using datasource
	 * @category method
	 * @author ESE-MILLER
	 * @since 2015.02.05
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}
		   
	/**
	 * Injection mybatis configuration
	 * point the mybatis mapper location
	 * @author ESE-MILLER
	 * @since 2015.02.05
	 * @category method
	 * @return SqlSessionFactory
	 * @throws Exception
	 */
	@Bean
	public SqlSessionFactory sqlSessionFatory(DataSource datasource) throws Exception{
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(datasource);
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:dwg/mapper/mapper_"+initDateBaseType+"/*.xml"));	
		return (SqlSessionFactory) sqlSessionFactory.getObject();
	}
	
	@Bean
	public SqlSession sqlsession(SqlSessionFactory sqlSessionFatory) {
		return sqlSessionFatory.openSession();
	}
	
    @Bean
    public PlatformTransactionManager txManager(DataSource datasource) {
        return new DataSourceTransactionManager(datasource);
    }
}	
