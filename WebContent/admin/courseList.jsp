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
		<form class="form-inline" action="AdminServlet?method=showCourseList" method="post">
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
				<td>课程简介</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach var="course" items="${ list }">
				<tr align="center">
					<td>${ course.cid }</td>
					<td>${ course.college.coname }</td>
					<td>${ course.cname }</td>
					<td>${ course.teacher.realname }</td>
					<td><a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#intro${ course.cid }">查看</a></td>
					<td><a class="btn btn-primary btn-xs" href="AdminServlet?method=updateCourseUI&cid=${ course.cid }">修改</a></td>
					<td><a class="btn btn-danger btn-xs" data-toggle="modal" data-target="#delete${ course.cid }">删除</a></td>
				</tr>
				<div class="modal fade" id="intro${ course.cid }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
										&times;
									</button>
									<h4 class="modal-title" id="myModalLabel">
										课程简介
									</h4>
								</div>
								<div class="modal-body">
									<p>${ course.introduction }</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭
									</button>
								</div>
							</div>
						</div>
					</div>
				<form  class="form-horizontal" action="AdminServlet?method=delCourse&cid=${ course.cid }" method="post">
					<div class="modal fade" id="delete${ course.cid }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
										&times;
									</button>
									<h4 class="modal-title" id="myModalLabel">
										提示
									</h4>
								</div>
								<div class="modal-body">
									<p>是否确认删除？？</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">取消
									</button>
									<button type="submit" class="btn btn-primary">确定 
									</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</c:forEach>
		</table>
	</div>

</body>

</html>