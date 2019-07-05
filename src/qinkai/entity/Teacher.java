package qinkai.entity;

public class Teacher {
	private String username;
	private String password;
	private String realname;
	private String position;
	private String introduction;
	private College college;
	public Teacher() {
		
	}
	public Teacher(String username, String password, String realname, College college) {
		super();
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.college = college;
	}
	
	public Teacher(String username, String password, String realname, String position, String introduction,
			College college) {
		super();
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.position = position;
		this.introduction = introduction;
		this.college = college;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
}
