package qinkai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import qinkai.entity.College;
import qinkai.entity.Teacher;
import qinkai.util.JDBCUtil;

public class TeacherDao {

	public Teacher login(String username, String password) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from teacher where username=? and password=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		Teacher teacher = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			String realname = rs.getString("realname");
			teacher = new Teacher(username, password, realname, null);
		}
		JDBCUtil.release(conn, ps, rs);
		return teacher;
	}

	public List<Teacher> findAllTeachers() throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from teacher join college on teacher.coid=college.coid";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Teacher> list = new ArrayList<>();
		while (rs.next()) {
			int coid = rs.getInt("coid");
			String coname = rs.getString("coname");
			String username = rs.getString("username");
			String realname = rs.getString("realname");
			String position = rs.getString("position");
			String introduction = rs.getString("introduction");
			College college = new College(coid, coname);
			Teacher teacher = new Teacher(username, null, realname, position, introduction, college);
			list.add(teacher);
		}
		JDBCUtil.release(conn, ps, rs);
		return list;
	}

	public Teacher findTeacher(String username) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from teacher join college on teacher.coid=college.coid where teacher.username=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		Teacher teacher = null;
		if (rs.next()) {
			int coid = rs.getInt("coid");
			String coname = rs.getString("coname");
			String realname = rs.getString("realname");
			String position = rs.getString("position");
			String introduction = rs.getString("introduction");
			College college = new College(coid, coname);
			teacher = new Teacher(username, null, realname, position, introduction, college);
		}
		JDBCUtil.release(conn, ps, rs);
		return teacher;
	}

	public boolean updateTeacher(String username, String realname, String position, String introduction, int coid) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update teacher set realname=?, position=?, introduction=?, coid=? where username=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, realname);
		ps.setString(2, position);
		ps.setString(3, introduction);
		ps.setInt(4, coid);
		ps.setString(5, username);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

	public boolean addTeacher(String username, String realname, String position, String introduction, int coid) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into teacher values(?, '123', ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, realname);
		ps.setString(3, position);
		ps.setString(4, introduction);
		ps.setInt(5, coid);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

	public boolean delTeacher(String username) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from teacher where username=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

	public boolean changePassword(String username, String password) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update teacher set password=? where username=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, password);
		ps.setString(2, username);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}
	
}
