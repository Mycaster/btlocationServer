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
      <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	  <script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
	  <script src="http://code.jquery.com/jquery-latest.js"></script>
      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src="bootstrap/js/bootstrap.min.js"></script>
   </head>
   <script type="text/javascript">		
   		$(window).resize(function () { 
			 $('body').css('padding-top', parseInt($('#navbar').css("height"))+5);
			 
		});
		$(window).load(function () { 
		 	$('body').css('padding-top', parseInt($('#navbar').css("height"))+5);  
		});
	</script>
   <script type="text/javascript">
   		function getReady(){
   			console.log("getReady()");
   			$(document).ready(function() {
   			   $("#record").find("tr:gt(0)").remove();
   			});
   			//向服务器请求数据
   			$.ajax({
   			    type : "POST", 
   			    contentType : "application/json", 
   			    url : "/btlocationServer/GetScanRecord", 
   			    dataType : 'json',
   			    success : function(data){
   			    	//将数据填充到表格中
   			    	$.each(data, function(i, item) { 
   			    		//console.log(item.time);
					 	var newRow = "<tr ><td>"+
					 				item.time+"</td><td>"+
					 				item.a_addr+"</td><td>"+
					 				item.a_location+"</td><td>"+
					 				item.i_addr+"</td><td>"+
					 				item.rssi+"</td><td>"+"<button type='button' class='btn btn-primary' data-toggle='collapse' data-target='#demo'> 展开扫描到的节点</button></td></tr>";
					 	$("#record tr:last").after(newRow);
   			    	}); 
   			    }
   			});
	   		//为每个表格元素添加按钮事件
	   	     $("#record td").click(function () {
	   	         var tdSeq = $(this).parent().find("td").index($(this));
	   	         var trSeq = $(this).parent().parent().find("tr").index($(this).parent());
	   	         alert("第" + (trSeq) + "行，第" + (tdSeq+1) + "列");
	   	     })
   			t=setTimeout("getReady()",10*1000);//每10秒钟刷新一次
   		}
   </script>
   <body onload="getReady()" >
   		<nav class="navbar navbar-inverse navbar-fixed-top">
   		   <div class="navbar-header">
		      <a class="navbar-brand" >LBS Service</a>
		   </div>
			<div id="navbar" class="navbar-collapse collapse">
			  <ul class="nav navbar-nav navbar-center">
				<li class="active"><a href="index.jsp"><span data-hover="首页">首页</a></li>
				<li><a href="location.jsp">定位服务</a></li>
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
				<li class = "text-right" ><a href="about.jsp">关于我们</a></li>
			  </ul>
			</div><!--/.nav-collapse -->
		  </div>
		</nav>
		<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<table id="record"  class="table" >
					<thead>
						<tr>
							<th>
								时间
							</th>
							<th>
								锚节点MAC地址
							</th>
							<th>
								锚节点位置
							<th>
								目标节点MAC地址
							</th>
							<th>
								信号强度
							</th>
							<th>
								操作
							</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
   </body>
</html>