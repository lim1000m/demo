/**************************************************************
	1. 버          전 : ver 1.0
	2. 파    일    명	: common.js
	3. 업무 시스템 명	: 공통모듈
	4. 원  도  우  명	: 공통인클루드 파일
	5. 프로그램  개요	: 전 화면에서 인클루드 할 js
	6. 작    성    자	: 이주상(2004/09/08)
	7. 수    정    자	: 
 **************************************************************/
  
 
/****************************************************
  Global 변수 정의
 ****************************************************/
go_gauceTransaction				= "";			//가우스 트랜잭션 컴포턴트						
gs_param						= "";			//가우스 트랜잭션에서 보낸 파라미터
gs_grup_cd						= "";			//트리에서 넘어오는 트랜잭션 콤포넌트의 파라미터(분류코드) 저장
gs_grup_index					= "";			//트리에서 넘어오는 트랜잭션 콤포넌트의 파라미터(트리인덱스) 저장
gs_include						= "";
gs_grup_drwngDvsnCd				= "";			//트리에서 선택한 분류코드 저장
gs_grup_drwngDvsnNm				= "";			//트리에서 선택한 분류명 저장
gs_tab_index				    = "";			//선택한 탭 인덱스
gs_grid_row						= "";			//그리드의 조회수
gs_gridAttch_row				= "";			//첨부그리드의 조회수
gs_gridHstry_row				= "";			//이력그리드의 조회수
gs_gridRef_row					= "";			//연관그리드의 조회수
gs_gridChk_row					= "";			//CheckOut사유그리드의 조회수
gs_gridsearch_row				= "";			//도면검색그리드의 조회수
gs_gridindex_row				= "";			//도면인덱스검색그리드의 조회수
gs_rtparam						= "";
gs_delete						= "";			//물품정보에서 삭제버튼 클릭여부
gs_sigubun						= "";			//신호기계실/공구구분

var tabCnt = 5;
var tabDir = "/tdms/images/common/";
var tabImgName = "tab";

myTabName = new Array();
myTabSrc = new Array();

/******* TAB LIST 글로발 변수 선언(선언된 TAB객체 리스트) ********/
var go_tabList  = new Array();

myTabName[0] = "Tab1";
myTabName[1] = "Tab2";
myTabName[2] = "Tab3";
myTabName[3] = "Tab4";
myTabName[4] = "Tab5";

myTabSrc[0] = null;
myTabSrc[1] = null;
myTabSrc[2] = null;
myTabSrc[3] = null;
myTabSrc[4] = null;

/**************************************************************
  내    용: 정규식을 활용한 trim 구현
 **************************************************************/
var TRIM_PATTERN = /(^\s*)|(\s*$)/g; // 내용의 값을 빈공백을 trim하기 위한것(앞/뒤)
 
String.prototype.trim = function() {
 return this.replace(TRIM_PATTERN, "");
}

/**************************************************************
  내    용: 작업중에 화면 표시될 사용자 메세지(조회,저장,삭제,Loading등...)
  파라미터: msgStr - 화면에 보여줄 메세지
  리 턴 값: 없음
  참고사항: msgStr 문자열이 "hidden"이면 메세지를 화면에서 제거한다.
            msgStr 문자열이 지정된 문자열 이외의 문자열이면 해당 문자열을 화면에 표시한다.
            팝업객체는 글로발 객체로 선언한다.
 **************************************************************/
var go_globalUserMagpopup   = window.createPopup();
var go_globalUserMagPopBody = go_globalUserMagpopup.document.body;
go_globalUserMagPopBody.style.backgroundColor = "yellow";
go_globalUserMagPopBody.style.border = "solid black 1px";
function gf_userMsg(msgStr)
{
  
  /* 현재 윈도우의 가로, 세로 사이즈 셋팅 */
  var li_windowWidth  = document.body.clientWidth /2;
  var li_windowHeight = document.body.clientHeight/3;

  /* 메세지 문자열이 hidden이면 표시된 메세지를 숨긴다 */
  msgStr = msgStr.toLowerCase();  //파라미터로 받은 메세지 문자열을 소문자로 치환  
  if(msgStr == "hidden") {
    go_globalUserMagpopup.hide();  //사용자 메세지 팝업을 숨긴다.
    return;
  }
  
  var li_xPos = 0;          //팝업창의 x위치
  var li_yPos = 0;          //팝업창의 y위치
  var li_popupWidth = 240;  //팝업 가로넓이
  var li_popupHeight= 36;   //팝업 세로높이
  var htmlStr  = "";        //사용자 메세지(HTML)
  
  if(msgStr == "save")          msgStr = "자료를 저장하는 중입니다...";
  else if(msgStr == "select")   msgStr = "자료를 조회하는 중입니다...";
  else if(msgStr == "delete")   msgStr = "자료를 삭제하는 중입니다...";
  else if(msgStr == "upload")   msgStr = "파일을 전송하는 중입니다...";
  else if(msgStr == "download") msgStr = "파일을 다운로드 중입니다...";
  else if(msgStr == "imgload")  msgStr = "이미지를 읽고있는 중입니다...";
  else if(msgStr == "drawload") msgStr = "도면을 열고있는 중입니다...";
  else if(msgStr == "loading")  msgStr = "화면을 구성하는 중입니다...";
  else if(msgStr == "working")  msgStr = "작업을 처리하는 중입니다...";

  htmlStr += '<table width="100%" height="100%" align="center" cellspacing="0" cellpadding="0"> ';
  htmlStr += '  <tr height="24"><td align="center" valign="middle"><font face="굴림" size="2" color="#000000"><b>';
  htmlStr += msgStr;
  htmlStr += '</b></font></td></tr>';
//  htmlStr += '  <tr height="20"><td align="center" valign="top">';  
//  for(i=1; i<=30; i++) {
//    htmlStr += '<INPUT TYPE=text id="progress'+ i +'" STYLE="background-color:#F73C10; BORDER:1px solid; BORDER-COLOR: #FF0000; width:5; height:10">';
//    htmlStr += '<INPUT TYPE=text id="space'+ i +'" STYLE="background-color:yellow; BORDER:0; width:2; height:10">';
//  }
//  htmlStr += '  </td></tr>';
  htmlStr += '</table> ';
  
  /* 팝업의 x,y 위치 값 셋팅 */
  li_xPos = li_windowWidth - (li_popupWidth/2);
  li_yPos = li_windowHeight - (li_popupHeight/2);
  
  go_globalUserMagPopBody.innerHTML = htmlStr;
  go_globalUserMagpopup.show(li_xPos, li_yPos, li_popupWidth, li_popupHeight, document.body);
}

/**************************************************************
  내    용: 자동 Submit
  파라미터: jobGubn - 작업구분
  리 턴 값: 없음
  참고사항: 자동으로 해당 JSP호출
 **************************************************************/
function gf_autoSubmit(jobGubn)
{
	var ls_urlArray = document.URL.split("tdms");
	
	go_gauceTransaction.ServerIP = ls_urlArray[0];	//IP셋팅

	/* JSP URL셋팅 */
	go_gauceTransaction.Action   = "/tdms" + ls_urlArray[1].replace("view/", "");
	go_gauceTransaction.Parameters = gs_param;	//Parameters 셋팅

	if(go_gauceTransaction.Parameters == "" || go_gauceTransaction.Parameters == null)
		go_gauceTransaction.Parameters = "jobGubn=" + jobGubn;
	else go_gauceTransaction.Parameters += ",jobGubn=" + jobGubn;
    
	go_gauceTransaction.Post();	//JSP 호출
	
//	alert("IP:"+go_gauceTransaction.ServerIP+"    "+"URL:"+go_gauceTransaction.Action+"    "+"파라미터:"+go_gauceTransaction.Parameters);
}

/**************************************************************
  내    용: 수동 Submit
  파라미터: jobGubn - 작업구분
            ls_jsp  - 호출할 JSP파일명
  리 턴 값: 없음
  참고사항: 개발자가 지정한 JSP호출
 **************************************************************/
function gf_nonAutoSubmit(jobGubn, ls_jsp)
{
	var ls_urlArray = document.URL.split("tdms");
	//alert(ls_urlArray);

	//go_gauceTransaction.ServerIP = ls_urlArray[0];	//IP셋팅	
	
	/* JSP URL셋팅 */
//	var ls_path     = ls_urlArray[1].replace("html", "jsp").split("view/");
	
	go_gauceTransaction.Action   = "/tdms"+ls_jsp;
	go_gauceTransaction.Parameters = gs_param;	//Parameters 셋팅
	
	/* Parameters의 null여부에 따라 셋팅 */
	if(go_gauceTransaction.Parameters == "" || go_gauceTransaction.Parameters == null)
		go_gauceTransaction.Parameters = "jobGubn=" + jobGubn;
	else go_gauceTransaction.Parameters += ",jobGubn=" + jobGubn;

	//alert("IP:"+go_gauceTransaction.ServerIP+"    "+"URL:"+go_gauceTransaction.Action+"    "+"파라미터:"+go_gauceTransaction.Parameters);

	go_gauceTransaction.Post();	//JSP 호출
	//alert("IP:"+go_gauceTransaction.ServerIP+"    "+"URL:"+go_gauceTransaction.Action+"    "+"파라미터:"+go_gauceTransaction.Parameters);
}


/**************************************************************
  내    용	: 익스플로러 버전에 따른 팝업창의 넓이를 가져온다.
  파라미터	: version - 익스플로러버전, popName - 팝업창이름, ex1 - 확장1
  리 턴 값	: width
  참고사항	: drwngSearch - 상세검색
  				drwngIndSearch - 색인어검색
  				drwngRegVD - 도면등록(차량)
  				drwngReg - 도면등록
  				userDrwngReg - 사용자도면등록
					wasteBasket - 휴지통
					circular - 회람
 **************************************************************/
