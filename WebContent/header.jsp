<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<!-- Logo部分-->
	<div class="row">
		<div class="col-md-2">
			<a href="${ pageContext.request.contextPath }/index.jsp"><img
				src="${ pageContext.request.contextPath }/img/logo.png" width="80px"
				height="80px" /></a>
		</div>
		<div class="col-md-6">
			<h1 style="color: #269ABC;">教学在线留言答疑系统</h1>
		</div>
	</div>

	<!-- 导航栏 -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand"
					href="${ pageContext.request.contextPath }/index.jsp">首页</a>
			</div>
			<c:if test="${ not empty student }">
				<ul class="nav navbar-nav navbar-left">
					<li><a href="${ pageContext.request.contextPath }/StudentServlet?method=showIndex">主页</a></li>
				</ul>
			</c:if>
			<c:if test="${ not empty teacher }">
				<ul class="nav navbar-nav navbar-left">
					<li><a href="${ pageContext.request.contextPath }/TeacherServlet?method=showIndex">主页</a></li>
				</ul>
			</c:if>
			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
			<c:if test="${ not empty student }">
				<p class="navbar-text navbar-right">学生:${ student.realname },欢迎！</p>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">个人中心<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${ pageContext.request.contextPath }/StudentServlet?method=showMyMsgList">我的提问</a></li>
							<li><a href="${ pageContext.request.contextPath }/student/changePassword.jsp">修改密码</a></li>
						</ul>
					</li>
					<li><a href="${ pageContext.request.contextPath }/StudentServlet?method=logOut">退出</a></li>
				</ul>
			</c:if>
			<c:if test="${ not empty teacher }">
				<p class="navbar-text navbar-right">教师:${ teacher.realname },欢迎！</p>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">个人中心<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${ pageContext.request.contextPath }/TeacherServlet?method=showMyAnswerList">我的回答</a></li>
							<li><a href="${ pageContext.request.contextPath }/teacher/changePassword.jsp">修改密码</a></li>
						</ul>
					</li>
					<li><a href="${ pageContext.request.contextPath }/TeacherServlet?method=logOut">退出</a></li>
				</ul>
			</c:if>
			<c:if test="${ empty student and empty teacher }">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${ pageContext.request.contextPath }/login.jsp">登录</a></li>
					<li><a href="${ pageContext.request.contextPath }/register.jsp">注册</a></li>
				</ul>
			</c:if>
		</div>
	</nav>
</body>