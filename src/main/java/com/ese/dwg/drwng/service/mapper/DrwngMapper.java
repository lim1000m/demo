package com.ese.dwg.drwng.service.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.ese.domain.Drwng;

public interface DrwngMapper {

//    final String SELECT_ROWS_BY_SUBJECT = 
//    "SELECT * FROM (SELECT ID,SUBJECT,NAME,"+
//    "CREATED_DATE, MAIL,MEMO,HITS, "+
//    "ceil( rownum / #{rowsPerPage}) as page FROM SPRING_BOARD "+
//    "WHERE SUBJECT LIKE '%'||'${likeThis}'||'%' ORDER BY ID DESC) "+
//    "WHERE page = #{page}";

	
	@Select("SELECT drwng_serno drwngSerno, new_drwng_no newDrwngNo, old_drwng_no oldDrwngNo, drwng_nm drwngNm FROM EEAM_DRWNG_TBL")
	 ArrayList<Drwng> getDwgList(Map<String, String> paramMap);
	 
	 @Select("SELECT count(DRWNG_SERNO) cnt FROM EEAM_DRWNG_TBL")
	 int getDwgListCnt(Map<String, String> paramMap); 
	 
	 ArrayList<HashMap<String, Object>> getDwgListXml();
	 
	 ArrayList<HashMap<String, Object>> getDwgList_2();
}