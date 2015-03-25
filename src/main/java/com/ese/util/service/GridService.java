package com.ese.util.service;

import java.util.Map;
import com.ese.domain.GridVO;
import com.ese.util.dao.GridDao;

public abstract class GridService {
	
	protected GridDao<Object, Map<String, String>> gridDao;
	
	protected void setDao(GridDao gridDao){
		this.gridDao = gridDao;
	}
	
	public GridVO getGridVO(Map<String, String> paramMap){
		int rowCount = Integer.parseInt(paramMap.get("rows")); // 페이지당 레코드 수
		int pageNum = Integer.parseInt(paramMap.get("page")); // 페이지 수

		int rowTotal = gridDao.getCnt(paramMap); // 전체 데이터 개수 조회
		int pageTotal = (int) (rowTotal / rowCount); // 전체 페이지 수

		if ((rowTotal % rowCount) > 0)
			pageTotal++;

		if (pageNum > pageTotal)
			pageNum = pageTotal;
		
		int beginRow = (pageNum - 1) * rowCount; // 시작 레코드 번호
		int endRow = beginRow + rowCount-1;                         // 종료 레코드 번호
		
		paramMap.put("beginRow", Integer.toString(beginRow));
		paramMap.put("endRow", Integer.toString(endRow));
		
		return new GridVO(pageNum, pageTotal, rowTotal, (rowTotal == 0) ? null :gridDao.getList(paramMap));
	}
}
