/**************************************************
    1. 파      일      명   : DiyViewer.js
    2. 업무 시스템 명    		: 뷰어에 사용되는 함수를 정의한다.
    3. 작      성      자   : 길기용(2006/08/09)
    4. 수      정      자   : 
****************************************************/

/**************************************************
  내    용 : 뷰어-화면 제어 기능
  파라미터 : 없음
  리 턴 값 : 없음
  참고사항 :  (1) ZoomIn 
						  (2) ZoomOut
						  (3) ZoomExtent
						  (4) ZoomWindow
						  (5) Pan
						  (6) HotSpot
						  (7) Print
						  (8) Layer Dlg
						  (9) Symbol Dlg
						  (10) Block Dlgw
						  (11) HotSpot Dlg
						  (12) Text Dlg
****************************************************/
var bZoomIn		= 0;
var bZoomOut	= 0;
var bZoomExts	= 0;
var bZoomWindow = 0;

var bPan		= 0;
var bHSpot		= 0;

var bPrint		= 0;
var bLayerCtl	= 0;

var bOpenSymbol = 0;
var bOpenBlock	= 0;

var bOpenHotspot	= 0;
var bOpenText		= 0;
var bOpenHyperlink	= 0;
var bPickTextData	= 0;
var bTextEdit		= 0;


function ZI()		{ bZoomIn			= send.DV.ZoomIn();}
function ZO()		{ bZoomOut			= send.DV.ZoomOut();}
function ZE()		{ bZoomExts			= send.DV.ZoomExts(); }
function ZW()		{ bZoomWindow		= send.DV.ZoomWindow(); }
function PAN()		{ bPan				= send.DV.Pan();}
function HSpot()	{ bHSpot			= send.DV.HighlightHotSpotByPick();}
function LAY()		{ bLayerCtl			= send.DV.LayerCtrl(); }		// CAD Only
function SYM()		{ bOpenSymbol		= send.DV.OpenSymbol();}		// CAD Only
//function BLK()	{ bOpenBlock		= send.DV.OpenBlock();}		// CAD Only
function BLK()		{ bOpenBlock		= send.DV.OpenBlockHL();}	// CAD Only
function LEGEND()	{ bOpenHotspot		= send.DV.OpenHotspot();	}	
//function TXT()	{ bOpenText			= send.DV.OpenText();}		// CAD Only
function TXT()		{ bOpenText			= send.DV.OpenTextHL();}		// CAD Only
function SelHyper() { bOpenHyperlink	= send.DV.OpenHyperlink();}
function PTXT()		{ bPickTextData		= send.DV.PickTextData();}	// CAD Only
function TXTEDIT()	{ bTextEdit			= send.DV.TextEdit(); }		// CAD Only
function FONT()		{ bFont				= send.DV.OpenFont(); }		// CAD Only
function LAYOUT()	{ bLayout			= send.DV.ViewSetActiveLayout(); } // CAD Only
function GETLAYER(){ send.DV.DiySetSelectDwgEntity(); }
function GETSYMBOL(){ send.DV.DiySimbolSelect(); }

function PRT(userId, drwngNo){
	var bPrint = send.DV.DiyPrintOut2(userId, drwngNo);
	
	return bPrint;
}
function CHECKLAYER(){ 
	if(document.getElementById("controlLayer").style.display == ""){
		document.getElementById("controlLayer").style.display = "none";
	}else{
		document.getElementById("controlLayer").style.display = "";
	}
}

function DIST()  { bPlot    = send.DV.DiyDist();}					// 거리 
function SELCPY()  { bPlot    = send.DV.DiyClipboard();} 	// 선택영역복사
function OpenUserControl(){send.DV.OpenUserControl();} // 유저 컨트롤
function BGCHG()		{ bPlot				= send.DV.DiyChangeBGColor();}	// 배경전환

/***************************************************************************
  내   용 : 뷰어-마킹 기능
  파라미터 : 없음
  리 턴  값 : 없음
  참고사항 : (1) Select
						 (2) Line
						 (3) Circle
						 (4) Arrow
						 (5) Rectangle
						 (6) Round Rectangle
						 (7) Poly Line
						 (8) Polygon
						 (9) Free Hand
						 (10) Text
						 (11) Memo
						 (12) Trans
						 (13) Cloud
						 (14) Hyper
						 (15) Undo
						 (16) SAno
****************************************************************************/
var bSelect = 0;
var bLine	= 0;
var bCircle = 0;
var bArrow	= 0;
var bRect	= 0;
var bRRect	= 0;
var bPLine	= 0;
var bPGon	= 0;
var bFHnd	= 0;
var bText	= 0;
var bMemo	= 0;
var bTrans	= 0;
var bClude	= 0;
var bHyper	= 0;
var bUndo	= 0;
var bSAno	= 0;

