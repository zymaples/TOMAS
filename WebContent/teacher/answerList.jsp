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
		<jsp:include page="../header.jsp"></jsp:include>
		<table class="table table-striped">
			<tr align="center">
				<td>提问标题</td>
				<td>日期</td>
				<td>所属课程</td>
				<td>回复内容</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach var="answer" items="${ list }">
				<tr align="center">
					<td>${ answer.message.title }</td>
					<td>${ answer.date }</td>
					<td>${ answer.message.course.cname }</td>
					<td><a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#content${ answer.aid }">查看</a></td>
					<td><a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#update${ answer.aid }">修改</a></td>
					<td><a class="btn btn-danger btn-xs" href="TeacherServlet?method=delAnswer&aid=${ answer.aid }">删除</a></td>
				</tr>
				
				<div class="modal fade" id="content${ answer.aid }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
									&times;
								</button>
								<h4 class="modal-title" id="myModalLabel">
									回答内容
								</h4>
							</div>
							<div class="modal-body">
								<p>${ answer.content }</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭
								</button>
							</div>
						</div>
					</div>
				</div>
				
				<form method="post" action="TeacherServlet?method=updateAnswer&aid=${ answer.aid }" id="answerForm" class="form-horizontal" role="form">
						<div class="modal fade" id="update${ answer.aid }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
										&times;
									</button>
									<h4 class="modal-title" id="myModalLabel">
										修改解答
									</h4>
								</div>
								<div class="modal-body">
		                    		<div class="form-group" style="margin:auto;">
		                    			<textarea style="width:100%;" rows="5" cols="50" name="content" placeholder="请输入文本内容">${ answer.content }</textarea>
		                    		</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭
									</button>
									<button type="submit" class="btn btn-primary">确定 
									</button>
								</div>
							</div><!-- /.modal-content -->
							</div>
						</div>
					</form>
			</c:forEach>
		</table>
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>

</body>

</html>