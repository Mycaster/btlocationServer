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
      <link href="bootstrap/css/signin.css" rel="stylesheet">
      <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	  <script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
	  <script src="http://code.jquery.com/jquery-latest.js"></script>
      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src="bootstrap/js/bootstrap.min.js"></script>
   </head>
    <script type="text/javascript">   			
   		$(window).resize(function () { 
			 $('body').css('padding-top', parseInt($('#navbar').css("height"))+15);
		});
		$(window).load(function () { 
		 $('body').css('padding-top', parseInt($('#navbar').css("height"))+15);  
		});
	</script>
   <body >
   		<nav class="navbar navbar-inverse navbar-fixed-top">
   		  <div class="navbar-header">
		      <a class="navbar-brand" >LBS Service</a>
		   </div>
			<div id="navbar" class="navbar-collapse collapse">
			  <ul class="nav navbar-nav">
				<li ><a href="index.jsp"><span data-hover="首页">首页</a></li>
				<li ><a href="location.jsp">定位服务</a></li>
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
				<li  class="active"><a href="login.jsp">登录</a></li>
				<li class = "text-right" ><a href="about.jsp">关于我们</a></li>
			  </ul>
			</div><!--/.nav-collapse -->
		  </div>
		</nav>
		<div class="container">
		      <form class="form-signin">
		        <h2 class="form-signin-heading">请登录</h2>
		        <input type="text" class="form-control" placeholder="Email address" required autofocus>
		        <input type="password" class="form-control" placeholder="Password" required>
		        <label class="checkbox">
		          <input type="checkbox" value="remember-me"> 记住我
		        </label>
		        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		        <button class="btn btn-lg btn-primary btn-block" type="submit">注册？</button>
		      </form>
    	</div> <!-- /container -->

		
   </body>
</html>