package com.ese.util.grid; 

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jdom.CDATA;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.ese.domain.GridVO;
import com.ese.util.StringUtil;
import com.ese.util.controller.MessageSourceController;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * The Class GridUtil.
 *
 * @Class Name : GridUtil.java
 * @Description : 공통 데이터 처리를 위한 Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 11.12.08     길기용       최초생성
 * @ 12.05.29     이진형       DataConfig 파일을 파일명을 받아서 사용하는 형태로 변경 및 Ubicahn에 맞도록 커스터마이징(ver 1.0과 호환성 없음!!!!)
 * @ 14.05.07     이진형	   1. Grid Data type으로 json 지원
 * @ 						   2. Gird 설정 XML에 javascript 호출 관련 설정 추가 및 적용
 * @						   3. Gird 설정 XML에 html 삽입 관련 설정 추가 및 적용
 * @author 이에스이 길기용
 * @since 2011. 12. 8.
 * @version 1.0a
 * @see
 * 
 * Copyright (C) by ESE All right reserved.
 */

@Component
public class GridUtil extends MessageSourceController {
	
	public static final int DB_COLUMN_INDEX_MODE = 1;
	public static final int PROPERTY_INDEX_MODE = 2;
	
	private static GridUtil instance = null; 
	
	private WebApplicationContext context = null;
	private int mode = 0;
	
	
	/** The column sets. */
	private Map<String, ColumnSet> columnSets = new HashMap<String, ColumnSet>();
	
	public static synchronized GridUtil getInstance(){
		if(instance == null){
			//instance = new GridUtil();
			throw new NullPointerException();
		}
		return instance;
	}
	
	public static synchronized void setInstance(GridUtil gridUtil){
		instance = gridUtil;
	}
	
	public void setApplicationContext(WebApplicationContext context){
		this.context = context;
	}
	
	public void setMode(int mode){
		this.mode = mode;
	}
	
	private GridUtil(){
		columnSets = new HashMap<String, ColumnSet>();
	}
	
	public GridUtil(WebApplicationContext context, int mode){
		this.context = context;
		this.mode = mode;
		this.columnSets = new HashMap<String, ColumnSet>();
	}
	
