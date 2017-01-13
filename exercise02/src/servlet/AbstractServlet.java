package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

	public abstract void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	public abstract void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	public abstract void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	public abstract void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	public abstract void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}