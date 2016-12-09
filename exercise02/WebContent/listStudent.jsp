<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:directive.page import="dao.*" />
<jsp:directive.page import="util.*" />
<jsp:directive.page import="bean.*" />
<jsp:directive.page import="java.util.List" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>
<body>
	<table align=right>
		<tr>
			<td><a href="addStudent.jsp">新增學生資料</a></td>
			<td><a href="addDepartment.jsp">新增系所資料</a></td>
		</tr>
	</table>
	<br />
	<br />
	<form action="OperateStudentServlet" method="post">
		<table bgcolor="#CCCCCC" cellspacing=1 cellpadding=5 width=100%>
			<tr bgcolor=#DDDDDD>
				<th></th>
				<th>ID</th>
				<th>姓名</th>
				<th>年齡</th>
				<th>地址</th>
			</tr>

			<c:forEach items="${ studentlist }" var="student">
				<tr bgcolor="#FFFFFF">
					<td>${ student.id }</td>
					<td>${ student.name }</td>
					<td>${ student.age }</td>
					<td>${ student.address }</td>
					<td><a
						href="OperateStudentServlet?action=edit&id=${ student.id }">修改</a>
						<a href="OperateStudentServlet?action=delete&id=${ student.id }"
						onclick="return confirm('確定刪除?')">刪除</a></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>
