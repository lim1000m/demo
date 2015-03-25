package com.ese.dwg.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.ese.config.init.DataInit;
import com.ese.domain.DeptAuth;
import com.ese.domain.HrhaEmp;
import com.ese.domain.JsonResponse;
import com.ese.domain.UserInfo;
import com.ese.dwg.user.service.UserService;
import com.ese.util.controller.GridController;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/user/")
public class UserController extends GridController {
	
	/** HomeService */
	@Autowired
	private UserService service;
	
	@ModelAttribute("formBean")
	public HrhaEmp createNewBean() {
		return new HrhaEmp();
	}
	
	/**
	 * 사용자 체크
	 *
	 * @author GOEDOKID
	 * @since 2015. 3. 9. 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/loginSso", method = RequestMethod.POST)
    public @ResponseBody JsonResponse loginSso(@ModelAttribute @Valid UserInfo userInfo,
    					BindingResult bindingResult,
    					Model model,
    					HttpSession session,
    					HttpServletRequest request,
    					SessionStatus sessionStatus
    		) {
		DeptAuth deptAuth = new DeptAuth();
		JsonResponse res = new JsonResponse();
		
		if(bindingResult.hasErrors()) {
			return jsonReponse(bindingResult, request.getLocale());
		} else {
			sessionStatus.setComplete();
			if(StringUtils.isNotEmpty(userInfo.getUserName())) {
				if(service.loginSso(userInfo) != null) {
					deptAuth = service.getDeptAuth(userInfo.getUserName());
					session.setAttribute("deptAuth", deptAuth);
					res.setStatus(DataInit.success);
					return res;
				} else {
					res.setStatus(DataInit.failed);
					return res;
				}
			} else {
				res.setStatus(DataInit.success);
				return res;
			}			
		}
    }
	
	/**
	 * 사용자 패스워트 체크
	 *
	 * @author GOEDOKID
	 * @since 2015. 3. 9. 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/loginPswd", method = RequestMethod.POST)
    public @ResponseBody String loginPswd(@ModelAttribute UserInfo userInfo, 
    					Model model,
    					HttpServletRequest request,
    					HttpSession session
    		) {
		DeptAuth deptAuth = new DeptAuth();

		if(StringUtils.isNotEmpty(userInfo.getUserName()) && StringUtils.isNotEmpty(userInfo.getPasswd())) {
			if(service.loginSso(userInfo) != null) {
				deptAuth = service.getDeptAuth(userInfo.getUserName());
				session.setAttribute("deptAuth", deptAuth);
				return DataInit.success;
			} else {
				return DataInit.failed;
			}	
		} else {
			return DataInit.failed;
		}
    }
}
