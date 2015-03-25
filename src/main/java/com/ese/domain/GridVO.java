package com.ese.domain;

import java.util.ArrayList;
import java.util.List;

public class GridVO {
	int pageNum;
	int pageTotal;
	int rowTotal;
	List recordRow = null;
	
	public GridVO(){
		recordRow = new ArrayList();
	}
	
	public GridVO(int pageNum, int pageTotal, int rowTotal, List recordRow){
		this.pageNum = pageNum;
		this.pageTotal = pageTotal;
		this.rowTotal = rowTotal;
		this.recordRow = recordRow;
	}
	/**
	 * @return the pageNum
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * @return the pageTotal
	 */
	public int getPageTotal() {
		return pageTotal;
	}

	/**
	 * @param pageTotal the pageTotal to set
	 */
	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	/**
	 * @return the rowTotal
	 */
	public int getRowTotal() {
		return rowTotal;
	}

	/**
	 * @param rowTotal the rowTotal to set
	 */
	public void setRowTotal(int rowTotal) {
		this.rowTotal = rowTotal;
	}
	

	public List getRecordRow() {
		return recordRow;
	}
	
	public void setRecordRow(List recordRow) {
		this.recordRow = recordRow;
	}
}
