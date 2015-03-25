package com.ese.util;

import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;

public class WebApplicationContextUtil extends WebApplicationContextUtils {
	private static WebApplicationContext wac;
	private static WebApplicationContext requiredWac;
	private static ServletContext servletContext;
	
	public static void setServletContext(ServletContext servletContext) {
		WebApplicationContextUtil.servletContext = servletContext;	
	}
	
	public static WebApplicationContext getRequiredWebApplicationContext() {
		requiredWac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		return requiredWac;
	}
	
	public static WebApplicationContext setWebApplicationContext(WebApplicationContext wac){
		WebApplicationContextUtil.wac = wac;
		return WebApplicationContextUtil.wac;
	}
	
	public static WebApplicationContext setWebApplicationContextByDispatcherName(ServletContext servletContext, String dispathcerServletName){
		WebApplicationContextUtil.wac = WebApplicationContextUtils.getWebApplicationContext(
				servletContext, FrameworkServlet.SERVLET_CONTEXT_PREFIX+dispathcerServletName
				);
		requiredWac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		return WebApplicationContextUtil.wac;
	}
	
	public static WebApplicationContext getWebApplicationContext() {
		return WebApplicationContextUtil.wac;
	}
	
	public static Object getBeanFromRoot(String beanName){
		return getRequiredWebApplicationContext().getBean(beanName);
	}
}
