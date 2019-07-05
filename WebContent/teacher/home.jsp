<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>老师主页</title>
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
					<a href="#" class="list-group-item active">我的课程</a> 
					<c:forEach var="course" items="${ courseList }">
						<a href="TeacherServlet?method=showCourseInfo&cid=${ course.cid }" class="list-group-item">${ course.cname }</a>
					</c:forEach>
				</div>
			</div>
			
			<div class="col-md-3">
				<div class="list-group">
					<a href="#" class="list-group-item active">待回复提问 <span class="badge">${ cnt }</span></a> 
					<c:forEach var="message" items="${ list }">
						<a href="TeacherServlet?method=showCourseInfo&cid=${ message.course.cid }" class="list-group-item">${ message.title }</a> 
					</c:forEach>
				</div>
			</div>
			
			<div class="col-md-3">
				<div>
					<a href="${ pageContext.request.contextPath }/TeacherServlet?method=showAuthorityList&cid=0"><button type="button" class="btn btn-primary">管理学生权限信息</button></a>
				</div>
				<br />
				<div>
					<a href="${ pageContext.request.contextPath }/TeacherServlet?method=showMsgList"><button type="button" class="btn btn-primary">查看所有学生提问</button></a>
				</div>
				<br />
			</div>
		</div>
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>
</body>

</html>