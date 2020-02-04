<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품상세</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
	<c:if test="${sessionScope.userid != null || sessionScope.adminUserid == null}">
	<%@ include file="../include/menu.jsp" %>
	</c:if>
	<c:if test="${sessionScope.adminUserid != null}">
	<%@ include file="../include/admin_menu.jsp" %>
	</c:if>
	<h2>상품 정보</h2>
	<table>
		 <tr>
		 	<td>
		 		<img src="${path}/images/${detail.pictureUrl}" width="300px" height="300px">
		 	</td>
		 	<td align="center">
		 		<table>
		 			<tr>
		 				<td>상품명</td>
		 				<td>${detail.productName}</td>
		 			</tr>
		 			<tr>
		 				<td>가격</td>
		 				<td>${detail.price}</td>
		 			</tr>
		 			<tr>
		 				<td>상품설명</td>
		 				<td>${detail.description}</td>
		 			</tr>
		 			<tr>
		 				<td colspan="2">
			 				<form name="frm" method="post" action="${path}/shop/cart/insert.do">
			 					<input type="hidden" name="productId" value="${detail.productId}">
			 					<select name="amount">
			 						<c:forEach begin="1" end="10" var="i">
			 							<option value="${i}">${i}</option>
			 						</c:forEach>
			 					</select>&nbsp;개
			 					<input type="submit" value="장바구니에 담기">
			 				</form>
		 				</td>
		 			</tr>
		 		</table>
		 	</td>
		 </tr>
	</table>
	<br/>
	
	<a href="#this" class="btn" id="list">목록으로</a>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#list").on("click", function(e){ //목록으로 버튼
				e.preventDefault();
				fn_openList();
			});
			
		});
		
		function fn_openList(){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/shop/product/list.do' />");
			comSubmit.submit();
		}
		
		CKEDITOR.replace("description", {
			filebrowserUploadUrl : "${path}/imageUpload.do"
		});
	</script>
</body>
</html>