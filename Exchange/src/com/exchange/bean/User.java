package com.exchange.bean;

public class User {
	private String username;
	private int head_pic_id; //头像的位置
	
	
	
	public User(String username, int head_pic_id) {
		this.username = username;
		this.head_pic_id = head_pic_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getHead_pic_id() {
		return head_pic_id;
	}
	public void setHead_pic_id(int head_pic_id) {
		this.head_pic_id = head_pic_id;
	}
	
}
