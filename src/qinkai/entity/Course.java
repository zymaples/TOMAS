package qinkai.entity;

public class Course {
	private int cid;
	private String cname;
	private String introduction;
	private Teacher teacher;
	private College college;
	public Course() {
		
	}
	public Course(int cid, String cname, String introduction, Teacher teacher, College college) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.introduction = introduction;
		this.teacher = teacher;
		this.college = college;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
}
