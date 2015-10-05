package com.dantefung.hibernate_one2one;

public class Card {
	private int user_id;
	private String cardNo;
	private String place;
	// 对应的用户
	private User user;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Card [user_id=" + user_id + ", cardNo=" + cardNo + ", place="
				+ place + ", user=" + user + "]";
	}
	
	
}
