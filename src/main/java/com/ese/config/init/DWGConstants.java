package com.ese.config.init;

public class DWGConstants {

	// IAD Login 처리
	public static final int IAD_LOGIN_SUCCESS=0;
	public static final int IAD_NOT_FOUND_USER=1;
	public static final int IAD_PASSWORD_MISS=2;
	
	public static final String IAD_YES="Y";
	public static final String IAD_NO="N";
	
	// WIDGET SET 처리 성공 // 권한없음 // 위젯이 없음
	public static final int IAD_WIDGET_NOT_EXIST=0;
	public static final int IAD_WIDGET_EXIST=1;
	public static final int IAD_WIDGET_SUCCESS=2;
	
	// IAD mode 타입 
	public static final String IAD_MODE_TYPE_CREATE 	= "C";
	public static final String IAD_MODE_TYPE_DELETE 	= "D";
	public static final String IAD_MODE_TYPE_UPDATE 	= "U";

	// IAD 처리 성공/실패
	public static final String IAD_PROC_SUCCESS 		= "SUCCESS"; 
	public static final String IAD_PROC_FAIL	 		= "FAIL"; 
	
	
	// 시스템 CHRACTER SET
	public static final String CHAR_SET = "UTF-8";
	
	// 메시지 교환 방식
	public static class MSG_EXCH_PTRN{
		public static final String ONE_WAY 			= "1";	
		public static final String ONE_WAY_ACK 		= "2";
		public static final String ACK 				= "3";
	}
	
	public static class LAYOUT_COMMAND{
		public static final String LAYOUT_LIST = "LIST";
		public static final String LAYOUT_OPEN = "OPEN";
	}
	
	public static class LAYOUT_API_FORMAT{
		public static final String JSON = "JSON";
		public static final String XML = "XML";
	}
	
	public static class LAYOUT_API_CODE{
		public static final String SUCCESS = "2000";
		public static final String DATA_EMPTY = "2001";
		public static final String FAIL = "2002";
		public static final String CMD_NOT_FOUND = "2003";
		public static final String FORMAT_NOT_FOUND="2004";
		public static final String ID_NOT_FOUND="2005";
		public static final String ID_NOT_EXSIT="2006";
	}
	
}

