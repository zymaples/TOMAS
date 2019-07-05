<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>菜单</title>
<link rel="StyleSheet" href="${pageContext.request.contextPath}/css/dtree.css" type="text/css" />
</head>
<body style="background-color: #f5f5f5;">
<div style="margin-top: 10px; margin-left: 20px;">
	<div class="dtree">
		<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
		<script type="text/javascript">   
			d = new dTree('d');
			d.add('01',-1,'系统菜单树');
			
			d.add('0101','01','课程管理','','','mainFrame');
			//param1: 当前节点id
			//param2: 父节点id
			//param3: 节点上的文字描述
			//param4: 跳转路径
			//param5: 提示信息
			//param6: 发生变化的frame的name属性值
			d.add('010101','0101','课程列表','${pageContext.request.contextPath}/AdminServlet?method=showCourseList','','mainFrame');
			d.add('010102','0101','添加课程','${pageContext.request.contextPath}/admin/addCourse.jsp','','mainFrame');
			
			d.add('0102','01','教师管理');
			d.add('010201','0102','教师列表','${pageContext.request.contextPath}/AdminServlet?method=showTeacherList','提示信息','mainFrame');
			d.add('010202','0102','添加教师','${pageContext.request.contextPath}/admin/addTeacher.jsp','提示信息','mainFrame');
			
			d.add('0103','01','学院管理');
			d.add('010301','0103','学院列表','${pageContext.request.contextPath}/AdminServlet?method=showCollegeList','','mainFrame');
			d.add('010302','0103','添加学院','${pageContext.request.contextPath}/admin/addCollege.jsp','','mainFrame');
			
			d.add('0104','01','留言管理');
			d.add('010401','0104','留言列表','${pageContext.request.contextPath}/AdminServlet?method=showMsgList','','mainFrame');
			
			d.add('0105','01','解答管理');
			d.add('010501','0105','解答列表','${pageContext.request.contextPath}/AdminServlet?method=showAnswerList','','mainFrame');
			d.add('0106','01','个人中心');
			d.add('010601','0106','修改密码','${pageContext.request.contextPath}/admin/changePassword.jsp','','mainFrame');
			d.add('010602','0106','退出','${pageContext.request.contextPath}/AdminServlet?method=logOut','','parent');
			document.write(d);
		</script>
	</div>
</div>
</body>
</html>
