package com.ese.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;
import java.util.Vector;


/**
 * Class Name 	: StringUtil.java
 * Description 	: 문자열 처리를 담당하는 유틸
 * Modification Information
 *
 *       수정일			수정자			수정내용
 *    -----------      -----------     	---------------------
 *     2010.10.27			이진욱		nullChk() exception 제거
 *	   2013.10.02			이진형		HexStrToByteArray추가	
 * 
 * @author 차근수
 * @since 2010.09.08
 * @version 1.0
 */
public class StringUtil{
	
	/**
	 * clob 을 스트링으로 변경 
	 * @param clob
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public static String clobToString(Clob clob) throws SQLException,
	   IOException {

	  if (clob == null) {
	   return "";
	  }

	  StringBuffer strOut = new StringBuffer();

	  String str = "";

	  BufferedReader br = new BufferedReader(clob.getCharacterStream());

	  while ((str = br.readLine()) != null) {
	   strOut.append(str);
	  }
	  return strOut.toString();
	 }


	/**
	 * null 이면 ""공백으로
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String nullChk(String str)  {
		
		if(str == null){
			return "";
		}else if(str.equals("null")){
			return "";
		}else if(str.equals("NULL")){
			return "";
		}else{ 
			return str;
		}
	}
	
	/**
	 * String 문자열을 boolean으로 변환
	 * @param 	paramStr : 변경할 문자열
	 * @return 	boolean
	 */
	public static boolean strToBoolean(String paramStr){
		boolean result = false;
		if(paramStr.equals("Y")||paramStr.equals("y"))
			result = true;
		return result;
	}
	
	/**
	 * String 문자열을 날짜 형식으로 변경
	 * @param 	paramStr  : 변경할 문자열
	 * @param 	len		  : 8 - 년월일
	 * 					    12 - 년월일시분
	 * 					    14 - 년월일시분초
	 * 					    17 - 년월일시분초밀리세컨드
	 * @return	String
	 */
	public static String strToDateFormat(String paramStr, int len){
		
		if(paramStr == null || paramStr.equals("") || paramStr.equals(" ")){
			return "";
			
		}else{
			
			if(len == 8){
				return paramStr.substring(0, 4) + "/" + paramStr.substring(4, 6) + "/" + paramStr.substring(6, 8) ;
				
			}else if(len == 12){
				return paramStr.substring(0, 4) + "/" + paramStr.substring(4, 6) + "/" + paramStr.substring(6, 8) + " " + 
					   paramStr.substring(8, 10) + ":" + paramStr.substring(10, 12);
			
			}else if(len == 14){
				return paramStr.substring(0, 4) + "/" + paramStr.substring(4, 6) + "/" + paramStr.substring(6, 8) + " " + 
				       paramStr.substring(8, 10) + ":" + paramStr.substring(10, 12) + ":" + paramStr.substring(12, 14);
				
			}else if(len == 17){
				return paramStr.substring(0, 4) + "/" + paramStr.substring(4, 6) + "/" + paramStr.substring(6, 8) + " " + 
			       	   paramStr.substring(8, 10) + ":" + paramStr.substring(10, 12) + ":" + paramStr.substring(12, 14) + "." +
					   paramStr.substring(14, 17);
				
			}else{
				return paramStr;
			}
		}
	}
	
	/**
	 * String 문자열을 밀리초 형식으로 변경(밀리초는 3자리)
	 * @param 	paramStr : 변경할 문자열
	 * 		  	msLen	 : 밀리초 길이
	 * @return 	String
	 */
	public static String strToTimeFormat(String paramStr, int msLen){
		if(paramStr == null || paramStr.equals("") || paramStr.equals(" ")){
			return "";
			
		}else{
			String zero = "0";
			String spot = ".";

			if( msLen >= paramStr.length() ){
				return zero + spot + setPad(paramStr, msLen - paramStr.length(), zero, "L");
			}else{
				return paramStr.substring(0, paramStr.length() - msLen) + spot + paramStr.substring(paramStr.length() - msLen, paramStr.length());
			}
		}
	}
	
