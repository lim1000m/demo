package com.ese.util.grid;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Class Name : ColumnSet.java
 * @Description : Grid 형태의 데이터 처리를 위한 DataSet
 * @Modification Information  
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 11.12.07     길기용       최초생성
 * @ 12.05.29     이진형       UbiCahn용으로 수정
 * 
 * @author 이에스이 길기용
 * @since 2011. 12. 7.
 * @version 1.0
 * @see
 * 
 *  Copyright (C) by ESE All right reserved.
 */

public class ColumnSet {
	private String setName = null;
	private String dataKind = null;
	private ArrayList<String> rowIdList = null;
	private ArrayList<String> columnTypeList = null;
	private ArrayList<String> fieldList = null;
	private ArrayList<String> nameList = null;
	private ArrayList<String> typeList = null;
	private ArrayList<String> lengthList = null;
	private ArrayList<String> alignList = null;
	private ArrayList<String> sortList = null;
	private ArrayList<String> hiddenList = null;
	private ArrayList<String> styleList = null; 
	private ArrayList<String> editableList = null;
	private ArrayList<String> linkList = null;
	private ArrayList<String> cdataYnList = null;
	private ArrayList<List<FunctionSet> >funcList = null;
	private ArrayList< List<HtmlTagSet> > htmlTagSet = null;
	private int curTagSetIdx = 0;
	
	public ColumnSet() {
		rowIdList = new ArrayList<String>();
		columnTypeList = new ArrayList<String>(); 
		fieldList = new ArrayList<String>();
		nameList = new ArrayList<String>();
		typeList = new ArrayList<String>();
		lengthList = new ArrayList<String>();
		alignList = new ArrayList<String>();
		sortList = new ArrayList<String>();
		hiddenList = new ArrayList<String>();
		styleList = new ArrayList<String>();
		editableList = new ArrayList<String>();
		linkList = new ArrayList<String>();
		cdataYnList = new ArrayList<String>();
		funcList = new ArrayList< List<FunctionSet> >();
		htmlTagSet = new ArrayList< List<HtmlTagSet> >();
	}
	
	public ColumnSet(String setName, String dataKind) {
		this();
		this.setName = setName;
		this.dataKind = dataKind;
	}

	public String getSetName() {
		return setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}

	public String getDataKind() {
		return dataKind;
	}

	public void setDataKind(String dataKind) {
		this.dataKind = dataKind;
	}

	public ArrayList<String> getColumnTypeList() {
		return columnTypeList;
	}

	public void setColumnTypeList(ArrayList<String> columnTypeList) {
		this.columnTypeList = columnTypeList;
	}

	public ArrayList<String> getFieldList() {
		return fieldList;
	}

	public void setFieldList(ArrayList<String> fieldList) {
		this.fieldList = fieldList;
	}
	
	public ArrayList<String> getNameList() {
		return nameList;
	}

	public void setNameList(ArrayList<String> nameList) {
		this.nameList = nameList;
	}

	public ArrayList<String> getTypeList() {
		return typeList;
	}

	public void setTypeList(ArrayList<String> typeList) {
		this.typeList = typeList;
	}

	public ArrayList<String> getLengthList() {
		return lengthList;
	}

	public void setLengthList(ArrayList<String> lengthList) {
		this.lengthList = lengthList;
	}

	public ArrayList<String> getAlignList() {
		return alignList;
	}

	public void setAlignList(ArrayList<String> alignList) {
		this.alignList = alignList;
	}

	public ArrayList<String> getSortList() {
		return sortList;
	}

	public void setSortList(ArrayList<String> sortList) {
		this.sortList = sortList;
	}

	public ArrayList<String> getHiddenList() {
		return hiddenList;
	}

	public void setHiddenList(ArrayList<String> showList) {
		this.hiddenList = showList;
	}

	public ArrayList<String> getStyleList() {
		return styleList;
	}

	public void setStyleList(ArrayList<String> styleList) {
		this.styleList = styleList;
	}

