package qinkai.entity;

public class Admin {
	private String username;
	private String password;
	private String realname;
	
	public Admin() {
		
	}
	
	public Admin(String username, String password, String realname) {
		super();
		this.username = username;
		this.password = password;
		this.realname = realname;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
