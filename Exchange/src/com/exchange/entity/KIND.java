package com.exchange.entity;

public class KIND {
	private String kind_Id;
	private String kind_name;
	private int kind_Pid;
	public KIND() {
	}
	public KIND(String kind_Id, String kind_name, int kind_Pid) {
		this.kind_Id = kind_Id;
		this.kind_name = kind_name;
		this.kind_Pid = kind_Pid;
	}
	public String getKind_Id() {
		return kind_Id;
	}
	public void setKind_Id(String kind_Id) {
		this.kind_Id = kind_Id;
	}
	public String getKind_name() {
		return kind_name;
	}
	public void setKind_name(String kind_name) {
		this.kind_name = kind_name;
	}
	public int getKind_Pid() {
		return kind_Pid;
	}
	public void setKind_Pid(int kind_Pid) {
		this.kind_Pid = kind_Pid;
	}
}
