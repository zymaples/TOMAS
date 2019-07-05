<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-3">
				<div class="list-group">
					<a href="#" class="list-group-item active">最新提问 </a> 
					<c:forEach var="message" items="${ msgs }" begin="0" end="4">
						<a href="StudentServlet?method=showCourseInfo&cid=${ message.course.cid }" class="list-group-item">${ message.title }</a>
					</c:forEach>
				</div>
			</div>

			<div class="col-md-3">
				<div class="list-group">
					<a href="#" class="list-group-item active">回复我的<span class="badge">${ cnt }</span></a> 
					<c:forEach var="answer" items="${ list }">
						<a href="StudentServlet?method=showCourseInfo&cid=${ answer.message.course.cid }" class="list-group-item">${ answer.message.title }</a>
					</c:forEach>
				</div>
			</div>

			<div class="col-md-3">
				<div>
					<a href="${ pageContext.request.contextPath }/StudentServlet?method=showCourseList"><button type="button" class="btn btn-primary">浏览课程信息</button></a>
				</div>
				<br />
				<div>
					<a href="${ pageContext.request.contextPath }/StudentServlet?method=showMsgList&currentPage=1"><button type="button" class="btn btn-primary">查看所有提问</button></a>
				</div>
				<br />
			</div>
		</div>
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>
</body>

</html>