function rtWidth(version, popName, ex1){
	var width = "";
	if(popName == "drwngSearch"){
		if(version == "7"){
			width = "835";
		}else{
			width = "860";
		}
	}else if(popName == "drwngIndSearch"){
		if(version == "7"){
			width = "835";
		}else{
			width = "845";
		}
	}else if(popName == "drwngRegVD"){
		if(version == "7"){
			//width = "835";
			width = "755";
		}else{
			width = "835";
		}
	}else if(popName == "drwngReg"){
		if(version == "7"){
			//width = "840";
			width = "765";
		}else{
			//width = "840";
			width = "835";
		}
	}else if(popName == "userDrwngReg"){
		if(version == "7"){
			width = "830";
		}else{
			width = "840";
		}
	}else if(popName == "wasteBasket"){
		if(version == "7"){
			width = "900";
		}else{
			width = "910";
		}
	}else if(popName == "circular"){
		if(version == "7"){
			width = "900";
		}else{
			width = "900";
		}
	}else if(popName == "noApprList"){
		if(version == "7"){
			width = "900";
		}else{
			width = "905";
		}
	}else if(popName == "categoryEdit"){
		if(version == "7"){
			//width = "430";
			width = "760";
		}else{
			//width = "430";
			width = "760";
		}
	}else if(popName == "favoritesInsert"){
		if(version == "7"){
			//width = "430";
			width = "760";
		}else{
			//width = "430";
			width = "760";
		}
	}else if(popName == "favoritesUpdate"){
		if(version == "7"){
			//width = "430";
			width = "760";
		}else{
			//width = "430";
			width = "760";
		}
	}else if(popName == "apprReg"){
		if(version == "7"){
			width = "550";
		}else{
			width = "550";
		}
	}else if(popName == "checkOut"){
		if(version == "7"){
			width = "780";
		}else{
			width = "780";
		}
	}else if(popName == "checkIn"){
		if(version == "7"){
			width = "830";
		}else{
			width = "810";
		}
	}else if(popName == "attachReg"){
		if(version == "7"){
			width = "575";
		}else{
			width = "575";
		}
	}else if(popName == "refDrwngReg"){
		if(version == "7"){
			width = "835";
		}else{
			width = "845";
		}
	}else if(popName == "othRefDrwngReg"){
		if(version == "7"){
			width = "835";
		}else{
			width = "835";
		}
	}else if(popName == "linkEquipReg"){
		if(version == "7"){
			width = "645";
		}else{
			width = "645";
		}
	}else if(popName == "adminUserReg"){
		if(version == "7"){
			width = "830";
		}else{
			width = "830";
		}
	}else if(popName == "deptReg"){
		if(version == "7"){
			width = "810";
		}else{
			width = "810";
		}
	}else if(popName == "deptUserReg"){
		if(version == "7"){
			width = "655";
		}else{
			width = "655";
		}
	}else if(popName == "groupSelect"){
		if(version == "7"){
			width = "380";
		}else{
			width = "380";
		}
	}else if(popName == "multiDrwngEdit"){			//일괄등록 
		if(version == "7"){
			width = "820";
		}else{
			width = "830";
		}
	}else if(popName == "insertDeptGroupAuth"){		//분류 권한 관리 등록
		if(version == "7"){
			width = "600";
		}else{
			
			width = "610";
		}
	}else if(popName == "CommonCodeEdit"){		//분류 권한 관리 등록
		if(version == "7"){
			width = "600";
		}else{
			
			width = "610";
		}
	}else if(popName == "areaCdSelect"){		//분류 권한 관리 등록
		if(version == "7"){
			width = "800";
		}else{
			
			width = "800";
		}
	}else if(popName == "jibunSelect"){		//분류 권한 관리 등록
		if(version == "7"){
			width = "800";
		}else{
			
			width = "800";
		}
	}else if(popName == "authReuqest"){		//권한요청 관리
		if(version == "7"){
			width = "800";
		}else{
			
			width = "800";
		}
	}
	
	
	return width;
}

/**************************************************************
  내    용	: 익스플로러 버전에 따른 팝업창의 길이를 가져온다.
  파라미터	: version - 익스플로러버전, popName - 팝업창이름, ex1 - 확장1
  리 턴 값	: width
  참고사항	: 
 **************************************************************/
function rtHeight(version, popName, ex1){
	var height = "";
	if(popName == "drwngSearch"){
		if(version == "7"){
			//height = "620";
			height = "845";
		}else{
			height = "890";
		}
	}else if(popName == "drwngIndSearch"){
		if(version == "7"){
			//height = "690";
			height = "663";
		}else{
			height = "720";
		}
	}else if(popName == "drwngReg"){
		if(version == "7"){
		   // 도면인경우
		   if(ex1 == "1"){
			   //height = "510";
			   height = "505";
		   }else{
			   height = "585";
		   }
		}else{
		   // 도면인경우
		   if(ex1 == "1"){
			   height = "555";
		   }else{
			   height = "555";
		   }
		}
	}else if(popName == "userDrwngReg"){
		if(version == "7"){
			height = "630";
		}else{
			height = "660";
		}
	}else if(popName == "wasteBasket"){
		if(version == "7"){
			//height = "580";
			height = "625";
		}else{
			height = "670";
		}
	}else if(popName == "circular"){
		if(version == "7"){
			height = "560";
		}else{
			height = "590";
		}
	}else if(popName == "noApprList"){
		if(version == "7"){
			height = "580";
		}else{
			height = "620";
		}
	}else if(popName == "categoryEdit"){
		if(version == "7"){
			//height = "180";
			height = "555";
			
		}else{
			//height = "220";
			height = "575";
		}
	}else if(popName == "favoritesInsert"){
		if(version == "7"){
			height = "195";
			
		}else{
			height = "245";
		}
	}else if(popName == "favoritesUpdate"){
		if(version == "7"){
			height = "165";
			
		}else{
			height = "215";
		}
	}else if(popName == "apprReg"){
		if(version == "7"){
			height = "270";
		}else{
			height = "310";
		}
	}else if(popName == "checkOut"){
		if(version == "7"){
			height = "285";
		}else{
			height = "335";
		}
	}else if(popName == "checkIn"){
		if(version == "7"){
			//height = "300";
			height = "395";
		}else{
			//height = "350";
			height = "435";
		}
	}else if(popName == "checkInVD"){
		if(version == "7"){
			height = "330";
		}else{
			//height = "380";
		}
	}else if(popName == "attachReg"){
		if(version == "7"){
			height = "320";
		}else{
			height = "360";
		}
	}else if(popName == "refDrwngReg"){
		if(version == "7"){
			height = "795";
		}else{
			height = "845";
		}
	}else if(popName == "othRefDrwngReg"){
		if(version == "7"){
			height = "730";
		}else{
			height = "790";
		}
	}else if(popName == "linkEquipReg"){
		if(version == "7"){
			height = "635";
		}else{
			height = "685";
		}
	}else if(popName == "adminUserReg"){
		if(version == "7"){
			height = "550";
		}else{
			height = "620";
		}
	}else if(popName == "deptReg"){
		if(version == "7"){
			height = "180";
		}else{
			height = "230";
		}
	}else if(popName == "deptUserReg"){
		if(version == "7"){
			height = "630";
		}else{
			height = "680";
		}
	}else if(popName == "groupSelect"){
		if(version == "7"){
			height = "613";
		}else{
			height = "660";
		}
	}else if(popName == "multiDrwngEdit"){
		if(version == "7"){
			height = "720";
		}else{
			height = "770";
		}
	}else if(popName == "insertDeptGroupAuth"){
		if(version == "7"){
			   if(ex1 == "1"){
				   height = "180";
			   }else{
				   height = "520";
			   }
		}else{
			   if(ex1 == "1"){
				   height = "230";
			   }else{
				   height = "570";
			   }
		}
	}else if(popName == "CommonCodeEdit"){
		if(version == "7"){
			height = "200";
		}else{
			height = "245";
		}
	}else if(popName == "areaCdSelect"){
		if(version == "7"){
			height = "550";
		}else{
			height = "580";
		}
	}else if(popName == "jibunSelect"){
		if(version == "7"){
			height = "550";
		}else{
			height = "590";
		}
	}else if(popName == "authReuqest"){
		if(version == "7"){
			height = "550";
		}else{
			height = "590";
		}
	}
	return height;
}

/**************************************************************
  내    용: 쿠키로부터 값을 읽어오는 함수
  파라미터: keyname - 쿠키로부터 얻어내고 싶은 값의 키이름
  리 턴 값: 문자열의 반환값
  참고사항: 
 **************************************************************/
function getCookie(keyname)
{
	tmp=document.cookie + ";";
	index1=tmp.indexOf(keyname, 0);
	if(index1 !=-1)
	{
		tmp=tmp.substring(index1,tmp.length);
		index2=tmp.indexOf("=",0) + 1;
		index3=tmp.indexOf(";",index2);
		return(unescape(tmp.substring(index2,index3)));
	}
	return("");
	
}

/**************************************************************
  내    용: 쿠키의 값을 기입하는 함수
  파라미터: keyname, val - 쿠키로부터 얻어내고 싶은 값의 키이름, 매개변수
  리 턴 값: 문자열의 반환값
  참고사항: 
 **************************************************************/
function setCookie(keyname, val)
{
	tmp=keyname + "=" + escape(val) + ";";
	document.cookie = tmp;
	
}

/*********************************************************
  내    용: 트랜잭션 화면의 디자인 HTML스크립트를 생성한다.
  파라미터: gubn   - table처음 혹은 끝
  리 턴 값: 없음
  참고사항: gubn에 받을 수 있는 값은 다음과 같다
            테이블처음: "start", 테이블끝: "end"
 *********************************************************/
function gf_transactionHtmlTitle(gubn) {  
	if(window.dialogArguments != null || parent.opener != null) { //팝업

		if(gubn == "end") {
			document.write('    </td> ');
			document.write('  </tr> ');
			document.write(' </table> ');
		}
		else {
			document.write(' <table width="100%" border="0" cellpadding="0" cellspacing="0"> ');
			document.write('  <tr> ');
			document.write('    <td width="37"><img src="/tdms/images/open_top_01.gif"></td> ');
			document.write('    <td background="/tdms/images/open_top_02.gif" align="left" style="padding-left: 3"> ');
		
			document.write('    <table border="0" cellspacing="0" cellpadding="0" width="'+ (gubn.length*16) +'"> ');
			document.write('    <tr> ');
			document.write('		<td align="left" class="window_title_popup">'+ gubn +'</td> ');
			document.write('    </tr> ');
			document.write('    </table> ');

			document.write('    </td> ');
			document.write('    <td background="/tdms/images/open_top_02.gif" align="right" style="padding-right: 10"> '); 
			document.write('    </td> ');
			document.write('    <td width="263" height="45" align="right" valign="middle" background="/tdms/images/open_top_03.gif"> ');
		}
	}
}

/*********************************************************
  내    용: 트랜잭션 화면의 디자인 HTML스크립트를 생성한다.
  파라미터: gubn   - table처음 혹은 끝
  리 턴 값: 없음
  참고사항: gubn에 받을 수 있는 값은 다음과 같다
            테이블처음: "start", 테이블끝: "end"
 *********************************************************/
