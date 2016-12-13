package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Course;
import dao.GenericDao;

/**
 * Servlet implementation class CourseServlet
 */
@WebServlet("/CourseServlet")
public class CourseServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;

	//private Course course = new Course();

	private GenericDao<Course> courseDao = new GenericDao<Course>();

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("courselist", courseDao.listAll("from Course"));
		request.getRequestDispatcher("/courses.jsp").forward(request, response);
	}

	@Override
	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
