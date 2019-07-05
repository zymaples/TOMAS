<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.css" />
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
 </head>
<frameset rows="15%, 85%" frameborder="yes" border="1px" bordercolor="#f5f5f5">
   <frame src="top.jsp" name="topFrame">
   <frameset cols="10%, 80%">
   	<frame src="left.jsp" name="leftFrame">
   	<frame src="welcome.jsp" name="mainFrame">
   </frameset>
   <frame src="../footer.jsp">
</frameset>
</html>
