<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>회원목록</title>
	<%@ include file="../include/header.jsp" %>
</head>
<body>
	<c:if test="${sessionScope.userid != null || sessionScope.adminUserid == null}">
	<%@ include file="../include/menu.jsp" %>
	</c:if>
	<c:if test="${sessionScope.adminUserid != null}">
	<%@ include file="../include/admin_menu.jsp" %>
	</c:if>
	<h2>회원목록</h2>
	<input type="button" value="회원등록" onclick="location.href='${path}/member/write.do'">
	<table border="1" width="700px">
		<tr>
			<td>아이디</td>
			<td>이름</td>
			<td>이메일</td>
			<td>가입일자</td>
		</tr>
		<c:forEach var="row" items="${list}">
		<tr>
			<td>${row.userid}</td>
			<td><a href="${path}/member/view.do?userid=${row.userid}">${row.name}</a></td>
			<td>${row.email}</td>
			<td><fmt:formatDate value="${row.joinDate}" type="date" dateStyle="full" /></td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>
