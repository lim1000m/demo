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
<title>DIYDOCS</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/css/jQueryUI/jquery-ui-1.10.4.custom.css'/> "/>
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/css/jqGrid/ui.jqgrid.css'/>"/>
<script type="text/javascript" src="<c:url value='/js/jquery-1.10.2.js'/> "></script>
<script type="text/javascript" src="<c:url value='/js/jqGrid/jquery.jqGrid.min.js'/> "></script>
<script type="text/javascript" src="<c:url value='/js/jQueryUI/jquery-ui-1.10.4.custom.js'/> "></script>
<script type="text/javascript" src="<c:url value='/js/jqGrid/i18n/grid.locale-${lang}.js'/> "></script>
<script type="text/javascript" src="<c:url value='/js/jquery.form.js' /> "></script>
<script>

function login() {
	$("#login").ajaxSubmit({
		 success : function(data) {
			if(data.status != 'N')
				window.location="<c:url value='/drwng/drwng.do' />";
			else
				alert("입력하신 정보가 잘못되었습니다.");
		 },
		 error : function(error) {
			 alert("입력하신 정보가 잘못되었습니다.");
		 }
	 });	
}
</script>
<style>
/* 버튼 */
	.button {
		display: inline-block;
		outline: none;
		cursor: pointer;
		text-align: center;
		text-decoration: none;
		font: 14px/100% Arial, Helvetica, sans-serif;
		padding: 5px 10px 5px 10px;
		text-shadow: 0 1px 1px rgba(0,0,0,.3);
		-webkit-border-radius: .5em; 
		-moz-border-radius: .5em;
		border-radius: .5em;
		-webkit-box-shadow: 0 1px 2px rgba(0,0,0,.2);
		-moz-box-shadow: 0 1px 2px rgba(0,0,0,.2);
		box-shadow: 0 1px 2px rgba(0,0,0,.2);
	}
	.button:hover {
		text-decoration: none;
	}
	.button:active {
		position: relative;
		top: 1px;
	}

	.orange {
		color: #fef4e9;
		border: solid 1px #da7c0c;
		background: #f78d1d;
		background: -webkit-gradient(linear, left top, left bottom, from(#faa51a), to(#f47a20));
		background: -moz-linear-gradient(top,  #faa51a,  #f47a20);
		filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#faa51a', endColorstr='#f47a20');
	}
	.orange:hover {
		background: #f47c20;
		background: -webkit-gradient(linear, left top, left bottom, from(#f88e11), to(#f06015));
		background: -moz-linear-gradient(top,  #f88e11,  #f06015);
		filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#f88e11', endColorstr='#f06015');
	}
	.orange:active {
		color: #fcd3a5;
		background: -webkit-gradient(linear, left top, left bottom, from(#f47a20), to(#faa51a));
		background: -moz-linear-gradient(top,  #f47a20,  #faa51a);
		filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#f47a20', endColorstr='#faa51a');
	}
</style>
</head>
<body>
	<form id="login" action="<c:url value="/user/loginSso.do" />" method="post">
		<div style="width:20%;height:45px;margin:0 auto;">
			<div style="width:40%;float:left;">
				<input type="text" value="" id="userName" name="userName" value="" style="width:150px;margin-top:5px;"/>
				<input type="password" value="" id="passwd" name="passwd" value="" style="width:150px;margin-top:5px;"/>
			</div>
			<div class="button orange" style="width:30%;float:right;backgroup-color:yellow;cursor:pointer;" onclick="login()"><p style="padding-top:3px;">로그인</p></div>
		</div>
	</form>
</body>
</html>
