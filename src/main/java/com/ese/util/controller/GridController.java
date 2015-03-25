package com.ese.util.controller;

import java.io.InputStream;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.ese.config.init.DataInit;
import com.ese.domain.JsonResponse;
import com.ese.util.grid.GridUtil;

public class GridController extends MessageSourceController {
	
	@Autowired
	protected GridUtil gridUtil;
	
	protected GridUtil loadGridConfig(Object ctrl, String fileName){
		InputStream is = ctrl.getClass().getResourceAsStream(fileName);
		gridUtil.addConfFileByInputStream(is);
		return gridUtil;
	}
	
	protected Model setGridConfigToModel(Model model, Object ctrl, String gridName, String lang){
		
		model.addAttribute("lang", lang);
		model.addAttribute("ColName", gridUtil.getGridHeaderName(ctrl.getClass().getPackage().getName() + "." + gridName, lang, messageSource));
		model.addAttribute("ColModel", gridUtil.getGridHeader(ctrl.getClass().getPackage().getName() + "." + gridName));
		
		return model;
	}
	
	/**
	 * Validation Check 결과 
	 *
	 * @author GOEDOKID
	 * @since 2015. 3. 12. 
	 * @param 
	 * @return
	 */
	protected JsonResponse jsonReponse(BindingResult result, Locale locale) {
		JsonResponse jr = new JsonResponse();
		ObjectError oe = result.getAllErrors().get(0);
		jr.setFieldNm(result.getFieldError().getField());
		jr.setCode(oe.getCode());
		jr.setObjNm(oe.getObjectName());
		jr.setMessage(getMessage(oe.getDefaultMessage(), locale));
		jr.setStatus(DataInit.failed);
		
		return jr;
	}
}
