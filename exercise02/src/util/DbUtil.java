package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbUtil {

	private Properties props;

	private String url;

	private String user;

	private String password;

	public DbUtil() throws IOException, ClassNotFoundException {

		this("/jdbc.properties");
	}

	public DbUtil(String configFile) throws IOException, ClassNotFoundException {
		props = new Properties();
		props.load(DbUtil.class.getResourceAsStream("/jdbc.properties"));

		url = props.getProperty("url");
		user = props.getProperty("user");
		password = props.getProperty("password");
		Class.forName(props.getProperty("driver"));
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	public void close(Connection connection) throws SQLException {
		connection.close();
	}

	public void close(PreparedStatement pst) throws SQLException {
		pst.close();
	}

	public void close(Statement st) throws SQLException {
		st.close();
	}

	public void close(ResultSet rs) throws SQLException {
		rs.close();
	}
}