function gf_transactionHtmlTitle2(gubn) {  
	if(window.dialogArguments != null || parent.opener != null) { //팝업

		if(gubn == "end") {
			document.write('    </td> ');
			document.write('  </tr> ');
			document.write(' </table> ');
		}
		else {
			document.write(' <table width="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;"> ');
			document.write('  <tr> ');
			document.write('    <td width="37"><img src="/tdms/images/open_top_01.gif"></td> ');
			document.write('    <td background="/tdms/images/open_top_02.gif" align="left" style="padding-left: 3"> ');
		
			document.write('    <table border="0" cellspacing="0" cellpadding="0" width="'+ (gubn.length*200) +'" style="table-layout:fixed;" > ');
			document.write('    <tr> ');
			document.write('		<td align="left" class="window_title_popup" nowrap><div id=viewerTitle1>'+ gubn +'</div><div id=viewerTitle></div></td> ');
			document.write('    </tr> ');
			document.write('    </table> ');

			document.write('    </td> ');
			document.write('    <td background="/tdms/images/open_top_02.gif" align="right" style="padding-right: 10"> '); 
			document.write('    </td> ');
			document.write('    <td width="263" height="45" align="right" valign="middle" background="/tdms/images/open_top_03.gif"> ');
		}
	}
}

/*********************************************************
  내    용: 트랜잭션 화면의 디자인 HTML스크립트를 생성한다.
  파라미터: gubn   - table처음 혹은 끝
  리 턴 값: 없음
  참고사항: gubn에 받을 수 있는 값은 다음과 같다
            테이블처음: "start", 테이블끝: "end"
 *********************************************************/
function gf_queryHtmlTitle(gubn) { 
	
 if(window.dialogArguments != null || parent.opener != null) { //팝업

    if(gubn == "end") {
		document.write('    </td> ');
		document.write('  </tr> ');
		document.write(' </table> ');
	}
	else {
		document.write(' <table width="100%" border="0" cellpadding="0" cellspacing="0"> ');
		document.write('  <tr> ');
		document.write('    <td width="37"><img src="/tdms/images/search_top01.gif"></td> ');
		document.write('    <td background="/tdms/images/search_top02.gif" align="left" style="padding-left: 3"> ');
		
		document.write('    <table border="0" cellspacing="0" cellpadding="0" width="'+ (gubn.length*16) +'"> ');
		document.write('    <tr> ');
		document.write('		<td align="left" class="window_title_popup">'+ gubn +'</td> ');
		document.write('    </tr> ');
		document.write('    </table> ');

		document.write('    </td> ');
		document.write('    <td background="/tdms/images/search_top02.gif" align="right" style="padding-right: 10"> '); 	
	}
  }

}

/*********************************************************
  내    용: 트랜잭션 화면의 디자인 HTML스크립트를 생성한다.
  파라미터: gubn   - table처음 혹은 끝
  리 턴 값: 없음
  참고사항: gubn에 받을 수 있는 값은 다음과 같다
            테이블처음: "start", 테이블끝: "end"
 *********************************************************/
function gf_transactionHtml(gubn) {

  if(window.dialogArguments != null || parent.opener != null) { //팝업
    
    switch(gubn) {
      case "start":
        document.write('<table width="100%" border="0" cellspacing="0" cellpadding="0"> ');
        document.write(' <tr> ');
        document.write('   <td width="5">&nbsp;</td> ');
        document.write('   <td> ');
        break;
        
      case "end":
        document.write('    </td> ');
        document.write('   <td width="5">&nbsp;</td> ');
        document.write('  </tr> ');
        document.write('</table> ');
        break;
    }
  }
  
  else {  //메인
    switch(gubn) {
      case "start":
        document.write('<table width="100%" border="0" cellspacing="0" cellpadding="0"> ');
        document.write(' <tr> ');
        document.write('   <td width="5">&nbsp;</td> ');
        document.write('   <td> ');
        break;
        
      case "end":
        document.write('    </td> ');
        document.write('  </tr> ');
        document.write('</table> ');
        break;
    }
  }

}
/*********************************************************
  내    용: 조회 화면의 디자인 HTML스크립트를 생성한다.
  파라미터: gubn   - table처음 혹은 끝
  리 턴 값: 없음
  참고사항: gubn에 받을 수 있는 값은 다음과 같다
            테이블처음: "start", 테이블끝: "end"
 *********************************************************/
function gf_queryHtml(gubn) {

  if(window.dialogArguments != null || parent.opener != null) { //팝업
    
    switch(gubn) {
      case "start":
        document.write('<table width="100%" border="0" cellspacing="0" cellpadding="0"> ');
        document.write(' <tr> ');
        document.write('   <td width="5">&nbsp;</td> ');
        document.write('   <td> ');
        break;
        
      case "end":
        document.write('   </td> ');
        document.write('   <td width="5">&nbsp;</td> ');
        document.write('  </tr> ');
        document.write('</table> ');
        break;
    }
  }
  
  else {  //메인
    switch(gubn) {
      case "start":
        document.write('<table width="100%" border="0" cellspacing="0" cellpadding="0"> ');
        document.write(' <tr> ');
        document.write('   <td width="5">&nbsp;</td> ');
        document.write('   <td> ');
        break;
        
      case "end":
        document.write('    </td> ');
        document.write('  </tr> ');
        document.write('</table> ');
        break;
    }
  }

}
/*********************************************************
  내    용: 공통팝업 오픈
  파라미터: target_obj - 선택한 날짜를 표시할 오브젝트 배열
            col_name   - 그리드일 경우 값을 넣어줄 해당 칼럼배열
            popup_type - 팝업타입
  리 턴 값: Array - 결과 리스트 배열
  참고사항: popup_type(1):달력, popup_type(2):문자입력
 *********************************************************/
function gf_popUp(target_obj, col_name, popup_type)
{ 

  var position  = "";
  var winset    = "";
  var jsp_url   = "";
  var ls_rtnVal = "";
  var lb_cmcd   = false;  //공통코드 팝업을 호출하였는지 여부
  var ls_callGubn = "";

  switch(popup_type) {
    case 1: //달력(날짜 리턴)
      position = 'dialogWidth:188px;dialogHeight:275px;';
      jsp_url  = "/tdms/jsp/common/calendar.jsp";
      break;
    case 2: //문자입력화면(문자리턴)
      position = 'dialogWidth:210px;dialogHeight:50px;';
      jsp_url  = "/tdms/jsp/common/textprompt.jsp?prntGrupNm="+col_name;
      col_name = "";
      break;      
  }
  winset = 'unadorned:yes;resizable:no;center:yes;help:no;status:yes;scroll:no;' + position;

  if(lb_cmcd && col_name=="yes") ls_rtnVal = window.showModalDialog(jsp_url,popup_type+":yes",winset); //yes(다중코드선택)이면 파라미터로 true를 넘긴다
  else if(lb_cmcd) ls_rtnVal = window.showModalDialog(jsp_url,popup_type,winset);                      //코드선택
  else if(popup_type == 3 && ls_callGubn[ls_callGubn.length-1] == "mainTransactionTitle.html")
    ls_rtnVal = window.showModalDialog(jsp_url,["yes"],winset);  //트랜잭션화면에서 부서팝업이 호출되면 전체라는 항목이 표시되지 않도록 하기위하여 파라미터를 넘긴다
  else if(popup_type == 333 && ls_callGubn[ls_callGubn.length-1] == "mainTransactionTitle.html")
    ls_rtnVal = window.showModalDialog(jsp_url,["yes","333"],winset);  //트랜잭션화면에서 부서팝업이 호출되면 전체라는 항목이 표시되지 않도록 하기위하여 파라미터를 넘긴다    
  else if(popup_type==22 || popup_type==44) ls_rtnVal = window.showModalDialog(jsp_url,"true",winset);  //팝업타입이 22(사원다중선택), 44(자재다중선택)이면 파라미터로 true를 넘긴다
  else if(popup_type==444) ls_rtnVal = window.showModalDialog(jsp_url,"ALL",winset);  //팝업타입이 444(해당공종의 모든창고리스트)이면 파라미터로 ALL을 넘긴다.
  else if(popup_type==33) ls_rtnVal = window.showModalDialog(jsp_url,[target_obj,col_name],winset);     //33(부서다중선택)이면 파라미터로 true를 넘긴다  
  else ls_rtnVal = window.showModalDialog(jsp_url,"",winset);
  
  if(ls_rtnVal == undefined || ls_rtnVal.length == null || ls_rtnVal.length == 0) return;//리턴 value가 없을 경우 리턴
  
  /* target_obj배열의 갯수 만큼 루프를 돌며 해당 데이타 셋팅 */
  for(i=0; i<target_obj.length; i++) {
    if(popup_type==33) break; //부서다중 선택일 경우는 루프문을 돌지 않는다
    
    switch (target_obj[i].tagName) {
      case "INPUT" :    //HTML 객체
        if(target_obj[i].type == "text" || target_obj[i].type == "hidden") target_obj[i].value = ls_rtnVal[i];        
        break;
      case "OBJECT" :    //가우스 객체
        switch (target_obj[i].attributes.classid.nodeValue.toUpperCase()) {
            case "CLSID:E6876E99-7C28-43AD-9088-315DC302C05F": // EMEdit Component
              target_obj[i].Text = ls_rtnVal[i];
              //target_obj[i].PutSelText(0,8,ls_rtnVal[i]);
              break;
            case "CLSID:3267EA0D-B5D8-11D2-A4F9-00608CEBEE49": // DataSet Component
              //target_obj[i].NameValue(target_obj[i].RowPosition,col_name[i]) = ls_rtnVal[i];
              break;
            case "CLSID:1F57AEAD-DB12-11D2-A4F9-00608CEBEE49": // Grid Component
              break;
          }
        break;
    } //case문의 끝
  }   //for문의  끝
  
  return ls_rtnVal;
}


/*********************************************************
  내    용: 사용자 메세지 리턴
  파라미터: msgCode - 메세지코드
  리 턴 값: string(메세지)
  참고사항: 100번대(사용자메세지), 900번대(시스템메세지)
 *********************************************************/
