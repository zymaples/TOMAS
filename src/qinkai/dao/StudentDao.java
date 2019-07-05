package qinkai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import qinkai.entity.Student;
import qinkai.util.JDBCUtil;

public class StudentDao {

	public Student login(String username, String password) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from student where username=? and password=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		Student student = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			String realname = rs.getString("realname");
			student = new Student(username, password, realname);
		}
		JDBCUtil.release(conn, ps, rs);
		return student;
	}

	public boolean addStudent(String username, String password, String realname) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into student values(?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3, realname);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

	public boolean changePassword(String username, String password) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update student set password=? where username=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, password);
		ps.setString(2, username);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

	public boolean checkUsername(String username) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from student where username=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		boolean flag = false;
		if (rs.next()) {
			flag = true;
		}
		JDBCUtil.release(conn, ps, rs);
		return flag;
		
	}
	
	
}
