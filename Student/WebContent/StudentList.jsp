<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.sql.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>StudentList</title>
</head>
<body>
	<center>
		<button type="button" name="create"
			onclick="location='CreateStuData.jsp'">新增</button>
		<button type="button" name="delete" onclick="location='Delete.jsp'">刪除</button>
		<button type="button" name="update"
			onclick="location='UpdateStdData.jsp'">修改</button>
	</center>

	<%
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/school?" + "user=root&password=123456";
			Connection connect = DriverManager.getConnection(url);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("SELECT * from student");
	%>
	<center>
		<table bgcolor="white" cellspacing=1 cellpadding=5 width=50%
			border="1">
			<tr bgcolor="pink">
				<th>學號</th>
				<th>姓名</th>
				<th>地址</th>
				<th>年齡</th>
			</tr>

			<%
				while (rs.next()) {
						out.println("<tr><td>" + rs.getObject("stdID") + "</td>");//學號
						out.println("<td>" + rs.getObject("stdName") + "</td>");//姓名
						out.println("<td>" + rs.getObject("stdAddress") + "</td>");//地址
						out.println("<td>" + rs.getObject("stdAge") + "</td></tr>");//年齡

					}
					rs.close();
					st.close();
					connect.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			%>
		</table>
	</center>
</body>
</html>