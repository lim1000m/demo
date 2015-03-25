package com.ese.dwg.tree;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ese.domain.Group;
import com.ese.dwg.tree.service.TreeService;
import com.ese.util.controller.GridController;
import com.ese.util.grid.GridUtil;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/tree/")
public class TreeController extends GridController {
	
	/** HomeService */
	@Autowired
	private TreeService service;
	
	/**
	 * Get the classification system tree data   
	 * @category Method
	 * @author ESE-MILLER
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/treeData.do")
	public @ResponseBody String getTreeData(
			@RequestParam(value="groupSerno", required=false) String groupSerno,
			@RequestParam(value="groupLvl", required=false) String groupLvl,
			@RequestParam(value="dvsnCd", required=true, defaultValue="CI") String dvsnCd,
			Model model
			) {

		HashMap<String, String> paramMap= new HashMap<String, String>();
		paramMap.put("groupSerno", groupSerno);
		paramMap.put("groupLvl", groupLvl);
		paramMap.put("dvsnCd", dvsnCd);
		
		List<EgovMap> dataList = service.getTreeData(paramMap);
		System.out.println(GridUtil.getTreeJSONData(dataList).toString());
		return GridUtil.getTreeJSONData(dataList).toString();
	}
}
