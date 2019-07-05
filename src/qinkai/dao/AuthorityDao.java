package qinkai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import qinkai.entity.Authority;
import qinkai.entity.Course;
import qinkai.entity.Student;
import qinkai.util.JDBCUtil;

public class AuthorityDao {
	public boolean checkAuthority(String username, int cid) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from authority where suname=? and cid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setInt(2, cid);
		ResultSet rs = ps.executeQuery();
		boolean flag = false;
		if (rs.next()) {
			flag = true;
		}
		JDBCUtil.release(conn, ps, rs);
		return flag;
	}

	public List<Authority> findByConditions(int cid, String suname, String tuname) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from authority, student, course where"
				+ " authority.suname=student.username and authority.cid=course.cid and course.tuname=?";
		if (cid != 0) {
			sql = sql + " and course.cid='" + cid + "'";
		}
		if (suname != null && !suname.trim().equals("")) {
			sql = sql + " and authority.suname='" + suname + "'";
		}
	
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, tuname);
		ResultSet rs = ps.executeQuery();
		List<Authority> list = new ArrayList<Authority>();
		while (rs.next()) {
			String username = rs.getString("username");
			String realname = rs.getString("realname");
			int courseId = rs.getInt("cid");
			String cname = rs.getString("cname");
			Student student = new Student(username, null, realname);
			Course course = new Course(courseId, cname, null, null, null);
			Authority anthority = new Authority(student, course);
			list.add(anthority);
		}
		JDBCUtil.release(conn, ps, rs);
		return list;
	}

	public boolean delAuthority(int cid, String suname) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from authority where suname=? and cid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, suname);
		ps.setInt(2, cid);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

	public boolean addAuthority(int cid, String suname) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into authority values(?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, suname);
		ps.setInt(2, cid);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}
}
