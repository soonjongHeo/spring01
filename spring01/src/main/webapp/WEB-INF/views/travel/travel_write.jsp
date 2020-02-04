<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>main</title>
	<%@ include file="../include/header.jsp" %>
	<script src="../resources/showModalDialog.js"></script>
	<style>
        /* Always set the map height explicitly to define the size of the div
         * element that contains the map. */
        #map {
            height: 100%;
        }
        /* Optional: Makes the sample page fill the window. */
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
        #floating-panel {
            position: absolute;
            top: 10px;
            left: 25%;
            z-index: 5;
            background-color: #fff;
            padding: 5px;
            border: 1px solid #999;
            text-align: center;
            font-family: 'Roboto','sans-serif';
            line-height: 30px;
            padding-left: 10px;
        }
    </style>
    
	<script>
	
		$(document).ready(function() {
			$("#sumit").click(function(){
				document.travelForm.method = "post"; 
				document.travelForm.action = "${path}/";
				document.travelForm.submit();

			});
			
			$("#mapPopup").click(function(){
				window.name = "parentForm";
				var returnValue = null;
				returnValue = window.showModalDialog("/googleMap.do","childForm","dialogWidth:600px;dialogHeight:600px");
				console.log("returnValue: " + returnValue);
				$(".location").text(returnValue);
				
				if(returnValue != null && returnValue != ""){
					$("[name=travelLatitude]").val(returnValue.split("/")[0]);
					$("[name=travelLongitude]").val(returnValue.split("/")[1]);
				}
			});
			
			
		});
		
		
// 		function initMap() {
// 	        var map = new google.maps.Map(document.getElementById('map'), {
// 	            zoom: 8,
// 	            center: {lat: -34.397, lng: 150.644}
// 	        });
// 	        var geocoder = new google.maps.Geocoder();
	
// 	        document.getElementById('submit').addEventListener('click', function() {
// 	            geocodeAddress(geocoder, map);
// 	        });
// 	    }
	
// 	    function geocodeAddress(geocoder, resultsMap) {
// 	        var address = document.getElementById('address').value;
// 	        geocoder.geocode({'address': address}, function(results, status) {
// 	            if (status === 'OK') {
// 	                // 아래 코드에서 위도, 경도를 구할 수 있다.
// 	                console.log(results[0].geometry.location);
// 	                console.log("lat: " + results[0].geometry.location.lat());
// 	                console.log("lng: " + results[0].geometry.location.lng());
// 	                $("#travelLatitude").val(results[0].geometry.location.lat());
// 	                $("#travelLongitude").val(results[0].geometry.location.lng());
// 	                resultsMap.setCenter(results[0].geometry.location);
// 	                var marker = new google.maps.Marker({
// 	                    map: resultsMap,
// 	                    position: results[0].geometry.location
// 	                });
// 	            } else {
// 	                alert('Geocode was not successful for the following reason: ' + status);
// 	            }
// 	        });
// 	    }
	</script>

</head>
<body>
	<header>
	</header>
	<nav>
		<%@ include file="../include/menu.jsp" %>
	</nav>
	<section>
		<form accept-charset="utf-8" id="travelForm" name = "person_info" action="${path}/travel/insert.do">
				여행지명 : <input type = "text" name = "travelName"/><br><br>
                            여행날짜 : <input type = "date" name = "travelDate"><br><br>
                            여행주소 : <input type = "text" name = "travelAddress"><br><br>
                            여행좌표 : <input type = "text" name = "travelLatitude"> 
                        <input type = "text" name = "travelLongitude">
                        <span id="mapPopup">
		                	<a href="javascript:;">지도</a>
		                </span><br><br>	
			    <input type = "submit" id="sumit" value = "submit"/>
                <input type = "reset" value = "reset"/><br><br>
                
                <span class="location"></span>
		</form>
	</section>
	<footer>
	</footer>
</body>
</html>
