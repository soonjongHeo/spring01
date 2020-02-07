<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
	<c:if test="${sessionScope.userid != null || sessionScope.adminUserid == null}">
	<%@ include file="../include/menu.jsp" %>
	</c:if>
	<c:if test="${sessionScope.adminUserid != null}">
	<%@ include file="../include/admin_menu.jsp" %>
	</c:if>
	<h2>게시판 등록</h2>
	<div class="col-s-12 w3-light-gray searchbar">
		<div class="col-s-12 menu-title">
<%-- 			${currentMenu.mnNm} --%>제목
		</div>
	</div>
	<div class="content-container">
		<div class="col-l-12 col-s-12">
			<div class="form-button-wrap">
<%-- 				<c:if test="${requestScope['javax.servlet.forward.servlet_path'] eq '/bbs/write.do'}"> --%>
					<button class="btn btn-add" style="display:inline-block">등록</button>
<%-- 					<a class="btn btn-cancel" href="/bbs/list.do?mnId=${param.mnId}&bbsId=${param.bbsId}&page=${param.page}&searchType=${param.searchType}&keyword=${param.keyword}" style="display:inline-block">취소</a> --%>
<%-- 				</c:if> --%>
<%-- 				<c:if test="${requestScope['javax.servlet.forward.servlet_path'] eq '/bbs/edit.do'}"> --%>
					<button class="btn btn-update" style="display:inline-block">저장</button>
<%-- 					<a class="btn btn-cancel" href="/bbs/view.do?mnId=${param.mnId}&bbsId=${param.bbsId}&articleNo=${article.articleNo}&page=${param.page}&searchType=${param.searchType}&keyword=${param.keyword}" style="display:inline-block">취소</a> --%>
<%-- 				</c:if> --%>
			</div>
			<form name="writeForm">
				<div class="easi-form-container">
<%-- 					<c:if test="${menuRole.admRoleYn eq 'Y'}"> --%>
						<div class="box-item col-l-12 col-s-12">
							<div class="box-label" style="width:70px;">상단고정</div>
							<div class="box-content">
								<div id="topFixYn"></div>
							</div>
						</div>
<%-- 					</c:if> --%>
<%-- 					<c:if test="${bbs.categoryYn eq 'Y'}"> --%>
						<div class="box-item col-l-3 col-s-12">
							<div class="box-label" style="width:70px;">카테고리</div>
							<div class="box-content">
								<div id="bbsCateId"></div>
							</div>
						</div>
<%-- 					</c:if> --%>
					<div class="box-item col-l-12 col-s-12" style="padding:0"></div>
					<div class="box-item col-l-4 col-s-12">
						<div class="box-label" style="width:70px;">제목</div>
						<div class="box-content">
							<input type="text" id="articleTitle" class="box-input" maxlength="50" placeholder="제목을 입력해주세요." value="${requestScope['javax.servlet.forward.servlet_path'] eq '/bbs/edit.do' ? article.articleTitle : ''}">
						</div>
					</div>
					<div class="box-item col-l-12 col-s-12">
						<div class="box-label" style="width:70px;">내용</div>
					</div>
					<div class="box-item col-l-12 col-s-12">
						<div id="articleCont">
<%-- 							${requestScope['javax.servlet.forward.servlet_path'] eq '/bbs/edit.do' ? article.articleCont : ''} --%>
						</div>
					</div>
<%-- 					<c:if test="${bbs.attachYn eq 'Y'}"> --%>
						<div class="box-item col-l-12 col-s-12">
							<div class="box-label" style="width:70px;">파일첨부</div>
						</div>
						<div class="box-item col-l-3 col-s-12">
							<div id="attachList"></div>
						</div>
<%-- 					</c:if> --%>
				</div>
			</form>
		</div>
	</div> 
<!-- 	<form id="frm" name="frm" method="post" enctype="multipart/form-data"> -->
<!-- 		<table class="board_view"> -->
<!-- 			<colgroup> -->
<!-- 				<col width="15%"> -->
<!-- 				<col width="*"/> -->
<!-- 			</colgroup> -->
<!-- 			<caption>게시글 작성</caption> -->
<!-- 			<tbody> -->
<!-- 				<tr> -->
<!-- 					<th scope="row">제목</th> -->
<!-- 					<td><input type="text" id="TITLE" name="TITLE" class="wdp_90"></input></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td colspan="2" class="view_text"> -->
<!-- 						<textarea rows="20" cols="100" title="내용" id="CONTENTS" name="CONTENTS"></textarea> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 			</tbody> -->
<!-- 		</table> -->
<!-- 		<div id="fileDiv"> -->
<!-- 			<p> -->
<!-- 				<input type="file" id="file" name="file_0"> -->
<!-- 				<a href="#this" class="btn" id="delete" name="delete">삭제</a> -->
<!-- 			</p> -->
<!-- 		</div> -->
		
<!-- 		<br/><br/> -->
<!-- 		<a href="#this" class="btn" id="addFile">파일 추가</a> -->
<!-- 		<a href="#this" class="btn" id="write">작성하기</a> -->
<!-- 		<a href="#this" class="btn" id="list">목록으로</a> -->
<!-- 	</form> -->
	
	<script type="text/javascript">
		var gfv_count = 1;
		
		$(document).ready(function(){
			$("#articleCont").froalaEditor();
			$('#CONTENTS').summernote({
		        tabsize: 2,
		        width: 800,
		        height: 300
		    });
			
			$("#list").on("click", function(e){
				e.preventDefault();
				fn_openBoardList();
			});	
			
			$("#write").on("click", function(e){ 
				//작성하기 버튼
				e.preventDefault();
				fn_insertBoard();
			});
			
			$("#addFile").on("click", function(e){ 
				//파일 추가 버튼 
				e.preventDefault(); 
				fn_addFile(); 
			});
			
			$("a[name='delete']").on("click", function(e){ 
				//삭제 버튼 
				e.preventDefault(); 
				fn_deleteFile($(this)); 
			});
			
		});
		
		function fn_openBoardList(){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/board/list.do' />");
			comSubmit.submit();
		}
		
		function fn_insertBoard(){
			var comSubmit = new ComSubmit("frm");
			comSubmit.setUrl("<c:url value='/board/insert.do' />");
			comSubmit.submit();
		}
		
		function fn_addFile(){
			var str = "<p><input type='file' name='file_"+(gfv_count++)+"'><a href='#this' class='btn' name='delete'>삭제</a></p>";
			$("#fileDiv").append(str);
			$("a[name='delete']").on("click", function(e){ //삭제 버튼
				e.preventDefault();
				fn_deleteFile($(this));
			});
		}
		
		function fn_deleteFile(obj){
			obj.parent().remove();
		}
		
	</script>
	 

</body>
</html>