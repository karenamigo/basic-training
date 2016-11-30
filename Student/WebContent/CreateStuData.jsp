<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Student Data</title>
</head>
<body>
	<center>
		<h1>Create Student Info</h1>
		<form method="post" action="InsertData.jsp">
			<table style="with: 50%">
				<tr>
					<td>學號:</td>
					<td><input type="text" name="stdID" required="required"></td>
				</tr>
				<tr>
					<td>姓名:</td>
					<td><input type="text" name="stdName" required="required"></td>
				</tr>
				<tr>
					<td>地址:</td>
					<td><input type="text" name="stdAddress" required="required"></td>
				</tr>
				<tr>
					<td>年齡:</td>
					<td><input type="text" name="stdAge" required="required"></td>
				</tr>
			</table>
			<input type="submit" value="確認" /> <input type="reset" value="取消" />
		</form>
	</center>
</body>
</html>