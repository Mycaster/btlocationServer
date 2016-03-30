 <%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
   <head>
 	  <%@include file="header.jsp"%>
   </head>
   <body  >
	  <%request.setAttribute("MENU_INDEX", "about");%>
      <!-- 包含导航栏 -->
 	  <%@include file="navbar.jsp"%>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="col-md-8 col-md-offset-2">
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