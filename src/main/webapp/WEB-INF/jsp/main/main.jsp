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
<script>

$(document).bind("contextmenu", function(e){
	return false;
});

$(document).ready(function(){
	
    jQuery("#gridTbl").jqGrid({
        datatype : "json" ,
        url : "<c:url value='/drwng/getTestList.do' />",
        autowidth : true,
        height : 345,
        width : 200,
        pager : jQuery("#pagerDiv"),
        viewrecords : true,
        rowNum : 1000,
        rowList : [10, 20, 30],
        rownumbers:true,
        sortname : 'dtm',                                          
        sortorder : "desc",
        loadComplete : function(data) {
        },
        ondblClickRow : function(rowid, iRow, iCol, e){
        },
        onSelectRow : function(rowid) {
        	$(".contextMenu").hide();
        },         
        onRightClickRow : function(rowid) {
        	
        	console.log("context Menu clicked");
        	
        	$('#gridTbl').destroyContextMenu();

        	if(rowid != null || rowid != ""){

	        	if($("#gridTbl").jqGrid('getGridParam','selarrrow') != rowid)
	        		$("#gridTbl").setSelection(rowid);
	
	        	$('#gridTbl').destroyContextMenu();
	
	        	$("#gridTbl").contextMenu({
	        		menu: 'normalMenu'
	        	}); 
        	}
        },
        afterInsertRow: function(rowid, rowdata, rowelem) { 
//         	$("#"+rowid).css("background", "#E8D9FF");
        },
        colNames : [${ColName}],
        colModel : [${ColModel}]
    });
    
    resizeAllConpoenent();
});

/**
 * Context Menu Function
 * @author GOEDOKID
 * @since 2015.02.10
 */
function onCommand(flag) {

	switch (flag) {
	case 0 : 
		alert("자료조회");		
		break;
	case 1 :
		alert("정보수정");
		break;
	case 2 :
		alert("자료상세정보");
		break;
	case 3 :
		alert("자료삭제");
		break;
	case 4 :
		alert("다운로드");
		break;
	case 5 : 
		alert("자료이력변경");
		break;
	case 6 : 
		alert("자료삭제"); 
		break;
	}
}

/**
 * Grid Reload
 * @author GOEDOKID
 * @since 2015.02.10
 */
function reloadGrid(){
	data = {lang : '${lang}'};
	$("#gridTbl").jqGrid('setGridParam').trigger('reloadGrid');
}

/**
 * 윈도우 창 크기 조정 시 Grid Resize
 * @author GOEDOKID
 * @since 2015.02.10
 */
$(window).bind('resize', function() {
    resizeAllConpoenent();
}).trigger('resize');


/**
 * Grid 리사이즈
 * @author GOEDOKID
 * @since 2015.02.10
 */
function resizeAllConpoenent(){
	jQuery('#gridTbl').setGridWidth($(window).width()-20, true);
    jQuery('#gridTbl').setGridHeight($(window).height()-113, true);
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
	<div style="width:100%;height:40px;">
		<span class="button orange">등록</span>
		<span class="button orange">수정</span>
		<span class="button orange">삭제</span>
	</div>
	<div id="gridDiv">
		<table id="gridTbl"></table>
		<div id="pagerDiv"></div>

		<ul id="normalMenu" class="gridMenu">
			<li class='edit'>
				<a href="javascript:return false;" onclick='onCommand(1)' >정보수정</a>
			</li>
			<li class='paste'>
				<a href="javascript:return false;" onclick='onCommand(2)' >자료상세정보</a>
			</li>
			<li class='wastebasket separator'>
				<a href="javascript:return false;" onclick='onCommand(6)' >자료삭제</a>
			</li>
			<li class='save'>
				<a href="javascript:return false;" onclick='onCommand(3)' >다운로드</a>
			</li>
			<li class='search'>
				<a href="javascript:return false;" onclick='onCommand(4)' >자료조회</a>
			</li>
			<li class='copy separator'>
				<a href="javascript:return false;" onclick='onCommand(5)' >자료 이력변경</a>
			</li>
			<li class='quit separator'>
				<a href="javascript:return false;">닫기</a>
			</li>
		</ul>
	</div>

</body>
</html>
