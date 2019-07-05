<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>课程列表浏览</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.css" />
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
		<form class="form-inline" action="StudentServlet?method=showCourseList" method="post">
			<div class="col-md-5"></div>
			<div class="col-md-3">
				<div class="form-group">
				    <label for="college">学院</label>
				    <input type="text" class="form-control" id="college" name="collegeName" placeholder="输入学院名称">
				  </div>
			</div>
			<div class="col-md-3">
				 <div class="form-group">
				    <label for="teacher">教师</label>
				    <input type="text" class="form-control" id="teacher" name="teacherName" placeholder="输入教师姓名">
				  </div>
			</div>
		  <div class="col-md-1">
		  	<button type="submit" class="btn btn-default">查询</button>
		  </div>
		</form>
		<table class="table table-striped">
			<tr align="center">
				<td>课程号</td>
				<td>开课学院</td>
				<td>课程名</td>
				<td>任课教师</td>
				<td></td>
				<td></td>
			</tr>
			<c:forEach var="course" items="${ list }">
				<tr align="center">
					<td>${ course.cid }</td>
					<td>${ course.college.coname }</td>
					<td>${ course.cname }</td>
					<td>${ course.teacher.realname }</td>
					<td><a class="btn btn-primary btn-xs" href="StudentServlet?method=showCourseInfo&cid=${ course.cid }">查看详情</a></td>
					<td><a class="btn btn-success btn-xs" href="StudentServlet?method=askUI&cid=${ course.cid }">我要提问</a></td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>

</body>

</html>