	/**
	 * LPAD, RPAD 처리
	 * @param 	str 	: 입력문자열
	 * @param 	padLen	: 총자리수
	 * @param 	padChar	: 채워야할 문자
	 * @param 	padFlag	: 'L' LPAD, 'R' RPAD 구분
	 * @return 	String
	 */
	public static String setPad(String str, int padLen, String padChar, String padFlag){
		if (str == null) str = "";
		
		// LPAD
		if( padFlag.equals("L") ){
			while (str.length() < padLen)
				str = padChar + str;
		// RPAD
		}else if( padFlag.equals("R") ){
			while (str.length() < padLen)
				str =  str + padChar;
		}
		
		return str;
	}
	
	public static String setPad(String str, int padLen, char padChar, String padFlag){
		return setPad(str, padLen, String.valueOf(padChar), padFlag);
	}
	
	
	/**
	 * 패딩 제거
	 * @param 	str 	: 전체 문자열
	 * @param 	padLen	: 총자리수
	 * @param 	padChar	: 제거할 문자
	 * @param 	padFlag	: 'L' LPAD, 'R' RPAD 구분 (default = L)
	 * @return 	String
	 */
	public static String removePad(String str, int padLen, String padChar, String padFlag){
		return removePad(str,  padLen , padChar.equals("")? ' ': padChar.charAt(0) ,  padFlag.equals("")? 'L': padFlag.charAt(0));
	}
	public static String removePad(String str, int padLen, char padChar, String padFlag){
		return removePad(str,  padLen , padChar ,  padFlag.equals("")? 'L': padFlag.charAt(0));
	}
	
	/**
	 * 패딩 제거
	 * @param str	: 전체 문자열
	 * @param padChar	: 패딩 문자열
	 * @param padFlag	: 패딩 방향 (default = L)
	 * @return
	 */
	public static String removePad(String str, String padChar, String padFlag){
		return removePad(str, str.length(), padChar, padFlag);
	}

	/**
	 * 패딩 제거
	 * @param 	str 	: 전체 문자열
	 * @param 	padLen	: 총자리수
	 * @param 	padChar	: 제거할 문자
	 * @param 	padFlag	: 'L' LPAD, 'R' RPAD 구분 
	 * @return
	 */
	public static String removePad(String str, int padLen, char padChar, char padFlag){
		if (str == null) str = "";
		String result = "";
		boolean flag = false;
		if( padFlag == 'L' ){
			char[] arr = str.toCharArray();
			for (int i =0 ; i < padLen ; i++) {
				if (arr[i] != padChar)
					flag = true;
				if(flag)
					result = result+arr[i];
			}
		}else if( padFlag == 'R' ){
			char[] arr = str.toCharArray();
			for (int i = padLen-1  ; i >= 0 ; i--) {
				if (arr[i] != padChar)
					flag = true;
				if(flag)
					result = arr[i]+result;
			}
		}
		return result;
	}
	
	public static String setPad(int str, int padLen, String padChar, String padFlag){
		
		return setPad(Integer.toString(str), padLen, padChar,  padFlag);
	}
	
	/**
	 * LPAD, RPAD 처리
	 * @param 	str 	: 입력문자열
	 * @param 	padLen	: 총자리수
	 * @param 	padChar	: 채워야할 문자
	 * @param 	padFlag	: 'L' LPAD, 'R' RPAD 구분
	 * @return 	String
	 */
	public static String setPadByte(String str, int padLen, String padChar, String padFlag){
		if (str == null) str = "";
		
		// LPAD
		if( padFlag.equals("L") ){
			while (str.getBytes().length < padLen)
				str = padChar + str;
		// RPAD
		}else if( padFlag.equals("R") ){
			while (str.getBytes().length < padLen)
				str =  str + padChar;
		}
		
		return str;
	}
	public static String toHan(String str) throws UnsupportedEncodingException {

	    if (str==null) 
	      return null;

	    else
	      return new String(str.getBytes("ISO-8859-1"),"utf-8");
	}
	
