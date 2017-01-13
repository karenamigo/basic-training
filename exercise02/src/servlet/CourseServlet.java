package servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Course;
import bean.Student;
import dao.GenericDao;

@WebServlet("/CourseServlet")
public class CourseServlet {

	private static final long serialVersionUID = 1L;

	private GenericDao<Course> courseDao;

	private GenericDao<Student> studentDao;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		if ("add".equals(action)) {
			add(request, response);
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

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Course course = courseDao.find(Course.class, id);
		if (course != null) {
			courseDao.delete(course);
		}
		list(request, response);
	}

	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Student std = studentDao.find(Student.class, id);
		Set<Course> course = std.getCourses();
		for (Course c : course) {
			request.setAttribute("course", c.getName());
		}
		request.getRequestDispatcher("/listStdCourse.jsp").forward(request, response);
	}

	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		Course course = new Course();
		course.setName(name);
		courseDao.insert(course);
		list(request, response);
	}

}
