package com.m.tripcard.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobGeoPoint;

/*************************************************************
这是我们的景点的最简数据结构，由于景点只允许查询不允许修改，
但评级栏是可以动态修改的
*************************************************************/
public class ScenicSpots extends BmobObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int city_id;     //城市ID
	private int spots_id;    //景点ID
	private String name;     //景点名称
	private String info;     //景点介绍
	private int rate;        //景点星级
	//private Arry picture;  //景点图片
	private BmobGeoPoint spots_gps; //GPS坐标数据
	
	public int getCityId() {
		return city_id;
	}
	public void setCityId(int city_id) {
		this.city_id = city_id;
	}

	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	public int getSpotsId() {
		return spots_id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getInfo() {
		return info;
	}
	
	public BmobGeoPoint getSpotsGps() {
		return spots_gps;
	}
}
