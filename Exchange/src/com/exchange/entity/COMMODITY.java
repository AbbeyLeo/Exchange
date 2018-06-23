package com.exchange.entity;
//
/**
 * @author 杨贤锋
 * 商品实体类
 */
public class COMMODITY {
	private String commodity_id;					//商品标识编号
	private String commodity_name;					//商品名称
	private char commodity_type;					//商品类别
	private char commodity_Ptype;					//商品父类别
	private String commodity_detail;				//商品规格参数
	private String commodity_imgUrl;				//商品图片地址
	private double commodity_originalPrice;			//原价
	private char commodity_exchangeSth;				//预期交换物品类型
	private double commodity_expectPrice;			//预期价格
	private boolean commodity_state;				//商品状况
	private String commodity_degree;				//新旧程度
	private String commodity_sellerAddress;			//卖家地址
	
	/**
	 * @author 杨贤锋
	 * 无参构造函数
	 */
	public COMMODITY() {
		
	}
	/**@author 杨贤锋
	 * 全参构造函数
	 * @param commodity_id 商品标识编号
	 * @param commodity_name 商品名称
	 * @param commodity_type 商品类别
	 * @param commodity_Ptype 商品父类别
	 * @param commodity_detail 商品规格参数
	 * @param commodity_imgUrl 商品图片地址
	 * @param commodity_originalPrice 原价
	 * @param commodity_exchangeSth 预期交换物品类型
	 * @param commodity_expectPrice 预期价格
	 * @param commodity_state 商品状况
	 * @param commodity_degree 新旧程度
	 * @param commodity_sellerAddress 卖家地址
	 */
	public COMMODITY(String commodity_id, String commodity_name,
			char commodity_type, char commodity_Ptype, String commodity_detail,
			String commodity_imgUrl, double commodity_originalPrice,
			char commodity_exchangeSth, double commodity_expectPrice,
			boolean commodity_state, String commodity_degree,
			String commodity_sellerAddress) {
		super();
		this.commodity_id = commodity_id;
		this.commodity_name = commodity_name;
		this.commodity_type = commodity_type;
		this.commodity_Ptype = commodity_Ptype;
		this.commodity_detail = commodity_detail;
		this.commodity_imgUrl = commodity_imgUrl;
		this.commodity_originalPrice = commodity_originalPrice;
		this.commodity_exchangeSth = commodity_exchangeSth;
		this.commodity_expectPrice = commodity_expectPrice;
		this.commodity_state = commodity_state;
		this.commodity_degree = commodity_degree;
		this.commodity_sellerAddress = commodity_sellerAddress;
	}
	/**
	 * @author 杨贤锋
	 * setter和getter
	 */
	public String getCommodity_id() {
		return commodity_id;
	}
	public void setCommodity_id(String commodity_id) {
		this.commodity_id = commodity_id;
	}
	public String getCommodity_name() {
		return commodity_name;
	}
	public void setCommodity_name(String commodity_name) {
		this.commodity_name = commodity_name;
	}
	public char getCommodity_type() {
		return commodity_type;
	}
	public void setCommodity_type(char commodity_type) {
		this.commodity_type = commodity_type;
	}
	public char getCommodity_Ptype() {
		return commodity_Ptype;
	}
	public void setCommodity_Ptype(char commodity_Ptype) {
		this.commodity_Ptype = commodity_Ptype;
	}
	public String getCommodity_detail() {
		return commodity_detail;
	}
	public void setCommodity_detail(String commodity_detail) {
		this.commodity_detail = commodity_detail;
	}
	public String getCommodity_imgUrl() {
		return commodity_imgUrl;
	}
	public void setCommodity_imgUrl(String commodity_imgUrl) {
		this.commodity_imgUrl = commodity_imgUrl;
	}
	public double getCommodity_originalPrice() {
		return commodity_originalPrice;
	}
	public void setCommodity_originalPrice(double commodity_originalPrice) {
		this.commodity_originalPrice = commodity_originalPrice;
	}
	public char getCommodity_exchangeSth() {
		return commodity_exchangeSth;
	}
	public void setCommodity_exchangeSth(char commodity_exchangeSth) {
		this.commodity_exchangeSth = commodity_exchangeSth;
	}
	public double getCommodity_expectPrice() {
		return commodity_expectPrice;
	}
	public void setCommodity_expectPrice(double commodity_expectPrice) {
		this.commodity_expectPrice = commodity_expectPrice;
	}
	public boolean isCommodity_state() {
		return commodity_state;
	}
	public void setCommodity_state(boolean commodity_state) {
		this.commodity_state = commodity_state;
	}
	public String getCommodity_degree() {
		return commodity_degree;
	}
	public void setCommodity_degree(String commodity_degree) {
		this.commodity_degree = commodity_degree;
	}
	public String getCommodity_sellerAddress() {
		return commodity_sellerAddress;
	}
	public void setCommodity_sellerAddress(String commodity_sellerAddress) {
		this.commodity_sellerAddress = commodity_sellerAddress;
	}
	@Override
	public String toString() {
		return "COMMODITY [commodity_id=" + commodity_id + ", commodity_name="
				+ commodity_name + ", commodity_type=" + commodity_type
				+ ", commodity_Ptype=" + commodity_Ptype
				+ ", commodity_detail=" + commodity_detail
				+ ", commodity_imgUrl=" + commodity_imgUrl
				+ ", commodity_originalPrice=" + commodity_originalPrice
				+ ", commodity_exchangeSth=" + commodity_exchangeSth
				+ ", commodity_expectPrice=" + commodity_expectPrice
				+ ", commodity_state=" + commodity_state
				+ ", commodity_degree=" + commodity_degree
				+ ", commodity_sellerAddress=" + commodity_sellerAddress + "]";
	}
	
}
