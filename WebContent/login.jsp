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
      <link href="bootstrap/css/signin.css" rel="stylesheet">
   </head>
   <body >
 	  <%request.setAttribute("MENU_INDEX", "login");%>
      <!-- 包含导航栏 -->
 	  <%@include file="navbar.jsp"%>
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