package com.ese.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;

import com.google.common.base.Strings;




/**
 * Class Name 	: CommUtil.java
 * Description 	: ID 생성 및 공통으로 사용하는 유틸
 * Modification Information
 *
 *       수정일			수정자			수정내용
 *    -----------      -----------     	---------------------
 *    2014.06.10		윤성진			문자 -> MD5 변환 method 추가 (strToMD5)
 * 
 * @author 
 * @since 2014.06
 * @version 1.0
 */
public class CommUtil {
	private static int SEQ = 0;
	private static int SEQ4 = 0;
	

	private static AtomicLong atomicLong = new AtomicLong(0);
	private static AtomicLong atomicSeq = new AtomicLong(0);
	
	/**
	 * UniqueID 생성(20자리)
	 * @param prefix : prefix
	 * @return prefix_현재시간17자리
	 */
	public static String getUniqueID(String prefix){
		String rtnStr = "";
		rtnStr = prefix + "_" + getCurrentTime14()+getNextSequenceValue();
		
		return rtnStr;
	}
	/**
     * UniqueID 생성(20자리/24자리)
     * @param prefix : prefix, length: 아이디 길이
     * @return prefix_현재시간14자리SEQ3자리/prefix_현재시간17자리SEQ3자리
     */
    public static String getUniqueID(String prefix, int length){
        String rtnStr = "";
        if(length == 20)
            rtnStr = prefix + "_" + getCurrentTime14()+getNextSequenceValue();
        else if(length == 24)
            rtnStr = prefix + "_" + getCurrentTime17()+getNextSequenceValue();
        
        return rtnStr;
    }
    
    /**
	 * UUID 20자리 생성
	 * @param args
	 */
	public static String getUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-","").substring(0,20);
        
