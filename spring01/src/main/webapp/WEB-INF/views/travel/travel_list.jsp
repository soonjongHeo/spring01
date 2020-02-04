<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>main</title>
	<%@ include file="../include/header.jsp" %>
	<style>
   		#map {
	        width: 100%;
   			height: 500px;
	      }
    </style>
    
	<script>
	
		var map;	
		var infoWindow; // infoWindow Object를 담을 변수
		var markers; // Marker Object를 담을 변수
		var markerArray = [];
		var iConArray = [];
		var contentArray = [];
		var iterator = 0;
		var result = new Array();
		
		// infowindow contents 배열
		 contentArray[0] = "Kay";
		 contentArray[1] = "uhoons blog";
		 contentArray[2] = "blog.uhoon.co.kr";
		 contentArray[3] = "blog.uhoon.co.kr ";
		 contentArray[4] = "blog.uhoon.co.kr";
		 contentArray[5] = "blog.goodkiss.co.kr";
		 contentArray[6] = "GG";
		 contentArray[7] = "blog.goodkiss.co.kr";
		 contentArray[8] = "II";
		 contentArray[9] = "blog.goodkiss.co.kr";
 
		// marker icon 배열
		 iConArray[0] = "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png";
		 iConArray[1] = "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png";
		 iConArray[2] = "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png";
		 iConArray[3] = "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png";
		 iConArray[4] = "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png";
		 iConArray[5] = "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png";
		 iConArray[6] = "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png";
		 iConArray[7] = "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png";
		 iConArray[8] = "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png";
		 iConArray[9] = "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png";
 
		$(document).ready(function() { 
			  
			<c:forEach var="item" items="${travelList}">
				var json = new Object();  
				json.travelId = "${item.travelId}";
				json.travelName = "${item.travelName}";
				json.travelDate = "${item.travelDate}";
				json.travelAddress = "${item.travelAddress}";
				json.travelLatitude = "${item.travelLatitude}";
				json.travelLongitude = "${item.travelLongitude}";
				json.regDt = "${item.regDt}";
				json.updDt = "${item.updDt}";
				result.push(json);
			</c:forEach>
			
			 for (var i = 0; i < result.length; i++) {
	          var travelLatitude = result[i].travelLatitude;
	          console.log("travelLatitude: " + travelLatitude);
	          markerArray = [
        		new google.maps.LatLng(parseFloat(result[i].travelLatitude),parseFloat(result[i].travelLongitude))
	       	  ];
	        }
			 
		});
		
		function initMap() {
			var mapOptions = {
			        zoom: 11,
			        mapTypeId: google.maps.MapTypeId.ROADMAP,
			        center: new google.maps.LatLng(40.4167754,-3.7037901999999576)
			    };
			 
			    map = new google.maps.Map(document.getElementById('map'),mapOptions);
			 
			    for (var i = 0; i < markerArray.length; i++) {
			        addMarker();
			    }
 
	      }

	      function addMarker() {
	    	  
    	    var marker = new google.maps.Marker({
    	        position: markerArray[iterator],
    	        map: map,
    	        draggable: false,
    	        icon: iConArray[iterator]
    	    });
    	    markers.push(marker);
    	 
    	    var infowindow = new google.maps.InfoWindow({
    	      content: contentArray[iterator]
    	    });
    	 
    	    google.maps.event.addListener(marker, 'click', function() {
    	        infowindow.open(map,marker);
    	    });
    	    iterator++;
    	}

	</script>

</head>
<body>
	<header>
	</header>
	<nav>
		<c:if test="${sessionScope.userid != null || sessionScope.adminUserid == null}">
		<%@ include file="../include/menu.jsp" %>
		</c:if>
		<c:if test="${sessionScope.adminUserid != null}">
		<%@ include file="../include/admin_menu.jsp" %>
		</c:if>
	</nav>
	<h2>여행목록</h2>
	<input type="button" value="여행등록" onclick="location.href='${path}/travel/writeView.do'"> 
	<section>
		<table border="1" width="700px">
			<tr>
				<th>아이디</th>
				<th>여행명</th>
				<th>여행날짜</th>
				<th>여행지</th>
				<th>여행LATITUDE</th>
				<th>여행LONGITUDE</th>
				<th>등록일</th>
				<th>수정일</th>
			</tr>
			<c:forEach var="list" items="${travelList}">
				<tr>
					<td>${list.travelId}</td>
					<td><a href="${path}/travel/view.do?travelId=${list.travelId}">
					${list.travelName}</a></td>
					<td>${list.travelDate}</td>
					<td>${list.travelAddress}</td>
					<td>${list.travelLatitude}</td>
					<td>${list.travelLongitude}</td>
					<td><fmt:formatDate value="${list.regDt}" 
					pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><fmt:formatDate value="${list.updDt}" 
					pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			</c:forEach>
		</table>
		<div id="map"></div>
	</section>
	<footer>
	</footer>
</body>
</html>
