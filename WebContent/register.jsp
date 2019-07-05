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
		<script type="text/javascript">
		function checkPassword() {
			var length = $("#password").val().length;
			if (length <3) {
				$("#s2").html("<font color='red'>长度不能小于3</font>");
				return false;
			} else {
				$("#s2").html("<font color='green'>密码可用</font>");
				return true;
			}
		}
		function checkUsername() {
			var flag = false;
			$.ajaxSettings.async = false;
			var username = $("#username").val();
			$.post("/TOMAS/StudentServlet?method=checkUsername", {username:username}, function(data) {
				if (data == 0) {
					$("#s1").html("<font color='red'>用户名已存在</font>");
					flag = false;
				} else if (username.length < 3) {
					$("#s1").html("<font color='red'>用户名长度太短</font>");
					flag = false;
				} else {
					$("#s1").html("<font color='green'>用户名可用</font>");
					flag = true;
				}
			})
			return flag;
		}
		
		function checkRealname() {
			var realname = $("#realname").val();
			if (realname.length == 0) {
				$("#s3").html("<font color='red'>不能为空</font>");
				return false;
			} 
			$("#s3").html("");
			return true;
		}

		function checkForm() {
			return checkUsername() && checkPassword() && checkRealname();
		}
		</script>
	</head>

	<body>
		<div class="container">
			<jsp:include page="header.jsp"></jsp:include>
			<!--注册表单-->
			<div class="container">
				<div class="row">
					<div class="col-sm-offset-3 col-sm-6 text-center">
						<h3>用户注册</h3>
					</div>
				</div>
				<form class="form-horizontal col-sm-offset-3" id="loginform" onsubmit="return checkForm()" action="StudentServlet?method=register"  method="post">
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名：</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="username" name="username" onkeyup="checkUsername()" placeholder="请输入用户名">
						</div> <span id="s1"></span>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-2 control-label">密码：</label>
						<div class="col-sm-4">
							<input type="password" class="form-control" id="password" name="password" onkeyup="checkPassword()" placeholder="请输入密码">
						</div> <span id="s2"></span>
					</div>
					<div class="form-group">
						<label for="realname" class="col-sm-2 control-label">真实姓名：</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="realname" name="realname" onkeyup="checkRealname()" placeholder="请输入姓名">
						</div> <span id="s3"></span>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-2 col-xs-6">
							<button class="btn btn-success btn-block">注册</button>
						</div>
					</div>
				</form>
			</div>

			<jsp:include page="footer.jsp"></jsp:include>
		</div>

	</body>

</html>