        return uuid.substring(0, 20);
        
    }
    
	/**
	 * UUID 20자리 생성
	 * @param args
	 */
	public static String getUUID_20() {
        String uuid = UUID.randomUUID().toString().replaceAll("-","").substring(0,20);
        
        return uuid.substring(0, 20);
        
    }
	
	/**
	 * UniqueID 생성(20자리)
	 * @return	String(현재시간17자리 + 랜덤3자리)
	 */
	public static String getUniqueID(){
		String rtnStr = "";
		rtnStr =getCurrentTime17()+getNextSequenceValue();
		return rtnStr;
	}
	
	/**
	 * Random 숫자 구하기
	 * @param 	length : 자리수
	 * @return	String
	 */
	public static String getRandomValue(int length){
		String rtnStr = "";
		Random rand = new Random(System.currentTimeMillis());

		int ranVal = Math.abs(rand.nextInt());
		int ranVal2 = Math.abs(rand.nextInt());
		int ranVal3 = ranVal + ranVal2;
		String ranStr = Integer.toString(ranVal3);
		rtnStr = ranStr.substring(ranStr.length()- length);
		
		return rtnStr;
	}
	
	/**
	 * 현재시간 구하기 14자리(년월일시분초)
	 * @return String
	 */
	public static String getCurrentTime14() {
		GregorianCalendar calendar = new GregorianCalendar();
		StringBuffer rtnStr = new StringBuffer();
		
		rtnStr.append(checkByte(Integer.toString(calendar.get(Calendar.YEAR)), 4, "0"));
		rtnStr.append(checkByte(Integer.toString(calendar.get(Calendar.MONTH) + 1), 2, "0"));
		rtnStr.append(checkByte(Integer.toString(calendar.get(Calendar.DATE)), 2, "0"));
		rtnStr.append(checkByte(Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)), 2, "0"));
		rtnStr.append(checkByte(Integer.toString(calendar.get(Calendar.MINUTE)), 2, "0"));
		rtnStr.append(checkByte(Integer.toString(calendar.get(Calendar.SECOND)), 2, "0"));
		
		return rtnStr.toString();
	}
	
	/**
	 * 현재시간 구하기 17자리(년월일시분초SSS)
	 * @return String
	 */
	public static String getCurrentTime17() {
		GregorianCalendar calendar = new GregorianCalendar();
		StringBuffer rtnStr = new StringBuffer();
		
		rtnStr.append(checkByte(Integer.toString(calendar.get(Calendar.YEAR)), 4, "0"));
		rtnStr.append(checkByte(Integer.toString(calendar.get(Calendar.MONTH) + 1), 2, "0"));
		rtnStr.append(checkByte(Integer.toString(calendar.get(Calendar.DATE)), 2, "0"));
		rtnStr.append(checkByte(Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)), 2, "0"));
		rtnStr.append(checkByte(Integer.toString(calendar.get(Calendar.MINUTE)), 2, "0"));
		rtnStr.append(checkByte(Integer.toString(calendar.get(Calendar.SECOND)), 2, "0"));
		rtnStr.append(checkByte(Integer.toString(calendar.get(Calendar.MILLISECOND)), 3, "0"));
		
		return rtnStr.toString();
	}

	/**
	 * 자리수 채우기
	 * @param 	source : 문자열
	 * @param 	digit  : 자리수
	 * @param 	fillString : 채울문자
	 * @return	String
	 */
	public static String checkByte(String source, int digit, String fillString) {
		
		String rtnStr = "";

		if (source.length() < digit) 
			for (int i = 0; i < digit - source.length(); i++) 
				rtnStr += fillString;
			
		
		rtnStr += source;
		
		return rtnStr;

	}
	
	public static String removeInvalidXMLCharacters(String s) {

        StringBuilder out = new StringBuilder();                // Used to hold the output.

    	int codePoint;                                          // Used to reference the current character.


		int i=0;

    	while(i<s.length()) {

    		codePoint = s.codePointAt(i);                       // This is the unicode code of the character.

			if ((codePoint == 0x9) ||          				    // Consider testing larger ranges first to improve speed. 

					(codePoint == 0xA) ||

					(codePoint == 0xD) ||
					
					(codePoint == 0xdc21) ||

					((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||

					((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||

					((codePoint >= 0x10000) && (codePoint <= 0x10FFFF))) {

				out.append(Character.toChars(codePoint));

			}				

			i+= Character.charCount(codePoint);                 // Increment with the number of code units(java chars) needed to represent a Unicode char.  

    	}

    	return out.toString();

    } 
	
	/**
	 * 시퀀스값 생성 3자리
	 * @return String
	 */
	public static String getNextSequenceValue(){
		String rtnStr = Integer.toString(SEQ);
		if(SEQ == 999)
			SEQ = 0;
		
		if(rtnStr.length() == 1) 
			rtnStr = "00" + rtnStr;
		else if (rtnStr.length() == 2 )
			rtnStr = "0" + rtnStr;
		SEQ++;
		return rtnStr;
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
	 * 오브젝트 전이 ( 제공 객체에 null 이 아닌 변수만 전이 )
	 * @param offerObj  - 제공 객체
	 * @param targetObj - 대상 객체
	 */
	public static boolean objTransfer(Object offerObj , Object targetObj){
		/*같은 객체인지 validation*/
		if(offerObj.getClass().equals(targetObj.getClass())){
			try {
			Field[] fields = targetObj.getClass().getDeclaredFields();
				for(Field field : fields){
					//static 변수가 아니면
					if(!Modifier.isStatic(field.getModifiers()))
					{
						//final이 아니면
						if(!Modifier.isFinal(field.getModifiers()))
						{
							field.setAccessible(true);
							//offerObj의 변수값이 null이 아니면
							if(field.get(offerObj)!=null){
								field.set(targetObj, field.get(offerObj));
							}
						}
					}
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{ 
			return false;
		}
		return true;
	}
	
	
	/**
	* 시퀀스값 생성 4자리
	* @return String
	*/
	public synchronized static String getNextSequenceValue4(){
		/*
		String rtnStr = Integer.toString(SEQ4);
		if(SEQ4 == 9999)
			SEQ4 = 0;
	
		if(rtnStr.length() == 1)
			rtnStr = "000" + rtnStr;
		else if (rtnStr.length() == 2 )
			rtnStr = "00" + rtnStr;
		else if (rtnStr.length() == 3 )
			rtnStr = "0" + rtnStr;
		
		SEQ4++;
		*/
		long seq = atomicLong.getAndIncrement();
		if(seq == 9999){
			atomicLong.set(0);
		}
		String rtnStr = Long.toString(seq);
		if(rtnStr.length() == 1) 
			rtnStr = "000" + rtnStr;
		else if (rtnStr.length() == 2 )
			rtnStr = "00" + rtnStr;
		else if (rtnStr.length() == 3 )
			rtnStr = "0" + rtnStr;
		
		return rtnStr;
	}
	
	
    final static char[] digits = {
    	'0' , '1' , '2' , '3' , '4' , '5' ,
    	'6' , '7' , '8' , '9' , 'a' , 'b' ,
    	'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
    	'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
    	'o' , 'p' , 'q' , 'r' , 's' , 't' ,
    	'u' , 'v' , 'w' , 'x' , 'y' , 'z'
    };

    /**
	 * 현재 시각과 노드명에 기반한 pseudo random 생성된 32자리의 ID를 반환한다.
	 * 
	 * @return Global Unique ID
	 */
	public static String getUUID32() {
		UUID uuid = UUID.randomUUID();
		char[] buffer = new char[32];
		long msbs = uuid.getMostSignificantBits();
		long lsbs = uuid.getLeastSignificantBits();
		long mask = 0x0f;
		int shift;
		for (int i = 0; i < 16; i++) {
			shift = (15 - i) * 4;
			buffer[i] = digits[(int)(((msbs & (mask << shift)) >>> shift))];  
		}
		for (int i = 0; i < 16; i++) {
			shift = (15 - i) * 4;
			buffer[i + 16] = digits[(int)(((lsbs & (mask << shift)) >>> shift))];  
		}

		return new String(buffer);
	}
	
	public static String convertSqlNamespace(String namespace, String dbType){
		StringBuffer sb = new StringBuffer();
		sb.append(namespace);
		sb.append("_");
		sb.append(dbType.toLowerCase());
		return sb.toString();
	}
	
	public static String getAppContextMsgByLang(ApplicationContext applicationContext, String key, String languageCode){
		MessageSource messageSource = (MessageSource) applicationContext;
		return messageSource.getMessage(key, new String[]{"Has No Properties"}, new Locale(languageCode));		
	}
	
	public static String getThreadSafeUniqeId_20(){
		String uniqSeq = "";
		long seq = atomicSeq.getAndIncrement();
		//uniqSeq = StringUtil.setPad(Long.toString(seq), 20, "0", "L");
		uniqSeq = Strings.padStart(Long.toString(seq), 20, '0');
		return uniqSeq; 
	}
	
	public static Map<String, String> voToMap(Object obj){
		Map<String, String> map = new HashMap<String, String>();

		for (Field field: obj.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				map.put(field.getName(), String.valueOf(field.get(obj)));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	/**
	* 문자를 MD5로 변환
	* @return String
	*/
	public static String strToMD5(String str){
		MessageDigest mdEnc = null;
		
		try{
			mdEnc = MessageDigest.getInstance("MD5");
		}catch(Exception e){
			e.printStackTrace();
		}
		String eip;
		byte[] bip;
		String temp="";
		
		bip = mdEnc.digest(str.getBytes());
		
		for(int i = 0 ; i < bip.length ; i++){
			eip = ""+Integer.toHexString((int) bip[i] & 0x000000ff);
			if(eip.length() < 2)
				eip = "0"+eip;
			temp += eip;
		}
		return temp;
	}
	
	/**
	 * src에서 target에 대항하는 비트가 있는지 판단
	 */
	public static boolean intBitEqual(int src, int target){
		return (src & target) == target;
	}
	
	/**
	 * 0 < long < 100000000 반환
	 * @return
	 */
	public static long getUniqueIDLong(){
		return (long)(Math.random()*100000000);
	}
	
	/**
	 * msg 의 개행 문자 제거 ( window = \r\n, unix = \n)
	 * @param msg
	 * @return
	 */
	public static String tabRemove(String msg){
		return msg.replaceAll("\r\n|\n|\r", "");   
	}
	
	public static String toFileSizeString(Long size){
		String rtn = "";
//		if(size == null)
//			return "0 B";
		if(size < 1024){
			rtn = String.format("%d", size) + " B";
		}else{
			double newSize = (double)size / 1024;
			if(newSize < 1024){
				rtn = String.format("%.2f", newSize) + " KB";
			}else{
				newSize = newSize / 1024;
				if(newSize < 1024){
					rtn = String.format("%.2f", newSize) + " MB";
				}else{
					newSize = newSize / 1024;
					if(newSize < 1024){
						rtn = String.format("%.2f", newSize) + " GB";
					}else{
						newSize = newSize / 1024;if(newSize < 1024){
							rtn = String.format("%.2f", newSize) + " TB";
						}else{
							newSize = newSize / 1024;
						}
					}
				}
			}
		}
		return rtn;
	}
	
	/**
	 * int형을 byte[]로 변경
	 * @param integer
	 * @param order
	 * @return
	 */
	public static byte[] intTobyte(int integer, ByteOrder order) {
 
		ByteBuffer buff = ByteBuffer.allocate(Integer.SIZE/8);
		buff.order(order);
		// 인수로 넘어온 integer을 putInt로설정
		buff.putInt(integer);
		return buff.array();
	}
 
	/**
	 * byte[] 을 int형로 변경
	 * @param bytes
	 * @param order
	 * @return
	 */
	public static int byteToInt(byte[] bytes, ByteOrder order) {
		ByteBuffer buff = ByteBuffer.allocate(Integer.SIZE/8);
		buff.order(order);
		// buff사이즈는 4인 상태
		// bytes를 put하면 position과 limit는 같은 위치
		buff.put(bytes);
		// flip()가 실행 되면 position은 0에 위치
		buff.flip();
		return buff.getInt(); // position위치(0)에서 부터 4바이트를 int로 변경하여 반환
	}
	
	/**
	 * json 포멧터 적용 메소드
	 * @param json
	 * @return
	 */
	public static String toFormatedJson(String json){
		StringBuffer sb = new StringBuffer();
		
		int indent = 0;
		int pair = 0;
		
		for(int i=0; i<json.length(); i++){
			char c = json.charAt(i);
			if(c == '{'){
				sb.append(c);
				sb.append("\n");
				indent++;
				sb.append(getTab(indent));
			}else if(c == '['){
				if(pair>0){
					sb.append(c);
				}else{
					sb.append(c);
					sb.append("\n");
					indent++;
					sb.append(getTab(indent));
				}
			}else if( c=='}'){
				indent--;
				sb.append("\n");
				sb.append(getTab(indent));
				sb.append(c);
			}else if(c == ']'){
				if(pair>0){
					sb.append(c);
				}else{
					indent--;
					sb.append("\n");
					sb.append(getTab(indent));
					sb.append(c);
					indent--;
				}
			}else if(c == ':'){
				char t = json.charAt(i+1);
				if(t=='['||t=='{'){
					sb.append(c);
					sb.append("\n");
					indent++;
					sb.append(getTab(indent));
				}else{
					sb.append(c);
				}
			}else if( c==','){
				sb.append(c);
				sb.append("\n");
				sb.append(getTab(indent));
			}else if( c=='\"'){
				if(pair==0){
					pair++;
				}else {
					pair--;
				}
				sb.append(c);
			}else{
				sb.append(c);
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 탭적용 메소드
	 * @param indent
	 * @return
	 */
	public static String getTab(int indent){
		StringBuffer sb = new StringBuffer();
		
		for(int i=0; i<indent; i++){
			sb.append("    ");
		}
		
		return sb.toString();
	}
	
	   /**
     * 
     * request에 담긴 정보를 HashMap 형태로 변환
     * @version 1.0
     * @since 2012. 1. 3.
     * @param request	request 객체
     * @return HashMap	데이터 정보
     */
    @SuppressWarnings("rawtypes")
    public static HashMap<String, Object> getSearchListEscape(HttpServletRequest request){
    	
    	HashMap<String, Object> searchList = new HashMap<String, Object>();
    	Enumeration paramNames = request.getParameterNames();           
    	
    	while(paramNames.hasMoreElements()) {
    		
    		String paramName = (String) paramNames.nextElement();
    		
    		if(paramName != null && !paramName.equals("page") && !paramName.equals("rows")
    				&& !paramName.equals("dataName") && !paramName.equals("dataKind")&& !paramName.equals("sidx") && !paramName.equals("sord")){
    			try {
					searchList.put(paramName, URLDecoder.decode(request.getParameter(paramName), "utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.toString();
				}
    		}
    	}
    	return searchList;
    }
	
}

