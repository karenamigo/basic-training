<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:directive.page import="dao.*" />
<jsp:directive.page import="util.*" />
<jsp:directive.page import="bean.*" />


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


	<table bgcolor="#CCCCCC" cellspacing=3 cellpadding=6 width=100%>
		<tr bgcolor=#DDDDDD>
			<th>ID</th>
			<th>姓名</th>
			<th>年齡</th>
			<th>地址</th>
			<th>科系</th>
			<th>修課狀況</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${ studentlist}" var="student">
			<tr bgcolor="#FFFFFF">
				<td>${ student.id }</td>
				<td>${ student.name }</td>
				<td>${ student.age }</td>
				<td>${ student.address }</td>
				<td>${ student.department.name }</td>
				<td>
					<form action="listStdCourse.jsp?action=showcourse" method="post">
						<input type="hidden" name="id" value=${student.id } /> <input
							type="hidden" name="action" value=${param.action } /> <input
							type="submit" value="修課列表" name="course" />
					</form>
				</td>
				<td>
					<form action="StudentServlet?action=edit" method="post">
						<input type="hidden" name="id" value=${student.id } /> <input
							type="submit" name="edit" value="修改" />
					</form>
					<form action="StudentServlet?action=delete" method="post">
						<input type="hidden" name="id" value=${student.id } /> <input
							type="submit" value="刪除" name="del"
							onclick="return confirm('確定刪除?')" />
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>
