package com.ese.config.spring;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration 
@EnableTransactionManagement
@ComponentScan(basePackages="com.ese.demo", excludeFilters={@Filter(Controller.class), @Filter(Configuration.class)} )
@PropertySource(value={"classpath:application.properties"})
@EnableScheduling
@EnableAspectJAutoProxy
@EnableCaching
public class AppConfig  {

	
	
}
