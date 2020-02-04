<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>상품목록</title>
	<%@ include file="../include/header.jsp" %>
</head>
<body>
	<c:if test="${sessionScope.userid != null || sessionScope.adminUserid == null}">
	<%@ include file="../include/menu.jsp" %>
	</c:if>
	<c:if test="${sessionScope.adminUserid != null}">
	<%@ include file="../include/admin_menu.jsp" %>
	</c:if>
	<h2>상품목록</h2>
	<input type="button" value="상품등록" onclick="location.href='${path}/shop/product/write.do'">
	<table border="1" width="700px">
		<tr>
			<td>아이디</td>
			<td>이미지</td>
			<td>상품명</td>
			<td>가격</td>
		</tr>
		<c:forEach var="row" items="${list}">
		<tr align="center">
			<td>${row.productId}</td>
			<td><img src="/images/${row.pictureUrl}" width="100" height="100" /></td>
			<td><a href="${path}/shop/product/detail.do?productId=${row.productId}">${row.productName}</a>
			<c:if test="${sessionScope.adminUserid != null}">
				<br/>
				<a href="${path}/shop/product/updateP.do?productId=${row.productId}">[편집]</a>
			</c:if>
			</td>
			<td><fmt:formatNumber value="${row.price}" pattern="#,###" /></td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>