	/**
	 * 주어진 문자열이 문자로만 구성되어 있는지 확인한다. 공백은 false를 반환한다.
     * 입력된 String을 대문자로 모두 변환한다. (알파벳은 65(A)에서 90(Z)까지이다.)
	 * @param	String	: 문자열
	 * @return 	boolean	: 문자열이 모두 문자로만 이루어진경우 true
	 */
	public static boolean isAlpha(String str) {
		if (str==null || str.equals("")) return false;

		for (int i=0; i<str.length(); i++) {
			if ((int)str.charAt(i) < 65 && ((int)str.charAt(i) != 32)) return false;
		}
		return true;
	}
	
	/**
	 * 주어진 문자열이 숫자로만 구성되어 있는지 확인한다. 공백이 있으면 false를 반환한다.
	 * char값이 48에서 57사이이면 숫자이다.
	 * @param	String	: 문자열
	 * @return 	boolean	: 문자열이 모두 숫자로만 이루어진경우 true
	 */
	public static boolean isNumeric(String str) {
		if (str==null || str.equals("")) return false;

		for (int i=0; i<str.length(); i++) {
			if ((int)str.charAt(i) < 48 || (int)str.charAt(i) > 57) return false;
		}
		return true;
	}
	
	/**
	 * 주어진 문자열에서 해당하는 위치의 문자를 변경한다.
	 * @param src			: 입력값
	 * @param replaceStr	: 변경문자열
	 * @param startPoint	: 변경하고자 하는 문자 시작 위치
	 * @param endPoint		: 변경하고자 하는 문자 끝 위치
	 * @return
	 */
	public static String replaceStr(String src, String replaceStr, int startPoint, int endPoint){
		StringBuffer sb = new StringBuffer();
		
		int padLen = endPoint-startPoint;
		
		sb.append(src.substring(0,startPoint));
		sb.append(StringUtil.setPadByte(replaceStr, padLen, " ", "L"));
		sb.append(src.substring(endPoint));
		
		return sb.toString();
	}
	
