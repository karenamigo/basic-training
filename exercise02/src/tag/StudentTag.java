package tag;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import bean.Student;

public class StudentTag extends SimpleTagSupport {

	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		//Student std = new Student();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/school?" + "user=root&password=123456";
			Connection connect = DriverManager.getConnection(url);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("SELECT * from student");
			while (rs.next()) {
				out.println("<tr><td>" + rs.getString("id") + "</td>");//�Ǹ�
				out.println("<td>" + rs.getString("name") + "</td>");//�m�W
				out.println("<td>" + rs.getString("address") + "</td>");//�a�}
				out.println("<td>" + rs.getString("age") + "</td>");//�~��
				out.println("<td>" + rs.getString("department_id") + "</td>");//�t��
				//out.println("<td><a href=\"listCourse.jsp?action=showcourse&id=" + std.getId() + "\">��J�C��</a></td></tr>");
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
