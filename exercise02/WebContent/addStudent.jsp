<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:directive.page import="bean.*" />
<jsp:directive.page import="dao.*" />
<jsp:directive.page import="util.*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create and Update</title>

</head>
<body>

	<%
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		boolean isEdit = "edit".equals(action);
		Student student = new Student();
		if ("add".equals(action)) {
			StudentDAO.insert(student);
		}
		if (isEdit) {
			int id = Integer.parseInt(request.getParameter("id"));
			student = StudentDAO.find(id);
			request.setAttribute("student", student);
		}
	%>
	<form action="operateStudent.jsp" method="post">
		<input type="hidden" name="action"
			value="<%=isEdit ? "save" : "add"%>"> <input type="hidden"
			name="id" value="${ student.id }">

		<fieldset>
			<legend><%=isEdit ? "修改人員資料" : "新增學生資料"%></legend>
			<table align=center>
				<tr>
					<td>姓名</td>
					<td><input type="text" name="name"
						value="<%=isEdit ? student.getName() : ""%> " /></td>
				</tr>
				<tr>
					<td>年齡</td>
					<td><input type="text" name="age"
						value="<%=isEdit ? student.getAge() : ""%>" /></td>
				</tr>
				<tr>
					<td>地址</td>
					<td><input type="text" name="address"
						value="<%=isEdit ? student.getAddress() : ""%>" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="<%=isEdit ? "儲存" : "增加學生資訊"%>" />
						<input type="button" value="返回" onclick="history.go(-1); " /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>