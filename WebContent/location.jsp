 <%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
 <%@ page 
 import="com.controller.GetScanRecord" 
 import="java.text.SimpleDateFormat"
 import="java.sql.Date"
 %>
<!DOCTYPE html>
<html lang="zh-CN">
   <head>
 	  <%@include file="header.jsp"%>
 	  <!-- 与选择器有关的 -->
   	  <link href="bootstrap/css/bootstrap-select.css" rel="stylesheet" >
      <link href="bootstrap/css/center.css" rel="stylesheet">
      <script src="bootstrap/js/bootstrap-select.js"></script>

   </head>
	<script type="text/javascript">
	function getReady(){
		//$('.selectpicker').empty(); 
		//向服务器请求数据
		$.ajax({
		    type : "POST", 
		    contentType : "application/json", 
		    url : "/btlocationServer/GetBeaconAddress", 
		    dataType : 'json',
		    success : function(data){
		    	//将数据填充到表格中
		    	$.each(data, function(i, item) {
		    		$('.selectpicker').append("<option >"+item.name+"--"+item.address+"--</option>");
		    	});
		    	$('.selectpicker').selectpicker('refresh')
		    }
		});
		//t=setTimeout("getReady()",5000);//每5秒钟刷新一次
	}
	</script>
   <body onload=" getReady()">
  	  <%request.setAttribute("MENU_INDEX", "location");%>
      <!-- 包含导航栏 -->
 	  <%@include file="navbar.jsp"%>
		<div class="container-fluid ">
		<div class="row-fluid">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <div class="col-md-3 col-md-offset-3">
                    <select id="beaconSelector" class="selectpicker show-tick form-control" data-icon="icon-ok" multiple data-live-search="true">
                    </select>
                </div>
                <div>
                     <button id="locatedButton" type="button" class="btn btn-success btn-sm" >定位</button>
                </div>
            </div>
        </form>
    	</div>
		</div>
	<h2 id="showlocation" class ="text-center text-success" >您请求定位的节点位置为：</h2>
   </body>
   <script type="text/javascript">
   $(function() { 
	      $("#locatedButton").click(function(){
	    	  var selected=$("#beaconSelector  option:selected").text();
	    	  console.log(selected);
	    	  if(selected){
	    		  $.ajax({
	  	  		    type : "POST",
	  	  		  	//contentType : "application/json", 
	  	  		    url : "/btlocationServer/Locatedservlet",
	  	  		    data: selected,
	  	  		    dataType : 'json', 
	  	  		    success : function(data){
	  	  		    	$(".locationlist").remove();
	  	  		    	console.log("success");
	  	  		    	var result="";
	  	  		    	$.each(data, function(i, item) {
	  	  		    		$("#showlocation").after("<h3 class ='text-center text-success locationlist' >"+item.name+"    ("+item.address+"):  "+item.location+"  米 </h3>");
	     			    	}); 
	  	  		    }
	  	  		  }); 
	    	  }else{
	    		  alert("至少选取一个点！");
	    	  }
	      });
	   });  
   </script>
</html>