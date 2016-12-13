package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Department;
import bean.Student;
import dao.GenericDao;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;

	private Student student = new Student();

	private GenericDao<Student> studentDao = new GenericDao<Student>();

	private Department department = new Department();

	private GenericDao<Department> departmentDao = new GenericDao<Department>();

	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Student> studentlist = studentDao.listAll(" from Student ");
		request.setAttribute("studentlist", studentlist);
		request.setAttribute("departmentId", request.getParameter("departmentId"));
		request.getRequestDispatcher("/listStudent.jsp").forward(request, response);

	}

	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		student = studentDao.find(Student.class, id);
		request.setAttribute("student", student);
		request.setAttribute("departmentId", request.getParameter("departmentId"));
		request.getRequestDispatcher("/addStudent.jsp").forward(request, response);

	}

	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		student = studentDao.find(Student.class, id);
		if (student != null) {
			studentDao.delete(student);
		}
		list(request, response);
	}

}
