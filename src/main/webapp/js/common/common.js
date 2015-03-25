// Grid 조회 관련 공통 Js
var params = {};
   // params["method"]    = "gridXml";

/***********************************************************************
 * 내	용 	: 메인 팝업 창 오픈
 * 파라미터 	: 1. url - 파일 위치 정보
       	  	  2. ls_width  - 창 넓이 값
              3. ls_heigth - 창 높이 값
              4. flag - 자신창의 이름
              
 * 리 턴 값 	: 
 * 참고사항 	:
***********************************************************************/
function cf_openWindow(url, ls_width, ls_height , windowName, msg) {
	var li_top = (window.screen.height - ls_height) / 2;
 	var li_left = (window.screen.width - ls_width) / 2;

    var position = 'width=' + ls_width + ',height=' + ls_height + ',top=' + li_top + ',left=' + li_left;
    var winset = 'resizable=no,menubar=no,scrollbars=no,status=yes,titlebar=no,toolbar=no,' + position;
    
    var newwin = window.open('about:blank', windowName, winset);
    if(newwin == null){
    	alert(msg);
    }else{
    	newwin = window.open(url, windowName, winset);
    }
}

/************************************************************************
 * 내    용	: 팝업 창 오픈(modal)
 * 파라미터	: 1. url - 파일 위치 정보
          	  2. ls_width  - 창 넓이 값
          	  3. ls_heigth - 창 높이 값
 * 리 턴 값	: 없음
 * 참고사항	:
*************************************************************************/
function cf_modalWindow(url, ls_width, ls_height) {
	var position  = "";
	var winset    = "";

	position = 'dialogWidth:' + ls_width + 'px;' + 'dialogHeight:' + ls_height + 'px;';
	winset = 'resizable:no;center:yes;help:no;status:yes;scroll:no;toolbar=no;' + position;
	window.showModalDialog(url, window, winset);
}

/*************************************
 * 내	용 	: NULL 값 체크
 * 파라미터 	: 1. inputStr : 입력값
 * 리 턴 값 	: true, false 
 * 참고사항 	:
**************************************/
function cf_nullChk( inputStr ){
	if( inputStr == '' ) return true;
	else return false;
}