	/**
	 * String Escape 처리
	 * @param src
	 * @return
	 */
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}
	
	/**
	 * escape 된 문자열 unescape
	 * @param str
	 * @return
	 */
	public static String unescape(String str) {
	    String rtnStr = new String();
	    char [] arrInp = str.toCharArray();
	    int i;
	    
	    for(i=0; i<arrInp.length; i++) {
	    	
	        if(arrInp[i] == '%') {
	        
	        	String hex;
	            if(arrInp[i+1] == 'u') {    //유니코드.
	                hex = str.substring(i+2, i+6);
	                i += 5;
	                
	            } else {    //ascii
	                hex = str.substring(i+1, i+3);
	                i += 2;
	            }
	            
	            try{
	                rtnStr += new String(Character.toChars(Integer.parseInt(hex, 16)));
	                
	            } catch(NumberFormatException e) {
	                rtnStr += "%";
	                i -= (hex.length()>2 ? 5 : 2);
	            }
	        } else {
	            rtnStr += arrInp[i];
	        }
	    }
	 
	    return rtnStr;
	}
	

	public static boolean isNotEmpty (String msg) {
		return msg != null && msg.trim().length() > 0;
	}
	public static boolean isEmpty (String msg) {
		return msg == null || msg.trim().length() == 0;
	}
	

	/**
	 * 정수형 값을 특정 자리수(depth) 만큼 앞에 "0" 이 채워진 문자열을 만든다.
	 *
	 * @param value 값
	 * @param depth 자리수
	 * @return 적용된 문자열
	 */
	public static String prependZeroString (int value, int depth) {
		String res = null;

		res = value + "";

		int loopCnt = depth - res.length();

		for (int inx = 0; inx < loopCnt; inx ++) {
			res = "0" + res;
		}

		return res;
	}
	
	/**
	 * 주어진 캘린더 정보에 대해 패턴을 적용한 날짜 문자열을 반환한다.
	 * 
	 * @param pattern 패턴  'YYYY' 년도, 'MM' 월, 'DD' 날짜, 'hh' 시, 'mm' 분, 'ss'초, 'mis' 밀리초
	 *                 예) "YYYY/MM/DD hh:mm:ss.mis"
	 * @param pCalendar 특정 순간을 나타내는 캘린더 정보
	 * @return
	 */
	public static String getDateString (String pattern, Calendar pCalendar) {
		String res = null;

		String strYYYY 	= prependZeroString(pCalendar.get(java.util.Calendar.YEAR), 4);
		String strMM 	= prependZeroString(pCalendar.get(java.util.Calendar.MONTH) + 1, 2);
		String strDD 	= prependZeroString(pCalendar.get(java.util.Calendar.DAY_OF_MONTH), 2);
		String strHour 	= prependZeroString(pCalendar.get(java.util.Calendar.HOUR_OF_DAY), 2);
		String strMin 	= prependZeroString(pCalendar.get(java.util.Calendar.MINUTE), 2);
		String strSec 	= prependZeroString(pCalendar.get(java.util.Calendar.SECOND), 2);
		String strMis 	= prependZeroString(pCalendar.get(java.util.Calendar.MILLISECOND), 3);

		if (pattern == null) {
			pattern = "YYYYMMDD";
		}

		res = pattern.replaceAll("YYYY", strYYYY);
		res = res.replaceAll("MM", strMM);
		res = res.replaceAll("DD", strDD);
		res = res.replaceAll("hh", strHour);
		res = res.replaceAll("mm", strMin);
		res = res.replaceAll("ss", strSec);
		res = res.replaceAll("mis", strMis);

		return res;
	}

	/**
	 * 현재 날짜를 나타내는 문자열을 반환한다. (패턴 적용됨)
	 * 
	 * @param pattern 패턴  'YYYY' 년도, 'MM' 월, 'DD' 날짜, 'hh' 시, 'mm' 분, 'ss'초, 'mis' 밀리초
	 *                 예) "YYYY/MM/DD hh:mm:ss.mis"
	 *                 
	 * @return pattern 이 null 인 경우 'YYYYMMDD' 형식의 날짜를 반환한다.
	 */
	public static String getCurrentDate (String pattern) {
		String 		res 		= null;
		Calendar 	lCurDate 	= Calendar.getInstance();

		res = getDateString (pattern, lCurDate);

		return res;
	}
	
	/**
	 * HEX 문자열을 byte배열로 변환
	 * @param str HEX 문자열
	 * @return Byte[]
	 */
	public static byte[] hexStrToByteArray(String hexStr){
		if (hexStr == null || hexStr.length() % 2 != 0) {
            return new byte[]{};
        }

        byte[] bytes = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length(); i += 2) {
            byte value = (byte)Integer.parseInt(hexStr.substring(i, i + 2), 16);
            bytes[(int) Math.floor(i / 2)] = value;
        }
        return bytes;
	}
	
	/**
	 * VO 객체 toString 으로 변환(디버그시 사용)
	 * @param obj 	: 변환하고자하는 vo 객체
	 * @param first	: 
	 * @return
	 */
	public static String toString(Object obj , boolean first) {
		@SuppressWarnings("rawtypes")
		Class cls = obj.getClass();	
		
		String mthNm = "";

		StringBuffer sb = new StringBuffer();
		
		try{
			Method[] mthArr = cls.getMethods();
			Method mth = null;
			
			if(!first)
				sb.append(" { ");	
			
			sb.append(cls.getName()+ " && ");
			
			for(int j = 0; j < mthArr.length ; j++){
				mthNm = mthArr[j].getName();
				
				if(mthNm.toUpperCase().startsWith("GET") && !mthNm.toUpperCase().startsWith("GETCLASS")) {
					sb.append(mthNm.substring(3) +" == ");
					Object rtnObj = mthArr[j].invoke(obj, new Object[]{});
					
					if(rtnObj != null) {
						if(rtnObj instanceof String[]) {
							String[] instanceX = (String[]) rtnObj;
							
							for(int x=0 ; x < instanceX.length ; x++){
								sb.append(instanceX[x]);
								
								if(x < instanceX.length -1)
									sb.append(" # ");
							}
							
							sb.append(" | ");
							
						}else if(rtnObj instanceof Integer){
							sb.append(rtnObj.toString() + " | ");
							
						}else if(rtnObj instanceof String){
							sb.append(rtnObj.toString() + " | ");
							
						}else if(rtnObj instanceof Long){
							sb.append(rtnObj.toString() + " | ");
							
						}else if(rtnObj instanceof Double){
							sb.append(rtnObj.toString() + " | ");
							
						}else{
							sb.append(CommUtil.toString(rtnObj,false)+" | ");
						}	
					}
				}
			}
			
			if(!first)
				sb.append(" } ");	
			
		}catch(Exception e){
			e.printStackTrace();	
		}
		
		return sb.toString();
	}
	
	/**
	 * Map 객체 toString 으로 변환(디버그시 사용)
	 * @param map 	: 변환하고자하는 map 객체
	 * @return
	 */
	public static String toString(Map<String, Object> map){
		StringBuffer sb = new StringBuffer();
		Set<String> keySet = map.keySet();
		sb.append("{");
		for(String key : keySet){
			sb.append(key).append("=").append(map.get(key)).append(",");
		}
		sb.append("}");
		return sb.toString();
	}
	/**
	 * 문자열 내의 UniCode Null(u0000)을 공백(Empty)로 전환
	 * @param str
	 * @return
	 */
	public static String uniCodeNullToEmpty(String str){
		StringBuffer sb = new StringBuffer();
		
		for (int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
			
			if(ch == 0x0000){
				sb.append("");
			}else {
				sb.append(ch);
			}
		}
		
		return sb.toString();
	}
	
    /**
     * 특수문자를 XML에 맞도록 변환해주는 메소드
     * @param strString
     * @return String
     */
    public static String strToXML(String strString) {
        Vector<String> aResult = new Vector<String>();
        String strResult = "";

        try {
            if (strString.indexOf("&") != -1) {
                aResult = strGetSplit(strString, "&");
                strString = "";
                for (String str: aResult) {
                    strString += str + "&amp;";
                }
                aResult = null;
                strString = strString.substring(0, strString.length() - 5);
            }

            if (strString.indexOf("<") != -1) {
                aResult = strGetSplit(strString, "<");
                strString = "";
                for (String str: aResult) {
                    strString += str + "&lt;";
                }
                aResult = null;
                strString = strString.substring(0, strString.length() - 4);
            }

            if (strString.indexOf(">") != -1) {
                aResult = strGetSplit(strString, ">");
                strString = "";
                for (String str: aResult) {
                    strString += str + "&gt;";
                }
                aResult = null;
                strString = strString.substring(0, strString.length() - 4);
            }

            if (strString.indexOf("'") != -1) {
                aResult = strGetSplit(strString, "'");
                strString = "";
                for (String str: aResult) {
                    strString += str + "&apos;";
                }
                aResult = null;
                strString = strString.substring(0, strString.length() - 6);
            }

            if (strString.indexOf("\"") != -1) {
                aResult = strGetSplit(strString, "\"");
                strString = "";
                for (String str: aResult) {
                    strString += str + "&quot;";
                }
                aResult = null;
                strString = strString.substring(0, strString.length() - 6);
            }
            strResult = strString;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
        return strResult;
    }
    
    /**
     * 스트링을 특정 문자를 기준으로 나누어 준다.
     * @param      strString String         : input string
     * @param      strDelimeter String      : delimeter character
     * @return     Vector      vResult      : result string
     */
    public static Vector<String> strGetSplit(String strString, String strDelimeter) {
        Vector<String> vResult = new Vector<String>();
        int nCount = 0, nLastIndex = 0;

        try {
            nLastIndex = strString.indexOf(strDelimeter);
            if (nLastIndex == -1) {
                vResult.add(0, strString);
            } else {
                while ((strString.indexOf(strDelimeter) > -1)) {
                    nLastIndex = strString.indexOf(strDelimeter);
                    vResult.add(nCount, strString.substring(0, nLastIndex));
                    strString = strString.substring(
                            nLastIndex + strDelimeter.length(),
                            strString.length());
                    nCount++;
                }
                vResult.add(nCount, strString);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
        return vResult;
    }
}	// end class
