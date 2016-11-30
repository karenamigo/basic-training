<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>

		<form action="DstdInfo.jsp" method=post>
			<table>
				<tr>
					<td>請輸入要刪除的學生學號:</td>
					<td><input type="text" name="stdID" /></td>
				</tr>
			</table>
			<input type="submit" value="確認"
				onclick="return confirm('Are you sure u want to delete this info')" />
			<input type="reset" value="取消" />
		</form>

		<p>數據更新前的數據紀錄:</p>
		<%
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/school?" + "user=root&password=123456";
				Connection connect = DriverManager.getConnection(url);
				Statement st = connect.createStatement();
				ResultSet rs = st.executeQuery("SELECT * from student");
		%>

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