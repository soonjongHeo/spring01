<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>main</title>
	<%@ include file="include/header.jsp" %>
	<script>
	$(document).ready(function() { 
		for(var i=2; i<=9; i++){
			for(var j=1; j<=9; j++){
				console.log(i + "*" + j + "= " + (i*j));
			}	
		}
	});
	
		function doF(){
			$.ajax({
				type: "post",
				url: "${path}/doF.do",
				success:function(result){
					console.log(result);
					$("#result").html("상품명: " + result.pName + ", 가격: " + result.pPrice)
				}
			})
		}
	</script>
</head>
<body>
	<c:if test="${sessionScope.userid != null || sessionScope.adminUserid == null}">
	<%@ include file="include/menu.jsp" %>
	</c:if>
	<c:if test="${sessionScope.adminUserid != null}">
	<%@ include file="include/admin_menu.jsp" %>
	</c:if>
	<h2>${serverTime}</h2>
	<a href="javascript:doF()">jsonType</a>
	<div id="result"></div>
<%-- 	<c:set var="realPath" value="" /> --%>
	<%=application.getRealPath("/WEB-INF/views/images/") %>
<%-- ${realPath} --%>
<%-- 	<c:out value="${realPath}" /> --%>
</body>
</html>