function gf_msgStr(msgCode)
{
	if	   (msgCode == 100) return "";
	else if(msgCode == 101) return "저장되었습니다.";
	else if(msgCode == 102) return "삭제하시겠습니까?";
	else if(msgCode == 103) return "삭제되었습니다.";
	else if(msgCode == 104) return "변경된 사항을 저장하시겠습니까?";
	else if(msgCode == 105) return "항목은 값을 반드시 입력하셔야 합니다.";
	else if(msgCode == 106) return "날짜의 기간이 올바르지 않습니다.";
	else if(msgCode == 107) return "처리중 오류가 발생하였습니다.";
	else if(msgCode == 108) return "먼저 원하는 행을 선택하십시요.";
	else if(msgCode == 109) return "삭제할 수 없습니다.";
	else if(msgCode == 110) return "해당 데이타가 없습니다.";
	else if(msgCode == 111) return "이미 존재하는 데이타 입니다. 다른 키로 저장하십시요.";
	else return "개발시 메세지 코드를 잘못넘겼습니다. 개발자분은 확인해 보세요";
}

/*********************************************************
  내    용: 팝업윈도우 오픈
  파라미터: url		    - 오픈할 윈도우 url
		    width	    - 가로넓이
		    height      - 세로높이

  리 턴 값: 없음
  참고사항: 
 *********************************************************/
function gf_openWindow(url, ls_width, ls_height)
{
	var posx      = (screen.availWidth - ls_width) / 2 - 1;
	var posy      = (screen.availHeight - ls_height) / 2 - 1;
	var position  = "";
	var winset    = "";
	
	// = 'width=' + ls_width + ',height=' + ls_height + ',top=' + posy + ',left=' + posx;
	position = 'width=' + screen.availWidth + 'px,height=' + screen.availHeight + 'px,top=0,left=0';
	winset = ',resizable=yes,denter=yes,menubar=no,scrollbars=no,status=yes,titlebar=no,toolbar=no,' + position;	    
	window.open(url, '', winset);
}
function gf_openWindow_resize(url, ls_width, ls_height, ls_param)
{
	var posx      = (screen.availWidth - ls_width) / 2 - 1;
	var posy      = (screen.availHeight - ls_height) / 2 - 1;
	var position  = "";
	var winset    = "";
	var ls_rtnVal = "";

	position = 'dialogWidth:' + screen.availWidth + 'px;' + 'dialogHeight:' + screen.availHeight + 'px;';
    winset = 'resizable:yes;center:yes;help:no;status:yes;scroll:no;' + position;	    
	ls_rtnVal = window.showModalDialog(url,ls_param,winset);

	if(ls_rtnVal == null || ls_rtnVal.length == 0)  return "";
    return ls_rtnVal;
	
}
function gf_openWindow_modal(url, ls_width, ls_height, ls_param)
{
	var posx      = (screen.availWidth - ls_width) / 2 - 1;
	var posy      = (screen.availHeight - ls_height) / 2 - 1;
	var position  = "";
	var winset    = "";
	var ls_rtnVal = "";
	
	position = 'dialogWidth:' + ls_width + 'px;' + 'dialogHeight:' + ls_height + 'px;';
    winset = 'resizable:yes;center:yes;help:no;status:yes;scroll:no;' + position;
    ls_rtnVal = window.showModalDialog(url,ls_param,winset);

	if(ls_rtnVal == null || ls_rtnVal.length == 0)  return "";
    return ls_rtnVal;	
	
}
function gf_openWindow_modal_interface(url, ls_width, ls_height, ls_param)
{
	var posx      = (screen.availWidth - ls_width) / 2 - 1;
	var posy      = (screen.availHeight - ls_height) / 2 - 1;
	var position  = "";
	var winset    = "";
	var ls_rtnVal = "";
	
	position = 'dialogWidth:' + ls_width + 'px;' + 'dialogHeight:' + ls_height + 'px;';
    winset = 'resizable:yes;center:yes;help:no;status:yes;scroll:no;' + position;
    ls_rtnVal = window.showModalDialog(url,ls_param,winset);
      
    if(ls_rtnVal == null || ls_rtnVal.length == 0)  return "";
    return ls_rtnVal;	
}
function gf_openWindow_compact(url, ls_width, ls_height)
{
	
	var posx      = (screen.availWidth - ls_width) / 2 - 1;
	var posy      = (screen.availHeight - ls_height) / 2 - 1;
	var position  = "";
	var winset    = "";
	
	// = 'width=' + ls_width + ',height=' + ls_height + ',top=' + posy + ',left=' + posx;
	position = 'width=' + ls_width + ',height=' + ls_height + ',top=' + posy + ',left=' + posx;
	winset = ',resizable=yes,denter=yes,menubar=no,scrollbars=no,status=yes,titlebar=no,toolbar=no,' + position;	    
	window.open(url, '', winset);
}
function gf_openWindow_compact2(url, ls_width, ls_height)
{
	
	var posx      = (screen.availWidth - ls_width) / 2 - 1;
	var posy      = (screen.availHeight - ls_height) / 2 - 1;
	var position  = "";
	var winset    = "";
	
	// = 'width=' + ls_width + ',height=' + ls_height + ',top=' + posy + ',left=' + posx;
	position = 'width=' + ls_width + ',height=' + ls_height + ',top=' + posy + ',left=' + posx;
	winset = ',resizable=yes,denter=yes,menubar=no,status=yes,titlebar=no,toolbar=no,' + position;	    
	window.open(url, '', winset);
}

function gf_openWindow_compact3(url, ls_width, ls_height)
{
	
	var posx      = (screen.availWidth - ls_width) / 2 - 1;
	var posy      = (screen.availHeight - ls_height) / 2 - 1;
	var position  = "";
	var winset    = "";
	
	// = 'width=' + ls_width + ',height=' + ls_height + ',top=' + posy + ',left=' + posx;
	position = 'width=' + ls_width + ',height=' + ls_height + ',top=' + posy + ',left=' + posx;
	winset = ',resizable=yes,denter=yes,menubar=no,status=yes,scrollbars=yes,titlebar=no,toolbar=no,' + position;	    
	window.open(url, '', winset);
}

function gf_openWindow2(url, ls_width, ls_height, ls_param)
{
	var posx      = (screen.availWidth - ls_width) / 2 - 1;
	var posy      = (screen.availHeight - ls_height) / 2 - 1;
	var position  = "";
	var winset    = "";
	
	position = 'width=' + ls_width + ',height=' + ls_height + ',top=' + posy + ',left=' + posx;
	winset = ',resizable=yes,denter=yes,menubar=no,scrollbars=no,status=yes,titlebar=no,toolbar=no,' + position;	    
	
	ls_param.window.open(url, '', winset);
}
/*********************************************************
  내    용: 조회건수 표수
  파라미터: queryObj - 조회건수표시 객체
            queryCnt - 조회건수
  리 턴 값: 없음
  참고사항: '조회수: 0' 이외의 문자열(서브타이틀)이 있을 경우
            해당 타이틀은 그대로 표시하고 조회수는 타이틀 다음에
            ()안에 표시한다.
 *********************************************************/
function gf_displayCnt(queryObj, queryCnt)
{
  var ls_str = queryObj.innerText;
  var pos    = ls_str.search("조회수");
  
  if(pos > 0) queryObj.innerHTML = ls_str.substring(0,pos) +"조회수: "+ queryCnt +")";
  else		  queryObj.innerHTML = "조회수: "+ queryCnt;
//  if(queryCnt == 0)  alert("해당 데이타가 없습니다.");
}

/*********************************************************
  내    용: 문자열길이 리턴
  파라미터: obj       - 문자열 길이를 체크할 객체
            maxLen    - 최대 문자열 길이
  리 턴 값: true(길이초과), false(정상)
  참고사항: 없음
 *********************************************************/
function gf_korEngLenCheck(obj, maxLen)
{
  var cnt, len;
  
  len = 0;
  
  for(cnt=0 ; cnt<obj.value.length; cnt++ ) {
    if( obj.value.charCodeAt(cnt) > 255 ) len += 2;  
    else len++;  
  }
  if( len > maxLen ) {
    if(!obj.disabled) {
      obj.focus();
      obj.select();
    }
    return true;
  }
  return false;
}
/*
' ------------------------------------------------------------------
' Function    : fc_chk_byte(aro_name)
' Description : 입력한 글자수를 체크
' Argument    : Object Name(글자수를 제한할 컨트롤)
' Return      : 
' ------------------------------------------------------------------
*/
function uf_ValidationCheck(aro_name,ari_max)
{

   var ls_str     = aro_name.value; // 이벤트가 일어난 컨트롤의 value 값
   var li_str_len = ls_str.length;  // 전체길이

   // 변수초기화
   var li_max      = ari_max; // 제한할 글자수 크기
   var i           = 0;  // for문에 사용
   var li_byte     = 0;  // 한글일경우는 2 그밗에는 1을 더함
   var li_len      = 0;  // substring하기 위해서 사용
   var ls_one_char = ""; // 한글자씩 검사한다
   var ls_str2     = ""; // 글자수를 초과하면 제한할수 글자전까지만 보여준다.

   for(i=0; i< li_str_len; i++)
   {
      // 한글자추출
      ls_one_char = ls_str.charAt(i);

      // 한글이면 2를 더한다.
      if (escape(ls_one_char).length > 4)
      {
         li_byte += 2;
      }
      // 그밗의 경우는 1을 더한다.
      else
      {
         li_byte++;
      }

      // 전체 크기가 li_max를 넘지않으면
      if(li_byte <= li_max)
      {
         li_len = i + 1;
      }
   }
   
   // 전체길이를 초과하면
   if(li_byte > li_max)
   {
      alert( " 영문 "+ li_max + "글자, 한글 "+ li_max/2+"글자를 \n 초과 입력할수 없습니다. \n 초과된 내용은 자동으로 삭제 됩니다. ");
      ls_str2 = ls_str.substr(0, li_len);
      aro_name.value = ls_str2;
      
   }
   aro_name.focus();

}


/*

/*****************[ common.js 에 들어갈 내용]**************
  내    용: 리포트 오픈
  파라미터: ls_param - 2차원배열 파라미터
  리 턴 값: 없음
  참고사항: 호출종류: (1-리포트, 2-전자결재, 3-양식출력)
 *********************************************************/
