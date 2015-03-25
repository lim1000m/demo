package com.ese.util.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class MessageSourceController {
	
	@Autowired
	protected MessageSource messageSource;
	
	/**
	 * MessageSource를 통해 메세지를 생성
	 *
	 * @author GOEDOKID
	 * @since 2015. 3. 12. 
	 * @param 
	 * @return
	 */
	public String getMessage(String code, Locale locale) {
		return messageSource.getMessage(code,new String[]{"Has No Properties"},locale);
	}
}
	
