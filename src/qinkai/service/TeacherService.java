package qinkai.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import qinkai.dao.AnswerDao;
import qinkai.dao.AuthorityDao;
import qinkai.dao.CourseDao;
import qinkai.dao.MessageDao;
import qinkai.dao.TeacherDao;
import qinkai.entity.Answer;
import qinkai.entity.Authority;
import qinkai.entity.Course;
import qinkai.entity.Message;
import qinkai.entity.Teacher;

public class TeacherService {
	public Teacher login(String username, String password) throws SQLException {
		TeacherDao teacherDao = new TeacherDao();
		return teacherDao.login(username, password);
	}
	
	public List<Message> showUnansweredMsgList(String username) throws SQLException {
		MessageDao messageDao = new MessageDao();
		return messageDao.findUnansweredMsgsByTuname(username);
	}

	public List<Message> findMessageAndAnswer(int cid) throws SQLException {
		MessageDao messageDao = new MessageDao();
		List<Message> list = messageDao.findMsgsByCid(cid);
		AnswerDao answerDao = new AnswerDao();
		for (Message m : list) {
			List<Answer> answers = answerDao.findAnswersByMid(m.getMid());
			m.setAnswers(answers);
		}
		return list;
	}

	public Course findCourse(int cid) throws SQLException {
		CourseDao courseDao = new CourseDao();
		return courseDao.findCourse(cid);
	}

	public List<Course> showMyCourseList(String username) throws SQLException {
		CourseDao courseDao = new CourseDao();
		return courseDao.findCourseByTuname(username);
	}

	public boolean answer(String content, int mid) throws SQLException {
		AnswerDao answerDao = new AnswerDao();
		MessageDao messageDao = new MessageDao();
		messageDao.updateStatus(mid);
		return answerDao.saveAnswer(content, mid);
	}

	public List<Answer> showMyAnswerList(String username) throws SQLException {
		CourseDao courseDao = new CourseDao();
		MessageDao messageDao = new MessageDao();
		AnswerDao answerDao = new AnswerDao();
		List<Answer> list = new ArrayList<>();
		List<Course> courses = courseDao.findCourseByTuname(username);
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

	public boolean delAnswer(int aid) throws SQLException {
		AnswerDao answerDao = new AnswerDao();
		return answerDao.delAnswer(aid);
	}

	public boolean updateAnswer(int aid, String content) throws SQLException {
		AnswerDao answerDao = new AnswerDao();
		return answerDao.updateAnswer(aid, content);
	}

	public List<Message> showMsgList(String username) throws SQLException {
		CourseDao courseDao = new CourseDao();
		MessageDao messageDao = new MessageDao();
		List<Course> courses = courseDao.findCourseByTuname(username);
		List<Message> list = new ArrayList<>();
		for (Course course : courses) {
			List<Message> msgs = messageDao.findMsgsByCid(course.getCid());
			for (Message msg : msgs) {
				msg.setCourse(course);
			}
			list.addAll(msgs);
		}
		return list;
	}

	public boolean delMsg(int mid) throws SQLException {
		MessageDao messageDao = new MessageDao();
		return messageDao.delMsg(mid);
	}

	public boolean changePassword(String username, String password) throws SQLException {
		TeacherDao teacherDao = new TeacherDao();
		return teacherDao.changePassword(username, password);
	}

	public List<Authority> showAuthorityList(int cid, String suname, String tuname) throws SQLException {
		AuthorityDao authorityDao = new AuthorityDao();
		return authorityDao.findByConditions(cid, suname, tuname);
	}

	public boolean delAuthority(int cid, String suname) throws SQLException {
		AuthorityDao authorityDao = new AuthorityDao();
		return authorityDao.delAuthority(cid, suname);
	}

	public boolean addAuthority(int cid, String suname) throws SQLException {
		AuthorityDao authorityDao = new AuthorityDao();
		return authorityDao.addAuthority(cid, suname);
	}
}
