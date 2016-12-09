<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.List"%>
<%@page import="bean.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body, div, td, th {
	font-size: 12px;
}

table {
	border-collapse: collapse;
	border: 1px solid #000000;
}

th, td {
	border: 1px solid #000000;
	padding: 2px;
	padding-left: 5px;
	padding-right: 5px;
}

a {
	color: blue;
}

a:hover {
	color: red;
}
</style>
</head>
<body>

	查看學生 资料 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[
	<a href="catServlet?action=initAdd">新增學生</a> ][
	<a href="catServlet?action=list">學生列表</a> ]
	<table>
		<tr>
			<td>Name:</td>
			<td>${ student.name }</td>
		</tr>
		<tr>
			<td>age:</td>
			<td>${ student.age }</td>
		</tr>
		<tr>
			<td>address:</td>
			<td>${ student.address }</td>
		</tr>

	</table>

</body>
</html>