function gf_report(ls_param)
{ 
  var position  = "";
  var winset    = "";
  var html_url  = "";
  var ls_rtnVal = "";
  var ls_urlArray = document.URL.split("/tdms");
  var url = ls_urlArray[0];
  
  /* 호출종류 타입이 2(업무쪽에서 결재의뢰를 호출했으면 결재의뢰등록팝업을 호출하고
     아닐경우는 보고서 화면을 호출한다 */      
  if(ls_param[0][0] == "2") { //전자결재 쪽에 있는 결재의뢰등록 팝업
    position  = 'dialogWidth:700px;dialogHeight:440px;';
    html_url  = url +"/ess/html/fq/FQ10_AprvReqMng_M.html";
    winset    = 'unadorned:yes;resizable:no;center:yes;help:no;status:no;scroll:no;' + position;
	
  }
  else {                      //전자결재 쪽에 있는 리포트파일 호출
    position  = 'dialogWidth:1024px;dialogHeight:735px;';
    html_url  = url + "/ess/include/report.html";    
    winset    = 'unadorned:yes;resizable:no;center:yes;help:no;status:no;scroll:no;' + position;
  }
  
  /* 파라미터로 받은 배열에 결재목록 키값이 없을 경우 널값 셋팅 */
  if(ls_param[0].length < 2) {
    ls_param[0][1] = "";  //class코드
    ls_param[0][2] = "";  //office코드
    ls_param[0][3] = "";  //발생일시
  }
  return window.showModalDialog(html_url,ls_param,winset); 
}


/**
 * @type   : function
 * @access : public
 * @desc   : 가우스의 데이터셋 오브젝트 간에 데이터를 복사한다. 복사대상이 되는 데이터셋의 기존의 데이터는 모두 삭제된다.
 *           features 파라미터에서 copyHeader를 yes로 할 경우 DataSet의 컬럼정보까지 복사된다.
 * <pre>
 *    cfCopyDataSet(oDelivRsltOriginGDS, oDelivRsltCopiedGDS, "copyHeader=no");
 * </pre>
 * @sig    : oOriginDataSet, oTargetDataSet[, features]
 * @param  : oOriginDataSet required 원본 DataSet
 * @param  : oTargetDataSet required 복사되어질 DataSet
 * @param  : features       optional 기타 속성을 정의하는 스트링. 속성종류는 아래와 같다.
 * <pre>
 *     copyHeader : Header를 복사할지 여부.  (default:yes) 
 *     rowFrom    : 복사할 row의 시작 index. (default:1) 
 *     rowCnt     : 복사할 row의 갯수 index. (default:DataSet.CountRow 의 값) 
 *     사용예) "true,1,3"  -> 1번 row 부터 3번 row까지 Header와 함께 복사함.
 * </pre>
 * @author : 이왕석
 */
function gf_copyDataSet(oOriginDataSet, oTargetDataSet, features)
{
  var featureValues = ["true", 1, oOriginDataSet.CountRow];
  
  if (features != null && features != "") featureValues = features.split(",");
  
  var copyHeader  = featureValues[0];
  var rowFrom     = featureValues[1];
  var rowCnt      = featureValues[2];

  if (copyHeader == "true") {
    gf_copyDataSetHeader(oOriginDataSet, oTargetDataSet);
  }

  oTargetDataSet.ClearData();
  if ( oOriginDataSet.CountRow > 0 ) {
    var temp = oTargetDataSet.dataid;  // importdata를 한 후 DataSet의 기존의 dataid 속성값이 지워지는 것을 방지.
    oTargetDataSet.ImportData(oOriginDataSet.ExportData(rowFrom, rowCnt, true));
    oTargetDataSet.dataid = temp;
    oTargetDataSet.ResetStatus();
  }
}
/**
 * @type   : function
 * @access : public
 * @desc   : 가우스의 데이터셋 오브젝트 간에 컬럼 헤더 정보를 복사한다.
 * <pre>
 *    cfCopyDataSet(oDelivRsltOriginGDS, oDelivRsltCopiedGDS); 
 * </pre>
 * @sig    : oOriginDataSet, oTargetDataSet
 * @param  : oOriginDataSet required 원본 DataSet
 * @param  : oTargetDataSet required 복사되어질 DataSet
 * @author : 이왕석
 */
function gf_copyDataSetHeader(oOriginDataSet, oTargetDataSet) {
  var DsHeader = "";
  var colId   = "";
  var colType = "";
  var colProp = "";
  var colSize = ""

  for (var i = 1; i <= oOriginDataSet.CountColumn; i++) {
     colId   = oOriginDataSet.ColumnID(i);       //column id
    colIndex= oOriginDataSet.ColumnIndex(colId);  //column id에 해당하는 index값  
    colSize = oOriginDataSet.ColumnSize(colIndex);//column size
       
    /* column의 type 정의 코드
    
      Type  Description
      -----------------
       1    String 
       2    Integer
       3    Decimal
       4    Date
       5    URL
    */
    
    //column type정의
    switch (oOriginDataSet.ColumnType(colIndex)){
         case 1 :
                  colType = 'String';
                  break;
         case 2 :
                  colType = 'Integer';
                  break;
         case 3 :
                  colType = 'Decimal';
                  break;
         case 4 :
                  colType = 'Date';
                  break;
         case 5 :
                  colType = 'URL';
                  break;
                  
         default :
                  colType = "";
                  break;
    }        

    /* column의 property 정의
      0 : Normal (Key = No)
      1 : Constant
      2 : Key (Normal, Sequence)
      3 : Sequence (Key = No) // 현재 의미없음.
    */
    switch (oOriginDataSet.ColumnProp(i)) {
         case 0 :
                  colProp = "NORMAL";
                  break;
         case 1 :
                  colProp = "CONSTANT";
                  break;
         case 2 :
                  colProp = "KEYVALUE";
                  break;
         default :
                  colProp = "";
                  break;
    }        
    
    //column id,column type,column size, column property
    DsHeader = DsHeader + colId + ":" + colType + "(" + colSize + ")" + ":" + colProp + ",";
  }

  DsHeader = DsHeader.substr(0, DsHeader.length - 1);
  oTargetDataSet.SetDataHeader(DsHeader);
}
/*********************************************************
  내    용: 문자열길이 리턴
  파라미터: obj       - 문자열 길이를 체크할 객체
            maxLen    - 최대 문자열 길이
  리 턴 값: true(길이초과), false(정상)
  참고사항: 없음
 *********************************************************/
function gf_SpecialTextCheck(obj)
{
	var checkText = obj.value;
	
	/** 특수문자가 있는지를 알기위해 **/
	var char1 = checkText.indexOf('+', 0);		
	var char2 = checkText.indexOf('#', 0);		
	var char3 = checkText.indexOf('%', 0);		
	var char4 = checkText.indexOf('&', 0);
	var char5 = checkText.indexOf('~', 0);
	var char6 = checkText.indexOf('`', 0);
	var char7 = checkText.indexOf('!', 0);
	var char8 = checkText.indexOf('@', 0);
	var char9 = checkText.indexOf('#', 0);
	var char10 = checkText.indexOf('$', 0);
	var char11 = checkText.indexOf('^', 0);		
	var char12 = checkText.indexOf('*', 0);		
	var char13 = checkText.indexOf('(', 0);		
	var char14 = checkText.indexOf(')', 0);
//	var char15 = checkText.indexOf('-', 0);
	var char16 = checkText.indexOf('_', 0);
	var char17 = checkText.indexOf('=', 0);
	var char18 = checkText.indexOf('{', 0);
	var char19 = checkText.indexOf('}', 0);
	var char20 = checkText.indexOf('[', 0);
	var char21 = checkText.indexOf(']', 0);		
	
	var char23 = checkText.indexOf('|', 0);		
	var char24 = checkText.indexOf("'", 0);
	var char25 = checkText.indexOf('"', 0);
	var char26 = checkText.indexOf(':', 0);
	var char27 = checkText.indexOf(';', 0);
	var char28 = checkText.indexOf('?', 0);
	var char29 = checkText.indexOf('/', 0);
	var char30 = checkText.indexOf('.', 0);
	var char31 = checkText.indexOf('>', 0);		
	var char32 = checkText.indexOf('<', 0);		
	var char33 = checkText.indexOf(',', 0);		

	if(char1 != -1 || char2 != -1 || char3 != -1 || char4 != -1 || char5 != -1 ||
		char6 != -1 || char7 != -1 || char8 != -1 || char9 != -1 || char10 != -1 ||
		char11 != -1 || char12 != -1 || char13 != -1 || char14 != -1 || 
		char16 != -1 || char17 != -1 || char18 != -1 || char19 != -1 || char20 != -1 ||
		char21 != -1 || char23 != -1 || char24 != -1 || char25 != -1 ||
		char26 != -1 || char27 != -1 || char28 != -1 || char29 != -1 || char30 != -1 ||
		char31 != -1 || char32 != -1 || char33 != -1)
	{
		alert("특수문자는 등록할 수 없습니다. 다시 입력해 주십시오.");
		obj.focus();
		obj.select();
		
		return;
	}
}

/*********************************************************
  내    용: 3개월전 날짜를 가져옴
  파라미터: 
  리 턴 값: true(길이초과), false(정상)
  참고사항: 없음
 *********************************************************/
function gf_common_3MonthPostDate()
{
	today = new Date();

	var Y = today.getFullYear();
	var M = today.getMonth() - 2;
	var D = today.getDate();

	if(M < 10)
	{
		switch(M)
		{
		  case -2:
				M = "10";
				Y = Y-1;
				break;
			case -1:
				M = "11";
				Y = Y-1;
				break;
			case 0:
				M = "12";
				Y = Y-1;
				break;
			case 1:
				M = "01";
				break;
			case 2:
				M = "02";
				break;
			case 3:
				M = "03";
				break;
			case 4:
				M = "04";
				break;
			case 5:
				M = "05";
				break;
			case 6:
				M = "06";
				break;
			case 7:
				M = "07";
				break;
			case 8:
				M = "08";
				break;
			case 9:
				M = "09";
				break;
		}
	}
	if(D < 10)
	{
		switch(D)
		{
			case 1:
				D = "01";
				break;
			case 2:
				D = "02";
				break;
			case 3:
				D = "03";
				break;
			case 4:
				D = "04";
				break;
			case 5:
				D = "05";
				break;
			case 6:
				D = "06";
				break;
			case 7:
				D = "07";
				break;
			case 8:
				D = "08";
				break;
			case 9:
				D = "09";
				break;
		}
	}	

	var rtDate = Y + "" + M + "" + D;
	
	return rtDate;
}

/*********************************************************
  내    용: 2개월전 날짜를 가져옴
  파라미터: 
  리 턴 값: true(길이초과), false(정상)
  참고사항: 없음
 *********************************************************/
