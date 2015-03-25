package com.ese.util.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.context.MessageSource;

public class HtmlTagSet {

	String tagStr = "";
	List<String> paramList = new ArrayList<String>();
	List<String> msgKeyList = new ArrayList<String>();
	
	public void setTagStr(String htmlTagStr){
		this.tagStr = htmlTagStr;
	}
	
	public String getTagStr() {
		return tagStr;
	}

	public List<String> getParamList() {
		return paramList;
	}

	public List<String> getMsgKeyList() {
		return msgKeyList;
	}

	public HtmlTagSet() {
		// TODO Auto-generated constructor stub
	}
	
	public HtmlTagSet(String tagStr) {
		this.tagStr = tagStr;
	}
	
	public void addParam(String param){
		paramList.add(param);
	}
	
	public void addMessageSourceKey(String key){
		msgKeyList.add(key);
	}
	
	public String getTagString(Map<String, String> values, MessageSource messageSource, String lang){
		String tags = tagStr;
		for(int i=0; i<paramList.size(); i++){
			int index = i+1;
			tags = tags.replaceFirst("%%", String.valueOf(values.get(paramList.get(i))));
		}
		for(int i=0; i<msgKeyList.size(); i++){
			int index = i+1;
			tags = tags.replaceFirst("%", messageSource.getMessage(msgKeyList.get(i), new String[]{"Has No Properties"}, new Locale(lang)));
			//COMLog.info(this, tags);
		}
		//COMLog.info(this, tags);
		return tags;
	}
	
	public String getTagString(Object obj, MessageSource messageSource, String lang){
		String tags = tagStr;
		for(int i=0; i<paramList.size(); i++){
			int index = i+1;
			tags = tags.replaceFirst("%%", GridUtil.getInstance().getFieldStringValue(obj, paramList.get(i)));
		}
		for(int i=0; i<msgKeyList.size(); i++){
			int index = i+1;
			tags = tags.replaceFirst("%", messageSource.getMessage(msgKeyList.get(i), new String[]{"Has No Properties"}, new Locale(lang)));
			//COMLog.info(this, tags);
		}
		//COMLog.info(this, tags);
		return tags;
	}

}