	/**
	 * DataSet 설정 파일 추가(이미 추가되어있는 경우 다시 로딩 하지 않음).
	 *
	 * @param confFilePath 설정파일 경로
	 */
	public void addConfFile(String confFilePath){
		InputStream fs = null;
		
		try{
			fs = context.getServletContext().getResourceAsStream(confFilePath);
			addConfFileByInputStream(fs);
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	/**
	 * DataSet 설정 파일 추가(이미 추가되어있는 경우 다시 로딩 하지 않음).
	 *
	 * @param confFilePath 설정파일 경로
	 */
	public void addConfFileByInputStream(InputStream is){
		SAXBuilder builder = null;
		Document doc = null;
		
		try{
			builder = new SAXBuilder();
			doc = builder.build(is);
		}catch(Exception e){
			e.getMessage();
		}
		try{
			Element root = doc.getRootElement();
			List<Element> dataSets = root.getChildren("DataSet");
			for(Element dataSet: dataSets){
				ColumnSet cs = new ColumnSet();
				if(!isContainDataSet(dataSet.getAttributeValue("Name"), dataSet.getAttributeValue("DataKind"))){
					cs.setSetName(dataSet.getAttributeValue("Name"));
					cs.setDataKind(dataSet.getAttributeValue("DataKind"));
					ColumnSet oldColSet = columnSets.get(cs.getDataKind().toLowerCase() + "_" + cs.getSetName().toLowerCase());
					if(oldColSet != null){
						return;
					}
					List<Element> columns = dataSet.getChildren("Column");
					for (Element column: columns) {
						try {
							cs.addField(column.getChild("Field").getTextTrim());
						} catch (Exception e) {
							cs.addField("");
						}
						try {
							cs.addName(column.getChild("Name").getTextTrim());
						} catch (Exception e) {
							cs.addName("");
						}
						try {
							cs.addType(column.getChild("Type").getTextTrim());
						} catch (Exception e) {
							cs.addType("");
						}
						try {
							cs.addLength(column.getChild("Length").getTextTrim());
						} catch (Exception e) {
							cs.addLength("");
						}
						try {
							cs.addAlign(column.getChild("Align").getTextTrim());
						} catch (Exception e) {
							cs.addAlign("");
						}
						try {
							cs.addSort(column.getChild("Sort").getTextTrim());
						} catch (Exception e) {
							cs.addSort("");
						}
						try {
							cs.addHidden(column.getChild("Hidden").getTextTrim());
						} catch (Exception e) {
							cs.addHidden("");
						}
						try {
							cs.addLink(column.getChild("Link").getTextTrim());
						} catch (Exception e) {
							cs.addLink("");
						}
						try {
							List<Element> jsFunctions = column.getChildren("JSFunction");
							List<FunctionSet> funcSetList = new ArrayList<FunctionSet>();
							for(Element jsFunction  : jsFunctions){
								FunctionSet funcSet = new FunctionSet(jsFunction.getChild("Name").getTextTrim());
								List<Element> params = jsFunction.getChildren("Param");
								for(Element param : params){
									funcSet.addParam(param.getTextTrim());
								}
								funcSetList.add(funcSet);
							}
							if(jsFunctions.size()>0){
								cs.addFuncList(funcSetList);
							}
						} catch (Exception e) {
							cs.addLink("");
						}
						try{		//TAG 처리
							List<Element> tags = column.getChildren("Tags");
							List<HtmlTagSet> tagSetList = new ArrayList<HtmlTagSet>();
							for(Element tag : tags){
								HtmlTagSet htmlTagSet = new HtmlTagSet();
								htmlTagSet.setTagStr(tag.getChild("Tag").getTextTrim());
								List<Element> params = tag.getChildren("Param");
								for(Element param : params){
									htmlTagSet.addParam(param.getTextTrim());
								}
								List<Element> msgKeys = tag.getChildren("MsgKey");
								for(Element msgKey : msgKeys){
									htmlTagSet.addMessageSourceKey(msgKey.getTextTrim());
								}
								tagSetList.add(htmlTagSet);
							}
							if(tags.size() > 0){
								cs.addHtmlTagList(tagSetList);
							}
						}catch (Exception e) {
							cs.addLink("");
						}
						try {
							cs.addCdataYnList(column.getChild("CdataYn").getTextTrim());
						} catch (Exception e) {
							cs.addCdataYnList("");
						}
					}
					List<Element> rowIds = dataSet.getChildren("RowId");
					
					for(Element rowId : rowIds){
						List<Element> fields = rowId.getChildren("Field");
						for(Element field : fields){
							cs.addRowIdList(field.getTextTrim());
						}
					}
					columnSets.put(cs.getDataKind().toLowerCase() + "_" + cs.getSetName().toLowerCase(), cs);
				}else{
					//COMLog.debug("설정파일을 이미 로드하였음");
				}
			}
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	/**
	 * DataSet 존재 여부 확인
	 * @param name dataSet이름
	 * @param kind 종류
	 * @return true, if is contain data set
	 */
	
	private boolean isContainDataSet(String name, String kind){
		boolean result = columnSets.containsKey(kind.toLowerCase()+"_"+name.toLowerCase());
		return result;
	}
	
	/**
	 * 그리드 헤더 반환
	 *
	 * @param name DataSet 이름
	 * @return 생성된 그리드 헤더
	 */
	public String getGridHeader(String name){
		String header = getGridHeader(name, "Grid");
		return header;
	}
	
	/**
	 * 그리드 헤더 반환
	 *
	 * @param ctrl Controller Class Instance
	 * @param name DataSet 이름
	 * @return 생성된 그리드 헤더
	 */
	public String getGridHeader(Object ctrl, String name){
		String gridName = ctrl.getClass().getPackage().getName() + "." + name;
		String header = getGridHeader(gridName, "Grid");
		return header;
	}
	
	/**
	 * 그리드 헤더 반환.
	 *
	 * @param name DataSet 이름
	 * @param kind DataSet 종류
	 * @return 생성된 그리드 헤더
	 */
	public String getGridHeader(String name, String kind){
		String header = "";
		ColumnSet cs = getColumnSet(name, kind);
		
		for(int i=0; i<cs.size(); i++){
			String indexName = "";
			switch(this.mode){
				case DB_COLUMN_INDEX_MODE :{
					indexName = getDbColumn(cs.getField(i));
					break;
				}
				case PROPERTY_INDEX_MODE :{
					indexName = cs.getField(i);
					break;
				}
				default :{
					indexName = getDbColumn(cs.getField(i));
				}
			}
			header += "{name : '" + cs.getField(i) +"', index : '" + indexName + "', width : " + cs.getLength(i)
					+", sortable : " + cs.getSort(i) +", align : '" + cs.getAlign(i) + "', hidden : " + cs.getHidden(i) +"";
			if(cs.getType(i).equals("checkbox"))
				header +=  ", editable:true, edittype:'checkbox', editoptions: {value:'Y:N'}, formatter: 'checkbox', formatoptions: {disabled : false}";
			
			header +=  " },\n";
		}
		
		header = header.substring(0, header.length() - 2);
		
		return header;
	}
	
	/**
	 * 그리드 컬럼 셋 반환
	 *
	 * @param name Dataset 이름
	 * @param kind DataSet 종류
	 * @return the column set
	 */
	public ColumnSet getColumnSet(String name, String kind){
		return columnSets.get(kind.toLowerCase()+"_"+name.toLowerCase());
	}
	
	/**
	 * 그리드 컬럼명 반환.
	 *
	 * @param name 그리드셋 명
	 * @return 그리드 헤더 리스트 스트링
	 */
	public String getGridHeaderName(String name, /*ApplicationContext context, */ String lang, MessageSource messageSource){
		String temp ="";
		temp = getGridHeaderName(name, "Grid" , /* context,*/ lang, messageSource);
		return temp;
	}
	
	/**
	 * 그리드 컬럼명 반환.
	 *
	 * @param ctrl  Controller Class Instance
	 * @param name 그리드셋 명
	 * @return 그리드 헤더 리스트 스트링
	 */
	public String getGridHeaderName(Object ctrl, String name, String lang){
		String temp ="";
		String gridName = ctrl.getClass().getPackage().getName() + "." + name;
		temp = getGridHeaderName(gridName, "Grid" , /* context,*/ lang);
		return temp;
	}
	
	/**
	 * 그리드 컬럼명 반환.
	 *
	 * @param name 그리드셋 명
	 * @param kind 그리드 종류
	 * @return 그리드 헤더 리스트 스트링
	 */
	public String getGridHeaderName(String name, String kind, /*ApplicationContext context, */String lang, MessageSource messageSource){
		
		String headerName = "";
		ColumnSet cs = getColumnSet(name, kind);
		
		for(int i=0; i<cs.size(); i++){
			String[] names = cs.getName(i).split("[,]");
			if(names.length > 1){
				String[] params = new String[names.length-1];
				for(int j=0; j<params.length; j++){
					try {
						params[j] = messageSource.getMessage(names[j+1], new String[]{"Has No Properties"}, new Locale(lang));
					} catch (NoSuchMessageException e) {
						params[j] = names[j+1];
					}
				}
				try {
					headerName += "'"+messageSource.getMessage(names[0], params, new Locale(lang)) +"',";
				} catch (NoSuchMessageException e) {
					headerName += "'"+names[0] +"',";
				}
			}else{
				try {
					headerName += "'"+messageSource.getMessage(cs.getName(i), new String[]{"Has No Properties"}, new Locale(lang)) +"',";
				} catch (NoSuchMessageException e) {
					headerName += "'"+cs.getName(i) +"',";
				}
			}
		}

		headerName = headerName.substring(0, headerName.length() - 1);
		
		return headerName;
	}
	
	/**
	 * data 값을 DB 컬럼 형태로 변환 ('_' 포함).
	 *
	 * @param name the name
	 * @return String DB 컬럼 정보
	 * @version 1.0
	 * @since 2012. 1. 3.
	 */
	private String getDbColumn(String name){
		StringBuffer result = new StringBuffer();
    	
    	if(name != null){
    		for(int i=0; i<name.length(); i++){
        		int code = name.charAt(i);
        		
        		if(code >= 65 && code <= 90){		// 대문자인 경우
        			result.append("_" + Character.toLowerCase((char) code));
        		}else{								// 소문자인 경우
        			result.append((char) code);
        		}
        	}
    	}
    	return result.toString();
	}
	
	public String getDataXmlString(GridVO vo, String name, String lang){
		return getDataXmlString(vo, name, "Grid", lang);
	}
	
	public String getDataXmlString(GridVO vo, String name, String kind, String lang){
		return getDataXmlString(vo.getPageNum(), vo.getPageTotal(), vo.getRowTotal(), vo.getRecordRow(), name, kind, lang);
	}
	
	public String getDataXmlString(int page, int total, int records, @SuppressWarnings("rawtypes") List rowList, String name, String kind, String lang){
		Element gridElement = new Element("rows");
		gridElement.addContent(new Element("page").addContent(String.valueOf(page)));
		gridElement.addContent(new Element("total").addContent(String.valueOf(total)));
		gridElement.addContent(new Element("records").addContent(String.valueOf(records)));
		
		ColumnSet cs = getColumnSet(name, kind);
		//List rowList = vo.getRecordRow();
		
		for(int i=0; i<rowList.size(); i++){
			//BaseVO rowVo = (BaseVO)(rowList.get(i));
			//Map voMap = rowVo.toMap();
			//Map<String, String> voMap = voToMap(rowList.get(i));
			List<String> rowIdFields = cs.getRowIdList();
			String rowId = "";
			for(String rowIdField : rowIdFields){
//				rowId += voMap.get(rowIdField) + "__";
				rowId += getFieldStringValue(rowList.get(i), rowIdField) + "__";
			}
			rowId = rowId.substring(0, rowId.length()-2);
			
			Element rowElement = new Element("row").setAttribute("id", rowId);
			int curHtmlTagListIdx = 0;
			int curFuncSetListIdx = 0;
			for(int j=0; j<cs.size(); j++){
//				String value = String.valueOf(voMap.get(cs.getField(j)));
				String value = getFieldStringValue(rowList.get(i),cs.getField(j));
				if(cs.getType(j).toLowerCase().equals("cdata")){
					rowElement.addContent(new Element("cell").addContent(new CDATA("CDATA").setText(value)));
				}else if(cs.getType(j).toLowerCase().equals("function")) {
					List<FunctionSet> funcList = cs.getFuncList(curFuncSetListIdx);
					curFuncSetListIdx++;
					String function = "";
					for(FunctionSet func : funcList){
//						function += func.getJavaScriptFunc(voMap) + ";";
						function += func.getJavaScriptFunc(rowList.get(i)) + ";";
					}
					rowElement.addContent(new Element("cell").addContent(new CDATA("CDATA").setText("<a href='javascript:" + function + "'>"+value+"</a>")));
				}else if(cs.getType(j).trim().toLowerCase().equals("htmltag")){
					List<HtmlTagSet> htmlTagList = cs.getHtmlTagList(curHtmlTagListIdx);
					curHtmlTagListIdx++;
//					List<HtmlTagSet> htmlTagList = cs.getHtmlTagSet();
					String tagStr = "";
					for(HtmlTagSet tagset: htmlTagList){
//						tagStr += tagset.getTagString(voMap, (MessageSource)context, lang);
						tagStr += tagset.getTagString(rowList.get(i), (MessageSource)context, lang);
					}
					rowElement.addContent(new Element("cell").addContent(new CDATA("CDATA").setText(tagStr)));
				}else {
					rowElement.addContent(new Element("cell").addContent(value));
				}
			}
			gridElement.addContent(rowElement);
		}
		
		Document doc = new Document();
		doc.setRootElement(gridElement);
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat().setEncoding("UTF-8"));
		return outputter.outputString(doc);
	}
		
	public String getJsonString(GridVO vo, String name, String lang){
		return getJsonString(vo.getPageNum(), vo.getPageTotal(), vo.getRowTotal(), vo.getRecordRow(), name, "Grid", lang);
	}
	
	public String getJsonString(Object ctrl, GridVO vo, String shortName, String lang){
		StringBuffer sb = new StringBuffer();
		sb.append(ctrl.getClass().getPackage().getName());
		sb.append(".");
		sb.append(shortName);
		String name = sb.toString();
		return getJsonString(vo.getPageNum(), vo.getPageTotal(), vo.getRowTotal(), vo.getRecordRow(), name, "Grid", lang);
	}
	
	public String getJsonString(GridVO vo, String name, String kind, String lang){
		return getJsonString(vo.getPageNum(), vo.getPageTotal(), vo.getRowTotal(), vo.getRecordRow(), name, kind, lang);
	}
	
	public String getJsonString(int page, int pageTotal, int rowTotal, @SuppressWarnings("rawtypes") List rowList, String name, String kind, String lang){
		ColumnSet cs = getColumnSet(name, kind);
		
		if(rowList == null || rowList.size()==0){
			return "{\"page\":1,\"records\":0,\"rows\":[],\"total\":0,\"userdata\":null}";
		}
		
		jqGridRow[] rows = new jqGridRow[rowList.size()];
		for(int i=0; i<rowList.size(); i++){
//			Map<String, String> voMap = voToMap(rowList.get(i));
			List<String> rowIdFields = cs.getRowIdList();
			String rowId = "";
			for(String rowIdField : rowIdFields){
//				rowId += voMap.get(rowIdField) + "__";
				rowId += getFieldStringValue(rowList.get(i), rowIdField) + "__";
			}
			rowId = rowId.substring(0, rowId.length()-2);
			
			Object[] cells = new Object[cs.size()];
			int curHtmlTagListIdx = 0;
			int curFuncSetListIdx = 0;
			for(int j=0; j<cs.size(); j++){
				if(cs.getType(j).toLowerCase().equals("function")) {
//					String value = String.valueOf(voMap.get(cs.getField(j)));
					String value = getFieldStringValue(rowList.get(i),cs.getField(j));
					List<FunctionSet> funcList = cs.getFuncList(curFuncSetListIdx);
					curFuncSetListIdx++;
					String function = "";
					for(FunctionSet func : funcList){
//						function += func.getJavaScriptFunc(voMap) + ";";
						function += func.getJavaScriptFunc(rowList.get(i)) + ";";
					}
					cells[j] = "<a href='javascript:" + function + "'>"+value+"</a>";
				}else if(cs.getType(j).trim().toLowerCase().equals("htmltag")){
					List<HtmlTagSet> htmlTagList = cs.getHtmlTagList(curHtmlTagListIdx);
					curHtmlTagListIdx++;
//					List<HtmlTagSet> htmlTagList = cs.getHtmlTagSet();
					String tagStr = "";
					for(HtmlTagSet tagset: htmlTagList){
						tagStr += tagset.getTagString(rowList.get(i), (MessageSource)context, lang);
					}
					cells[j] = tagStr;
				}else {
					String value = getFieldStringValue(rowList.get(i),cs.getField(j));
					cells[j] = value;
				}
			}
			
			jqGridRow row = new jqGridRow(rowId, cells);
			rows[i] = row;
		}
		jqGridDataFrame dataFrame = new jqGridDataFrame(page, rowTotal, pageTotal, rows, null);
		return dataFrame.GetDefaultJSON();
	}
	
//	public Map<String, String> voToMap(Object obj){
//		Map<String, String> map = new HashMap<String, String>();
//
//		for (Field field: obj.getClass().getDeclaredFields()) {
//			try {
//				field.setAccessible(true);
//				String name = field.getType().getSimpleName();
//				name = name.toLowerCase();
//				if(name.equals("string") || name.equals("long") || name.equals("integer") || name.equals("float")){
//					map.put(field.getName(), String.valueOf(field.get(obj)));
//				}
//			} catch (IllegalArgumentException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			}
//		}
//		return map;
//	}
	
	public String getFieldStringValue(Object obj, String fieldName){
		String rtn = "";
		try {
			String[] fieldNameSplit = fieldName.split("[.]");
			if(fieldNameSplit.length > 1){
				rtn = getFieldStringValue(obj, fieldNameSplit);
			}else{
				Field field = obj.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);
				rtn = String.valueOf(field.get(obj));
				if (rtn.equals("null")) {
					rtn = "";
				}
			}
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rtn;
	}
	
	public String getFieldStringValue(Object obj, String[] fieldNames){
		String rtn = "";
		if(obj == null){
			return "";
		}
		if(fieldNames.length == 1){
			Field field;
			try {
				field = obj.getClass().getDeclaredField(fieldNames[0]);
				field.setAccessible(true);
				rtn = String.valueOf(field.get(obj));
				if (rtn.equals("null")) {
					rtn = "";
				}
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			String[] newFieldNames = new String[fieldNames.length-1];
			System.arraycopy(fieldNames, 1, newFieldNames, 0, newFieldNames.length);
			Field field;
			try {
				field = obj.getClass().getDeclaredField(fieldNames[0]);
				field.setAccessible(true);
				rtn = getFieldStringValue(field.get(obj), newFieldNames);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rtn;
	}
	
   /**
     * 
     * 조회된 데이터를 Tree에 적합한 json 형태로 변환 
     * @version 1.0
     * @since 2013. 7. 5.
     * @param List<EgovMap>	조회 결과
     * @param String					Tree 유형
     * @return Element				조회 결과
     * 참고사항 : Tree 키 값은 id로 DB Alias 지정, 표시될 항목은 name으로 DB Alias 지정
     */
	public static Element getTreeXMLData(List<EgovMap> dataList){
		
    	Element rootElt = new Element("root");
    	if(dataList != null && dataList.size()>0){
        	
        	for(EgovMap data : dataList){
        		Element itemElt = new Element("item");

        		Iterator<String> iterator = data.keySet().iterator();
        		while (iterator.hasNext()) {
        			String key = iterator.next();
        			if("name".equals(key)){
        				Element contentElt = new Element("content");
        				
        				// 이름 표시
        				if("Y".equals(data.get("gray"))){
        					
        					Element nameElt = new Element("name");
        					Element fontElt = new Element("font").setAttribute("color", "#cccccc");
        					fontElt.addContent(StringUtil.strToXML(data.get(key).toString()));
        					
        					nameElt.addContent(fontElt);
        					contentElt.addContent(nameElt);
        				}else{
        					Element nameElt = new Element("name");
        					nameElt.addContent(StringUtil.strToXML(data.get(key).toString())).toString();
        					
        					contentElt.addContent(nameElt);
        				}
        				
        				itemElt.addContent(contentElt);
        				        				
        			}else if("isLeaf".equals(key)){
        				itemElt.setAttribute(key, StringUtil.strToXML(data.get(key).toString()));
        				// 하위 분류가 존재하는 경우 open 아이콘 표시
        				if(data.get(key).toString().equals("Y"))
        					itemElt.setAttribute("state", "closed");
        			}else{
        				// 그 외 
        				itemElt.setAttribute(key, StringUtil.strToXML(data.get(key).toString()));
        			}
        		}
        		// 적용된 xml을 root 하위에 생성
        		rootElt.addContent(itemElt);
        	}
    	}
    	return rootElt;
    }
	
	  /**
     * 
     * 조회된 데이터를 Tree에 적합한 json 형태로 변환 
     * @version 1.0
     * @since 2013. 7. 5.
     * @param List<EgovMap>	조회 결과
     * @param String					Tree 유형
     * @return Element				조회 결과
     * 참고사항 : Tree 키 값은 id로 DB Alias 지정, 표시될 항목은 name으로 DB Alias 지정
     */
	public static JSONArray getTreeJSONData(List<EgovMap> dataList){
		
		JSONArray jsonArr = new JSONArray();
		
    	if(dataList != null && dataList.size()>0){
        	
        	for(EgovMap data : dataList){

        		JSONObject jsonObject = new JSONObject();
        		JSONObject jsonMeta = new JSONObject();
        		
        		Iterator<String> iterator = data.keySet().iterator();
        		while (iterator.hasNext()) {
        			String key = iterator.next();
        			
        			if("data".equals(key)) {
        				jsonObject.put("data", data.get(key));
        			}else if("isLeaf".equals(key)){
        				if(data.get(key).toString().equals("Y"))
        					jsonObject.put("state", "open");
        			}else{
        				jsonMeta.put(key, data.get(key));
        			}
        		}
        		jsonObject.put("metadata", jsonMeta);
        		jsonArr.add(jsonObject);
        	}
    	}
    	return jsonArr;
    }
}
