<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>

		<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.css" />
		<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
		<script type="text/javascript" src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<meta name="viewport" content="width=device-width, initial-scale=1">
	</head>

	<body>
		<div class="container">
			<jsp:include page="header.jsp"></jsp:include>
			<!--登录表单-->
			<div style="margin: auto;">
				<div class="text-center">
					<h3>用户登录</h3>
					<p style="color:red;">${ msg }</p>
				</div>
				<form class="form-horizontal col-sm-offset-3" action="LoginServlet?method=login" method="post">
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名：</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="username" placeholder="请输入用户名">
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-2 control-label">密码：</label>
						<div class="col-sm-4">
							<input type="password" class="form-control" name="password" placeholder="请输入密码">
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-2"></div>
						<div class="col-md-1"><input type="radio" name="userType" id="radio1"  value="student" checked="checked"><label for="radio1">学生</label></div>
						<div class="col-md-1"><input type="radio" name="userType" id="radio2" value="teacher"><label for="radio2">教师</label></div>
						<div class="col-md-2"><input type="radio" name="userType" id="radio3" value="admin"><label for="radio3">管理员</label></div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-2 col-xs-6">
							<button class="btn btn-success btn-block">登录</button>
						</div>
						<div class="col-sm-2  col-xs-6">
							<a class="btn btn-warning btn-block" href="register.jsp">注册</a>
						</div>
					</div>
				</form>
			</div>
			<jsp:include page="footer.jsp"></jsp:include>
		</div>

	</body>

</html>