<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ page session="true" %>
<c:set var="path" value="${pageContext.request.contextPath}" /> 

<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

<!-- include summernote css/js -->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote.js"></script>

<!-- <script src="http://code.jquery.com/jquery-3.3.1.min.js"></script> -->
<script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js"></script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBdzJmTyfEzA_nkKILfk-Q0GWlprDqGi9Q&callback=initMap"></script>
<script src="${path}/resources/js/common.js" charset="utf-8"></script>
<script src="${path}/resources/ckeditor/ckeditor.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/resources/css/ui.css" />

<link rel="stylesheet" href="${path}/resources/css/devexpress/dx.common.css">
<link rel="stylesheet" href="${path}/resources/css/devexpress/dx.light.css">
<link rel="stylesheet" href="${path}/resources/fontawesome-free-5.9.0-web/css/font-awesome.min.css">
<link rel="stylesheet" href="${path}/resources/css/froalaeditor/froala_editor.pkgd.min.css">
<link rel="stylesheet" href="${path}/resources/css/froalaeditor/froala_style.min.css">
<link rel="stylesheet" href="${path}/resources/css/froalaeditor/custom-froala.css">

<script src="${path}/resources/js/devexpress/cldr.min.js"></script>
<script src="${path}/resources/js/devexpress/cldr/event.min.js"></script>
<script src="${path}/resources/js/devexpress/cldr/supplemental.min.js"></script>
<script src="${path}/resources/js/devexpress/globalize.min.js"></script>
<script src="${path}/resources/js/devexpress/globalize/message.min.js"></script>
<script src="${path}/resources/js/devexpress/globalize/number.min.js"></script>
<script src="${path}/resources/js/devexpress/globalize/currency.min.js"></script>
<script src="${path}/resources/js/devexpress/globalize/date.min.js"></script>
<script src="${path}/resources/js/devexpress/jszip.min.js"></script>
<script src="${path}/resources/js/devexpress/dx.all.js"></script>
<script src="${path}/resources/js/devexpress/localization/dx.messages.ko.js"></script>
<script src="${path}/resources/js/devexpress/cldrdata/supplemental.js"></script>
<script src="${path}/resources/js/devexpress/cldrdata/ko.js"></script>
<script src="${path}/resources/js/froalaeditor/froala_editor.pkgd.min.js"></script>

<script type="text/javascript">
	function number_filter(str_value){
		return str_value.replace(/[^0-9]/gi, ""); 
	}
</script>
 
 