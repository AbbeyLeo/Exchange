package com.exchange.bean;

public class PublishedItem {
	String username;
	String decration;
	String price;
	String time = "10分钟前";
	public PublishedItem(String username, String decration, String price){
		this.username = username;
		this.decration = decration;
		this.price = price;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDecration() {
		return decration;
	}
	public void setDecration(String decration) {
		this.decration = decration;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
