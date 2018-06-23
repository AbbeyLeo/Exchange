package com.exchange.entity;
//
/**
 * @author ���ͷ�
 * ��Ʒʵ����
 */
public class COMMODITY {
	private String commodity_id;					//��Ʒ��ʶ���
	private String commodity_name;					//��Ʒ����
	private char commodity_type;					//��Ʒ���
	private char commodity_Ptype;					//��Ʒ�����
	private String commodity_detail;				//��Ʒ������
	private String commodity_imgUrl;				//��ƷͼƬ��ַ
	private double commodity_originalPrice;			//ԭ��
	private char commodity_exchangeSth;				//Ԥ�ڽ�����Ʒ����
	private double commodity_expectPrice;			//Ԥ�ڼ۸�
	private boolean commodity_state;				//��Ʒ״��
	private String commodity_degree;				//�¾ɳ̶�
	private String commodity_sellerAddress;			//���ҵ�ַ
	
	/**
	 * @author ���ͷ�
	 * �޲ι��캯��
	 */
	public COMMODITY() {
		
	}
	/**@author ���ͷ�
	 * ȫ�ι��캯��
	 * @param commodity_id ��Ʒ��ʶ���
	 * @param commodity_name ��Ʒ����
	 * @param commodity_type ��Ʒ���
	 * @param commodity_Ptype ��Ʒ�����
	 * @param commodity_detail ��Ʒ������
	 * @param commodity_imgUrl ��ƷͼƬ��ַ
	 * @param commodity_originalPrice ԭ��
	 * @param commodity_exchangeSth Ԥ�ڽ�����Ʒ����
	 * @param commodity_expectPrice Ԥ�ڼ۸�
	 * @param commodity_state ��Ʒ״��
	 * @param commodity_degree �¾ɳ̶�
	 * @param commodity_sellerAddress ���ҵ�ַ
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
	 * @author ���ͷ�
	 * setter��getter
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
