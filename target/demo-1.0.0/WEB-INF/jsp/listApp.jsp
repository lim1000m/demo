<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html><meta charset="UTF-8">
<head>
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/css/jquery/jquery-ui-1.10.3.custom.css '/> " />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/main.css '/> " />
<script type="text/javascript" src="<c:url value='/js/lib/jquery-1.10.2.js' /> "></script>
<script type="text/javascript" src="<c:url value='/js/lib/jquery-ui-1.10.3.custom.js' /> "></script>
<script type="text/javascript" src="<c:url value='/js/facebook.js' /> "></script>
<script type="text/javascript">
$(document).ready( function() {
	var canvas = document.getElementById("drawCanvas");
	var context = canvas.getContext("2d");	
});

function drag(target, e) {		//드래그 시작시 호출 할 함수
	e.dataTransfer.setData('Text', target.id);
}
function drop(target, e) {		//드롭시 호출 할 함수
	var id = e.dataTransfer.getData('Text');
	target.appendChild(document.getElementById(id));
}
</script>
</head>
<body>
	<div id="fb-root"></div>
	<div id="header">메뉴나 뭐 기타</div>
	<div id="container">
		<canvas id="drawCanvas_1" ondrop="drop(this, event)" ondragenter="return false;" ondragover="return false;"></canvas>
		<canvas id="drawCanvas_2" ondrop="drop(this, event)" ondragenter="return false;" ondragover="return false;"></canvas>
		<canvas id="drawCanvas_3" ondrop="drop(this, event)" ondragenter="return false;" ondragover="return false;"></canvas>
		<canvas id="drawCanvas_4" ondrop="drop(this, event)" ondragenter="return false;" ondragover="return false;"></canvas>
	</div>
	<div id="facebook">
		<div>
			<a href="#" onclick="fb_login();"><img src="<c:url value='/images/login/facebook.png' />" width='40px' height='40px' style='cursor:pointer;' /></a>
			<span><img src="<c:url value='/images/login/twitter.png' />" width='40px' height='40px' /></span>
			<span><img src="<c:url value='/images/login/instagram.png' />" width='40px' height='40px' /></span>
			<span><img src="<c:url value='/images/login/self.png' />" width='40px' height='40px' /></span>
		</div>
		<div id="photoArea"></div>
	</div>
	<div id="footer">카피라이터</div>
</body>
</html>
