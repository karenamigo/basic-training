package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import util.DbUtil;

public class StudentDAO {

	//插入一筆資料 create
	public static void insert(Student student) throws Exception {
		String sql = "INSERT into student(name,age,address) values(?,?,?)";
		Connection connect = DbUtil.getConnection();
		PreparedStatement pst = connect.prepareStatement(sql);
		pst.setString(1, student.getName());
		pst.setString(2, student.getAge());
		pst.setString(3, student.getAddress());
		pst.executeUpdate();
		DbUtil.close(pst);
		DbUtil.close(connect);
	}

	//儲存一筆資料 update
	public static void save(Student student) throws Exception {
		Connection connect = DbUtil.getConnection();

		String updateSQL = "UPDATE student SET name = ?, age = ?, address =?  WHERE id=?";
		PreparedStatement pst = connect.prepareStatement(updateSQL);
		pst.setString(1, student.getName());
		pst.setString(2, student.getAge());
		pst.setString(3, student.getAddress());
		pst.setInt(4, student.getId());
		pst.executeUpdate();
		DbUtil.close(pst);
		DbUtil.close(connect);
	}

	//查詢一筆資料 
	public static Student find(Integer id) throws Exception {

		String sql = "SELECT * FROM student WHERE id = ? ";

		Connection connect = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			connect = DbUtil.getConnection();
			pst = connect.prepareStatement(sql);
			pst.setInt(1, id);

			rs = pst.executeQuery();

			if (rs.next()) {
				Student Student = new Student();
				Student.setId(id);
				Student.setName(rs.getString("name"));
				Student.setAge(rs.getString("age"));
				Student.setAddress(rs.getString("address"));
				//Student.setDepartment(DepartmentDAO.find(rs.getInt("departmentId")));
				return Student;
			} else {
				return null;
			}

		} finally {
			DbUtil.close(rs);
			DbUtil.close(pst);
			DbUtil.close(connect);
		}
	}

	//列出所有學生資訊
	public static List<Student> listAllStudents() throws Exception {
		List<Student> list = new ArrayList<Student>();

		String sql = "SELECT * FROM student ORDER BY id  ";

		Connection connect = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			connect = DbUtil.getConnection();
			pst = connect.prepareStatement(sql);

			rs = pst.executeQuery();

			while (rs.next()) {

				Student Student = new Student();

				Student.setId(rs.getInt("id"));
				Student.setName(rs.getString("name"));
				Student.setAge(rs.getString("age"));
				Student.setAddress(rs.getString("address"));
				//Student.setDepartment(DepartmentDAO.find(rs.getInt("departmentId")));

				list.add(Student);

			}

		} finally {
			DbUtil.close(rs);
			DbUtil.close(pst);
			DbUtil.close(connect);
		}

		return list;
	}

}