function gf_common_2MonthPostDate()
{
	today = new Date();

	var Y = today.getFullYear();
	var M = today.getMonth() - 1;
	var D = today.getDate();


	if(M < 10)
	{
		switch(M)
		{
			case -1:
				M = "11";
				Y = Y-1;
				break;
			case 0:
				M = "12";
				Y = Y-1;
				break;
			case 1:
				M = "01";
				break;
			case 2:
				M = "02";
				break;
			case 3:
				M = "03";
				break;
			case 4:
				M = "04";
				break;
			case 5:
				M = "05";
				break;
			case 6:
				M = "06";
				break;
			case 7:
				M = "07";
				break;
			case 8:
				M = "08";
				break;
			case 9:
				M = "09";
				break;
		}
	}
	if(D < 10)
	{
		switch(D)
		{
			case 1:
				D = "01";
				break;
			case 2:
				D = "02";
				break;
			case 3:
				D = "03";
				break;
			case 4:
				D = "04";
				break;
			case 5:
				D = "05";
				break;
			case 6:
				D = "06";
				break;
			case 7:
				D = "07";
				break;
			case 8:
				D = "08";
				break;
			case 9:
				D = "09";
				break;
		}
	}	

	var rtDate = Y + "" + M + "" + D;
	return rtDate;
}
/*********************************************************
  내    용: 오늘날짜를 가져옴
  파라미터: 
  리 턴 값: true(길이초과), false(정상)
  참고사항: 없음
 *********************************************************/
function gf_TodayDate()
{
	today = new Date();

	var Y = today.getFullYear();
	var M = today.getMonth() + 1;
	var D = today.getDate();

	if(M < 10)
	{
		switch(M)
		{
			case 1:
				M = "01";
				break;
			case 2:
				M = "02";
				break;
			case 3:
				M = "03";
				break;
			case 4:
				M = "04";
				break;
			case 5:
				M = "05";
				break;
			case 6:
				M = "06";
				break;
			case 7:
				M = "07";
				break;
			case 8:
				M = "08";
				break;
			case 9:
				M = "09";
				break;
		}
	}
	if(D < 10)
	{
		switch(D)
		{
			case 1:
				D = "01";
				break;
			case 2:
				D = "02";
				break;
			case 3:
				D = "03";
				break;
			case 4:
				D = "04";
				break;
			case 5:
				D = "05";
				break;
			case 6:
				D = "06";
				break;
			case 7:
				D = "07";
				break;
			case 8:
				D = "08";
				break;
			case 9:
				D = "09";
				break;
		}
	}	

	var rtDate = Y + "" + M + "" + D;
	
	return rtDate;
}
/*********************************************************
  내    용: 트리 화면의 디자인 HTML스크립트를 생성한다.
  파라미터: gubn   - table처음 혹은 끝
  리 턴 값: 없음
  참고사항: 
 *********************************************************/
function gf_TitleImage(gubn) {

    switch(gubn) {
      case "CI":			// 토목
        document.write('<img src="/tdms/images/directory/item01.gif" border="0">');
        break;        
      case "RA":			// 보선
        document.write('<img src="/tdms/images/directory/item02.gif" border="0">');
        break;
		  case "AR":			// 건축
        document.write('<img src="/tdms/images/directory/item03.gif" border="0">');
        break;
		  case "EA":			// 변전
        document.write('<img src="/tdms/images/directory/item05_01.gif" border="0">');
        break;
		  case "EB":			// 수송배전
	      document.write('<img src="/tdms/images/directory/item05_02.gif" border="0">');
	      break;
		  case "EC":			// 전차선
	      document.write('<img src="/tdms/images/directory/item05_04.gif" border="0">');
	      break;
		  case "ER":			// 일반전기
	      document.write('<img src="/tdms/images/directory/item05_03.gif" border="0">');
	      break;
		  case "FA":			// 기계설비
	      document.write('<img src="/tdms/images/directory/item04_01.gif" border="0">');
	      break;
		  case "FB":			// 전기설비
	      document.write('<img src="/tdms/images/directory/item04_02.gif" border="0">');
	      break;
		  case "SI":			// 신호
	      document.write('<img src="/tdms/images/directory/item06.gif" border="0">');
	      break;
		  case "TE":			// 통신
	      document.write('<img src="/tdms/images/directory/item07.gif" border="0">');
	      break;
		  case "RM":			// 혁신정보
	      document.write('<img src="/tdms/images/directory/item11.gif" border="0">');
	      break;
		  case "EL":			// 역무자동화
	      document.write('<img src="/tdms/images/directory/item08.gif" border="0">');
	      break;
		  case "SC":			// 시스템제어
	      document.write('<img src="/tdms/images/directory/item09.gif" border="0">');
	      break;
		  case "MT":			// 운영관제
	      document.write('<img src="/tdms/images/directory/item10.gif" border="0">');
	      break;
		  case "OS":			// 운영지원
	      document.write('<img src="/tdms/images/directory/item12.gif" border="0">');
	      break;
		  case "VD":			// 차량
	      document.write('<img src="/tdms/images/directory/item13.gif" border="0">');
	      break;
    }
}
/*********************************************************
  내    용: TAB콘트롤(TAB기능을 하는 공통메소드)
  파라미터: tabID     - table처음 혹은 끝
            divHeight - tab버튼과 연결된 div객체의 높이(height)
            feature   - div객체명:탭명(div객체와 tab버튼간의 연결을 get방식으로 기술)
                        예) "divTab1=첫번째탭,divTab2=두번째탭"
  리 턴 값: 없음
  참고사항: 화면에 TAB HTML소스를 생성하며
            TAB에 대한 Attribute, Method, Event Method를 생성한다.
            Attribute     : index(현재 선택된 TAB버튼 index)
            Attribute     : clickIndex(클릭한 TAB버튼 index)
            Attribute     : count(TAB버튼의 갯수)
            Attribute     : id(TAB의 ID)
            Attribute     : title(TAB버튼의 타이틀명 배열)
            Attribute     : divID(TAB버튼과 연결된 DIV객체의 ID 배열)
            Method        : changeTab(tab의 선택을 바꾼다)
            Method        : changeTitle(tab의 선택을 바꾼다)
            Event Method  : onChange(tab이 변경될 때 호출되는 이벤트 메소드)
 *********************************************************/
function gf_makeTab(tabID, divHeight, feature)
{
  if(tabID == null || tabID == "" || feature == null || feature =="") return;

  var gubn = "";
  var featureValues = feature.split(",");
  
  /************************************************
                  tab HTML생성
   *************************************************/
  document.write('<table width="" border="0" cellspacing="0" cellpadding="0"> ');
  document.write('  <tr> ');
  
  for(i=1; i<=featureValues.length; i++) {
    document.write('    <td style="cursor: hand" onClick= "'+ tabID +'.changeTab('+ i +');"> ');
    document.write('      <div id='+ tabID +'_'+ i +'> ');
    if(i == 1) gubn = "choice";
    else if(i != featureValues.length) gubn = "middle";
    else if(i == featureValues.length) gubn = "end";
    document.write(gf_tabTable(gubn, tabID, gf_strCut(featureValues[i-1],"=",2), i));
    document.write('      </div> ');
    document.write('    </td> ');
  }

  document.write('  </tr> ');
  document.write('  </table> ');
  
  /************************************************
        tab 멤버(속성,메소드,이벤트메소드)생성
   *************************************************/
  document.write('<script language="javascript"> ');
  document.write('var '+ tabID +'= ""; ');
  document.write(tabID +' = new gf_tabMember('+ tabID +', "'+ tabID +'"); ');
  document.write(tabID +'.index = 1; ');
  document.write(tabID +'.count = '+ featureValues.length +'; ');
  document.write(tabID +'.height = '+ divHeight +'; ');
  document.write('go_tabList[go_tabList.length] = '+ tabID +'; ');
  
  for(i=0; i<featureValues.length; i++) {
    document.write(tabID+ '.title['+ i +'] = "'+ gf_strCut(featureValues[i],"=",2) +'"; '); //TAB Title명 배열  셋팅
    document.write(tabID+ '.divID['+ i +'] = "'+ gf_strCut(featureValues[i],"=",1) +'"; '); //TAB div id명 배열 셋팅
  }  
  document.write('</script> ');
  
}
/*************************************************************
  내    용: tab버튼 생성
  파라미터: gubn      - 처음(start), 중간(middle), 끝(end), 선택(choicd)된 상태 구분
            tabID     - tab의 ID
            tab_title - tab버튼 타이틀명
            tba_index - tab버튼의 index
  리 턴 값: string - tab버튼 HTML String
  참고사항: 없음
 *************************************************************/
function gf_tabTable(gubn, tabID, tab_title, tab_index)
{
  var tabTableHtml = "";
  
  if(gubn == "choice") {
    tabTableHtml += '<table width="100%" border="0" cellspacing="0" cellpadding="0"> ';
    tabTableHtml += '  <tr> ';
    tabTableHtml += '    <td><img src="/tdms/images/tab/tab_v1.gif" width="3"></td> ';
    tabTableHtml += '    <td align="center" background="/tdms/images/tab/tab_v2.gif"><font size="2"><strong><font color="#FFFFFF" id='+ tabID +'_name'+ tab_index +'> &nbsp;&nbsp;'+ tab_title +'&nbsp;&nbsp;</font></strong></font></td> ';
    tabTableHtml += '    <td><img src="/tdms/images/tab/tab_v3.gif" width="5"></td> ';
    tabTableHtml += '  </tr> ';
    tabTableHtml += '</table> ';
    
  } //if문의 끝
  else if(gubn == "start") {
    tabTableHtml += '<table width="100%" border="0" cellspacing="0" cellpadding="0"> ';
    tabTableHtml += ' <tr> ';
    tabTableHtml += '   <td><img src="/tdms/images/tab/blank.gif" width="2" height="4"></td> ';
    tabTableHtml += '   <td><img src="/tdms/images/tab/blank.gif" width="2" height="4"></td> ';
    tabTableHtml += '   <td><img src="/tdms/images/tab/blank.gif" width="2" height="4"></td> ';
    tabTableHtml += ' </tr> ';
    tabTableHtml += ' <tr> ';
    tabTableHtml += '   <td><img src="/tdms/images/tab/tab_df1.gif" width="2"></td> ';
    tabTableHtml += '   <td align="center" background="/tdms/images/tab/tab_df2.gif"><font id='+ tabID +'_name'+ tab_index +' size="2"> &nbsp;&nbsp;'+ tab_title +'&nbsp;&nbsp;</font></td> ';
    tabTableHtml += '   <td><img src="/tdms/images/tab/tab_dc1.gif" width="2"></td> ';
    tabTableHtml += ' </tr> ';
    tabTableHtml += '</table> ';
  } //else if문의 끝
  else {
    tabTableHtml += '<table width="100%" border="0" cellspacing="0" cellpadding="0"> ';
    tabTableHtml += ' <tr> ';
    tabTableHtml += '   <td><img src="/tdms/images/tab/blank.gif" width="2" height="4"></td> ';
    tabTableHtml += '   <td><img src="/tdms/images/tab/blank.gif" width="2" height="4"></td> ';
    tabTableHtml += ' </tr> ';
    tabTableHtml += ' <tr> ';  

    switch(gubn) {
      case "middle":
        tabTableHtml += '   <td align="center" background="/tdms/images/tab/tab_df2.gif"><font id='+ tabID +'_name'+ tab_index +' size="2"> &nbsp;&nbsp;'+ tab_title +'&nbsp;&nbsp;</font></td> ';
        tabTableHtml += '   <td><img src="/tdms/images/tab/tab_dc1.gif" width="2"></td> ';
        break;
      case "end":
        tabTableHtml += '   <td align="center" background="/tdms/images/tab/tab_df2.gif"><font id='+ tabID +'_name'+ tab_index +' size="2"> &nbsp;&nbsp;'+ tab_title +'&nbsp;&nbsp;</font></td> ';
        tabTableHtml += '   <td><img src="/tdms/images/tab/tab_dl1.gif"></td> ';
        break;    
    }
    tabTableHtml += ' </tr> ';
    tabTableHtml += '</table> ';
    
  } //else문의 끝
  
  return tabTableHtml;
}


