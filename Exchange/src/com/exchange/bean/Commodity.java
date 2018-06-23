package com.exchange.bean;

import java.util.ArrayList;
import java.util.List;

import com.exchange.activity.R;

public class Commodity {
	private List<Integer> cmd_image_list;  //发布二手商品的图片的id
	private String decration;										  //二手商品的描述
	private String price;											  //二手商品的价格
	
	
	
	public Commodity(String decration, String price) {
		this.decration = decration;
		this.price = price;
		setCmd_image_list();
	}
	
	public List<Integer> getCmd_image_list() {
		return cmd_image_list;
	}
	public void setCmd_image_list(List<Integer> cmd_image_list) {
		this.cmd_image_list = cmd_image_list;
	}
	public void setCmd_image_list() {
		cmd_image_list = new ArrayList<Integer>();
		cmd_image_list.set(0, R.drawable.game1);
		cmd_image_list.set(1, R.drawable.game2);
		cmd_image_list.set(2, R.drawable.game3);
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
}
