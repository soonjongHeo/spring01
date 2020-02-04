<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>회원상세</title>
	<%@ include file="../include/header.jsp" %>
	
	<script>
		$(document).ready(function(){
			$("#btnUpdate").click(function(){
				document.form.action="${path}/member/update.do";
				document.form.submit();
			});
			$("#btnDelete").click(function(){
				if(confirm("삭제하시겠습니까?")){
					document.form.action="${path}/member/delete.do";
					document.form.submit();
				}
			});
		})
	</script>
</head>
<body>
	<%@ include file="../include/menu.jsp" %>
	<h2>회원상세</h2>
	<form name="form" method="post">
		<table border="1" width="400px">
			<tr>
				<td>아이디</td>
				<td><input name="userid" value="${viewMember.userid}" readonly></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="passwd"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input name="name" value="${viewMember.name}"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input name="email" value="${viewMember.email}"></td>
			</tr>
			<tr>
				<td>회원가입일자</td>
				<td><fmt:formatDate value="${viewMember.joinDate}" type="date" dateStyle="full" /></td>
			</tr>
			<tr>
				<td colspan="2" aling="center">
					<input type="button" value="수정" id="btnUpdate">
					<input type="button" value="삭제" id="btnDelete">
					<div style="color:red;">${message}</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
