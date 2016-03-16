 <%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
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

   <body  >
   		<nav class="navbar navbar-inverse navbar-fixed-top">
   		   <div class="navbar-header">
		      <a class="navbar-brand" >LBS Service</a>
		   </div>
			<div id="navbar" class="navbar-collapse collapse ">
			  <ul class="nav navbar-nav ">
				<li ><a href="index.jsp"><span data-hover="首页">首页</a></li>
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
				<li class=" active" ><a href="about.jsp">关于我们</a></li>
			  </ul>
			</div><!--/.nav-collapse -->
		  </div>
		</nav>
		
		
		<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="hero-unit">
					<h1>
						LBS Service
					</h1>
					<p>
						这是一个室内定位服务。
					</p>
					<p>
						<a class="btn btn-primary btn-large" href="#">参看更多 »</a>
					</p>
				</div>
			</div>
		</div>
	</div>
   </body>
</html>