package tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import bean.Student;
import dao.GenericDao;

public class StudentTag extends SimpleTagSupport {

	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		try {
			GenericDao<Student> studentDao = new GenericDao<Student>();
			List<Student> stdlist = studentDao.listAll("from Student");
			for (Student std : stdlist) {
				out.println("<tr><td>" + std.getId() + "</td>");
				out.println("<td>" + std.getName() + "</td>");
				out.println("<td>" + std.getAge() + "</td>");
				out.println("<td>" + std.getAddress() + "</td>");
				out.println("<td>" + std.getDepartment().getName() + "</td></tr>");
			}

		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
}
