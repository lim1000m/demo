package com.ese.config.spring;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(basePackages="com.ese.demo", useDefaultFilters=false, includeFilters={@Filter(Controller.class)})
@EnableWebMvc
public class WebAppConfig  extends WebMvcConfigurerAdapter{
	
	private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/jsp/";
	private static final String VIEW_RESOLVER_SUFFIX = ".jsp";
	
	/**
	 * Configuration for view resolver 
	 * @author ESE-MILLER
	 * @category configMethod
	 * @since 2014. 04. 25
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver()  {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
		viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);
		return viewResolver;
	}
	
	/**
	 * Configuration mapping using by ParameterizableViewController
	 * no need any special business logic.
	 * @author ESE-MILLER
	 * @see NOW TESTING
	 * @category configMethod
	 * @since 2014. 04. 25
	 * @return void
	 */
	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		registry.addViewController("/test.do").setViewName("home");
		registry.addViewController("/insert.do").setViewName("regedit");
	}
	
	/**
	 * Configuration the LocaleInterceptor
	 * @author ESE-MILLER
	 * @category configMethod
	 * @since 2014. 04. 25
	 * @return void
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	/**
	 * Configuration the LocaleResolver for using globalization 
	 * @author ESE-MILLER
	 * @category configMethod
	 * @since 2014. 04. 25.
	 * @return LocaleResolver 
	 */
	@Bean
	public LocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}
	
	/**
	 * Configuration HandlerInterceptor
	 * Before return the locale catch the request and setting the locale came form user side
	 * @author ESE-MILLER
	 * @category configMethod
	 * @since 2014. 04. 25.
	 * @return HandlerInterceptor 
	 */
	@Bean
	public HandlerInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor;
		localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}
	
	/**
	 * Configuartion MessageSource
	 * @author ESE-MILLER
	 * @category configMethod
	 * @since 2014. 04. 25.
	 */
	@Bean
	public MessageSource messageSource() {
		
		ResourceBundleMessageSource messageSource;
		messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("demo/messages/message");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		
		return messageSource;
	}
}
