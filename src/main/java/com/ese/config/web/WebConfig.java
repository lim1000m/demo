package com.ese.config.web;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.ese.config.init.SystemInitialization;
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
        
        ServletRegistration.Dynamic systeminitialize = servletContext.addServlet("Initialize", SystemInitialization.class);
        systeminitialize.setLoadOnStartup(2);
    }
}
