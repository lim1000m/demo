package com.ese.dwg.user.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.ese.domain.DeptAuth;
import com.ese.domain.Dvsn;
import com.ese.domain.UserInfo;


public interface UserMapper {
	
	@Select("SELECT USER_NAME userName, PERSON_NAME personName, PASSWD passwd FROM EEAM_USER_INFO_TBL WHERE USER_NAME = #{userName}")
	public UserInfo getLoginSso(UserInfo userInfo);
	
	@Select("SELECT deptUser.USER_ID userId, "
			+ "CASE WHEN dvsn.dvsn_cd is null THEN dept.DVSN_CD "
			+ "		ELSE dvsn.dvsn_cd "
			+ "		END dvsnCd,"
			+ "deptAuth.READ_AUTH readAuth, deptAuth.VIEW_AUTH viewAuth, "
			+ "deptAuth.WRITE_AUTH writeAuth, deptAuth.MODIFY_AUTH modifyAuth, deptAuth.REVISION_AUTH revisionAuth, deptAuth.DELETE_AUTH deleteAuth, "
			+ "deptAuth.DOWNLOAD_AUTH downloadAuth, deptAuth.PRINT_AUTH printAuth, dept.DEPT_NM deptNm, dept.description "
			+ "FROM EEAM_DEPT_USER_TBL deptUser LEFT JOIN EEAM_USER_TBL dvsn ON dvsn.USER_ID = deptUser.user_id, "
			+ "EEAM_DEPT_TBL dept, EEAM_DEPT_AUTH_TBL deptAuth "
			+ "WHERE deptUser.user_id = #{userName} "
			+ "AND deptUser.DEPT_CD = dept.DEPT_CD "
			+ "AND dept.DEPT_CD = deptAuth.DEPT_CD ")
	public DeptAuth getDeptAuth(String userName);
	
	@Select("SELECT DVSN_CD dvsnCd, DVSN_NM dvsnNm, STD_DVSN_CD stdDvsnCd, USE_YN useYn "
			+ "FROM EEAM_DVSN_TBL "
			+ "WHERE USE_YN = 'Y' AND ORDR is not null "
			+ "ORDER BY ORDR")
	public List<Dvsn> getDvsn(); 
}