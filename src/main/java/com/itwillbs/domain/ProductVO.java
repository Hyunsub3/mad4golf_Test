package com.itwillbs.domain;

public class ProductVO {
	private int prod_num;
	private String seller_num;
	private String buyer_num;
	private String prod_name;
	private int price;
	private String detail;
	private String condition;
	private String category;
	private int gender;
	private String state;
	private int like_count;
	private String payment;
	public int getProd_num() {
		return prod_num;
	}
	public void setProd_num(int prod_num) {
		this.prod_num = prod_num;
	}
	public String getSeller_num() {
		return seller_num;
	}
	public void setSeller_num(String seller_num) {
		this.seller_num = seller_num;
	}
	public String getBuyer_num() {
		return buyer_num;
	}
	public void setBuyer_num(String buyer_num) {
		this.buyer_num = buyer_num;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	
	@Override
	public String toString() {
		return "ProductVO [prod_num=" + prod_num + ", seller_num=" + seller_num + ", buyer_num=" + buyer_num
				+ ", prod_name=" + prod_name + ", price=" + price + ", detail=" + detail + ", condition=" + condition
				+ ", category=" + category + ", gender=" + gender + ", state=" + state + ", like_count=" + like_count
				+ ", payment=" + payment + "]";
	}
	
	
}
