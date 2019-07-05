package qinkai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import qinkai.entity.College;
import qinkai.util.JDBCUtil;

public class CollegeDao {
	public College findCollegeByConame(String coname) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from college where coname=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, coname);
		ResultSet rs = ps.executeQuery();
		College college = null;
		if (rs.next()) {
			int coid = rs.getInt("coid");
			college = new College(coid, coname);
		}
		JDBCUtil.release(conn, ps, rs);
		return college;
	}

	public List<College> findAllColleges() throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from college";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<College> list = new ArrayList<>();
		while (rs.next()) {
			int coid = rs.getInt("coid");
			String coname = rs.getString("coname");
			College college = new College(coid, coname);
			list.add(college);
		}
		JDBCUtil.release(conn, ps, rs);
		return list;
	}

	public boolean updateCollege(int coid, String coname) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update college set coname=? where coid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, coname);
		ps.setInt(2, coid);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

	public boolean addCollege(int coid, String coname)throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into college values(?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, coid);
		ps.setString(2, coname);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

	public boolean delCollege(int coid) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from college where coid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, coid);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}
}
