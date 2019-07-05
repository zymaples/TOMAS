package qinkai.entity;

public class College {
	private int coid;
	private String coname;
	public College() {
		
	}
	
	public College(int coid, String coname) {
		super();
		this.coid = coid;
		this.coname = coname;
	}

	public int getCoid() {
		return coid;
	}
	public void setCoid(int coid) {
		this.coid = coid;
	}
	public String getConame() {
		return coname;
	}
	public void setConame(String coname) {
		this.coname = coname;
	}
	
}
