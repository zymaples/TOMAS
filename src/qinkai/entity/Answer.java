package qinkai.entity;

import java.util.Date;

public class Answer {
	private int aid;
	private String content;
	private Date date;
	private int mid;
	private Message message;
	public Answer() {
	}
	public Answer(int aid, String content, Date date, int mid, Message message) {
		super();
		this.aid = aid;
		this.content = content;
		this.date = date;
		this.mid = mid;
		this.message = message;
	}
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
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
	
}
