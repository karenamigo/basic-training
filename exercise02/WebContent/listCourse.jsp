<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:directive.page import="dao.*" />
<jsp:directive.page import="util.*" />
<jsp:directive.page import="bean.*" />
<jsp:directive.page import="org.hibernate.Session" />
<jsp:directive.page import="org.hibernate.SessionFactory" />
<jsp:directive.page import=" org.hibernate.cfg.Configuration" />
<jsp:directive.page import=" java.util.Set" />


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>
<body>
	<button type="button" name="StdList"
		onclick="location='OperateStudentServlet?action=list'">返回學生列表</button>
	<table cellspacing=1 cellpadding=5>
		<tr>
			<th>修課編號</th>
			<th>修課名稱</th>
		</tr>
		<%
			String action = request.getParameter("action");
			if ("showcourse".equals(action)) {
				int id = Integer.parseInt(request.getParameter("id"));
				Configuration cfg = new Configuration().configure();
				SessionFactory sfactory = cfg.buildSessionFactory();
				Session s = sfactory.openSession();
				s.beginTransaction();
				Student std = (Student) s.load(Student.class, id);
				Set<Course> course = std.getCourses();
				for (Course c : course) {
					out.write("<tr>");
					out.write("<td>" + c.getId() + "</td>");
					out.write("<td>" + c.getName() + "</td>");
					out.write("</tr>");
				}
			}
		%>
	</table>
</body>
</html>


