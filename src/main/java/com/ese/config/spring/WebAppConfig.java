package com.ese.config.spring;


import java.util.List;

import javax.servlet.jsp.tagext.ValidationMessage;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
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

import com.ese.config.annotation.impl.SessionAttributeProcessor;
import com.ese.config.interceptor.CommonInterceptor;
import com.ese.config.spring.custom.CustomArgumentResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.ese", useDefaultFilters=false, includeFilters={@Filter(Controller.class)})
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
		registry.addViewController("/").setViewName("login/login");
		registry.addViewController("/login.do").setViewName("login/login");
		registry.addViewController("/socket.do").setViewName("main/socket");
	}
	
	/**
	 * Configuration the LocaleInterceptor
	 * This configuration is very powerful and flexible then setting interceptor into  RequestMappingHandlerMapping configuration
	 * This Interceptor is called InterceptorRegistory
	 * powerful, flexible and possible mapping interceptor each URL
	 * If you don't setting the URI Pattern, mapping all URI to Interceptor  
	 * @author ESE-MILLER
	 * @category configMethod
	 * @since 2014. 04. 25
	 * @return void
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
		registry.addWebRequestInterceptor(commonInterceptor());
//		InterceptorRegistration registration;
//		registration = registry.addInterceptor(localeChangeInterceptor());
//		registration.addPathPatterns("/demo/**");
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
	 * Spring provide two Intercetor 
	 * One thing is HandlerInterceptor and the other thing is webRequestInterceptor
	 * What's different between handlerInterceptor and WebReuqestInterceptor is 
	 * extends and implements
	 * @return
	 */
	@Bean
	public WebRequestInterceptor commonInterceptor() {
		CommonInterceptor commonInterceptor = new CommonInterceptor();
		return commonInterceptor ;
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
		messageSource.setBasename("dwg/messages/message");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		
		return messageSource;
	}
	
	/**
	 * injection messageSource to Validator
	 *
	 * @author GOEDOKID
	 * @since 2015. 3. 12. 
	 * @param 
	 * @return
	 */
	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean validator= new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		return validator;
	}
	
	@Override
	public Validator getValidator() {
		return validator();
	}
	
	/**
	 * @SessionAttribute annotation configuration
	 */
	@Bean
	public SessionAttributeProcessor sessionAttributeProcessor() {
		return new SessionAttributeProcessor();
	}

	@Bean
	public HandlerMethodArgumentResolver customArgumentResolver() {
		return new CustomArgumentResolver();
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(sessionAttributeProcessor());
		argumentResolvers.add(customArgumentResolver());
	}
	
	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		returnValueHandlers.add(sessionAttributeProcessor());
	}
}
