package com.ese.dwg.main;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ese.dwg.user.service.UserService;
import com.ese.util.SessionLocale;
import com.ese.util.controller.GridController;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController extends GridController {
	
	/** HomeService */
	@Autowired
	private UserService service;
	
	/**
	 * 로그인 성공 시 이후 main 화면으로 이동
	 *
	 * @author GOEDOKID
	 * @since 2015. 3. 9. 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/main")
    public String main(@RequestParam(value="lang", required=false, defaultValue="kr") String lang,
			Model model,
			HttpServletRequest request){

		Locale lo = SessionLocale.main(request, lang);
		
		loadGridConfig(this, "DrwngController.xml");
		
		model = setGridConfigToModel(model, this, "drwng", lo.toString());

		return "drwng/drwng"; 
	}
	
	/**
	 * 로그인 페이지로 이동
	 *
	 * @author GOEDOKID
	 * @since 2015. 3. 9. 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/login")
    public String  login( 
    		) {
		return "login/login"; 
	}
}
