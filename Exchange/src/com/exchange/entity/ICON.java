package com.exchange.entity;

public class ICON {
	private String iconColor;			//icon������ɫ
	private int iconResource;			//icon��resource
	private String iconTitle;				//��������
	public ICON() {
		super();
	}
	public ICON(String iconColor, int iconResource, String iconTitle) {
		super();
		this.iconColor = iconColor;
		this.iconResource = iconResource;
		this.iconTitle = iconTitle;
	}
	public String getIconColor() {
		return iconColor;
	}
	public void setIconColor(String iconColor) {
		this.iconColor = iconColor;
	}
	public int getIconResource() {
		return iconResource;
	}
	public void setIconResource(int iconResource) {
		this.iconResource = iconResource;
	}
	public String getIconTitle() {
		return iconTitle;
	}
	public void setIconTitle(String iconTitle) {
		this.iconTitle = iconTitle;
	}
}
