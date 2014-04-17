package com.ese.config.spring;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages="com.ese.demo", excludeFilters={@Filter(Controller.class), @Filter(Configuration.class)} )
@EnableScheduling
@EnableAspectJAutoProxy
@EnableCaching
public class AppConfig  {

	/**
	 * ${...} 랑 @Value같은걸 사용하게 해줌
	 * @author ESE-MILLER
	 * @category Method
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	/**
	 * manage set of caches
	 * @author ESE-MILLER
	 * @category Method
	 * @return
	 */
	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}
}
