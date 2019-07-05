package qinkai.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import qinkai.dao.AnswerDao;
import qinkai.dao.AuthorityDao;
import qinkai.dao.CourseDao;
import qinkai.dao.MessageDao;
import qinkai.dao.StudentDao;
import qinkai.entity.Answer;
import qinkai.entity.Course;
import qinkai.entity.Message;
import qinkai.entity.PageModel;
import qinkai.entity.Student;

public class StudentService {
	public Student login(String username, String password) throws SQLException {
		StudentDao studentDao = new StudentDao();
		return studentDao.login(username, password);
	}

	public boolean register(String username, String password, String realname) throws SQLException {
		StudentDao studentDao = new StudentDao();
		return studentDao.addStudent(username, password, realname);
	}

	public List<Course> showCourseList(String collegeName, String teacherName) throws SQLException {
		CourseDao courseDao = new CourseDao();
		return courseDao.findCoursesByCollegeAndTeacher(collegeName, teacherName);
	}

	public boolean saveMsg(String title, String content, int cid, String username) throws SQLException {
		MessageDao messageDao = new MessageDao();
		return messageDao.saveMsg(title,content, cid, username);
	}
	
	public Course findCourse(int cid) throws SQLException {
		CourseDao courseDao = new CourseDao();
		return courseDao.findCourse(cid);
	}

	public List<Message> showMyMsgs(String username) throws SQLException {
		MessageDao messageDao = new MessageDao();
		return messageDao.findMsgsBySuname(username);
	}

	public List<Message> showMsgList(String courseName, String teacherName, String keywords) throws SQLException {
		MessageDao messageDao = new MessageDao();
		return messageDao.findAllMsgsByConditions(courseName, teacherName, keywords);
	}

	public boolean delMsg(int mid) throws SQLException{
		MessageDao messageDao = new MessageDao();
		return messageDao.delMsg(mid);
	}

	public Message findMessageById(int mid) throws SQLException {
		MessageDao messageDao = new MessageDao();
		return messageDao.findMessageById(mid);
	}

	public boolean updateMsg(int mid, String title, String content) throws SQLException {
		MessageDao messageDao = new MessageDao();
		return messageDao.updateMsg(mid, title, content);
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

	public List<Answer> showAnswerList(String username) throws SQLException {
		MessageDao messageDao = new MessageDao();
		AnswerDao answerDao = new AnswerDao();
		List<Message> msgs = messageDao.findMsgsBySuname(username);
		List<Answer> list = new ArrayList<>();
		for (Message msg : msgs) {
			List<Answer> answers = answerDao.findAnswersByMid(msg.getMid());
			for (Answer answer : answers) {
				answer.setMessage(msg);
			}
			list.addAll(answers);
		}
		return list;
	}

	public List<Message> findAllMsgs() throws SQLException {
		MessageDao messageDao = new MessageDao();
		return messageDao.findAllMsgsByConditions(null, null, null);
	}

	public boolean changePassword(String username, String password) throws SQLException {
		StudentDao studentDao = new StudentDao();
		return studentDao.changePassword(username, password);
	}

	public boolean checkUsername(String username) throws SQLException {
		StudentDao studentDao = new StudentDao();
		return studentDao.checkUsername(username);
	}

	public boolean checkAuthority(String username, int cid) throws SQLException {
		AuthorityDao anthorityDao = new AuthorityDao();
		return anthorityDao.checkAuthority(username, cid);
	}

	public PageModel showMsgListWithPage(String courseName, String teacherName, String keywords, int currentPage) throws SQLException {
		MessageDao messageDao = new MessageDao();
		List<Message> msgs = messageDao.findAllMsgsByConditionsWithPage(courseName, teacherName, keywords, currentPage);
		int totalSize = messageDao.findMsgCountByConditions(courseName, teacherName, keywords);
		PageModel pageModel = new PageModel(currentPage, 5, totalSize, msgs);
		return pageModel;
	}
}