/*************************************************************
  내    용: tab버튼 멤버속성, 메소드, 이벤트메소드 생성
  파라미터: oTab    - 탭 오브젝트
            sTabID  - 탭의 ID(string)
  리 턴 값: 없음
  참고사항: 없음
 *************************************************************/
function gf_tabMember(oTab, sTabID)
{
  /* Attribute */
  this.id     = sTabID;
  this.oTab   = oTab;
  this.index  = 1;
  this.clickIndex  = 1;
  this.count  = 0;
  this.height = 0;
  this.title  = new Array();
  this.divID  = new Array();
  
  /* 메소드 */
  this.init         = gf_tabInit;
  this.changeTab    = gf_tabChange;
  this.changeTitle  = gf_tabChangeTitle;
  
  /* 이벤트 메소드 */
  document.write('<script language="javascript"> ');
  document.write('var '+ sTabID +'_onChange; ');
  document.write('</script> ');
  
  this.onChange = window[sTabID +"_onChange"];
}

/*************************************************************
  내    용: tab초기화 메소드
  파라미터: 없음
  리 턴 값: 없음
  참고사항: 없음
 *************************************************************/
function gf_tabInit()
{
  for(i=0; i<this.count; i++) {
//    if(i>0) window[this.divID[i]].style.display = "none";
    if((i+1) != this.index) window[this.divID[i]].style.display = "none";
    else    window[this.divID[i]].style.display = "";

    window[this.divID[i]].style.height = this.height;
    window[this.divID[i]].className = "div_tab";
  }
  
}

/*************************************************************
  내    용: tab선택 변경 메소드
  파라미터: tab_index - 선택된 상태로 바꿀 탭의 인덱스
  리 턴 값: 없음
  참고사항: 없음
 *************************************************************/
function gf_tabChange(tab_index)
{
  gs_tab_index = tab_index;
  this.clickIndex  = tab_index;
  if(this.onChange != undefined) {    
    if(!this.onChange()) { this.clickIndex  = this.index; return; }
  }
  this.index = tab_index;
  
  var gubn    = "";
  var tabHtml = "";
  
  for(i=1; i<=this.count; i++) {
    window[this.divID[i-1]].style.display="none";
    
    if(i == tab_index) {
      gubn = "choice";
      window[this.divID[i-1]].style.display="";
    }
    else if(i == 1) gubn = "start";
    else if(i == this.count) gubn = "end";
    else gubn = "middle";
    
    tabHtml = gf_tabTable(gubn, this.id, this.title[i-1], i);
    window[this.id +"_"+ i].innerHTML = tabHtml;
  }  
    
}

/*************************************************************
  내    용: tab 타이틀 변경 메소드
  파라미터: tab_index - 타이틀을 바꿀 탭의 인덱스
            tab_name  - 타이틀명
  리 턴 값: 없음
  참고사항: 없음
 *************************************************************/
function gf_tabChangeTitle(tab_index, tab_name)
{
  this.title[tab_index -1] = tab_name;
  tab_name = "&nbsp;&nbsp;"+ tab_name +"&nbsp;&nbsp;";
  document.all[this.id +"_name"+ tab_index].innerHTML = tab_name;
}
/**************************************************************
  내    용: 특정문자의 앞이나 뒤 문자열 반환
  파라미터: sourceStr   - 대상 문자열
            specialChar - 특정문자
            gubn        - 1(앞), 2(뒤)
  리 턴 값: string - 결과 문자열
  참고사항: 없음
 **************************************************************/
function gf_strCut(sourceStr, specialChar, gubn)
{
  var strPos = sourceStr.search(specialChar);
  
  if(strPos == -1) return "";
  if(gubn == 1) return sourceStr.substring(0, strPos);
  else return sourceStr.substring(strPos+1, sourceStr.length);
}
/*************************************************************
  내    용: 세션연결체크 메소드
  파라미터: session_index - 세션연결체크할 메소드
            gubun - 메인화면인지 팝업창인지 구분할 메소드
  리 턴 값: 없음
  참고사항: 없음
 *************************************************************/
function gf_session(session_index, gubn)
{
  if(session_index == 'null')
  {
	  if(gubn == 1)
	  {
	  }
  }
	
  return result;
}
/*********************************************************
  내    용: 그리드의 데이타를 엑셀파일로 생성하는 기능
  파라미터: objGrid   - 대상 그리드
            titleStr  - 시트명, 타이틀, 서브타이틀, 일자
            optionStr - 엑셀옵션(supress여부, hidden 컬럼 표시여부 셋팅)
  리 턴 값: 없음
  참고사항: 없음
 *********************************************************/
function gf_gridToExcel(objGrid, titleStr, optionStr)
{
  var file_path  = "자료목록.xls"; //Default파일명을 Utims.xls로 셋팅한다.
  var lOption = 0;
 
  /* 시트명, 타이틀, 서브타이틀 셋팅 */
  if ((titleStr != null) && (titleStr != "")) 
  {
	  titleValues = titleStr.split(",");
  }
  var sheet_name  = titleValues[0]; //시트명
  var ls_title    = "value:"+titleValues[1]+"; font-face:'굴림'; font-size:30pt; font-color:black;font-bold; font-underline; bgcolor:white; align:center; line-color:red;line-width:2pt; skiprow:1;";
  var ls_subTitle = "value:"+titleValues[2]+"; font-face:'굴림'; font-size:18pt; font-color:black; bgcolor:#99ffff; align:center; line-color:blue; line-width:1pt; skiprow:1;";
  
  if((sheet_name == "") || (sheet_name == null)) 
  {
	  sheet_name="자료목록";
  }
  objGrid.SetExcelTitle(0, "");
  if(titleValues[2] != "" && titleValues[2] != null) {
    objGrid.SetExcelTitle(1, ls_title);
    file_path = gf_delSpecialChar(titleValues[2]);  //파일명 셋팅(특수문자를 제거한)
  }
  else if(titleValues[1] != "" && titleValues[1] != null) {
    file_path = gf_delSpecialChar(titleValues[1]);  //파일명 셋팅(특수문자를 제거한)
  }
  
  if(titleValues[2] != "" && titleValues[2] != null)
    objGrid.SetExcelTitle(1, ls_subTitle);
  
  /* 그리드 출력 옵션 셋팅 */
  if ((optionStr != null) && (optionStr != "")) 
  {
	  optionValues = optionStr.split(",");
	
  }
  if(optionValues[0] != null && optionValues[0] != "" && optionValues[0]==true) lOption += optionValues[0]; //Supress
  if(optionValues[1] != null && optionValues[1] != "" && optionValues[1]==true) lOption += optionValues[1]; //Show False인 칼럼 출력
  
  lOption += 4;   //Excel을 화면에 띄우지 않고 파일로만 저장
  lOption += 8;   //Excel파일로 저장
  lOption += 16;  //파일저장시 대화상자 띄움
//lOption += 32;  //Excel파일의 워크시트에 추가

  objGrid.GridToExcel(sheet_name, file_path, lOption);
}
/*********************************************************
  내    용: 파일명에 들어 갈 수 없는 특수문자 제거
  파라미터: ls_fileName
  리 턴 값: 특수문자를 제거한 String에 "_일자"를 추가하여 리턴
  참고사항: 파일명으로 사용할 수 없는 특수문자는 다음과 같다
            \ / : * ? " <> |
 *********************************************************/
function gf_delSpecialChar(ls_fileName)
{
  /* 오늘 날짜를 구한다 */
  var ls_date  = new Date();
  var ls_year  = ls_date.getYear();
  var ls_month = (ls_date.getMonth())+1;
  var ls_day   = ls_date.getDate();  
  
  /* 구한 날짜의 달과 일이 1자리수 이면 2자리로 만든다 */
  if(ls_month < 10) ls_month = "0"+ls_month;
  if(ls_day < 10)   ls_day   = "0"+ls_day;
  
  var ls_char   = ""; //한문자를 임시로 담을 변수
  var ls_rtnStr = ""; //특수문자를 제거한 문자열을 담을 변수
  
  for(i=0; i<ls_fileName.length; i++) {
    ls_char = ls_fileName.charAt(i);
    if(ls_char == '/' ||
       ls_char == ':' ||
       ls_char == '*' ||
       ls_char == '?' ||
       ls_char == '"' ||
       ls_char == '<' ||
       ls_char == '>' ||
       ls_char == '|') continue;
     
     ls_rtnStr += ls_char;
  }
  return (ls_rtnStr + "_" + ls_year+ls_month+ls_day);
  
}



/* 도움말 호출을 위한 함수들 */

/*********************************************************
  내    용: 각 화면에 맞는 도움말 화면을 띠우는 함수
  파라미터: bigGrupCd   - 도면 분야
            page  - 도움말 Map ID
  리 턴 값: 없음
  참고사항: 없음
 *********************************************************/
