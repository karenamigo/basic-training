package tag;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class HelloTag extends SimpleTagSupport {

	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/school?" + "user=root&password=123456";
			Connection connect = DriverManager.getConnection(url);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("SELECT * from student");
			while (rs.next()) {
				out.println("<tr><td>" + rs.getString("id") + "</td>");//學號
				out.println("<td>" + rs.getString("name") + "</td>");//姓名
				out.println("<td>" + rs.getString("address") + "</td>");//地址
				out.println("<td>" + rs.getString("age") + "</td></tr>");//年齡
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
