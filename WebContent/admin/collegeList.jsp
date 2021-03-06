<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title>学院列表浏览</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.css" />
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div class="container">
		<table class="table table-striped">
			<tr align="center">
				<td>学院代号</td>
				<td>学院名称</td>
				<td colspan="2"></td>
			</tr>
			<c:forEach var="college" items="${ list }">
				<tr align="center">
					<td>${ college.coid }</td>
					<td>${ college.coname }</td>
					<td><a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#update${ college.coid }">修改</a></td>
					<td><a class="btn btn-danger btn-xs" data-toggle="modal" data-target="#delete${ college.coid }">删除</a></td>
				</tr>
				
				<form class="form-horizontal" action="AdminServlet?method=updateCollege&coid=${ college.coid }" method="post">
				<div class="modal fade" id="update${ college.coid}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
									&times;
								</button>
								<h4 class="modal-title" id="myModalLabel">
									学院修改
								</h4>
							</div>
							<div class="modal-body">
								<div class="from-group">
									<div style="margin:auto;">
										<label>学院名称</label>
										<input type="text" style="width: 50%" name="coname" value="${ college.coname}">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭
								</button>
								<button type="submit" class="btn btn-primary">确定 </button>
							</div>
						</div>
					</div>
				</div>
				</form>
				
				<form class="form-horizontal" action="AdminServlet?method=delCollege&coid=${ college.coid }" method="post">
				<div class="modal fade" id="delete${ college.coid }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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