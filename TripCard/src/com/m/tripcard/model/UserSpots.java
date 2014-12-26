package com.m.tripcard.model;

import cn.bmob.v3.BmobObject;

/********************************************************
用户景点数据

*********************************************************/


public class UserSpots extends BmobObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserDTO user;  //关联用户
	 
	private int city_id;  //城市ID，后续每个城市分配一个ID用于检索
	private int spots_id; //景点ID
	private String message; //关于每个景点用户的留言
	//private Arry picture; //关于每个景点用户上传的图片
	
	
	public void setCityId(int city_id) {
		this.city_id = city_id;
	}
	public int getCityId() {
		return city_id;
	}
	
	public void setSpotsId(int spots_id) {
		this.spots_id = spots_id;
	}
	public int getSpotsId() {
		return spots_id;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage(){
		return message;
	}
	
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public UserDTO getUser() {
		return user;
	}
	
	
}
