<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
	<title>관리자페이지</title>
	<%@ include file="../include/header.jsp" %>
	<script type="text/javascript">
		$(function(){
		})
	</script>
</head>
<body>
	<%@ include file="../include/admin_menu.jsp" %>
	<c:if test="${message == 'success'}">
		<h2>
			${sessionScope.adminName}
			${sessionScope.adminUserid}님 환영합니다.
		</h2>
	</c:if>
</body>
</html>
