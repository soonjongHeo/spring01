<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 보내기</title>
<%@ include file="../include/header.jsp" %>
<c:if test="${sessionScope.userid != null || sessionScope.adminUserid == null}">
	<script>
		alert("로그인 하신 후 사용하세요.");
		location.href="${path}/admin/login.do";
	</script>
</c:if>
</head>
<body>
	<c:if test="${sessionScope.userid != null || sessionScope.adminUserid == null}">
	<%@ include file="../include/menu.jsp" %>
	</c:if>
	<c:if test="${sessionScope.adminUserid != null}">
	<%@ include file="../include/admin_menu.jsp" %>
	</c:if>
	<h2>이메일 보내기</h2>
	<form id="frm" name="frm" method="post">
		<table class="board_view">
			<tbody>
				<tr>
					<th scope="row">발신자 이름</th>
					<td><input type="text" id="senderName" name="senderName" class="wdp_90" /></td>
				</tr>
				<tr>
					<th scope="row">발신자 이메일</th>
					<td><input type="text" id="senderMail" name="senderMail" class="wdp_90" /></td>
				</tr>
				<tr id="receiveBox">
					<th scope="row">수신자 이메일</th>
					<td><p><input type="text" id="receiveMail" name="receiveMail_0" class="wdp_90" /></p><a href="#this" class="btn" id="addReceive">수신자 추가</a></td>
					
				</tr>
				<tr>
					<th scope="row">제목</th>
					<td><input type="text" id="subject" name="subject" class="wdp_90" /></td>
				</tr>
				<tr>
					<th scope="row">내용</th>
					<td colspan="2" class="view_text">
						<textarea rows="5" cols="60" title="내용" id="message" name="message"></textarea>
					</td>
				</tr>
				<tr>
				   <th scope="row">File</th>
				   <td><input type="file" id="attachment" name="attachment"></td>
				</tr>
			</tbody>
		</table>
		<br/><br/>
		<a href="#this" class="btn" id="write">작성하기</a>
<!-- 		<a href="#this" class="btn" id="list">목록으로</a> -->
	</form>
		<span style="color:red;">${message}</span>
	
	<script type="text/javascript">
		var gfv_count = 1;
		
		$(document).ready(function(){
			$("#list").on("click", function(e){
				e.preventDefault();
				fn_openList();
			});	
			
			$("#write").on("click", function(e){ 
				//작성하기 버튼
				e.preventDefault();
				var senderName = document.frm.senderName.value;
				var senderMail = document.frm.senderMail.value;
				var receiveMail = document.frm.receiveMail.value;
				if(senderName == "") {
					alert("발신자 이름을 입력하세요.");
					document.frm.senderName.focus();
					return;
				}
				if(senderMail == "") {
					alert("발신자 이메일을 입력하세요.");
					document.frm.senderMail.focus();
					return;
				}
				if(receiveMail == "") {
					alert("수신자 이메일을 입력하세요.");
					document.frm.receiveMail.focus();
					return;
				}
				fn_insert();
			});
			
			$("#addReceive").on("click", function(e){ 
				//파일 추가 버튼 
				e.preventDefault(); 
				fn_addReceive(); 
			});
			
			$("a[name='delete']").on("click", function(e){ 
				//삭제 버튼 
				e.preventDefault(); 
				fn_deleteReceive($(this)); 
			});
			
		});
		
		function fn_openList(){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/shop/product/list.do' />");
			comSubmit.submit();
		}
		
		function fn_insert(){
			var comSubmit = new ComSubmit("frm");
			comSubmit.setUrl("<c:url value='/email/send.do' />");
			comSubmit.submit();
		}
		
		function fn_addReceive(){
			var str = "<p><input type='input' name='receiveMail_"+(gfv_count++)+"' class='wdp_90' style='margin-right: 5px;'><a href='#this' class='btn' name='delete'>삭제</a><p>";
			$("#receiveBox td").append(str);
			$("a[name='delete']").on("click", function(e){ //삭제 버튼
				e.preventDefault();
				fn_deleteReceive($(this));
			});
		}
		
		function fn_deleteReceive(obj){
			obj.parent().remove();
		}
		
// 		CKEDITOR.replace("description", {
// 			filebrowserUploadUrl : "${path}/imageUpload.do"
// 		});
	</script>
	 

</body>
</html>