function Select()	{ bSelect	= send.DV.AnnoSelect();}
function Line()		{ bLine		= send.DV.DrawLine(); }
function Circle()	{ bCircle	= send.DV.DrawCircle();}
function Arrow()	{ bArrow	= send.DV.DrawArrow();}
function Rect()		{ bRect		= send.DV.DrawRect();}
function RRect()	{ bRRect	= send.DV.DrawRoundRect();}
function PLine()	{ bPLine	= send.DV.DrawPolyline(); }
function PGon()		{ bPGon		= send.DV.DrawPolygon();}
function FHnd()		{ bFHnd		= send.DV.DrawFreehand();}
function Text()		{ bText		= send.DV.DrawText();}
function Memo()		{ bMemo		= send.DV.DrawMemo();}
function Trans()	{ bTrans	= send.DV.DrawTranslucent();}
function Cloud()	{ bClude	= send.DV.DrawCloud();}
function Hyper()	{ bHyper	= send.DV.DrawHyperlink();}
function Undo()		{ bUndo		= send.DV.Undo(); }
function SAno()		{ bSAno		= send.DV.SaveAnno();}

function MouseOver(obj, name) {	obj.src = 	"/tdms/images/viewer/" + name + "_on.gif"; }
function MouseOut(obj, name)  {	obj.src = 	"/tdms/images/viewer/" + name + ".gif";	}


/***************************************************************************
  내    용 : 뷰어-마킹 기능
  파라미터 : 없음
  리 턴 값 : 없음
	참고사항 : (1) FtpInit : Ftp 정보초기화
						 (2) LegendInit	: Legend 레이어명 및 부품목록 구분자 설정
						 (3) OpenFile	: 로컬파일열기
						 (4) SetFtpCurrentDir	: 원격디렉토리 지정(*원격파일 열기전에 먼저 설정되어야함.)
						 (5) View	: 원격파일열기
						 (6) ViewThumbnail : Thumbnail 보기 함수
						 (7) WebToViewer : 도면상의 레젼드를 뷰어에 표현하기위한 함수
***************************************************************************/

//var sWASIp = '192.168.1.208:8888';

//var sWASIp = '61.251.28.79';

var ls_urlArray = document.URL.split("/");

var sFtpIp = ls_urlArray[2];

//alert(sFtpIp);


var ftpIp = '127.0.0.1';
//var ftpIp = '211.238.177.44';
var ftpId = 'diydocs';
var ftpPw = 'diydocs';
var ftpPort = '21';


//var ftpIp = '10.11.52.203';
//var ftpId = 'diydocs';
//var ftpPw = 'tdms1!';
//var ftpPort = '21';

//(1)
function FtpInit(){
	send.DV.DiyInitSvrInfo(ftpIp, ftpId, ftpPw, ftpPort, '0');
//	send.DV.DiyInitSvrInfo('10.11.52.203', 'diydocs', 'tdms1!', '21', '0');
	

	if(send.DV.DiyIsFtpLive()) {
		send.DV.DiySetCryption(0);
		return true;
	}else{
		alert("FTP 접속을 실패 했습니다.");
	 	return false;
	}

}

function DCInit(){
	send.DC.DiyInitSvrInfo(ftpIp, ftpId, ftpPw, ftpPort, '0');
//	send.DC.DiyInitSvrInfo('10.11.52.203', 'diydocs', 'tdms1!', '21', '0');
	if(send.DC.DiyIsFtpLive()) {
		send.DC.DiySetMakeImgThumbnail(0);
		send.DC.DiySetCryption(0); 
		return true;
	}else{
		alert("FTP 접속을 실패 했습니다.");
		return false;
	}
	
}

function uploadComInit(uploadCom){
	uploadCom.SetDbServer('ekrtdms/tdms2010@192.168.1.13:1521:orcl');
	//uploadCom.SetDbServer('ekrtdms/tdms2010@211.238.177.44:1521:etip');
//	uploadCom.SetDbServer('ekrtdms/tdms2010@10.11.52.203:1521:newtech');
	/*
	 * param4 - 0:Active, 1:Passive 
	 */
	uploadCom.SetFtpHost(ftpIp, ftpId, ftpPw, 1, 21);
}


function LegendInit()	
{
	send.DV.InitLegend("legend", "   "); 
}


/**************************************************
//(7)
function WebToViewer()	
{
	var legd = legend.value;	send.DV.WebToViewer(legd);
}

//(?)
function GetHyperFileName() 
{
	hyperfname = "hyper.dwg";	
	send.DV.Hyperlink(hyperfname, hyperfileindex);
}
*******************************************/

