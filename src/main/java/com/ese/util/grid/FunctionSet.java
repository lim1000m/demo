package com.ese.util.grid;

import java.util.ArrayList;
import java.util.Map;

public class FunctionSet{
	String func = "";
	ArrayList<String> paramList = null;
	
	public FunctionSet(){
		paramList = new ArrayList<String>();
	}

	public FunctionSet(String function){
		func = function;
		paramList = new ArrayList<String>();
	}
	
	/**
	 * @return the function
	 */
	public String getFunc() {
		return func;
	}

	/**
	 * @param function the function to set
	 */
	public void setFunc(String function) {
		this.func = function;
	}
	
	/**
	 * @return the paramList
	 */
	public ArrayList<String> getParamList() {
		return paramList;
	}

	/**
	 * @param paramList the paramList to set
	 */
	public void setParamList(ArrayList<String> paramList) {
		this.paramList = paramList;
	}
	
	public void addParam(String param){
		paramList.add(param);
	}
	
	public String getJavaScriptFunc(@SuppressWarnings("rawtypes") Map values){
		String function = func;
		for(int i=0; i<paramList.size(); i++){
			int index = i+1;
			function = function.replaceFirst("%", String.valueOf(values.get(paramList.get(i))));
		}
		return function;
	}
	
	public String getJavaScriptFunc(@SuppressWarnings("rawtypes") Object obj){
		String function = func;
		for(int i=0; i<paramList.size(); i++){
			int index = i+1;
			function = function.replaceFirst("%", GridUtil.getInstance().getFieldStringValue(obj, paramList.get(i)));
		}
		return function;
	}
}