package com.ese.dwg.drwng;

import java.util.HashMap;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ese.domain.Drwng;
import com.ese.domain.GridVO;
import com.ese.dwg.drwng.service.DrwngService;
import com.ese.util.SessionLocale;
import com.ese.util.controller.GridController;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/drwng/")
public class DrwngController extends GridController {
	
	/** HomeService */
	@Autowired
	private DrwngService service;
	
	@ModelAttribute("formBean")
	public Drwng createNewBean() {
		return new Drwng();
	}
	
	/**
	 * 도면 목록 조회 화면
	 * @author GOEDOKID
	 * @param lang
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/drwng")
	public String getEnv(	@RequestParam(value="lang", required=false, defaultValue="kr") String lang,
							Model model,
							HttpServletRequest request){

		Locale lo = SessionLocale.main(request, lang);
		
		loadGridConfig(this, "DrwngController.xml");
		
		model = setGridConfigToModel(model, this, "drwng", lo.toString());
		
		return "drwng/drwng";
	}
	
	/**
	 * 도면 목록 조회
	 * @author GOEDOKID
	 * @param lang
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/drwngList")
	public @ResponseBody String gridMain(	@RequestParam(value="lang", required=false, defaultValue="kr") String lang,
							@RequestParam(value="page", required=false, defaultValue="1") String page,
							@RequestParam(value="rows", required=false, defaultValue="10")String rows,
							@RequestParam(value="sord", required=false, defaultValue="drwngNm") String sord,
							@RequestParam(value="sidx", required=false, defaultValue="desc") String sidx,
							HttpServletRequest request){
		
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("rows", rows);
		paramMap.put("page", page);
		paramMap.put("sidx", sidx);
		paramMap.put("sord", sord);
		
		String json = "";
		
		GridVO gridVO = service.getDwgList(paramMap);
		
		if(gridVO.getRecordRow() != null) {
			gridVO.setRecordRow(gridVO.getRecordRow());
			json= gridUtil.getJsonString(this, gridVO, "drwng", lang);	
		}
		return json;
	}
    
	/**
	 * 도면 삭제 이동
	 *
	 * @author GOEDOKID
	 * @since 2015. 3. 9. 
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/goTestInst", method=RequestMethod.POST)
	public @ResponseBody String goTestInst(
			@ModelAttribute @Valid Drwng drwng,
			BindingResult result
			) {
		
		return null;
	}
}
