<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.Statement"%>
<jsp:directive.page import="java.sql.ResultSet" />
<jsp:directive.page import="java.sql.SQLException" />
<jsp:directive.page import="java.sql.PreparedStatement" />
<jsp:directive.page import="bean.*" />
<jsp:directive.page import="dao.*" />
<jsp:directive.page import="util.*" />


<%
	request.setCharacterEncoding("UTF-8");
	Student student = new Student();
	String name = request.getParameter("name");
	String age = request.getParameter("age");
	String address = request.getParameter("address");
	String action = request.getParameter("action");
	//Department department = DepartmentDAO.find(Integer.parseInt(request.getParameter("departmentId")));
	if ("add".equals(action)) {
		String id = request.getParameter("id");
		student.setName(name);
		student.setAge(age);
		student.setAddress(address);
		StudentDAO.insert(student);
		String redirectURL = "listStudent.jsp";
		response.sendRedirect(redirectURL);
	}
	if ("edit".equals(action)) {
		int id = Integer.parseInt(request.getParameter("id"));
		Student std = StudentDAO.find(id);
		request.setAttribute("name", std.getName());
		request.setAttribute("age", std.getAge());
		request.setAttribute("address", std.getAddress());
		request.setAttribute("action", action);
		request.getRequestDispatcher("/addStudent.jsp").forward(request, response);
	}
	if ("save".equals(action)) {
		int id = Integer.parseInt(request.getParameter("id"));
		Student std = StudentDAO.find(id);
		std.setName(request.getParameter("name"));
		std.setAge(request.getParameter("age"));
		std.setAddress(request.getParameter("address"));
		std.setId(std.getId());
		StudentDAO.save(std);
		String redirectURL = "listStudent.jsp";
		response.sendRedirect(redirectURL);

	}
	if ("del".equals(action)) {
		// 取一個或者多個 ID 值
		DbUtil db = new DbUtil();
		String[] id = request.getParameterValues("id");
		if (id == null || id.length == 0) {
			out.println("沒有勾選任何行");
			return;
		}

		String condition = "";

		for (int i = 0; i < id.length; i++) {
			if (i == 0)
				condition = "" + id[i];
			else
				condition += ", " + id[i];
		}

		String sql = "DELETE FROM student WHERE id IN (" + condition + ") ";

		Connection connect = null;
		PreparedStatement pst = null;

		try {
			connect = db.getConnection();
			pst = connect.prepareStatement(sql);
			pst.executeUpdate();

			String redirectURL = "listStudent.jsp";
			response.sendRedirect(redirectURL);

		} catch (SQLException e) {
			out.println("執行SQL\"" + sql + "\"時發生例外：" + e.getMessage());
			e.printStackTrace();
		} finally {
			db.close(pst);
			db.close(connect);
		}
	}
%>

