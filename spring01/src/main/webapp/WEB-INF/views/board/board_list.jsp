<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<h2>게시판 목록</h2>
	
	<form name="form1" method="post" action="${path}/board/list.do">
		<select name="searchOption">
			<option value="all">이름+내용+제목</option>
			<option value="name">이름</option>
			<option value="contents">내용</option>
			<option value="title">제목</option>
		</select>
		<input name="keyword" value="${map.keyword}">
		<input type="submit" value="조회">
	</form>
	
	${map.pager.totalRecordCount}개의 게시물이 있습니다.
	<table class="board_list">
		<colgroup>
			<col width="10%"/>
			<col width="*"/>
			<col width="15%"/>
			<col width="20%"/>
		</colgroup>
		<thead>
			<tr>
				<th scope="col">글번호</th>
				<th scope="col">제목</th>
				<th scope="col">이름</th>
				<th scope="col">조회수</th>
				<th scope="col">작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(map.list) > 0}">
					<c:forEach items="${map.list}" var="row">
						<tr>
							<td>${row.IDX }</td>
							<td class="title"> 
								<a href="#this" name="title">${row.TITLE }</a> 
								<input type="hidden" id="IDX" value="${row.IDX }"> 
							</td>
							<td>${row.NAME }</td>
							<td>${row.HIT_CNT }</td>
							<td>${row.CREA_DTM }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4" align="center">조회된 결과가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
		
		<tr>
            <td colspan="5">
<!--                 **처음페이지로 이동 : 현재 페이지가 1보다 크면  [처음]하이퍼링크를 화면에 출력 -->
                <c:if test="${map.pager.currentPageNo > 10}">
                    <a href="javascript:fn_search('1')">[처음]</a>
                </c:if>
                
                <!-- **이전페이지 블록으로 이동 : 현재 페이지 블럭이 1보다 크면 [이전]하이퍼링크를 화면에 출력 -->
                <c:if test="${map.pager.currentPageNo > 10}">
                    <a href="javascript:fn_search('${map.pager.firstPageNoOnPageList-10}')">[이전]</a>
                </c:if>
                
                <!-- **하나의 블럭에서 반복문 수행 시작페이지부터 끝페이지까지 -->
                <c:forEach var="num" begin="${map.pager.firstPageNoOnPageList}" end="${map.pager.lastPageNoOnPageList}">
                    <!-- **현재페이지이면 하이퍼링크 제거 -->
                    <c:choose>
                        <c:when test="${num == map.pager.currentPageNo}">
                            <span style="color: red">${num}</span>&nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:fn_search('${num}')">${num}</a>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                
                <!-- **다음페이지 블록으로 이동 : 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 [다음]하이퍼링크를 화면에 출력 -->
                <c:if test="${map.pager.currentPageNo < map.pager.totalPageCount}">
                    <a href="javascript:fn_search('${map.pager.lastPageNoOnPageList+1}')">[다음]</a>
                </c:if>
                
                <!-- **끝페이지로 이동 : 현재 페이지가 전체 페이지보다 작으면 [끝]하이퍼링크를 화면에 출력 -->
                <c:if test="${map.pager.currentPageNo < map.pager.totalPageCount}">
                    <a href="javascript:fn_search('${map.pager.totalPageCount}')">[끝]</a>
                </c:if>
            </td>
        </tr>
	</table> 
	
	<br/> 
	<a href="#this" class="btn" id="write">글쓰기</a>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#write").on("click", function(e){ //글쓰기 버튼
				e.preventDefault();
				fn_openBoardWrite();
			});	
			
			$("a[name='title']").on("click", function(e){ //제목 
				e.preventDefault();
				fn_openBoardDetail($(this));
			});
		});
		
		
		function fn_openBoardWrite(){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/board/write.do' />");
			comSubmit.submit();
		}
		
		function fn_openBoardDetail(obj){
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/board/detail.do' />");
			comSubmit.addParam("IDX", obj.parent().find("#IDX").val());
			comSubmit.submit();
		}
		
		function fn_search(pageNo){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/board/list.do' />");
            comSubmit.addParam("currentPageNo", pageNo);
            comSubmit.submit();
        }
	</script>
</body>
</html>