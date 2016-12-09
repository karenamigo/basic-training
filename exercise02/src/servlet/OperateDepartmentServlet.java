package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Department;
import dao.GenericDao;

@WebServlet("/OperateDepartmentServlet")
public class OperateDepartmentServlet extends HttpServlet {

	private Department department = new Department();

	private static final long serialVersionUID = 2874858791160046196L;

	private GenericDao<Department> departmentDao = new GenericDao<Department>();

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

		department.setName(name);

		departmentDao.insert(department);

		list(request, response);
	}

	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("departmentlist", departmentDao.listAll(" from Department "));

		request.getRequestDispatcher("/addDepartment.jsp").forward(request, response);
	}

	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		department = departmentDao.find(Department.class, id);
		request.setAttribute("department", department);
		request.getRequestDispatcher("/addDepartment.jsp").forward(request, response);
	}

	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		department = departmentDao.find(Department.class, id);

		department.setName(request.getParameter("name"));
		departmentDao.update(department);

		list(request, response);
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		department = departmentDao.find(Department.class, id);
		if (department != null) {
			departmentDao.delete(department);
		}
		list(request, response);
	}
}
