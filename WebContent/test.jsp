 <%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
 <%@ page 
 import="com.controller.GetScanRecord" 
 import="java.text.SimpleDateFormat"
 import="java.sql.Date"
 %>
<!DOCTYPE html>
<html lang="zh-CN">
   <head>
   <%request.setAttribute("MENU_INDEX", "index");%>
   <%@include file="navbar.jsp"%>
   </head>
	<script type="text/javascript">
		function getReady(){
			$('.selectpicker').empty(); 
			console.log("第1次");
			//向服务器请求数据
				$.ajax({
				    type : "POST", 
				    contentType : "application/json", 
				    url : "/btlocationServer/GetBeaconInfo", 
				    dataType : 'json',
				    cache:false,
				    success : function(data){
				    	//将数据填充到表格中
				    	$.each(data, function(i, item) {
				    		$('.selectpicker').append("<option >"+item.name+"--"+item.address+"</option>");
					    	console.log(item.name+"--"+item.address);
				    	});
				    	$('.selectpicker').selectpicker('refresh')
				    }
				});
		}
	</script>
    <body onload=" getReady()">
		<div class="container-fluid">
			<div class="row-fluid">
		        <form class="form-horizontal" role="form">
		            <div class="form-group">
		                <div class="col-lg-3">
		                    <select id="beaconSelector" class="selectpicker show-tick form-control" data-icon="icon-ok" multiple data-live-search="true">
		                    </select>
		                </div>
		                <div class="col-lg-5">
		                	<input type="text" class="form-control" placeholder="Search">
		                </div>
		                <div class="col-lg-2">
		                      <button id="locatedButton" type="button" class="btn btn-success btn-sm" >定位</button>
		                </div>
		              </div>
		        </form>
	    	</div>
		</div>
	<h2 class ="text-center text-success" >您请求定位的节点位置为：</h2>
	<h3 class ="text-center text-success" id="showlocation"></h3>
	<h3 class ="text-center">定位进度</h3>
	<div class="progress">
	  <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
		70%
	  </div>
	</div>
   </body>
   <script type="text/javascript">
   $(function() { 
	      $("#locatedButton").click(function(){
	    	  var result1 = $("#input_text1").val();
	    	  $.ajax({
	  		    type : "POST", 
	  		    url : "/btlocationServer/Locatedservlet", 
	  		    data:"30:10:B3:0D:62:38",
	  		    dataType : 'text', 
	  		    success : function(data){
	  		    	$("#showlocation").text(data);
	  		    	//alert(data);
	  		    }
	  		});       
	      });
	   });  
   </script>
</html>