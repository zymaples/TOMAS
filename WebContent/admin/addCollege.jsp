<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>学生主页</title>

<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.css" />
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div class="container">
		<!--登录表单-->
			<div style="margin: auto;">
				<div class="text-center">
					<h3>添加学院</h3>
				</div>
				<form class="form-horizontal col-sm-offset-3" action="${ pageContext.request.contextPath }/AdminServlet?method=addCollege" method="post">
					<div class="form-group">
						<label for="coid" class="col-sm-2 control-label">学院代码</label>
						<div class="col-sm-4">
							<input type="text" id="coid" class="form-control" name="coid" placeholder="请输入学院代码">
						</div>
					</div>
					<div class="form-group">
						<label for="coname" class="col-sm-2 control-label">学院名称</label>
						<div class="col-sm-4">
							<input type="text" id="coname" class="form-control" name="coname"  placeholder="请输入课程名称">
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-2"></div>
						<div class="col-md-2">
							<button type="submit" class="btn btn-success btn-block">提交</button>
						</div>
						<div class="col-md-2">
							<input type="reset" class="btn btn-warning btn-block" value="重置">
						</div>
					</div>
				</form>
		</div>
	</div>
</body>

</html>