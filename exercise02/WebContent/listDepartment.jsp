<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:directive.page import="dao.*" />
<jsp:directive.page import="util.*" />
<jsp:directive.page import="bean.*" />
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
	<%
		List<Department> departmentlist = DepartmentDAO.listDepartments();
		request.setAttribute("departmentlist", departmentlist);
	%>

	<table>
		<tr>
			<th>系所id</th>
			<th>系所名稱</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${departmentlist }" var="department">
			<tr>
				<td>${department.id }</td>
				<td>${department.name }</td>
				<td><a
					href="addDepartment.jsp?action=edit&id=${department.id }">修改</a> <a
					href="addDeparment.jsp?action=del&id=${department.id }"
					onclick="return confirm('確定刪除?')">刪除</a></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>