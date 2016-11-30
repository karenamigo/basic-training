<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UpdateResult</title>
</head>
<body>
	<%
		String stdID = request.getParameter("stdID");
		String nstdName = request.getParameter("stdName");
		String nstdAddress = request.getParameter("stdAddress");
		String nstdAge = request.getParameter("stdAge");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/school?" + "user=root&password=123456";

			Connection connect = DriverManager.getConnection(url);
			String updateSQL = "UPDATE student SET stdName = ?, stdAddress = ?, stdAge =?" + "  WHERE stdID = ?";
			PreparedStatement pst = connect.prepareStatement(updateSQL);

			pst.setString(1, nstdName);
			pst.setString(2, nstdAddress);
			pst.setString(3, nstdAge);
			pst.setString(4, stdID);
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