<%@ page session="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}" /> 

<div style="text-align:center;">
	<a href="${path}/">main</a> | 
	<a href="${path}/shop/product/list.do">상품관리</a> | 
	<a href="${path}/travel/list.do">여행관리</a> | 
	<a href="${path}/member/list.do">회원관리</a> | 
	<a href="${path}/board/list.do">게시판관리</a> | 
	<a href="${path}/upload/uploadForm.do">업로드테스트</a> | 
	<a href="${path}/email/write.do">이메일발송</a> | 
	<a href="${path}/chart/chart1.do">구글차트(json)</a> | 
	<a href="${path}/chart/chart2.do">구글차트(db)</a> | 
	<a href="${path}/jchart/chart1.do">JFreeChart(png)</a> | 
	<a href="${path}/jchart/chart2.do">JFreeChart(pdf)</a> | 
</div>

<div style="text-aling: right;">
<c:choose>
	<c:when test="${sessionScope.adminUserid == null}">
		<a href="/admin/login.do">관리자 로그인</a>
	</c:when>
	<c:otherwise>
		${sessionScope.adminName}님이 로그인중입니다.
		<a href="/admin/logout.do">로그아웃</a>
	</c:otherwise>
</c:choose>	
</div>
<hr/>
<form id="commonForm" name="commonForm"></form>  
