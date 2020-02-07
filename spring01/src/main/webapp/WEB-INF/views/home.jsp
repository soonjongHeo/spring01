<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
	<%@ include file="./include/header.jsp" %>
	<style type="text/css">
		.dx-datagrid .dx-data-row > td.bullet {
		    padding-top: 0;
		    padding-bottom: 0;
		}
	</style>
	<script>
		$(function() {
		    $("#gridContainer").dxDataGrid({
		        dataSource: {
		            store: {
		                type: "odata",
		                url: "/shop/product/jsonProductList.do",
		                beforeSend: function(request) {
// 		                    request.params.startDate = "2018-05-10";
// 		                    request.params.endDate = "2018-05-15";
		                }
		            }
		        },
		        paging: {
		            pageSize: 10
		        },
		        pager: {
		            showPageSizeSelector: true,
		            allowedPageSizes: [10, 25, 50, 100]
		        },
		        remoteOperations: false,
		        searchPanel: {
		            visible: true,
		            highlightCaseSensitive: true
		        },
		        groupPanel: { visible: true },
		        grouping: {
		            autoExpandAll: false
		        },
		        allowColumnReordering: true,
		        rowAlternationEnabled: true,
		        showBorders: true,
		        columns: [
		            {dataField: "productId",groupIndex: 0},
		            {dataField: "price",caption: "가격",dataType: "number",format: "currency",alignment: "right"},
		            {dataField: "Discount",caption: "Discount %",dataType: "number",format: "percent",alignment: "right",allowGrouping: false,cellTemplate: discountCellTemplate,cssClass: "bullet"},
		            {dataField: "registDate",caption: "등록날짜",dataType: "date",format:"yyyy-MM-dd"},
		            {dataField: "productName",caption: "상품명",dataType: "string"},
		            {dataField: "pictureUrl",caption: "이미지경로",dataType: "string"},
		            {dataField: "description",caption: "내용",dataType: "string",width: 150}
		        ],
		        onContentReady: function(e) {
		            if(!collapsed) {
		                collapsed = true;
		                var key = e.component.getKeyByRowIndex(0);  
		                console.log("key: " + key);
		                e.component.expandRow([key]);
		            }
		        }
		    });
		});
	
		var discountCellTemplate = function(container, options) {
		    $("<div/>").dxBullet({
		        onIncidentOccurred: null,
		        size: {
		            width: 150,
		            height: 35
		        },
		        margin: {
		            top: 5,
		            bottom: 0,
		            left: 5
		        },
		        showTarget: false,
		        showZeroLevel: true,
		        value: options.value * 100,
		        startScaleValue: 0,
		        endScaleValue: 100,
		        tooltip: {
		            enabled: true,
		            font: {
		                size: 18
		            },
		            paddingTopBottom: 2,
		            customizeTooltip: function() {
		                return { text: options.text };
		            },
		            zIndex: 5
		        }
		    }).appendTo(container);
		};
	
		var collapsed = false;
	</script>
	
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<div class="dx-viewport demo-container">
        <div id="gridContainer"></div>
    </div>
</body>
</html>
