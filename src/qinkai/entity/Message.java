package qinkai.entity;

import java.util.Date;
import java.util.List;

public class Message {
	private int mid;
	private String title;
	private String content;
	private Date date;
	private Course course;
	private Student student;
	private List<Answer> answers;
	public Message() {
	}
	public Message(int mid, String title, String content, Date date, Course course, Student student) {
		super();
		this.mid = mid;
		this.title = title;
		this.content = content;
		this.date = date;
		this.course = course;
		this.student = student;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
}
