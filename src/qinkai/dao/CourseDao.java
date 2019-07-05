package qinkai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import qinkai.entity.College;
import qinkai.entity.Course;
import qinkai.entity.Teacher;
import qinkai.util.JDBCUtil;

public class CourseDao {
	
	public Course findCourse(int cid) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "SELECT * "
				+ "FROM course, teacher, college "
				+ "WHERE course.tuname = teacher.username AND course.coid = college.coid and course.cid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, cid);
		ResultSet rs = ps.executeQuery();
		Course course = null;
		if (rs.next()) {
			String cname = rs.getString("cname");
			String introduction = rs.getString("introduction");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String realname = rs.getString("realname");
			int coid = rs.getInt("coid");
			String coname = rs.getString("coname");
			College college = new College(coid, coname);
			Teacher teacher = new Teacher(username, password, realname, null);
			course = new Course(cid, cname, introduction, teacher, college);
		}
		JDBCUtil.release(conn, ps, rs);
		return course;
	}

	public List<Course> findCoursesByCollegeAndTeacher(String collegeName, String teacherName) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * "
				+ "FROM course, teacher, college "
				+ "WHERE course.tuname = teacher.username AND course.coid = college.coid";
		if (collegeName != null && !collegeName.trim().equals("")) {
			sql = sql + " and college.coname='" + collegeName + "'";
		}
		if (teacherName != null && !teacherName.trim().equals("")) {
			sql = sql + " and teacher.realname='" + teacherName + "'";
		}
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Course> list = new ArrayList<>();
		while (rs.next()) {
			int cid = rs.getInt("cid");
			String cname = rs.getString("cname");
			String introduction = rs.getString("introduction");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String realname = rs.getString("realname");
			int coid = rs.getInt("coid");
			String coname = rs.getString("coname");
			College college = new College(coid, coname);
			Teacher teacher = new Teacher(username, password, realname, null);
			Course course = new Course(cid, cname, introduction, teacher, college);
			list.add(course);
		}
		JDBCUtil.release(conn, ps, rs);
		return list;
	}

	public List<Course> findCourseByTuname(String username) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * "
				+ "FROM course, teacher, college "
				+ "WHERE course.tuname = teacher.username AND course.coid = college.coid and teacher.username=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		List<Course> list = new ArrayList<>();
		while (rs.next()) {
			int cid = rs.getInt("cid");
			String cname = rs.getString("cname");
			String introduction = rs.getString("introduction");
			String password = rs.getString("password");
			String realname = rs.getString("realname");
			int coid = rs.getInt("coid");
			String coname = rs.getString("coname");
			College college = new College(coid, coname);
			Teacher teacher = new Teacher(username, password, realname, null);
			Course course = new Course(cid, cname, introduction, teacher, college);
			list.add(course);
		}
		JDBCUtil.release(conn, ps, rs);
		return list;
	}

	public boolean updateCourse(int cid, String cname, String tuname, int coid, String introduction) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update course set cname=?, introduction=?, tuname=?, coid=? where cid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, cname);
		ps.setString(2, introduction);
		ps.setString(3, tuname);
		ps.setInt(4, coid);
		ps.setString(5, introduction);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

	public boolean delCourse(int cid) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "delete from course where cid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, cid);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

	public boolean addCourse(int cid, String cname, String introduction, String tuname, int coid) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into course values(?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, cid);
		ps.setString(2, cname);
		ps.setString(3, introduction);
		ps.setString(4, tuname);
		ps.setInt(5, coid);
		int cnt = ps.executeUpdate();
		JDBCUtil.release(conn, ps);
		return cnt > 0;
	}

}
