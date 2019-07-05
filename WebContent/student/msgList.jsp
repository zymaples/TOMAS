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
		
		<form class="form-inline" action="StudentServlet?method=showMsgList&currentPage=1" method="post">
			<div class="col-md-2"></div>
			<div class="col-md-3">
				<div class="form-group">
				    <label for="courseName">课程</label>
				    <input type="text" class="form-control" id="courseName" name="courseName" placeholder="输入课程名称">
				  </div>
			</div>
			<div class="col-md-3">
				 <div class="form-group">
				    <label for="teacher">教师</label>
				    <input type="text" class="form-control" id="teacher" name="teacherName" placeholder="输入教师姓名">
				  </div>
			</div>
			<div class="col-md-3">
				 <div class="form-group">
				    <label for="keywords">关键字</label>
				    <input type="text" class="form-control" id="keywords" name="keywords" placeholder="输入提问内容关键字">
				  </div>
			</div>
		  <div class="col-md-1">
		  	<button type="submit" class="btn btn-default">查询</button>
		  </div>
		</form>
		<table class="table table-striped">
			<tr align="center">
				<td>标题</td>
				<td>日期</td>
				<td>所属课程</td>
				<td>授课教师</td>
				<td>提问内容</td>
				<td>操作</td>
			</tr>
			<c:forEach var="message" items="${ pageModel.list }">
				<tr align="center">
					<td>${ message.title }</td>
					<td>${ message.date }</td>
					<td>${ message.course.cname }</td>
					<td>${ message.course.teacher.realname }</td>
					<td><a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#content${ message.mid }">查看</a></td>
					<td><a class="btn btn-primary btn-xs" href="StudentServlet?method=showCourseInfo&cid=${ message.course.cid }">查看详情</a></td>
				</tr>
				<div class="modal fade" id="content${ message.mid }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
									&times;
								</button>
								<h4 class="modal-title" id="myModalLabel">
									提问内容
								</h4>
							</div>
							<div class="modal-body">
								<p>${ message.content }</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭
								</button>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
			<tr align="center">
			  	<td colspan="6">
			  		<nav aria-label="Page navigation">
					  <ul class="pagination">
						<c:if test="${pageModel.currentPage !=1 }">
							<li>
						      <a href="StudentServlet?method=showMsgList&currentPage=${pageModel.currentPage-1 }" aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						      </a>
						    </li>
				  		</c:if>
					    
					    <c:forEach begin="1" end="${ pageModel.totalPage }" var="i">
				  			<c:if test="${ pageModel.currentPage == i }">
				  				<li><a href="javascript:volid(0);">${ i }</a><li>
				  			</c:if>
				  			<c:if test="${ pageModel.currentPage != i }">
				  				<li><a href="StudentServlet?method=showMsgList&currentPage=${ i }">${ i }</a><li>
				  			</c:if>
			  			</c:forEach>
			  			<c:if test="${pageModel.currentPage !=pageModel.totalPage }">
			  				<li>
						      <a href="StudentServlet?method=showMsgList&currentPage=${pageModel.currentPage+1 }" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						      </a>
					    	</li>
			  			</c:if>
					    
					  </ul>
					</nav>
			  	</td>
			  </tr>
		</table>
		<jsp:include page="../footer.jsp"></jsp:include>
	</div>

</body>

</html>