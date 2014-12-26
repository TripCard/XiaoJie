package com.m.tripcard.model;

import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.v3.datatype.BmobRelation;

/*****************************************************
对应的后台测试接口

注：这个是用户的基本数据结构，后续我会在这里扩充，目
前正在整理景点与用户的关联数据
*****************************************************/
public class UserDTO extends BmobChatUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer age;    //年龄
	private Boolean gender; //性别
	private int grade;      //等级暂定0~5吧
	private String owner_sign; //个性签名
	private String hometown;  //家乡
	private String current_address;  //当前所在地
	//private File  head_portrait; //头像
	
	private BmobRelation user_spots; //关于用户签到的景点
	
	public void setSpots(BmobRelation scenic_spots) {
		this.user_spots = scenic_spots;
	}
	
	
	
	public BmobRelation getUserSpots() {
		return user_spots;
	}



	public void setUserSpots(BmobRelation user_spots) {
		this.user_spots = user_spots;
	}



	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	
	public int getGrade() {
		return grade;
		
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public String getOwnerSign() {
		return owner_sign;
	}
	
	public void setOwnerSign(String ownersign) {
		this.owner_sign = ownersign;
	}
	
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	
	public String getCurrentAddress() {
		return current_address;
	}
	public void setCurrentAddress(String current_address) {
		this.current_address = current_address;
	}
}
