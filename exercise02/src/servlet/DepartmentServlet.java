package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Department;
import dao.GenericDao;

/**
 * Servlet implementation class DepartmentServlet
 */
@WebServlet("/DepartmentServlet")
public class DepartmentServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;

	private Department department = new Department();

	private GenericDao<Department> departmentDao = new GenericDao<Department>();

	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");

		department.setName(name);

		departmentDao.insert(department);

		list(request, response);
	}

	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("departmentlist", departmentDao.listAll(" from Department "));

		request.getRequestDispatcher("/addDepartment.jsp").forward(request, response);
	}

	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		department = departmentDao.find(Department.class, id);
		request.setAttribute("department", department);
		request.getRequestDispatcher("/addDepartment.jsp").forward(request, response);
	}

	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		department = departmentDao.find(Department.class, id);
		department.setName(request.getParameter("name"));
		departmentDao.update(department);
		list(request, response);
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		department = departmentDao.find(Department.class, id);
		if (department != null) {
			departmentDao.delete(department);
		}
		list(request, response);
	}
}