/*************************************
 * 내	용 	: 특수문자 체크
 * 파라미터 	: 1. inputStr : 입력값
 * 리 턴 값 	: true, false 
 * 참고사항 	:
**************************************/
function cf_specialChk(str) {
	var re = /[~!@\#$%<>^&*\()\-=+_\']/gi; //특수문자 정규식 변수 선언
	return re.test(str); 
}


/****************************************************************
 * 내	용 	: 그리드에 메시지 출력
 * 파라미터 	: 1. gridID - 그리드 아이디
 * 			  2. msg - 출력할 메시지
 * 리 턴 값 	: 
 * 참고사항 	: 그리드아이디emptyMsg의 아이디를 다른 태그에 부여금지
*****************************************************************/
function cf_showGridMsg(gridID, msg){
	jqGridID = "#" + gridID ;
	jQuery(jqGridID + "emptyMsg").remove();
	
	if(jQuery(jqGridID).getGridParam("records") == 0){
		jQuery(jqGridID).parent().append("<div id='" + gridID + "emptyMsg' style=\"font-size:'8pt';text-align:center;padding:'10px';height:'auto'\">" + msg + "<div>");
	}
}

/****************************************************************
 * 내	용 	: 입력글자수 체크
 * 파라미터 	: 1. obj : 입력폼객체
 * 			  2. byt : 입력을 제한할 바이트
 * 리 턴 값 	: 
 * 참고사항 	: 
*****************************************************************/
function cf_msgLenChk(obj, byt, msg){
	var str, msg;
 	var len = 0;
 	var temp;
 	var count = 0;
	//msg = obj.value;

 	str = new String(obj.value);
 	len = str.length;

 	for (k=0 ; k<len ; k++){
 		temp = str.charAt(k);
  	  
 		if (escape(temp).length > 4) {
 			count += 2;
     	}
 		else if (temp == '\r' && str.charAt(k+1) == '\n') { 	// \r\n일 경우
 			count += 2;
 		}
 		else if (temp != '\n') {
 			count++;
 		}
 	}

	if(count > byt) {
		
		alert( msg);
		obj.value = cf_cutText(obj, byt);
		obj.focus();
		return false;
	}

		return true; 
}

/****************************************************************
 * 내	용 	: 입력글자수 설정한 입력바이트만 가능하도록 체크
 * 파라미터 	: 1. obj : 입력폼객체
 * 			  2. byt : 입력을 제한할 바이트
 * 리 턴 값 	: 
 * 참고사항 	: 
*****************************************************************/
function cf_msgLenOnlyChk(obj, byt){
	var str, msg;
 	var len = 0;
 	var temp;
 	var count = 0;
	msg = obj.value;

 	str = new String(msg);
 	len = str.length;

 	for (k=0 ; k<len ; k++){
 		temp = str.charAt(k);
  	  
 		if (escape(temp).length > 4) {
 			count += 2;
     	}
 		else if (temp == '\r' && str.charAt(k+1) == '\n') { 	// \r\n일 경우
 			count += 2;
 		}
 		else if (temp != '\n') {
 			count++;
 		}
 	}

	if(count > byt || count < byt) {
		return false;
	}else {
		return true;
	}
}

/****************************************************************************
내    용	: 입력값이 특정 문자(chars)만으로 되어있는지 체크
                  특정 문자만 허용하려 할 때 사용
파라미터	: str :입력값
리 턴 값	:
참고사항	: 
***************************************************************************/
function cf_hasContainsCharsChk(str, chars) {
	if(cf_isNull(str)){
		return false;
	}else if(str.length==0){
		return false;
	}
	for (var inx = 0; inx < str.length; inx++) {
		if (chars.indexOf(str.charAt(inx)) == -1)
			return false;
	}
	return true;
}

/****************************************************************
 * 내	용 	: 입력값이 알파벳대문자 체크
 * 파라미터 	: 1. str : 입력문자열
 * 리 턴 값 	: 
 * 참고사항 	: 
*****************************************************************/
function cf_isAlphabetUpper(str) {
    var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
    return cf_hasContainsCharsChk(str, chars);
}

/****************************************************************
 * 내	용 	: 입력값이 숫자와 Dot(.) 체크
 * 파라미터 	: 1. str : 입력문자열
 * 리 턴 값 	: 
 * 참고사항 	: 
*****************************************************************/
function cf_isNumberDot(str) {
    var chars = "1234567890.";
    return cf_hasContainsCharsChk(str, chars);
}
/****************************************************************
 * 내	용 	: 입력값이 숫자와 영어로 되어있는지 체크
 * 파라미터 	: 1. str : 입력문자열
 * 리 턴 값 	: 
 * 참고사항 	: 
*****************************************************************/
function cf_isNumberAlphabet(str) {
    var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    return cf_hasContainsCharsChk(str, chars);
}
/****************************************************************
 * 내	용 	: 입력값이 숫자로 되어있는지 체크
 * 파라미터 	: 1. str : 입력문자열
 * 리 턴 값 	: 
 * 참고사항 	: 
*****************************************************************/
function cf_isNumber(str) {
    var chars = "0123456789";
    return cf_hasContainsCharsChk(str, chars);
}

/****************************************************************
 * 내	용 	: 입력값이 숫자 체크(음수포함)
 * 파라미터 	: 1. str : 입력문자열
 * 리 턴 값 	: 
 * 참고사항 	: 
*****************************************************************/
function cf_isNumberIncludeNegative(str) {
    var chars = "1234567890.";
    idx=0;
    if(str.charAt(0) == '-'){idx++}
	    for(idx; idx<str.length; idx++){
	        if (chars.indexOf(str.charAt(idx)) == -1)
	            return false;
	    }
    return true;
}

/****************************************************************
 * 내	용 	: 문자열 내에 있는 모든 공백제거
 * 파라미터 	: 1. str : 입력문자열
 * 리 턴 값 	: 
 * 참고사항 	: 
*****************************************************************/
function trim(str){
	//정규 표현식을 사용하여 화이트스페이스를 빈문자로 전환
	str = str.replace(/^s*/,'').replace(/s*$/, '');
	return str; //변환한 스트링을 리턴.
} 

/****************************************************************
 * 내	용 	: 문자열내 숫자빼고 모두 지우기
 * 파라미터 	: 1. str : 입력문자열
 * 리 턴 값 	: 
 * 참고사항 	: 
*****************************************************************/
function cf_remainNumOnly(str){
	cpyStr = "";
	var chars = "/:;-";
	for(idx=0; idx<str.length; idx++){
		if (chars.indexOf(str.charAt(idx)) == -1){
			cpyStr += str.charAt(idx);
		}
	}
	return cpyStr;
}

/**************************************************
 * 내    용	: SelectBox에 Option 생성
 * 파라미터	: obj	: selectbox 객체
		  	  param	: 비교값
 * 리 턴 값	: 
 * 참고사항	: 같은값이 있으면 false
***************************************************/
function cf_getOptionsCheck(obj, param){
	for(var i=0; i < obj.length; i++)
		if(obj.options[i].value == param)
			return false;
	
	return true;
}

/**************************************************
 * 내   용	: SelectBox에 Option 선택된 상태로 만들기
 * 파라미터	: obj	: selectbox 객체
 * 리 턴 값	: 
 * 참고사항	: 
***************************************************/
function cf_setOptionsSelected(obj){
	for(var i=0; i < obj.length; i++)
		obj.options[i].selected = true;
}

/**************************************************
 * 내   용	: SelectBox에 Option 생성
 * 파라미터	: obj	: selectbox 객체
		  	  index	: 추가될 index 위치
		  	  text	: 화면에 보여질 텍스트
		  	  value	: Option의 value
 * 리 턴 값	: 
 * 참고사항	: 
***************************************************/
function cf_setSelectOption(obj, index, text, value){
	obj.options[index] = new Option(text, value);
}

/**************************************
 * 내    용	: 수신목록 제거(개별)
 * 파라미터	: obj : 수신목록selectbox
 * 리 턴 값	: 
 * 참고사항	: 수신목록 option 더블클릭시 삭제 
***************************************/
function cf_rcvLstDbClickDel(obj) {
	for(var i = 0; i < obj.length; i++) 
		if (obj.options[i].selected == true) 
			obj.remove(i);
}

/**************************************************************
 * 내    용: 문자 입력를 받아 "/", ":" , " " 삭제 후 리턴 처리.
 * 파라미터: varStr [ 날짜형식문자(예:2006/01/01 11:11:11) ]
 * 리 턴 값: varReturnStr [ 날짜형식문자 (예:20060101111111) ]
 * 참고사항:
***************************************************************/
function cf_strToCharDel( varStr ) {
  var varLength = varStr.length ;

  varReturnStr = "" ;

  for ( var inx = 0 ; inx < varLength ; inx++ ) {
      if ( varStr.substring( inx, inx+1 ) != "/")
          if( varStr.substring( inx, inx+1 ) != " ")
              if( varStr.substring( inx, inx+1 ) != ":")
                  varReturnStr = varReturnStr + varStr.substring( inx, inx+1 ) ;
  }

  return varReturnStr;
}
/**************************************************************
 * 내    용: 문자 입력를 받아 "/", ":" , " " 삭제 후 리턴 처리.
 * 파라미터: varStr [ 날짜형식문자(예:2006-01-01 11:11:11) ]
 * 리 턴 값: varReturnStr [ 날짜형식문자 (예:20060101111111) ]
 * 참고사항:
***************************************************************/
function cf_strToCharDel2( varStr ) {
  var varLength = varStr.length ;

  varReturnStr = "" ;

  for ( var inx = 0 ; inx < varLength ; inx++ ) {
      if ( varStr.substring( inx, inx+1 ) != "-")
          if( varStr.substring( inx, inx+1 ) != " ")
              if( varStr.substring( inx, inx+1 ) != ":")
                  varReturnStr = varReturnStr + varStr.substring( inx, inx+1 ) ;
  }

  return varReturnStr;
}
/**************************************************************
 * 내    용: 입력한 문자열을 받아 한글로 되어있는지 체크
 * 파라미터: 1. str : 입력문자열
 * 리 턴 값: 
 * 참고사항:
***************************************************************/
function cf_isKorChk(str) {

 var koreanChar = cf_strToCharDel(str);
 
 if ( koreanChar == null ) return false ;
    
    for(var i=0; i < koreanChar.length; i++){ 

      var c=koreanChar.charCodeAt(i);
      
      //( 0xAC00 <= c && c <= 0xD7A3 ) 초중종성이 모인 한글자 
      //( 0x3131 <= c && c <= 0x318E ) 자음 모음 

      if( !( ( 0xAC00 <= c && c <= 0xD7A3 ) || ( 0x3131 <= c && c <= 0x318E ) ) ) {      
         return false ;
      }
    }  
    return true ;
}

/****************************************************************
 * 내 용  : 입력값이 알파벳인지 체크
 * 파라미터  : 1. str : 입력문자열
 * 리 턴 값  : 
 * 참고사항  : 
*****************************************************************/
function cf_isAlphabet(str) {
    var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";
    return cf_hasContainsCharsChk(str, chars);
}

/****************************************************************
 * 내 용  	: Null 체크
 * 파라미터  	: 1. str : 입력문자열
 * 리 턴 값  : 
 * 참고사항  	: 
*****************************************************************/
function cf_isNull(str){
	if(str == "") return true;
	else if(str == null) return true;
	else false;
	
}

/**************************************************
내 용 : IP 형식 체크
파라미터 : ip : 체크데이터
리 턴 값 :
참고사항 : true : 올바른 형식
***************************************************/
function cf_ipValid(ip){
	var dotLen = ip.split(".");			//'.'으로 구분하여 ip를 쪼갬 
	
	if(dotLen.length != 4)		//ip가 4개로 구분되지 않은 경우 
		return false;
	
	//숫자 유효성 체크
	for(i = 0; i < dotLen.length; i++){
		if( dotLen[i] < 0 || dotLen[i] > 255 )
		return false;
	}	
	
	//문자열구조 체크 
	/* '/^' ~ '/' 초기화  시작을 알리는 문구
	 * (1|2)? null or 1 또는 2로 시작하는가? 
	 * \d? 다음문자가 숫자냐? 
	 * \d[.](1|2)? 다음에 점이오고 1도는 2로 시작하느냐? 
	 * {3} 위 패턴이 3개 존재
	 * test() 해당 패턴이 존재하는지 보고 true or false 리턴 
	 */
	var expUrl = /^(1|2)?\d?\d([.](1|2)?\d?\d){3}$/;  //패턴
    return expUrl.test(ip);

}
/**************************************************
내 용 : 이메일 형식 체크
파라미터 : email : 체크데이터
리 턴 값 :
참고사항 : true : 올바른 형식
***************************************************/
function cf_emailValid(email){
	//문자열구조 체크 
	/* '/^' ~ '/' 초기화  시작을 알리는 문구
	 * (1|2)? null or 1 또는 2로 시작하는가? 
	 * \d? 다음문자가 숫자냐? 
	 * \d[.](1|2)? 다음에 점이오고 1도는 2로 시작하느냐? 
	 * {3}$ 위 패턴이 3개 존재
	 * test() 해당 패턴이 존재하는지 보고 true or false 리턴 
	 */
  // var expUrl = /^((\w+)@)?((\w+)[.])+(asia|biz|cc|cn|com|de|eu|in|info|jobs|jp|kr|mobi|mx|name|net|nz|org|travel|tv|tw|uk|us)(\/(\w*))*$/i;
    var expUrl = /^[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+)*@[0-9a-zA-Z-]+(\.[0-9a-zA-Z-]+)*$/;
	return expUrl.test(email);
}
/**************************************************
내 용 : 이미지 파일 형식 체크
파라미터 : number : 체크데이터
리 턴 값 :
참고사항 : true : 올바른 형식
***************************************************/
function cf_imgFileValid(file){
	//문자열구조 체크 
	/* '/^' ~ '$/' 초기화  시작을 알리는 문구
	 * (1|2)? null or 1 또는 2로 시작하는가? 
	 * \d? 다음문자가 숫자냐? 
	 * \d[.](1|2)? 다음에 점이오고 1도는 2로 시작하느냐? 
	 * {3} 위 패턴이 3개 존재
	 * test() 해당 패턴이 존재하는지 보고 true or false 리턴 
	 * d{2,3} 숫자형식의 2자리또는 3자리 
	 */
//var expUrl = /^(http\:\/\/)?((\w+)[.])+(asia|biz|cc|cn|com|de|eu|in|info|jobs|jp|kr|mobi|mx|name|net|nz|org|travel|tv|tw|uk|us)(\/(\w*))*$/i;
	
   var expUrl = /^((\w+)[.])+(jpg|png|bmp|gif)(\/(\w*))*$/;
   return expUrl.test(file);
}
/**************************************************
내 용 : 전화번호 형식 체크
파라미터 : number : 체크데이터
리 턴 값 :
참고사항 : true : 올바른 형식
***************************************************/
function cf_phoneValid(number){
	//문자열구조 체크 
	/* '/^' ~ '$/' 초기화  시작을 알리는 문구
	 * (1|2)? null or 1 또는 2로 시작하는가? 
	 * \d? 다음문자가 숫자냐? 
	 * \d[.](1|2)? 다음에 점이오고 1도는 2로 시작하느냐? 
	 * {3} 위 패턴이 3개 존재
	 * test() 해당 패턴이 존재하는지 보고 true or false 리턴 
	 * d{2,3} 숫자형식의 2자리또는 3자리 
	 */
   var expUrl = /^\d{2,3}-\d{3,4}-\d{4}$/;
   return expUrl.test(number);
}
/**************************************************
내 용 : 핸드폰 형식 체크
파라미터 : email : 체크데이터
리 턴 값 :
참고사항 : true : 올바른 형식
***************************************************/
function cf_hPhoneValid(number){
	//문자열구조 체크 
	/* '/^' ~ '/' 초기화  시작을 알리는 문구
	 * (1|2)? null or 1 또는 2로 시작하는가? 
	 * \d? 다음문자가 숫자냐? 
	 * \d[.](1|2)? 다음에 점이오고 1도는 2로 시작하느냐? 
	 * {3}$ 위 패턴이 3개 존재
	 * test() 해당 패턴이 존재하는지 보고 true or false 리턴 
	 */
   var expUrl = /^\d{3}-\d{3,4}-\d{4}$/;
   return expUrl.test(number);
}
/*********************************************** 
내용 : 상세URL 유효성 체크
파라미터: strURL : 체크 데이터 
리턴값 : 
참고사항: 
  정상적인 URL인지 체크
 * 정상 예1) http://www.naver.com
 * 정상 예2) http://www.naver.com/index.html
 * 정상 예3) http://naver.co.kr
 * 정상 예4) www.naver.com
 * 정상 예5) naver.com
 * 정상 예6) http://blog.naver.com/
 * 비정상 예1) http:/www.naver.com
 * 비정상 예2) http://www.naver.com2
 * 비정상 예3) mailto://www.naver.com
 **********************************************/
function cf_urlValid(strUrl) {
	//패턴 입력 http:\\
    //var expUrl = /^(http\:\/\/)?((\w+)[.])+(asia|biz|cc|cn|com|de|eu|in|info|jobs|jp|kr|mobi|mx|name|net|nz|org|travel|tv|tw|uk|us)(\/(\w*))*$/i;
	var expUrl = /^(http\:\/\/)?(\w*)?/;
    
	return expUrl.test(strUrl);
}
/**************************************************
내 용 : 텍스트 필드 자르기 
파라미터 : 1. obj : 체크 데이터 , 2. byt : 최대 길이값 		
리 턴 값 :
참고사항 : true : 올바른 형식
***************************************************/
function cf_cutText(obj, byt){
	  var str = obj.value;
	  var l = 0;
      for (var i=0; i<str.length; i++) {
         l += (str.charCodeAt(i) > 128) ? 2 : 1;
         if (l > byt) {
            return str.substring(0,i);
         }   
      }
      return str;
}
/**************************************************
내 용 : 새로 고침 방지 & 엔터 체크
파라미터 : 1. obj : 체크 데이터 , 2. byt : 최대 길이값 		
리 턴 값 :
참고사항 : true : 올바른 형식
***************************************************/
function cf_keycheck(){
	if (event.keyCode == 13){		
		uf_search();
	return false;
	}
}
/**************************************************
내 용    : 현재 날짜/시간 YYYY-MM-DD hh:mm:ss 포맷 함수 
파라미터  : 없음		
리 턴 값 :  YYYY-MM-DD hh:mm:ss
참고사항  : true : 올바른 형식
***************************************************/
function getTimeStamp() {
	var d = new Date();

	var s =
	leadingZeros(d.getFullYear(), 4) + '-' +
	leadingZeros(d.getMonth() + 1, 2) + '-' +
	leadingZeros(d.getDate(), 2) + ' ' +

	leadingZeros(d.getHours(), 2) + ':' +
	leadingZeros(d.getMinutes(), 2) + ':' +
	leadingZeros(d.getSeconds(), 2);

	return s;
}
/**************************************************
내 용    : 현재 날짜에 월을 셋팅. 
파라미터  : 없음		
리 턴 값 :  MM
참고사항  : true : 올바른 형식
***************************************************/
function getCureMonth() {
	var d = new Date();

	var m = d.getMonth() + 1;
	
	if(m < 10){
		m = "0" +""+m;
	}
	return m;
}
/**************************************************
내 용    : 현재 날짜에 년을 셋팅. 
파라미터  : 없음		
리 턴 값 :  MM
참고사항  : true : 올바른 형식
***************************************************/
function getCureYear() {
	var d = new Date();

	var y = d.getFullYear();
	return y;
}

function leadingZeros(n, digits) {
    var zero = '';
    n = n.toString();
    if (n.length < digits) {
      for (var i = 0; i < digits - n.length; i++)
      zero += '0';
    }
    return zero + n;
}


/**************************************************
내 용    : 현재 스트링 배열 오브잭트 변환 
파라미터  : 없음		
리 턴 값 :  배열
참고사항  : 'a','b','c'......
***************************************************/
function cf_getArrObj(str) {
	 var obj  = str.split(',');
	return obj;
}

/**************************************************
내 용    : 현재 스트링 배열 최대값 
파라미터  : 없음		
리 턴 값 :  배열
참고사항  : 'a','b','c'......
***************************************************/
function cf_getMax(arr) {
	var max = 0;
	for(var i=0;i<arr.length;i++){
	  if(arr[i] > Number(max)){
		  max=arr[i];
	  }
	} 	
	return Number(max);
}
/*************************************************
 * 내    용   : SelectBox 설정
 * 파라미터     : rtnMsg : 조회값
 * 리 턴 값    :
 * 참고사항     :
**************************************************/
function cf_setSelectBox(rtnMsg,obj){
    var row_data = rtnMsg.split("#");
    var row_size  = row_data.length;

    var options = "";

    if( row_size != 0 ) {
        for(var i = 0; i < row_size-1; i++) {

            var cols_data = row_data[i].split("@");
            options += "<option value='" + cols_data[0] + "'>" + cols_data[1] + "</option>\n";
        }
    }
    
 // 그룹코드 변경시 일반코드 selectbox 세팅
    jQuery("#"+obj).html(options);
    
}

/*************************************************
 * 내    용   : SelectBox 설정
 * 파라미터     : rtnMsg : 조회값
 * 리 턴 값    :
 * 참고사항     :
**************************************************/
function cf_setBindSelectBox(rtnMsg,obj){
    var row_data = rtnMsg.split("#");
    var row_size  = row_data.length;

    var options = "";

    if( row_size != 0 ) {
        for(var i = 0; i < row_size-1; i++) {

            var cols_data = row_data[i].split("@");
            options += "<option value='" + cols_data[0] + "'>" + cols_data[1] + "</option>\n";
        }
    }
    //alert(i);
    // 그룹코드 변경시 일반코드 selectbox 세팅
    jQuery("#"+obj).html(options);
    
}
/*************************************************
 * 내    용   : SelectBox 설정
 * 파라미터     : rtnMsg : 조회값
 * 리 턴 값    :
 * 참고사항     :
**************************************************/
function cf_setSelectBoxClear(obj,msg){
  
    options = "<option value=0>&#45;&#45;&#45;" +msg+"&#45;&#45;&#45;</option>\n";
   // 그룹코드 변경시 일반코드 selectbox 세팅
    jQuery("#"+obj).html(options);
}
/*************************************************
 * 내    용    : select value list
 * 파라미터     : select value 
 * 리 턴 값     : 
 * 참고사항     : cd : 조회 key , method : 호출메소드 , gb : 구분자, lang : 언어, obj: 만들 오브젝트명
**************************************************/
function cf_selectLst(method,obj,lang,msg) {
        jQuery.ajax({
            url : cf_getURL(method,lang),
            type : "POST",

            // 파라미터 정의
            data :params,

            dataType    : "xml",
            asyc        : "true",
            contentType : "application/x-www-form-urlencoded",

            // 요청 성공일 경우
            success :function(responseXML, textStatus){
                var seleList = responseXML.getElementsByTagName("message")[0].firstChild.data;
                cf_setSelectBox(seleList,obj);
            },

            // 요청 실패일 경우
            error :function(xhr, textStatus){
                alert(msg + textStatus);
            }
        });

}
/*************************************************
 * 내    용    : save value check
 * 파라미터     : save value 
 * 리 턴 값     : 
 * 참고사항     : cd : 조회 key , method : 호출메소드 , gb : 구분자, lang : 언어, obj: 만들 오브젝트명
**************************************************/
function cf_saveCk(method,lang,msg) {
        jQuery.ajax({
            url : cf_getURL(method,lang),
            type : "POST",
            // 파라미터 정의
            data :params,
            dataType    : "xml",
            asyc        : "true",
            contentType : "application/x-www-form-urlencoded",

            // 요청 성공일 경우
            success :function(responseXML, textStatus){
            	var saveCk = responseXML.getElementsByTagName("message")[0].firstChild.data;
            	uf_save(saveCk);
            },
            // 요청 실패일 경우
            error :function(xhr, textStatus){
                alert(msg + textStatus);
            }
        });

}
/*************************************************
 * 내    용    : delete value check
 * 파라미터     : delete value 
 * 리 턴 값     : 
 * 참고사항     : cd : 조회 key , method : 호출메소드 , gb : 구분자, lang : 언어, obj: 만들 오브젝트명
**************************************************/
function cf_delete(method,lang,obj,msg) {
	var itemInfo ="";
        jQuery.ajax({
            url : cf_getURL(method,lang),
            type : "POST",
            // 파라미터 정의
            data :params,
            dataType    : "xml",
            asyc        : "true",
            contentType : "application/x-www-form-urlencoded",

            // 요청 성공일 경우
            success :function(responseXML, textStatus){
            	var saveCk = responseXML.getElementsByTagName("message")[0].firstChild.data;
            	itemInfo = saveCk.split("@");            	
            	uf_delete(itemInfo[1],itemInfo[0],obj);
            },
            // 요청 실패일 경우
            error :function(xhr, textStatus){
                alert(msg + textStatus);
            }
        });

}
/*************************************************
 * 내    용   : URL 가져오기
 * 파라미터    : URL : 조회값
 * 리 턴 값   :
 * 참고사항    :
**************************************************/
function cf_getURL(method,lang){
	// URL 값 분리
    var URLArray = document.URL.split("AUI");
    var setURL = "";
    var  auiURL= URLArray[1].split("?"); 
    if(method == ""){
    	setURL = "/AUI" + auiURL[0] +"?"+"&lang=" + lang;
    }else{
        setURL = "/AUI" + auiURL[0] +"?"+ "method=" +method + "&lang=" + lang;
    }
    return setURL;
    
}
/*************************************************
내    용  : 날짜 기간값 유효성 체크
파라미터   :  pDateFr - 시작날짜 EMEdit ID
            pDateTo - 종료날짜 EMEdit ID
            pFlag   - 필수항목여부(Y/N)
Return값  : true(값이 유효하지 않은 경우) / false
**************************************************/
function fCheckEmEditDateTerm(pDateFr, pDateTo, pFlag, msg) {
	
	// 날짜컬럼 유효성 체크
	if(fCheckEmEditDate(pDateFr, pFlag) || fCheckEmEditDate(pDateTo, pFlag)) return true;
	
	vDateFr = eval("document.all." + pDateFr);
	vDateTo = eval("document.all." + pDateTo);
	
	pDateFrText = vDateFr.Text;
	pDateToText = vDateTo.Text;
	
	// 시작날짜가 종료날짜보다 클 경우
	if(eval(pDateToText) - eval(pDateFrText) < 0) {
	
	    alert(msg);
	    vDateFr.Focus();
	    return true;
	}
	
	return false;
}
/**************************************************
내      용	: 입력글자수 체크하여 해당 바이트수만 남기고 지우기
파라미터	: inputStr : 문자열
리 턴 값	: 
참고사항	: 
***************************************************/
function cf_getByteLength(inputStr) {
  var byteLength = 0;
  
  for (var inx = 0; inx < inputStr.length; inx++) {
      var oneChar = escape(inputStr.charAt(inx));
      
      if ( oneChar.length == 1 ) {
          byteLength ++;
          
      }else if (oneChar.indexOf("%u") != -1) {
          byteLength += 2;
          
      }else if (oneChar.indexOf("%") != -1) {
          byteLength += oneChar.length/3;
      }
  }
  
  return byteLength;
}
/**************************************
내    용	: 숫자인치 체크
파라미터	: input : 파라미터
리 턴 값	: 
참고사항	: 
***************************************/
function cf_isNumCheck(input) {
  var chars = "0123456789";
  return cf_containsCharsOnly(input, chars);
}
/******************************************
내    용	: 입력값과 비교할 문자들 입력받아 체크한다.
파라미터	: input : 파라미터
		  chars : 체크항목
리 턴 값	: 
참고사항	: 
*******************************************/
function cf_containsCharsOnly(input,chars) {
  for (var inx = 0; inx < input.length; inx++) {
     if (chars.indexOf(input.charAt(inx)) == -1)
         return false;
  }
  return true;
}
/*************************************************
 * 내    용       : 발생장소에 대한 거리값 가져오기
 * 파라미터     : statEvetOutbSeqn(상황이벤트발생시퀀스) 
 * 리 턴 값      : X + Y 좌표값
 * 참고사항     :
**************************************************/
function cf_StreVal(sessionId,gisPltfm,userId,typ,fram,lang,msg) {
  
        jQuery.ajax({
            url : cf_getURL("getStreVal",lang),
            type : "POST",
            // 파라미터 정의
            data : params ,

            dataType    : "xml",
            asyc        : "true",
            contentType : "application/x-www-form-urlencoded",
            // 요청 성공일 경우
            success :function(responseXML, textStatus){
                var streVal = responseXML.getElementsByTagName("message")[0].firstChild.data;
                if(streVal == "0"){
                	alert(msg);
                }else{                	
                	 cf_moveEvetPos(sessionId,gisPltfm,userId,typ,"",streVal,"",fram);
                }
            },
            // 요청 실패일 경우
            error :function(xhr, textStatus){
                alert(msg + textStatus);
            }
        });

}
/*************************************************
 * 내    용  : 로그아웃
 * 파라미터   : 없음 
 * 리 턴 값  : 성공여부
 * 참고사항  :
**************************************************/
function cf_logOut(msg,lang) {
  
        jQuery.ajax({
            url : cf_getURL("initSession",lang),
            type : "POST",
            // 파라미터 정의
            data : params ,

            dataType    : "xml",
            asyc        : "true",
            contentType : "application/x-www-form-urlencoded",
            // 요청 성공일 경우
            success :function(responseXML, textStatus){
                var sCheck = responseXML.getElementsByTagName("message")[0].firstChild.data;
                if(sCheck == "F"){
                	alert(msg);
                }else{                	
                	parent.opener.parent.location.href="/AUI/index.jsp";
                	parent.self.close();
                }
            },
            // 요청 실패일 경우
            error :function(xhr, textStatus){
                alert(msg + textStatus);
            }
        });

}


/*************************************************
 * 내    용 : 메인화면 객체 구하기
 * 파라미터  : 없음
 * 리 턴 값 : 오브젝트
 * 참고사항  :
**************************************************/
function cf_getMain() {
	var vObject = window;
   
	// 메인화면 객체 구하기
	if($.browser.msie) while("object" == typeof(vObject.opener)) vObject = vObject.opener;
	
	return vObject;
}

/*************************************************
 * 내    용   : 화면 권한 체크
 * 파라미터    : 화면아이디, 에러 메시지
 * 리 턴 값    : 화면권한 여부
 * 참고사항    :
**************************************************/
function cf_getActh(scrId,msg) {
	var vObject = cf_getMain(); // Window or Opener
	var rtn = false;
	
	vObject = vObject.parent.iFramtop.aAuthList;
	
	for(var i=0;i<vObject.length;i++){
		if(vObject[i] == scrId){
			rtn = true;
			break;
		}
	}
	
	if(!rtn){
		alert(msg);
	}
	
	return rtn;
}

/**************************************
내    용   : 글자 자리수 체크
파라미터    : 
리 턴 값   : 
참고사항    : 
***************************************/
function cf_maxlength(obj,msg){
	var maxLength = parseInt(obj.getAttribute("maxlength"));
	   if(obj.value.length>maxLength) {
	     alert(msg);
	     obj.value=obj.value.substring(0,maxLength);
	    }
 }

/**************************************************
내 용    	: byte 크기 비교 - 정규식 사용
파라미터  	: data - byte 크기를 구할 데이타		
리 턴 값 	: byte 크기
참고사항  	: 
***************************************************/
function cf_lenCheck(str, size) {
	var strSize = str.replace(/[\0-\x7f]|([\0-\u01ff]|(.))/g,"$&$1$2").length;
	if (strSize > size ) {
		return false;
	}
	return true;
}

/**************************************************
내 용    : n 값(String)의 length에 대해 digits에 맞도록 0 셋팅해주는 함수 
파라미터  : 없음		
리 턴 값 :  String
참고사항  : 
***************************************************/
function leadingZerosNegative(n, digits) {
    var zero = '';
    n = n.toString();
    if (n.length < digits) {
      for (var i = 0; i < digits - n.length; i++)
      zero += '0';
    }
    return n+zero;
}