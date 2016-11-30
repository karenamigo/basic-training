<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String stdID = request.getParameter("stdID");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/school?" + "user=root&password=123456";
			Connection connect = DriverManager.getConnection(url);
			Statement st = connect.createStatement();
			String delSQL = "DELETE from student  WHERE stdID = ?";
			PreparedStatement pst = connect.prepareStatement(delSQL);
			pst.setString(1, stdID);
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


</body>
</html>