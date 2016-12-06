<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:directive.page import="java.util.List" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:directive.page import="bean.*" />
<jsp:directive.page import="dao.*" />
<jsp:directive.page import="util.*" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("UTF-8");
	String action = request.getParameter("action");

	if ("add".equals(action)) {
		Department department = new Department();
		department.setName(request.getParameter("name"));

		DepartmentDAO.insert(department);
	} else if ("del".equals(action)) {
		DepartmentDAO.delete(new Integer(request.getParameter("id")));
	} else if ("edit".equals(action)) {
		Department department = DepartmentDAO.find(new Integer(request.getParameter("id")));
		request.setAttribute("department", department);
	} else if ("save".equals(action)) {
		Department department = DepartmentDAO.find(new Integer(request.getParameter("id")));
		department.setName(request.getParameter("name"));
		DepartmentDAO.save(department);
	}
%>
<%
	List<Department> departmentList = DepartmentDAO.listDepartments();
	request.setAttribute("departmentList", departmentList);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增系所</title>
</head>
<body>
	<table>
		<tr>
			<th>系所名稱</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${ departmentList }" var="department">
			<tr>
				<td>${ deparment.name }</td>
				<td><a
					href='addDepartment.jsp?action=edit&id=${ department.id }'>修改</a> <a
					href='addDepartment.jsp?action=del&id=${ department.id }'>刪除</a></td>
			</tr>
		</c:forEach>
	</table>

	<form action="addDepartment.jsp" method="post">
		<input type="hidden" name="action"
			value="${ param.action == 'edit' ? 'save' : 'add' }"> <input
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
					<td><input type="submit" value="傳送系所資訊" /> <input
						type="button" value="返回系所列表"
						onclick="location='addDepartment.jsp'" /> <input type="button"
						value="返回學生列表" onclick="location='listStudent.jsp'" /></td>
				</tr>
			</table>
		</fieldset>
	</form>

</body>
</html>