package com.ese.config.spring.custom;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CustomReturnValueResolver implements HandlerMethodReturnValueHandler {

	@Override
	public void handleReturnValue(Object arg0, MethodParameter arg1,
			ModelAndViewContainer arg2, NativeWebRequest arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supportsReturnType(MethodParameter arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
