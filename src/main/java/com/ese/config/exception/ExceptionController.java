package com.ese.config.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@ControllerAdvice
public class ExceptionController {
	
	@Autowired
	private MessageSource message;
	
	@ExceptionHandler
	public @ResponseBody String handleBusinessException(HttpServletRequest request, Exception ex) {
		return message.getMessage("excep.bussiness", null, request.getLocale());
	}
	
	@ExceptionHandler  
	public String handleBusinessException(HttpServletRequest request, DataAccessException DAE) {
		return "error/daeError";
	}
}
