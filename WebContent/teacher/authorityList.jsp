<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>管理权限</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.css" />
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript">
	function query() {
		$("#myForm").attr("action", "TeacherServlet?method=showAuthorityList");
		$("#myForm").submit();
	}
</script>
</head>

<body>
	<div class="container">
		<jsp:include page="../header.jsp"></jsp:include>
		<form id="myForm" class="form-inline" action="TeacherServlet?method=addAuthority" method="post">
			<div class="col-md-3"></div>
			<div class="col-md-3">
				<div class="form-group">
				   <label>课程名</label>
				   <select name="cid"> 
				    <option value="0">全部</option>
					    <c:forEach var="course" items="${ courseList }">
						    <option value="${ course.cid }">${ course.cname }</option>
					    </c:forEach>
				    </select>
				    <button type="button" class="btn btn-default" onclick="query()">查询</button>
				  </div>
			</div>
			<div class="col-md-4">
				 <div class="form-group">
				    <label>学生用户名</label>
				    <input type="text" class="form-control" name="suname" placeholder="输入学生用户名">
				  </div>
			</div>
			
		  <div class="col-md-1">
		  	<button type="submit" class="btn btn-default">添加</button>
		  </div>
		</form>
		<table class="table table-striped">
			<tr align="center">
				<td>学生用户名</td>
				<td>学生姓名</td>
				<td>课程号</td>
				<td>课程名</td>
				<td>操作</td>
			</tr>
			<c:forEach var="authority" items="${ list }">
				<tr align="center">
					<td>${ authority.student.username }</td>
					<td>${ authority.student.realname }</td>
					<td>${ authority.course.cid }</td>
					<td>${ authority.course.cname }</td>
					<td><a class="btn btn-danger btn-xs" href="TeacherServlet?method=delAuthority&suname=${ authority.student.username }&cid=${ authority.course.cid }">删除</a></td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>

</body>

</html>