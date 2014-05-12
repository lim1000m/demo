<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
<script type="text/javascript" src="<c:url value='/js/lib/jquery-1.10.2.js' /> "></script>
<script type="text/javascript" src="<c:url value='/js/lib/jquery-ui-1.10.3.custom.js' /> "></script>
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/css/smoothness/jquery-ui-1.10.3.custom.css '/> " />
<script src="<c:url value='/js/lib/jquery.form.js' /> "></script>
</head>
<body>
	<h3>${order.customer}</h3>
	<table border="1">
	<tr>
		<th>아이디</th>
		<th>상품아이디</th>
		<th>가격임</th>
		<th>수정</th>
		<th>삭제</th>
	</tr>
		<c:forEach var="result" items="${order.items}" varStatus="status">
			<tr>
			<td align="center" class="listtd"><c:out value="${result.id}"/></td>
			<td align="center" class="listtd"><c:out value="${result.product}"/> </td>
			<td align="center" class="listtd"><c:out value="${result.price}"/> </td>
			<td><a href="<c:url value='update.do'/>?id=${result.id}">수정</a></td>
			<td><a href="<c:url value='delete.do'/>?id=${result.id}">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
