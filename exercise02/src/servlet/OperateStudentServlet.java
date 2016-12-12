package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Course;
import bean.Department;
import bean.Student;
import dao.GenericDao;

@WebServlet("/OperateStudentServlet")
public class OperateStudentServlet extends HttpServlet {

	private Student student = new Student();

	private GenericDao<Student> studentDao = new GenericDao<Student>();

	private GenericDao<Course> courseDao = new GenericDao<Course>();

	private Department department = new Department();

	private GenericDao<Department> departmentDao = new GenericDao<Department>();

	private static final long serialVersionUID = 2874858791160046196L;

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
		int departmentId = Integer.parseInt(request.getParameter("departmentId"));

		department = departmentDao.find(Department.class, departmentId);
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String address = request.getParameter("address");
		student.setName(name);
		student.setAge(age);
		student.setAddress(address);
		student.setDepartment(department);
		studentDao.insert(student);
		list(request, response);

	}

	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Student> studentlist = studentDao.listAll(" from Student ");
		request.setAttribute("studentlist", studentlist);
		request.setAttribute("departmentId", request.getParameter("departmentId"));
		request.getRequestDispatcher("/listStudent.jsp").forward(request, response);

	}

	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		student = studentDao.find(Student.class, id);
		request.setAttribute("student", student);
		request.setAttribute("departmentId", request.getParameter("departmentId"));
		request.getRequestDispatcher("/addStudent.jsp").forward(request, response);

	}

	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int departmentId = Integer.parseInt(request.getParameter("departmentId"));
		department = departmentDao.find(Department.class, departmentId);
		int id = Integer.parseInt(request.getParameter("id"));
		student = studentDao.find(Student.class, id);
		student.setName(request.getParameter("name"));
		student.setAge(request.getParameter("age"));
		student.setAddress(request.getParameter("address"));
		student.setId(id);
		student.setDepartment(department);
		request.setAttribute("student", student);
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
