<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<center>
		<button type="button" name="create"
			onclick="location='CreateStuData.jsp'">新增</button>
		<button type="button" name="delete" onclick="location='delete.jsp'">刪除</button>
		<button type="button" name="update" onclick="location='update.jsp'">修改</button>
	</center>

	<center>
		<%
			String stdID = request.getParameter("stdID");
			String stdName = request.getParameter("stdName");
			String stdAddress = request.getParameter("stdAddress");
			String stdAge = request.getParameter("stdAge");

			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/school?" + "user=root&password=123456";
				Connection connect = DriverManager.getConnection(url);
				String insertSQL = "INSERT into student values(?,?,?,?)";
				PreparedStatement pst = connect.prepareStatement(insertSQL);
				pst.setString(1, stdID);
				pst.setString(2, stdName);
				pst.setString(3, stdAddress);
				pst.setString(4, stdAge);

				/*if (pst.executeUpdate() == 1)
					System.out.println("以新增一筆資料");*/
				pst.executeUpdate();
				pst.close();
				connect.close();

			} catch (Exception e) {
				out.println(e.getMessage());
			}
		%>
		<%
			String redirectURL = "StudentList.jsp";
			response.sendRedirect(redirectURL);
		%>
	</center>

</body>
</html>