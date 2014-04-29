<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />

<script type="text/javascript" src="<c:url value='/js/lib/jquery-1.10.2.js' /> "></script>
<script type="text/javascript" src="<c:url value='/js/lib/jquery-ui-1.10.3.custom.js' /> "></script>
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/css/smoothness/jquery-ui-1.10.3.custom.css '/> " />
<script src="<c:url value='/js/lib/jquery.form.js' /> "></script>
<script type="text/javascript">
/**************************************
 * content	: Insert 
 ***************************************/
function insert() {
	 $("#send").ajaxSubmit({
		 success : function(data) {
		 },
		 error : function(error) {
		 }
	 });
}
</script>
</head>
<body>
	<form name="send" id="send" action="<c:url value='/action.do'/>" method="post">
			<input type="hidden" id="mode" name="name" value="${mode}"/>
		아이디:<input type="text" name="id" id="id" value="${item.id}" /><br />
		가격:<input type="text" name="price" id="price" value="${item.price}" /><br />
		상품명:<input type="text" name="product" id="product" value="${item.product}" /><br />
		품질:<input type="text" name="quantity" id="quantity" value="${item.quantity}" /><br />
	</form>
	<button id="sendBt" name="sendBt" onclick="insert()">등록</button>
</body>
</html>
