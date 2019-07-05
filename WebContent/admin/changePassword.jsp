<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>修改密码</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.css" />
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript">
	function checkPassword() {
		var flag = false;
		$.ajaxSettings.async = false;
		var password = $("#oldPassword").val();
		$.post("/TOMAS/AdminServlet?method=checkPassword", {password:password}, function(data) {
			if (data == 0) {
				$("#s1").html("<font color='red'>原始密码不正确</font>");
			} else {
				$("#s1").html("");
				flag = true;
			}
		})
		return flag;
	}

	function checkNew() {
		var length = $("#newPassword").val().length;
		if (length <3) {
			$("#s2").html("<font color='red'>长度不能小于3</font>");
			return false;
		} else {
			$("#s2").html("<font color='green'>密码可用</font>");
			return true;
		}
	}
	function checkForm() {
		return checkPassword() && checkNew()
	}
</script>
</head>
<body>
	<div class="container">
		<div style="margin: auto;">
			<div class="text-center">
				<h3>修改密码</h3>
				<font color="green">${ msg }</font>
			</div>
			<form class="form-horizontal col-sm-offset-3" onsubmit="return checkForm()" action="${ pageContext.request.contextPath }/AdminServlet?method=changePassword" method="post">
				<div class="form-group">
					<label for="oldPassword" class="col-sm-2 control-label">旧密码</label>
					<div class="col-sm-4">
						<input type="password" id="oldPassword" class="form-control" name="oldPassword" placeholder="请输入旧密码"> 
					</div> <span id="s1"></span>
				</div>
				<div class="form-group">
					<label for="newPassword" class="col-sm-2 control-label">新密码</label>
					<div class="col-sm-4">
						<input type="password" id="newPassword" class="form-control" name="newPassword"  placeholder="请输入新密码" onkeyup="checkNew()"> 
					</div> <span id="s2"></span>
				</div>
				<div class="form-group">
					<div class="col-md-2"></div>
					<div class="col-md-2">
						<button type="submit" class="btn btn-success btn-block">提交</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>

</html>