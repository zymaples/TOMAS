package qinkai.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import qinkai.dao.AdminDao;
import qinkai.dao.AnswerDao;
import qinkai.dao.CollegeDao;
import qinkai.dao.CourseDao;
import qinkai.dao.MessageDao;
import qinkai.dao.TeacherDao;
import qinkai.entity.Admin;
import qinkai.entity.Answer;
import qinkai.entity.College;
import qinkai.entity.Course;
import qinkai.entity.Message;
import qinkai.entity.Teacher;

public class AdminService {

	public Admin login(String username, String password) throws SQLException {
		AdminDao adminDao = new AdminDao();
		return adminDao.login(username, password);
	}

	public List<Course> showCourseList(String collegeName, String teacherName) throws SQLException {
		CourseDao courseDao = new CourseDao();
		List<Course> list = courseDao.findCoursesByCollegeAndTeacher(collegeName, teacherName);
		return list;
	}

	public Course findCourse(int cid) throws SQLException {
		CourseDao courseDao = new CourseDao();
		return courseDao.findCourse(cid);
	}

	public boolean updateCourse(int cid, String cname, String tuname, int coid, String introduction) throws SQLException  {
		CourseDao courseDao = new CourseDao();
		return courseDao.updateCourse(cid, cname, tuname, coid,  introduction);
	}

	public boolean delCourse(int cid) throws SQLException {
		CourseDao courseDao = new CourseDao();
		return courseDao.delCourse(cid);
	}

	public boolean addCourse(int cid, String cname, String introduction, String tuname, int coid) throws SQLException  {
		CourseDao courseDao = new CourseDao();
		return courseDao.addCourse(cid, cname, introduction, tuname, coid);
	}

	public List<Teacher> showTeacherList() throws SQLException {
		TeacherDao teacherDao = new TeacherDao();
		return teacherDao.findAllTeachers();
	}

	public Teacher findTeacher(String username) throws SQLException {
		TeacherDao teacherDao = new TeacherDao();
		return teacherDao.findTeacher(username);
	}

	public boolean updateTeacher(String username, String realname, String position, String introduction,int coid) throws SQLException {
		TeacherDao teacherDao = new TeacherDao();
		return teacherDao.updateTeacher(username, realname, position, introduction, coid);
	}

	public boolean addTeacher(String username, String realname, String position, String introduction, int coid) throws SQLException {
		TeacherDao teacherDao = new TeacherDao();
		return teacherDao.addTeacher(username, realname, position, introduction, coid);
	}

	public boolean delTeacher(String username) throws SQLException {
		TeacherDao teacherDao = new TeacherDao();
		return teacherDao.delTeacher(username);
	}

	public List<Course> showTeacherCourseList(String username)  throws SQLException {
		CourseDao courseDao = new CourseDao();
		return courseDao.findCourseByTuname(username);
	}

	public List<College> showCollegeList() throws SQLException {
		CollegeDao collegeDao = new CollegeDao();
		return collegeDao.findAllColleges();
	}

	public boolean updateCollege(int coid, String coname) throws SQLException{
		CollegeDao collegeDao = new CollegeDao();
		return collegeDao.updateCollege(coid, coname);
	}

	public boolean addCollege(int coid, String coname) throws SQLException {
		CollegeDao collegeDao = new CollegeDao();
		return collegeDao.addCollege(coid, coname);
	}

	public boolean delCollege(int coid) throws SQLException {
		CollegeDao collegeDao = new CollegeDao();
		return collegeDao.delCollege(coid);
	}

	public List<Message> showMsgList(String courseName, String teacherName, String keywords) throws SQLException {
		MessageDao messageDao = new MessageDao();
		return messageDao.findAllMsgsByConditions(courseName, teacherName, keywords);
	}

	public boolean updateMsg(int mid, String title, String content) throws SQLException {
		MessageDao messageDao = new MessageDao();
		return messageDao.updateMsg(mid, title, content);
	}

	public boolean delMsg(int mid) throws SQLException {
		MessageDao messageDao = new MessageDao();
		return messageDao.delMsg(mid);
	}

	public List<Answer> showAnswerList() throws SQLException {
		CourseDao courseDao = new CourseDao();
		MessageDao messageDao = new MessageDao();
		AnswerDao answerDao = new AnswerDao();
		List<Answer> list = new ArrayList<>();
		List<Course> courses = courseDao.findCoursesByCollegeAndTeacher(null, null);
		for (Course course : courses) {
			List<Message> msgs = messageDao.findMsgsByCid(course.getCid());
			for(Message msg : msgs) {
				msg.setCourse(course);
				List<Answer> answers = answerDao.findAnswersByMid(msg.getMid());
				for (Answer answer : answers) {
					answer.setMessage(msg);
				}
				list.addAll(answers);
			}
		}
		return list;
	}

	public boolean updateAnswer(int aid, String content) throws SQLException {
		AnswerDao answerDao = new AnswerDao();
		return answerDao.updateAnswer(aid, content);
	}

	public boolean delAnswer(int aid) throws SQLException {
		AnswerDao answerDao = new AnswerDao();
		return answerDao.delAnswer(aid);
	}

	public boolean changePassword(String username, String password) throws SQLException {
		AdminDao adminDao = new AdminDao();
		return adminDao.changePassword(username, password);
	}
	
}
