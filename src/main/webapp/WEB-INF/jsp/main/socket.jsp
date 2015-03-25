<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name='author' content='ese, 이에스이' />
<title>도면 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/jQueryUI/jquery-ui-1.10.4.custom.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/jqGrid/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/contextMenu/jquery.context.css' />" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jqGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQueryUI/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jqGrid/i18n/grid.locale-${lang}.js"></script>
<script type="text/javascript" src="<c:url value='/js/contextMenu/jquery.context.js' />" ></script>
<script type="text/javascript" src="<c:url value='/js/webSocket.js' />" ></script>
<script type="text/javascript">

// {"StatEvet":{"outbPos":[{"z":"0","y":"22.99475","x":"113.78394"},{"z":"0","y":"22.99438","x":"113.78399"},{"z":"0","y":"22.99431","x":"113.78453"},{"z":"0","y":"22.99484","x":"113.78449"}],"outbPosCnt":4,"statEvetCntn":"Pangyo Seven Venture VallyEarthquake","statEvetItem":[],"statEvetActnRslt":"","outbPosNm":"Pangyo Seven Venture Vally","statEvetActnDtm":"","statEvetGdCd":"10","statEvetClrDtm":"","outbMainGb":"P","statEvetId":"413TRF005E07","statEvetNm":"Earthquake","procSt":"1","cpxRelEvetOutbSeqn":[],"outbScopRads":"","statEvetActnCntn":"","statEvetItemCnt":0,"statEvetActnMn":"","cpxRelEvetOutbSeqnCnt":0,"statEvetOutbDtm":"20150211102025","uSvcOutbId":"SIM_20150211102025"}}
// {"outbPos":"205480.343@513151.161@0#","outbPosNm":"경기도 화성시 반송동 93-11","outbDtm":"2015년 3월 11일 (수) 오후 1시 56분 05초","evetNm":"방범IP카메라","type":"FacEvet"}

$(document).ready(function() {
	$.initBinder("ws://"+window.location.hostname+":8900/demo/echo");	
});

function makeSomeNoise(data) {
	console.log(data);
}

function send() {
	_ws.send("Hello, world.");
}
</script>
</head>
<body>
</body>
</html>
