package com.ese.config.init;

import javax.servlet.http.HttpServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import com.ese.server.websocket.NettyServer;
import com.ese.util.WebApplicationContextUtil;
public class SystemInitialization extends HttpServlet {

	private static final long serialVersionUID = 137261166085334062L;

	public static Logger logger = LoggerFactory.getLogger(SystemInitialization.class);
	
	public SystemInitialization(){
		super();
	}
	
	public void init(){
		logger.debug("System Initialization Start ...");
	
		WebApplicationContext wac = WebApplicationContextUtil.setWebApplicationContextByDispatcherName(	getServletContext(), "AppServlet");
		
		try {
			// Socket bind
			NettyServer.getinstance("10714", wac).init();
			
		} catch (Exception e) {
			// 시스템 초기화 에러
			logger.debug("System Initialization Error");
			e.getStackTrace();
		}
		logger.debug("System Initialization End ...");
	}
}
