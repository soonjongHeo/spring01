<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>main</title>
	<%@ include file="include/header.jsp" %>
	
	<style>
        /* Always set the map height explicitly to define the size of the div
         * element that contains the map. */
        #map {
            height: 500px;
        }
        /* Optional: Makes the sample page fill the window. */
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
        #controls{
        	margin-top:10px;
        	border:1px soild transparent;
        	border-radius:2px 0 0 2px;
/*         	box-siring: */
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
	var map;
	var markers = [];
		function confirm(){
			retVal = $("[name=location]").val();
			if(window.returnValue){
				window.returnValue = retVal;
 
			}else{
				window.returnValue = retVal;
			}
			parent.document.getElementsByTagName('dialog')[0].close();
		}
		
		function cancel(){
			parent.document.getElementsByTagName('dialog')[0].close();
		}
		
	    function initMap() {
	         map = new google.maps.Map(document.getElementById('map'), {
	            zoom: 8,
	            center: {lat: -34.397, lng: 150.644}
	        });
	        var geocoder = new google.maps.Geocoder();
	
	        document.getElementById('submit').addEventListener('click', function() {
	            geocodeAddress(geocoder, map);
	        });
	        
	     // This event listener will call addMarker() when the map is clicked.
	        map.addListener('click', function(event) {
	        	console.log("event: " + event.latLng);
	          deleteMarkers();
	          addMarker(event.latLng);
	          var loc = event.latLng.lat() + "/" + event.latLng.lng();
	        	$(".map_position").text(loc);
	        	$("[name=location]").val(loc);
	        });

	    }
	
		 // Adds a marker to the map and push to the array.
		function addMarker(location) {
		  var marker = new google.maps.Marker({
		    position: location,
		    map: map
		  });
		  markers.push(marker);
		}
		 
		// Sets the map on all markers in the array.
	    function setMapOnAll(map) {
	      for (var i = 0; i < markers.length; i++) {
	        markers[i].setMap(map);
	      }
	    }
		
		// Removes the markers from the map, but keeps them in the array.
        function clearMarkers() {
          setMapOnAll(null);
        } 
	 
       // Deletes all markers in the array by removing references to them.
        function deleteMarkers() {
          clearMarkers();
          markers = [];
        }
     
	    function geocodeAddress(geocoder, resultsMap) {
	        var address = document.getElementById('address').value;
	        geocoder.geocode({'address': address}, function(results, status) {
	            if (status === 'OK') {
	                // 아래 코드에서 위도, 경도를 구할 수 있다.
	                console.log(results[0].geometry.location);
	                console.log("lat: " + results[0].geometry.location.lat());
	                console.log("lng: " + results[0].geometry.location.lng());
	                var loc = results[0].geometry.location.lat() + "/" + results[0].geometry.location.lng();
	 	        	$(".map_position").text(loc);
	 	        	$("[name=location]").val(loc);
// 	                resultsMap.setCenter(results[0].geometry.location);
					deleteMarkers();
	                addMarker(results[0].geometry.location);
// 	                var marker = new google.maps.Marker({
// 	                    map: resultsMap,
// 	                    position: results[0].geometry.location
// 	                });
	            } else {
	                alert('Geocode was not successful for the following reason: ' + status);
	            }
	        });
	    }
	</script>

</head>
 
	 <input type="hidden" name="location" value="">
			<div id="floating-panel">
			    <input id="address" type="textbox" value="Sydney, NSW">
			    <input id="submit" type="button" value="검색">
			</div>
			<div id="map"></div>
	 <div class="map_position"></div>
	 <div class="button">
		&nbsp;<span class="btn_page_rd confirm">
			<a href="javascript:confirm();">
				<span class="ico_apply"></span>확인
			</a>
		</span>
	</div>
</html>
