<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:directive.page import="dao.*" />
<jsp:directive.page import="util.*" />
<jsp:directive.page import="bean.*" />
<jsp:directive.page import="java.util.List" />
<%
	List<Student> studentlist = StudentDAO.listAllStudents();
	request.setAttribute("studentlist", studentlist);
%>
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
	<form action="operateStudent.jsp" method="post">
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
					<td><input type="checkbox" name="id" value="${ student.id }" /></td>
					<td>${ student.id }</td>
					<td>${ student.name }</td>
					<td>${ student.age }</td>
					<td>${ student.address }</td>
					<td><a
						href="operateStudent.jsp?action=edit&id=${ student.id }">修改</a> <a
						href="operateStudent.jsp?action=del&id=${ student.id }"
						onclick="return confirm('確定刪除?')">刪除</a></td>
				</tr>
			</c:forEach>

		</table>
		<table align=left>
			<tr>
				<td><input type='hidden' value='del' name='action'> <a
					href='#'
					onclick="var array=document.getElementsByName('id');for(var i=0; i<array.length;
					i++){array[i].checked=true;}">全選</a> <a href='#'
					onclick="var array=document.getElementsByName('id');for(var i=0; i<array.length;
					i++){array[i].checked=false;}">取消全選</a> <input type='submit'
					onclick="return confirm('即將刪除所選擇的記錄。是否刪除？'); " value='刪除'>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
