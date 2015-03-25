package com.ese.dwg.drwng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ese.domain.GridVO;
import com.ese.dwg.drwng.service.mapper.DrwngMapper;

@Service("drwngService")
public class DrwngService {

	@Autowired
	SqlSession session;
	
	public List<HashMap<String, Object>> getDwgXml() {
		return session.getMapper(DrwngMapper.class).getDwgListXml();
	}

	public GridVO getDwgList(Map<String, String> paramMap){
		int rowCount = Integer.parseInt(paramMap.get("rows")); // 페이지당 레코드 수
		int pageNum = Integer.parseInt(paramMap.get("page")); // 페이지 수
		int rowTotal = session.getMapper(DrwngMapper.class).getDwgListCnt(paramMap); // 전체 데이터 개수 조회
		int pageTotal = (int) (rowTotal / rowCount); // 전체 페이지 수

		if ((rowTotal % rowCount) > 0)
			pageTotal++;

		if (pageNum > pageTotal)
			pageNum = pageTotal;
		
		int beginRow = (pageNum - 1) * rowCount; // 시작 레코드 번호
		int endRow = beginRow + rowCount-1;                         // 종료 레코드 번호
		
		paramMap.put("beginRow", Integer.toString(beginRow));
		paramMap.put("endRow", Integer.toString(endRow));
		
		return new GridVO(pageNum, pageTotal, rowTotal, (rowTotal == 0) ? null : session.getMapper(DrwngMapper.class).getDwgList(paramMap));
	}
} 
