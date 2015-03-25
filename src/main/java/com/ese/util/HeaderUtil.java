package com.ese.util;

public class HeaderUtil {

	/*헤더 정보*/
	/** 헤더 타입 코드. */
	public static final int HEDR_TYP_CD 	= 0;
	/** 송신 시스템 코드. */
	public static final int SND_SYS_CD 		= 1;
	/** 메시지 교환 패턴. */
	public static final int MSG_EXCH_PATRN 	= 2;
	/** 데이터부 길이. */
	public static final int BODY_LEN 		= 3;
	/** 메시지 타입 코드. */
	public static final int MSG_TYP_CD 		= 4;
	/** 트레이스 아이디. */
	public static final int TRCE_ID 		= 5;
	/** 전송 시간. */
	public static final int SND_DTM 		= 6;
	
	
	
	/** The instance. */
	//private static HeaderUtil instance = null;
	
	/** The header item len ar. */
	private static int[] headerItemLenAr = new int[]{ 2, 9, 1, 4, 3, 24, 17 };
	
	/** The header item pos ar. */
	private static int[] headerItemStartPosAr = new int[]{ 0, 2, 11, 12, 16, 19, 43 };
	
	/** The header item end pos ar. */
	private static int[] headerItemEndPosAr = new int[]{ 2, 11, 12, 16, 19, 43, 60 };
	
	/**
	 * Instantiates a new header util.
	 */
	private HeaderUtil(){
		
	}
	
	/**
	 * Gets the header item length.
	 *
	 * @param fieldId the field id
	 * @return the header item length
	 */
	public static int getHeaderItemLength(int fieldId){
		return headerItemLenAr[fieldId];
	}
	
	/**
	 * Gets the header item pos.
	 *
	 * @param fieldId the field id
	 * @return the header item pos
	 */
	public static int getHeaderItemStartPos(int fieldId){
		return headerItemStartPosAr[fieldId];
	}
	
	/**
	 * Gets the header item end pos.
	 *
	 * @param fieldId the field id
	 * @return the header item end pos
	 */
	public static int getHeaderItemEndPos(int fieldId){
		return headerItemEndPosAr[fieldId];
	}
	
	/**
	 * Gets the header item total Len.
	 *
	 * @return header item total Len
	 */
	public static int getHeaderTotalLen(){
		int totalLen = 0;
		for(int i : headerItemLenAr){
			totalLen += i;
		}
		return totalLen;
	}
	
	
}