	public ArrayList<String> getEditableList() {
		return editableList;
	}

	public void setEditableList(ArrayList<String> editableList) {
		this.editableList = editableList;
	}

	public ArrayList<String> getLinkList() {
		return linkList;
	}

	public void setLinkList(ArrayList<String> linkList) {
		this.linkList = linkList;
	}

	
	public void addColumnType(String type){
		columnTypeList.add(type);
	}
	
	public String getColumnType(int index){
		return columnTypeList.get(index);
	}
	
	public void addField(String type){
		fieldList.add(type);
	}
	
	public String getField(int index){
		return fieldList.get(index);
	}

	public void addName(String type){
		nameList.add(type);
	}
	
	public String getName(int index){
		return nameList.get(index);
	}
	
	public void addType(String type){
		columnTypeList.add(type);
	}
	
	public String getType(int index){
		return columnTypeList.get(index);
	}
	
	public void addLength(String type){
		lengthList.add(type);
	}
	
	public String getLength(int index){
		return lengthList.get(index);
	}
	
	public void addAlign(String type){
		alignList.add(type);
	}
	
	public String getAlign(int index){
		return alignList.get(index);
	}
	public void addSort(String type){
		sortList.add(type);
	}
	
	public String getSort(int index){
		return sortList.get(index);
	}

	public void addHidden(String type){
		hiddenList.add(type);
	}
	
	public String getHidden(int index){
		return hiddenList.get(index);
	}
	public void addStyle(String type){
		styleList.add(type);
	}
	
	public String getStyle(int index){
		return styleList.get(index);
	}

	public void addLink(String type){
		linkList.add(type);
	}
	
	public String getLink(int index){
		return linkList.get(index);
	}	
	
	public int size(){
		return fieldList.size();
	}

	/**
	 * @return the rowIdList
	 */
	public ArrayList<String> getRowIdList() {
		return rowIdList;
	}

	/**
	 * @param rowIdList the rowIdList to set
	 */
	public void setRowIdList(ArrayList<String> rowIdList) {
		this.rowIdList = rowIdList;
	}

	public void addRowIdList(String rowIdFeild){
		this.rowIdList.add(rowIdFeild);
	}
	
	public String getRowIdList(int idx){
		return this.rowIdList.get(idx);
	}
	
	public int getRowIdListSize(){
		return this.rowIdList.size();
	}

	/**
	 * @return the cdataYnList
	 */
	public ArrayList<String> getCdataYnList() {
		return cdataYnList;
	}
	
	/**
	 * @return the cdataYnList
	 */
	public String getCdataYnList(int index) {
		return cdataYnList.get(index);
	}

	/**
	 * @param cdataYnList the cdataYnList to set
	 */
	public void setCdataYnList(ArrayList<String> cdataYnList) {
		this.cdataYnList = cdataYnList;
	}
	
	public void addCdataYnList(String yn){
		this.cdataYnList.add(yn);
	}
	
	/**
	 * @return the funcList
	 */
	public ArrayList< List<FunctionSet> > getFuncList() {
		return funcList;
	}

	/**
	 * @param funcList the funcList to set
	 */
	public void setFuncList(ArrayList< List<FunctionSet> > funcList) {
		this.funcList = funcList;
	}

	public void addFuncList(List<FunctionSet> funcSet){
		this.funcList.add(funcSet);
	}
	
	public List<FunctionSet> getFuncList(int index){
		return this.funcList.get(index);
	}

	public ArrayList< List<HtmlTagSet> > getHtmlTagSet() {
		return htmlTagSet;
	}
	

	public void setHtmlTagSet(ArrayList< List<HtmlTagSet> > tagSet) {
		this.htmlTagSet = tagSet;
	}
	
	public void addHtmlTagList(List<HtmlTagSet> htmlTagSet){
		this.htmlTagSet.add(htmlTagSet);
	}
	
	public List<HtmlTagSet> getHtmlTagList(int index){
		return this.htmlTagSet.get(index);
	}

}