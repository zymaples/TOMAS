package qinkai.entity;

public class Authority {
	private Student student;
	private Course course;
	public Authority() {
		
	}
	public Authority(Student student, Course course) {
		super();
		this.student = student;
		this.course = course;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	
}
