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
			<jsp:include page="../header.jsp"></jsp:include>
			<div align="center">
				<h2 style="color:red;">对不起你没有权限！</h2>
			</div>
			<jsp:include page="../footer.jsp"></jsp:include>
		</div>

	</body>

</html>