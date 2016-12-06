package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Department;
import util.DbUtil;

public class DepartmentDAO {

	Department department = new Department();

	//create 一筆資料
	public static void insert(Department department) throws Exception {
		String sql = "INSERT INTO department (name) VALUES ( ? ) ";
		Connection connect = DbUtil.getConnection();
		PreparedStatement pst = connect.prepareStatement(sql);
		pst.setString(1, department.getName());
		pst.executeUpdate();
		DbUtil.close(pst);
		DbUtil.close(connect);
	}

	public static void save(Department department) throws Exception {

		String sql = "UPDATE department SET name = ? WHERE id = ? ";
		Connection connect = DbUtil.getConnection();
		PreparedStatement pst = connect.prepareStatement(sql);
		pst.setString(1, department.getName());
		pst.setInt(2, department.getId());
		pst.executeUpdate();
		DbUtil.close(pst);
		DbUtil.close(connect);

	}

	public static void delete(Integer id) throws Exception {

		String sql = "DELETE FROM department WHERE id = ? ";
		Connection connect = DbUtil.getConnection();
		PreparedStatement pst = connect.prepareStatement(sql);
		pst.setInt(1, id);

	}

	public static Department find(Integer id) throws Exception {

		String sql = "SELECT * FROM department WHERE id = ? ";

		Connection connect = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			connect = DbUtil.getConnection();
			pst = connect.prepareStatement(sql);
			pst.setInt(1, id);

			rs = pst.executeQuery();

			if (rs.next()) {
				Department department = new Department();
				department.setId(id);
				department.setName(rs.getString("name"));
				return department;
			} else {
				return null;
			}

		} finally {
			DbUtil.close(rs);
			DbUtil.close(pst);
			DbUtil.close(connect);
		}
	}

	/**
	 * 列出所有的 Department
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<Department> listDepartments() throws Exception {

		List<Department> list = new ArrayList<Department>();

		String sql = "SELECT * FROM department ORDER BY id  ";

		Connection connect = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			connect = DbUtil.getConnection();
			pst = connect.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				Department department = new Department();
				department.setId(rs.getInt("id"));
				department.setName(rs.getString("name"));
				list.add(department);
			}

		} finally {
			DbUtil.close(rs);
			DbUtil.close(pst);
			DbUtil.close(connect);

		}
		return list;
	}

}
