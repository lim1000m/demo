package com.ese.config.web;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.ese.config.spring.AppConfig;
import com.ese.config.spring.DataSourceConfig;
import com.ese.config.spring.WebAppConfig;
 
public class WebConfig implements WebApplicationInitializer {
 
    public void onStartup(ServletContext servletContext)
            throws ServletException {
 
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.register(DataSourceConfig.class);
        servletContext.addListener(new ContextLoaderListener(context));
        
        AnnotationConfigWebApplicationContext dispatcherServletContext = new AnnotationConfigWebApplicationContext();
        dispatcherServletContext.register(WebAppConfig.class);
        
        FilterRegistration.Dynamic fr = servletContext.addFilter("CharacterEncodingFilter", org.springframework.web.filter.CharacterEncodingFilter.class);
        fr.setInitParameter("encoding", "UTF-8");
        fr.setInitParameter("forceEncoding", "true");
        fr.addMappingForServletNames(null, true, "*.do");
        
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("AppServlet", new DispatcherServlet(dispatcherServletContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("*.do"); 
    }

	/**
	 * ServletContainerInitializer랑 WebApplicationInitializer가 있음 
	 * 두가지의 차이점은 스타트업 시 시간적인 영향을 미친다는 것이다 
	 * 전자는 ServletContainerInitializer 구현체이 onStartUp만 찾아서 실행시키지만
	 * 후자는 두가지의 Initailizer의 구현체를 클래스 패스로부터 탐색하기 때문에 실행시 
	 * 실행 시간이 오래 걸린다.
	 */
//	@Override
//	public void onStartup(Set<Class<?>> arg0, ServletContext servletContext)
//			throws ServletException {
//         AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(AppConfig.class);
//        context.register(DataSourceConfig.class);
//        servletContext.addListener(new ContextLoaderListener(context));
//        
//        AnnotationConfigWebApplicationContext dispatcherServletContext = new AnnotationConfigWebApplicationContext();
//        dispatcherServletContext.register(WebAppConfig.class);
//        
//        FilterRegistration.Dynamic fr = servletContext.addFilter("CharacterEncodingFilter", org.springframework.web.filter.CharacterEncodingFilter.class);
//        fr.setInitParameter("encoding", "UTF-8");
//        fr.setInitParameter("forceEncoding", "true");
//        fr.addMappingForServletNames(null, true, "*.do");
//        
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("AppServlet", new DispatcherServlet(dispatcherServletContext));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("*.do");
//		
//	}
}
