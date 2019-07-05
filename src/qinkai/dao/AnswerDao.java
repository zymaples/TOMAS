package qinkai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import qinkai.entity.Answer;
import qinkai.util.JDBCUtil;

public class AnswerDao {

	public List<Answer> findAnswersByMid(int mid) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from answer where mid = ? order by date desc";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, mid);
		ResultSet rs = ps.executeQuery();
		List<Answer> list = new ArrayList<>();
		while (rs.next()) {
			int aid = rs.getInt("aid");
			String content = rs.getString("content");
			Date date = rs.getTimestamp("date");
			Answer answer = new Answer(aid, content, date, mid, null);
			list.add(answer);
		}
		JDBCUtil.release(conn, ps, rs);
		return list;
	}

	public boolean saveAnswer(String content, int mid) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into answer values(null, ?, ?,  ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, content);
		ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
		ps.setInt(3, mid);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

	public boolean delAnswer(int aid) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from answer where aid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, aid);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

	public boolean updateAnswer(int aid, String content) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update answer set content=?, date=? where aid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, content);
		ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
		ps.setInt(3, aid);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

	public boolean delAnswerByMid(int mid) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from answer where mid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, mid);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

	public List<Answer> findAllAnswers() throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from answer join message on answer.mid=message.mid";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int mid = rs.getInt("mid");
			String title = rs.getString("title");
			int aid = rs.getInt("aid");
			String content = rs.getString("content");
			Date date = rs.getDate("date");
		}
		JDBCUtil.release(conn, ps);
		return null;
	}
}
