package qinkai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import qinkai.entity.Admin;
import qinkai.util.JDBCUtil;

public class AdminDao {
	public Admin login(String username, String password) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from admin where username=? and password=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		Admin admin = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			String realname = rs.getString("realname");
			admin = new Admin(username, password, realname);
		}
		return admin;
	}

	public boolean changePassword(String username, String password) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update admin set password=? where username=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, password);
		ps.setString(2, username);
		int cnt = ps.executeUpdate();
		return cnt > 0;
	}
}
