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
		<jsp:include page="../header.jsp"></jsp:include>
		<!--登录表单-->
			<div style="margin: auto;">
				<div class="text-center">
					<h3>修改提问</h3>
				</div>
				<form class="form-horizontal col-sm-offset-3" action="StudentServlet?method=updateMsg&mid=${ message.mid }" method="post">
					<div class="form-group">
						<label for="course" class="col-sm-2 control-label">标题:</label>
						<div class="col-sm-4">
							<input type="text" id="course" class="form-control" name="title" value="${ message.title }" placeholder="请输入标题">
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-1"></div>
						<div class="col-sm-6">
							<textarea rows="10" cols="60" placeholder="请输入内容" name="content">${ message.content }</textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-2"></div>
						<div class="col-md-2">
							<button class="btn btn-success btn-block">提交</button>
						</div>
						<div class="col-md-2">
							<input type="reset" class="btn btn-warning btn-block" value="重置">
						</div>
					</div>
				</form>
			<jsp:include page="../footer.jsp"></jsp:include>
		</div>
	</div>
</body>

</html>