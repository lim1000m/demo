package com.ese.config.interceptor;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

public class CommonInterceptor implements WebRequestInterceptor{

	/**
	 * 요청에 대한 VIEW Rendering이 되고 난 뒤에 호출되는 메서드로서 
	 * 요청 중 Exception이 발생하더라도 호출되는 메서드이다.
	 * 리소스 정리하는 목적으로 많이 사용된다.
	 */
	@Override
	public void afterCompletion(WebRequest arg0, Exception arg1)
			throws Exception {
		System.out.println("Call - afterCompletion");
	}

	/**
	 * HandlerAdapter가 호출되기 전에 실행
	 */
	@Override
	public void postHandle(WebRequest arg0, ModelMap arg1) throws Exception {
		System.out.println("Call - postHandle");		
	}

	/**
	 * HandlerAdapter가 호출된 이후 실행
	 */
	@Override
	public void preHandle(WebRequest arg0) throws Exception {
		System.out.println("Call - preHandle");		
	}

}