/***************************************************************************
  내    용 : 화일(도면/이미지) 관련
  파라미터 : 없음
  리 턴 값 : 없음
	참고사항 : (1) MakeThumbnail	: Thumbnail bmp 파일 만들기
						 (2) MakeDwf : Dwf 파일 만들기
/***************************************************************************/
//(1)
function MakeThumbnail()	
{
	send.DV.MakeThumbnailBmpFile();
}

//(2)
function MakeDwf()			
{
	send.DV.MakeDwfFile(); 
}

/***************************************************************************
  내    용 : Viewer Object Tag 스크립트 처리
  파라미터 : 없음
  리 턴 값 : 없음
	참고사항 : 없음
***************************************************************************/
function displayDiyViewerTag()
{
	var sTag = "";

	sTag = sTag + ' <OBJECT ';
	sTag = sTag + '	  classid="clsid:FF6E4932-8B5E-4CBE-91AC-B62140F5D665" ';
	sTag = sTag + '	  codebase="/tdms/component/DiyView.cab#version=1,1,1,2"';
	sTag = sTag + '	  name="DV" ';
	sTag = sTag + '	  width=100% ';
	sTag = sTag + '	  height=100% ';
	sTag = sTag + '	  hspace=0 ';
	sTag = sTag + '	  vspace=0 ';
	sTag = sTag + '	  align=center> ';
	sTag = sTag + ' </OBJECT> ';   	
	
	//alert(sTag);
	document.write(sTag);
}

/***************************************************************************
  내    용 : Comuse Object Tag 스크립트 처리
  파라미터 : 없음
  리 턴 값 : 없음
	참고사항 : 없음
***************************************************************************/
function displayComuseTag()
{
	var sTag = "";

	sTag = sTag + ' <OBJECT ';
	sTag = sTag + '	  classid="clsid:1B9D18B9-5B41-4A88-8DB7-D9DE2156B388" ';
	sTag = sTag + '	  codebase="/tdms/component/DiyComm.cab#version=1,0,0,43"';
	sTag = sTag + '	  name="DC" ';
	sTag = sTag + '	  width=0 ';
	sTag = sTag + '	  height=0 ';
	sTag = sTag + '	  hspace=0 ';
	sTag = sTag + '	  vspace=0 ';
	sTag = sTag + '	  align=center> ';
	sTag = sTag + ' </OBJECT> ';   	
	
	//alert(sTag);
	document.write(sTag);
}

/***************************************************************************
  내    용 : GPS 정보얻기 
  파라미터 : 없음
  리 턴 값 : 없음
	참고사항 : 없음
***************************************************************************/
function FindXY()
{
	var dx, dy;
	if(dx == "" && dy == "")  {
		alert("X, Y 좌표를 입력");
		return;
	}
	dx = 236830;
 	dy = 330050;

	send.DV.FindCADLocation(dx, dy);
}

/***************************************************************************
  내    용 : GPS좌표 view/hidden 처리
  파라미터 : 없음
  리 턴 값 : 없음
	참고사항 : 없음
***************************************************************************/
function FindCADTog()
{
	send.DV.ToggleFindCadLocFlag();
}

/***************************************************************************
  내    용 : 도면형태가 이미지인경우와 도면인 경우에 대하여 기능 버는 활성화제어
  파라미터 : 파일이름
  리 턴 값 : 없음
	참고사항 : 없음
***************************************************************************/
function SetFunInit(fname)
{
	if(fname.indexOf("dwg") > 0 || fname.indexOf("DWG") > 0 ||
	   fname.indexOf("dxf") > 0 || fname.indexOf("DXF") > 0 ||
	   fname.indexOf("dwf") > 0 || fname.indexOf("DWF") > 0 ) { // 도면인경우
		document.layer.style.display	= "";
		document.symdlg.style.display	= "";
		document.blkdlg.style.display	= "";
		document.txtdlg.style.display	= "";
		document.textsel.style.display	= "";
		document.textwork.style.display = "";
		document.legend.style.display	= "";
		document.gps.style.display		= "";
		document.layout.style.display	= "";
		document.checkLayer.style.display	= "";
		document.symselet.style.display	= "";
	} else {													// 이미지
		document.layer.style.display	= "none";
		document.symdlg.style.display	= "none";
		document.blkdlg.style.display	= "none";
		document.txtdlg.style.display	= "none";
		document.textsel.style.display	= "none";
		document.textwork.style.display = "none";
		document.legend.style.display	= "none";
		document.gps.style.display		= "none";
		document.layout.style.display	= "none";
		document.checkLayer.style.display	= "none";
		document.symselet.style.display	= "none";
	}

}

