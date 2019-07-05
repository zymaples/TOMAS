<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>课程详情</title>

<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.css" />
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>
	<div class="container">
		<jsp:include page="header.jsp"></jsp:include>
		<div style="margin:auto; width:50%">
			<span class="glyphicon glyphicon-tag" aria-hidden="true" style="color:green;font-size:18px;"></span>
			<span style="font-size:16px;">课程名称</span>
			<p style="text-indent:2em; font-size:12px;">${ course.cname }</p>
			<span class="glyphicon glyphicon-education" aria-hidden="true" style="color:green;font-size:18px;"></span>
			<span style="font-size:16px;">开课学院</span>
			<p style="text-indent:2em; font-size:12px;">${ course.college.coname }</p>
			<span class="glyphicon glyphicon-user" aria-hidden="true" style="color:green;font-size:18px;"></span>
			<span style="font-size:16px;">课程老师</span>
			<p style="text-indent:2em; font-size:12px;">${ course.teacher.realname } </p>
			<span class="glyphicon glyphicon-tasks" aria-hidden="true" style="color:green;font-size:18px;"></span>
			<span style="font-size:16px; ">课程简介</span>
			<p style="text-indent:2em; font-size:12px;">${ course.introduction } </p>
			<hr style="height: 1px; background-color: #23527C" />
			<span class="glyphicon glyphicon-comment" aria-hidden="true" style="color:green;font-size:18px;"></span>
			<span style="font-size:16px; ">课程留言</span>
			<hr>
			<c:forEach var="message" items="${ list }" varStatus="status">
				<p style="font-size:14px;">${ message.title } ${ message.date } </p>
				<p style="font-size:12px;">${ message.content } </p>
				<c:if test="${ not empty teacher }">
					<a class="btn btn-primary btn-sm" data-toggle="modal" data-target="#answer${ status.index }">解答</a>
					<a class="btn btn-danger btn-sm" data-toggle="modal" data-target="#delete${ status.index }">删除</a>
					<form method="post" action="TeacherServlet?method=answer&mid=${ message.mid }&cid=${ course.cid }" id="answerForm" class="form-horizontal" role="form">
						<div class="modal fade" id="answer${ status.index }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
										&times;
									</button>
									<h4 class="modal-title" id="myModalLabel">
										欢迎解答
									</h4>
								</div>
								<div class="modal-body">
		                    		<div class="form-group" style="margin:auto;">
		                    			<textarea style="width:100%;" rows="5" cols="50" name="content" placeholder="请输入文本内容"></textarea>
		                    		</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭
									</button>
									<button type="submit" class="btn btn-primary">确定 
									</button>
								</div>
							</div>
							</div>
						</div>
					</form>
					
					<form method="post" action="TeacherServlet?method=delMsgIn&mid=${ message.mid }&cid=${ course.cid }" id="deleteForm" class="form-horizontal" role="form">	
						<div class="modal fade" id="delete${ status.index }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
				</c:if>
				<span class="glyphicon glyphicon-user" aria-hidden="true" style="color:green;font-size:14px;"></span>
				<span style="font-size:14px;">老师解答</span>
				<c:if test="${ empty message.answers}"><p>未解答</p></c:if>
				<c:forEach var="answer" items="${ message.answers }">
					<p style="font-size:12px;">${ answer.content }&nbsp;&nbsp;&nbsp;&nbsp;${ answer.date }</p>
				</c:forEach>
				<hr>
			</c:forEach>
			
			
		</div>
		
		<hr/>

		<jsp:include page="footer.jsp"></jsp:include>
	</div>

</body>

</html>