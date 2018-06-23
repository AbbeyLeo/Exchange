package com.exchange.entity;

public class CATEGORY {
	private int category_id;				//商品类别标识号
	private String category_name;			//商品类别名
	private String category_bgcolor;		//商品类别背景颜色
	private int category_icon;				//商品类别图标
	public CATEGORY() {
	}
	public CATEGORY(int category_id, String category_name,
			String category_bgcolor, int category_icon) {
		this.category_id = category_id;
		this.category_name = category_name;
		this.category_bgcolor = category_bgcolor;
		this.category_icon = category_icon;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCategory_bgcolor() {
		return category_bgcolor;
	}
	public void setCategory_bgcolor(String category_bgcolor) {
		this.category_bgcolor = category_bgcolor;
	}
	public int getCategory_icon() {
		return category_icon;
	}
	public void setCategory_icon(int category_icon) {
		this.category_icon = category_icon;
	}
}
