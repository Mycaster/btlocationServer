 <%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
 <%@ page 
 import="com.controller.GetScanRecord" 
 import="java.text.SimpleDateFormat"
 import="java.sql.Date"
 %>
<!DOCTYPE html>
<html lang="zh-CN">
   <head>
      <title>蓝牙定位</title>
	  <meta charset="utf-8">
	  <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0" >
      <!-- Bootstrap -->
      <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
   	  <link href="bootstrap/css/bootstrap-select.css" rel="stylesheet" >
      <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	  <script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
	  <script src="http://code.jquery.com/jquery-latest.js"></script>
      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src="bootstrap/js/bootstrap.min.js"></script>
      <script src="bootstrap/js/bootstrap-select.js"></script>
   </head>
   <script type="text/javascript">   
   		console.log("head之后的js 代码");
		$(window).load(function () { 
			console.log("window.load()");
		 $('body').css('padding-top', parseInt($('#navbar').css("height"))+15);  
		});
	</script>
	<script type="text/javascript">
	function getReady(){
		console.log("getReady()");
		$('.selectpicker').empty(); 
		console.log("进入控制台");
		//向服务器请求数据
		$.ajax({
		    type : "POST", 
		    contentType : "application/json", 
		    url : "/btlocationServer/GetBeaconInfo", 
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
   		<nav class="navbar navbar-inverse navbar-fixed-top">
 		   <div class="navbar-header">
		      <a class="navbar-brand" >LBS Service</a>
		   </div>
			<div id="navbar" class="navbar-collapse collapse">
			  <ul class="nav navbar-nav">
				<li ><a href="index.jsp"><span data-hover="首页">首页</a></li>
				<li class="active"><a href="location.jsp">定位服务</a></li>
				<li class="dropdown">
				  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">其他接口<span class="caret"></span></a>
				  <ul class="dropdown-menu" role="menu">
					<li><a href="#">Action</a></li>
					<li><a href="#">Another action</a></li>
					<li class="divider"></li>
					<li class="dropdown-header">Nav header</li>
					<li><a href="#">Separated link</a></li>
				  </ul>
				</li>
				<li ><a href="login.jsp">登录</a></li>
				<li class = "navbar-right" ><a href="about.jsp">关于我们</a></li>
			  </ul>
			</div><!--/.nav-collapse -->
		</nav>
		<div class="container-fluid">
		<div class="row-fluid">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <div class="col-lg-5">
                    <select id="beaconSelector" class="selectpicker show-tick form-control" data-icon="icon-ok" multiple data-live-search="true">
                    </select>
                </div>
                <div class="col-lg-3">
                	<input type="text" class="form-control" placeholder="Search">
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
	  		    		$("#showlocation").after("<h3 class ='text-center text-success locationlist' >"+item.name+"--"+item.address+":  "+item.location+"米 </h3>");
   			    	}); 
	  		    }
	  		  });    

	      });
	   });  
   </script>
</html>