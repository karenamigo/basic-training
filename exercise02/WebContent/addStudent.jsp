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
<title>Create and Update</title>
<%
	GenericDao<Department> departmentDao = new GenericDao<Department>();
	request.setAttribute("departmentlist", departmentDao.listAll("from Department"));
%>
</head>
<body>
	<form
		action="OperateStudentServlet?action=${param.action == 'edit' ? 'save' : 'add' }"
		method="post">
		<input type="hidden" name="action"
			value="${ param.action == 'edit' ? 'save' : 'add'}"> <input
			type="hidden" name="id" value="${student.id }">

		<fieldset>
			<legend>"${param.action == 'edit'? '修改人員資料':'新增學生資料'}"</legend>
			<table align=center>
				<tr>
					<td>姓名</td>
					<td><input type="text" name="name"
						value="${param.action == 'edit'? student.name : ''}" /></td>
				</tr>
				<tr>
					<td>年齡</td>
					<td><input type="text" name="age"
						value="${param.action == 'edit'? student.age : '' }" /></td>
				</tr>
				<tr>
					<td>地址</td>
					<td><input type="text" name="address"
						value="${param.action == 'edit'? student.address : '' }" /></td>
				</tr>
				<tr>
					<td>科系</td>
					<td><select name="departmentId">
							<c:forEach items="${ departmentlist }" var="department">
								<option value="${ department.id }">${ department.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>

					<td></td>
					<td><input type="submit"
						value="${param.action == 'edit'? '儲存':'增加學生資料'}" /> <input
						type="button" value="返回"
						onclick="location='OperateStudentServlet?action=list' " /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>