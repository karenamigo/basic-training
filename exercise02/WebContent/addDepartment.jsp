<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:directive.page import="bean.*" />
<jsp:directive.page import="dao.*" />
<jsp:directive.page import="util.*" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增系所</title>
</head>
<body>
	<table>
		<tr>
			<th>系所名稱</th>
			<th>修改/刪除</th>
		</tr>
		<c:forEach items="${departmentlist }" var="department">
			<tr>
				<td>${department.name }</td>
				<td><a
					href='DepartmentServlet?action=edit&id=${ department.id }'>修改</a> <a
					href='DepartmentServlet?action=delete&id=${ department.id }'
					onclick="return confirm('確定刪除?')">刪除</a></td>
			</tr>
		</c:forEach>
	</table>
	<form
		action="DepartmentServlet?action=${param.action == 'edit' ? 'save' : 'add' }"
		method="post">
		<input type="hidden" name="action"
			value="${ param.action == 'edit' ? 'save' : 'add'}"> <input
			type="hidden" name="id" value="${param.id }">
		<fieldset>
			<legend>${ param.action == 'edit' ? '修改系所資料' : '增加系所' }</legend>
			<table align=center>
				<tr>
					<td>系所名稱</td>
					<td><input type="text" name="name"
						value="${ department.name }" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="儲存" /> <input type="button"
						value="返回學生列表" onclick="location='StudentServlet?action=list'" /></td>
				</tr>
			</table>
		</fieldset>
	</form>

</body>
</html>