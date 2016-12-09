package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.GenericDao;

@WebServlet("/OperateStudentServlet")
public class OperateStudentServlet extends HttpServlet {

	private Student student = new Student();

	private static final long serialVersionUID = 2874858791160046196L;

	private GenericDao<Student> studentDao = new GenericDao<Student>();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");

		if ("add".equals(action)) {
			add(request, response);
		}
		if ("edit".equals(action)) {
			edit(request, response);
		}
		if ("save".equals(action)) {
			save(request, response);
		}
		if ("list".equals(action)) {
			list(request, response);
		}
		if ("delete".equals(action)) {
			delete(request, response);
		} else {
			list(request, response);
		}
	}

	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String address = request.getParameter("address");
		student.setName(name);
		student.setAge(age);
		student.setAddress(address);
		studentDao.insert(student);
		list(request, response);

	}

	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("studentlist", studentDao.listAll(" from Student "));

		request.getRequestDispatcher("/listStudent.jsp").forward(request, response);

	}

	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		student = studentDao.find(Student.class, id);
		request.setAttribute("student", student);
		request.getRequestDispatcher("/addStudent.jsp").forward(request, response);

	}

	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		student = studentDao.find(Student.class, id);
		student.setName(request.getParameter("name"));
		student.setAge(request.getParameter("age"));
		student.setAddress(request.getParameter("address"));
		student.setId(id);
		studentDao.update(student);
		list(request, response);

	}

	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		student = studentDao.find(Student.class, id);
		if (student != null) {
			studentDao.delete(student);
		}
		list(request, response);
	}
}