function gf_Help(bigGrupCd, page)
{  
  var path = null;

  switch (bigGrupCd)
  {
	  case "CI" : path="/utims/help/fo/ci/UTIMS_Help_FO_CI.htm>Main";
		break;
	  case "AR" : path="/utims/help/fo/ar/UTIMS_Help_FO_AR.htm>Main";
		break;
	  case "EA" : path="/utims/help/fo/ea/UTIMS_Help_FO_EA.htm>Main";
		break;
	  case "EB" : path="/utims/help/fo/eb/UTIMS_Help_FO_EB.htm>Main";
		break;
	  case "EC" : path="/utims/help/fo/ec/UTIMS_Help_FO_EC.htm>Main";
		break;
	  case "ED" : path="/utims/help/fo/ed/UTIMS_Help_FO_ED.htm>Main";
		break;
	  case "EL" : path="/utims/help/fo/el/UTIMS_Help_FO_EL.htm>Main";
		break;
	  case "FA" : path="/utims/help/fo/fa/UTIMS_Help_FO_FA.htm>Main";
		break;
	  case "RA" : path="/utims/help/fo/ra/UTIMS_Help_FO_RA.htm>Main";
		break;
	  case "SI" : path="/utims/help/fo/si/UTIMS_Help_FO_SI.htm>Main";
		break;
	  case "TE" : path="/utims/help/fo/te/UTIMS_Help_FO_TE.htm>Main";
		break;		
  }	
  
  RH_ShowHelp(0, path, HH_HELP_CONTEXT, page);
}

var gbNav6=false;
var gbNav61=false;
var gbNav4=false;
var gbIE4=false;
var gbIE=false;
var gbIE5=false;
var gbIE55=false;

var gAgent=navigator.userAgent.toLowerCase();
var gbMac=(gAgent.indexOf("mac")!=-1);
var gbSunOS=(gAgent.indexOf("sunos")!=-1);
var gbOpera=(gAgent.indexOf("opera")!=-1);

var HH_DISPLAY_TOPIC = 0;
var HH_DISPLAY_TOC = 1;
var HH_DISPLAY_INDEX = 2;
var HH_DISPLAY_SEARCH = 3;
var HH_HELP_CONTEXT = 15;

var gVersion=navigator.appVersion.toLowerCase();

var gnVerMajor=parseInt(gVersion);
var gnVerMinor=parseFloat(gVersion);

gbIE=(navigator.appName.indexOf("Microsoft")!=-1);
if(gnVerMajor>=4)
{
  if(navigator.appName=="Netscape")
  {
    gbNav4=true;
    if(gnVerMajor>=5)
      gbNav6=true;
  }
  gbIE4=(navigator.appName.indexOf("Microsoft")!=-1);
}
if(gbNav6)
{
  document.gnPageWidth=innerWidth;
  document.gnPageHeight=innerHeight;
  var nPos=gAgent.indexOf("netscape");
  if(nPos!=-1)
  {
    var nVersion=parseFloat(gAgent.substring(nPos+10));
    if(nVersion>=6.1)
      gbNav61=true;
  }
}else if(gbIE4)
{
  var nPos=gAgent.indexOf("msie");
  if(nPos!=-1)
  {
    var nVersion=parseFloat(gAgent.substring(nPos+5));
    if(nVersion>=5)
      gbIE5=true;
    if(nVersion>=5.5)
      gbIE55=true;
  }
}

function RH_ShowHelp(hParent, a_pszHelpFile, uCommand, dwData)
{  
  // this function only support WebHelp
  var strHelpPath = a_pszHelpFile;
  var strWnd = "";
  var nPos = a_pszHelpFile.indexOf(">");
  if (nPos != -1)
  {
    strHelpPath = a_pszHelpFile.substring(0, nPos);
    strWnd = a_pszHelpFile.substring(nPos+1); 
  }
  if (isServerBased(strHelpPath))
    RH_ShowWebHelp_Server(hParent, strHelpPath, strWnd, uCommand, dwData);
  else
    RH_ShowWebHelp(hParent, strHelpPath, strWnd, uCommand, dwData);
}

function RH_ShowWebHelp_Server(hParent, strHelpPath, strWnd, uCommand, dwData)
{
  // hParent never used.
  ShowWebHelp_Server(strHelpPath, strWnd, uCommand, dwData);
}

function RH_ShowWebHelp(hParent, strHelpPath, strWnd, uCommand, dwData)
{
  // hParent never used.
  ShowWebHelp(strHelpPath, strWnd, uCommand, dwData);
}


function ShowWebHelp_Server(strHelpPath, strWnd, uCommand, nMapId)
{
  var a_pszHelpFile = "";
  if (uCommand == HH_HELP_CONTEXT)
  {
    if (strHelpPath.indexOf("?") == -1)
      a_pszHelpFile = strHelpPath + "?ctxid=" + nMapId;
    else
      a_pszHelpFile = strHelpPath + "&ctxid=" + nMapId;
  }
  else
  {
    if (strHelpPath.indexOf("?") == -1)
      a_pszHelpFile = strHelpPath + "?ctxid=0";
    else
      a_pszHelpFile = strHelpPath + "&ctxid=0";
  }

  if (strWnd)
    a_pszHelpFile += ">" + strWnd;

  if (gbIE4)
  {
    a_pszHelpFile += "&cmd=newwnd&rtype=iefrm";
    loadData(a_pszHelpFile);
  }
  else if (gbNav4)
  {
    a_pszHelpFile += "&cmd=newwnd&rtype=nswnd";
    var sParam = "left="+screen.width+",top="+screen.height+",width=100,height=100";
    window.open(a_pszHelpFile, "__webCshStub", sParam);
  }
  else
  {
    var sParam = "left="+screen.width+",top="+screen.height+",width=100,height=100";
    if (gbIE5)
      window.open("about:blank", "__webCshStub", sParam);
    window.open(a_pszHelpFile, "__webCshStub");
  }
}


/* 실제 도움말 윈도우를 호출하는 메소드 */
function ShowWebHelp(strHelpPath, strWnd, uCommand, nMapId)
{
  var a_pszHelpFile = "";
  if (uCommand == HH_DISPLAY_TOPIC)
  {
    a_pszHelpFile = strHelpPath + "#<id=0";
  }
  if (uCommand == HH_HELP_CONTEXT)
  {
    a_pszHelpFile = strHelpPath + "#<id=" + nMapId;
  }
  else if (uCommand == HH_DISPLAY_INDEX)
  {
    a_pszHelpFile = strHelpPath + "#<cmd=idx";
  }
  else if (uCommand == HH_DISPLAY_SEARCH)
  {
    a_pszHelpFile = strHelpPath + "#<cmd=fts";
  }
  else if (uCommand == HH_DISPLAY_TOC)
  {
    a_pszHelpFile = strHelpPath + "#<cmd=toc";
  }
  if (strWnd)
    a_pszHelpFile += ">>wnd=" + strWnd;

  if (a_pszHelpFile)
  {
    if (gbIE4)
      loadData(a_pszHelpFile);
    else if (gbNav4)
    {
      var sParam = "left="+screen.width+",top="+screen.height+",width=100,height=100";
      window.open(a_pszHelpFile, "__webCshStub", sParam);
    }
    else
    {
      var sParam = "left="+screen.width+",top="+screen.height+",width=100,height=100";
      if (gbIE5)
        window.open("about:blank", "__webCshStub", sParam);
      window.open(a_pszHelpFile, "__webCshStub");
    }
  }
}

function isServerBased(a_pszHelpFile)
{
  if (a_pszHelpFile.length > 0)
  {
    var nPos = a_pszHelpFile.lastIndexOf('.');
    if (nPos != -1 && a_pszHelpFile.length >= nPos + 4)
    {
      var sExt = a_pszHelpFile.substring(nPos, nPos + 4);
      if (sExt.toLowerCase() == ".htm")
      {
        return false;
      }
    }
  }
  return true;
}

function getElement(sID)
{
  if(document.getElementById)
    return document.getElementById(sID);
  else if(document.all)
    return document.all(sID);
  return null;
}

/* 도움말 파일을 호출하는 펑션에서 호출하는 함수 */
function loadData(sFileName)
{
  if(!getElement("dataDiv"))
  {
    if(!insertDataDiv())
    {
      gsFileName=sFileName;
      return;
    }
  }
  var sHTML="";
  if(gbMac)
    sHTML+="<iframe name=\"__WebHelpCshStub\" src=\""+sFileName+"\"></iframe>";
  else
    sHTML+="<iframe name=\"__WebHelpCshStub\" style=\"visibility:hidden;width:0;height:0\" src=\""+sFileName+"\"></iframe>";
  
  var oDivCon=getElement("dataDiv");
  if(oDivCon)
  {
    if(gbNav6)
    {
      if(oDivCon.getElementsByTagName&&oDivCon.getElementsByTagName("iFrame").length>0)
      {
        oDivCon.getElementsByTagName("iFrame")[0].src=sFileName;
      }
      else
        oDivCon.innerHTML=sHTML;
    }
    else
      oDivCon.innerHTML=sHTML;  //해당 라인 스크립트가 실행되어 도움말을 띄운다
  }
}

function insertDataDiv()
{
  var sHTML="";
  if(gbMac)
    sHTML+="<div id=dataDiv style=\"display:none;\"></div>";
  else
    sHTML+="<div id=dataDiv style=\"visibility:hidden\"></div>";

  document.body.insertAdjacentHTML("beforeEnd",sHTML);
  return true;
}

// 문자열중 엔터키가 입력이 되면 공백으로 변경한다.
function replaceEnterKey(str){
	var trnStr = "";
	var tmpStr = str.split(String.fromCharCode(13));
	for(i=0; i<tmpStr.length; i++){
		if(i == 0){
			trnStr += tmpStr[i]+" ";
		}else{
			trnStr += tmpStr[i].substring(1, tmpStr[i].length)+" ";
		}
	}
	return trnStr;
}

function clearXSS(InStr){
    InStr = InStr.replace(/\</g,"");
    InStr = InStr.replace(/\>/g,"");
    InStr = InStr.replace(/\"/g,"");
    InStr = InStr.replace(/\'/g,"");
    InStr = InStr.replace(/\%/g,"");
    InStr = InStr.replace(/\;/g,"");
    //InStr = InStr.replace(/\(/g,"");
    //InStr = InStr.replace(/\)/g,"");
    InStr = InStr.replace(/\&/g,"");
    InStr = InStr.replace(/\+/g,"");
    return InStr;
}