package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtil {

	private static String dbDriver = "com.mysql.jdbc.Driver";

	private static String dburl = "jdbc:mysql://localhost:3306/school?" + "user=root&password=123456";

	public static Connection getConnection() throws SQLException {
		Connection connect = null;
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		connect = DriverManager.getConnection(dburl);
		return connect;
	}

	public static void close(Connection connect) {
		if (connect != null) {
			try {
				connect.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void close(PreparedStatement pst) {
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void close(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
