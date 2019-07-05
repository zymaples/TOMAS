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
		<table class="table table-striped">
			<tr align="center">
				<td>用户名</td>
				<td>教师姓名</td>
				<td>教师职称</td>
				<td>所属学院</td>
				<td>教师简介</td>
				<td>所授课程</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach var="teacher" items="${ list }">
				<tr align="center">
					<td>${ teacher.username }</td>
					<td>${ teacher.realname }</td>
					<td>${ teacher.position}</td>
					<td>${ teacher.college.coname }</td>
					
					<td><a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#intro${ teacher.username }">查看</a></td>
					<td><a class="btn btn-primary btn-xs" href="AdminServlet?method=showTeacherCourseList&username=${ teacher.username }">查看</a></td>
					<td><a class="btn btn-primary btn-xs" href="AdminServlet?method=updateTeacherUI&username=${ teacher.username }">修改</a></td>
					<td><a class="btn btn-danger btn-xs" data-toggle="modal" data-target="#delete${ teacher.username }">删除</a></td>
				</tr>
				
				
				<div class="modal fade" id="intro${ teacher.username }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
									&times;
								</button>
								<h4 class="modal-title" id="myModalLabel">
									教师简介
								</h4>
							</div>
							<div class="modal-body">
								<p>${ teacher.introduction }</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭
								</button>
							</div>
						</div>
					</div>
				</div>
				
				<form  class="form-horizontal" action="AdminServlet?method=delTeacher&username=${ teacher.username }" method="post">
					<div class="modal fade" id="delete${ teacher.username }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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