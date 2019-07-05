package qinkai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import qinkai.entity.Course;
import qinkai.entity.Message;
import qinkai.entity.Student;
import qinkai.entity.Teacher;
import qinkai.util.JDBCUtil;

public class MessageDao {
	public boolean saveMsg(String title, String content, int cid, String username) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into message values(null, ?, ?, ?, ?, ?, 0)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1,title);
		ps.setString(2, content);
		ps.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
		ps.setInt(4, cid);
		ps.setString(5, username);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

	public List<Message> findMsgsBySuname(String username) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from message, course, student "
				+ "where message.cid=course.cid and message.suname=student.username and message.suname=? order by date desc";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		List<Message> list = new ArrayList<>();
		while (rs.next()) {
			int mid = rs.getInt("mid");
			String title = rs.getString("title");
			String content = rs.getString("content");
			Date date = rs.getTimestamp("date");
			int cid = rs.getInt("cid");
			String cname = rs.getString("cname");
			Course course = new Course(cid, cname, null, null, null);
			Message message = new Message(mid, title, content, date, course, null);
			list.add(message);
		}
		JDBCUtil.release(conn, ps, rs);
		return list;
	}

	public List<Message> findAllMsgsByConditions(String courseName, String teacherName, String keywords) throws SQLException  {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select mid, message.title, content, date, message.cid, cname, teacher.realname as realname"
				+ " from message, course, student, teacher "
				+ "where message.cid=course.cid and message.suname=student.username and course.tuname=teacher.username";
		if (courseName != null && !courseName.trim().equals("")) {
			sql = sql + " and course.cname='" + courseName + "'";
		}
		if (teacherName != null && !teacherName.trim().equals("")) {
			sql = sql + " and teacher.realname='" + teacherName + "'";
		}
		if (keywords != null && !keywords.trim().equals("")) {
			sql = sql + " and message.content like '%" + keywords + "%'";
		}
		sql = sql + " order by date desc";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Message> list = new ArrayList<>();
		while (rs.next()) {
			int mid = rs.getInt("mid");
			String title = rs.getString("title");
			String content = rs.getString("content");
			Date date = rs.getTimestamp("date");
			int cid = rs.getInt("cid");
			String cname = rs.getString("cname");
			String realname = rs.getString("realname");
			Teacher teacher = new Teacher(null, null, realname, null);
			Course course = new Course(cid, cname, null, teacher, null);
			Message message = new Message(mid, title, content, date, course, null);
			list.add(message);
		}
		JDBCUtil.release(conn, ps, rs);
		return list;
	}
	
	public List<Message> findAllMsgsByConditionsWithPage(String courseName, String teacherName, String keywords, int currentPage) throws SQLException  {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select mid, message.title, content, date, message.cid, cname, teacher.realname as realname"
				+ " from message, course, student, teacher "
				+ "where message.cid=course.cid and message.suname=student.username and course.tuname=teacher.username";
		if (courseName != null && !courseName.trim().equals("")) {
			sql = sql + " and course.cname='" + courseName + "'";
		}
		if (teacherName != null && !teacherName.trim().equals("")) {
			sql = sql + " and teacher.realname='" + teacherName + "'";
		}
		if (keywords != null && !keywords.trim().equals("")) {
			sql = sql + " and message.content like '%" + keywords + "%'";
		}
		sql = sql + " order by date desc limit ?, 5";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, (currentPage - 1) * 5);
		ResultSet rs = ps.executeQuery();
		List<Message> list = new ArrayList<>();
		while (rs.next()) {
			int mid = rs.getInt("mid");
			String title = rs.getString("title");
			String content = rs.getString("content");
			Date date = rs.getTimestamp("date");
			int cid = rs.getInt("cid");
			String cname = rs.getString("cname");
			String realname = rs.getString("realname");
			Teacher teacher = new Teacher(null, null, realname, null);
			Course course = new Course(cid, cname, null, teacher, null);
			Message message = new Message(mid, title, content, date, course, null);
			list.add(message);
		}
		JDBCUtil.release(conn, ps, rs);
		return list;
	}

	public boolean delMsg(int mid) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from message where message.mid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, mid);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

	public Message findMessageById(int mid) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from message where mid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, mid);
		ResultSet rs = ps.executeQuery();
		Message message = null;
		while (rs.next()) {
			String title = rs.getString("title");
			String content = rs.getString("content");
			Date date = rs.getDate("date");
			message = new Message(mid, title, content, date, null, null);
		}
		JDBCUtil.release(conn, ps, rs);
		return message;
	}

	public boolean updateMsg(int mid, String title, String content) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update message set title=? , content=? where mid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, title);
		ps.setString(2, content);
		ps.setInt(3, mid);
		
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}
	
	public List<Message> findUnansweredMsgsByTuname(String username) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from message, course, teacher where message.cid=course.cid and course.tuname=teacher.username "
				+ "and status=0 and teacher.username=? order by date desc";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		List<Message> list = new ArrayList<>();
		while (rs.next()) {
			int mid = rs.getInt("mid");
			String title = rs.getString("title");
			String content = rs.getString("content");
			int cid = rs.getInt("cid");
			String cname = rs.getString("cname");
			String introduction = rs.getString("introduction");
			Course course = new Course(cid, cname, introduction, null, null);
			Message message = new Message(mid, title, content, null, course, null);
			list.add(message);
		}
		JDBCUtil.release(conn, ps, rs);
		return list;
	}
	
	public List<Message> findMsgsByCid(int cid) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from message join student on message.suname=student.username where cid=? order by date desc";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, cid);
		ResultSet rs = ps.executeQuery();
		List<Message> list = new ArrayList<>();
		while (rs.next()) {
			int mid = rs.getInt("mid");
			String title = rs.getString("title");
			String content = rs.getString("content");
			Date date = rs.getTimestamp("date");
			String realname = rs.getString("realname");
			Student student = new Student(null, null, realname);
			Message message = new Message(mid, title, content, date, null, student);
			list.add(message);
		}
		JDBCUtil.release(conn, ps, rs);
		return list;
	}

	public void updateStatus(int mid) throws SQLException  {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update message set status=1 where mid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, mid);
		ps.executeUpdate();
		JDBCUtil.release(conn, ps);
	}

	public int findMsgCountByConditions(String courseName, String teacherName, String keywords) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select count(*) from message, course, teacher where message.cid=course.cid and course.tuname=teacher.username";
		if (courseName != null && !courseName.trim().equals("")) {
			sql = sql + " and course.cname='" + courseName + "'";
		}
		if (teacherName != null && !teacherName.trim().equals("")) {
			sql = sql + " and teacher.realname='" + teacherName + "'";
		}
		if (keywords != null && !keywords.trim().equals("")) {
			sql = sql + " and message.content like '%" + keywords + "%'";
		}
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		int cnt = 0;
		if (rs.next()) {
			cnt = rs.getInt(1);
		}
		JDBCUtil.release(conn, ps);
		return cnt;
	}
	
}
