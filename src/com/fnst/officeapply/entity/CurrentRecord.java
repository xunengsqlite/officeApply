package com.fnst.officeapply.entity;

//该类与数据库中CurrentRecord数据表对应
public class CurrentRecord {
	private int goodsID;
	private String goodsName;     //办公用品名
	private int userID;
	private String userName;      //用户名
	private String userRealName;      //用户真实姓名
	private int count;            //数量
	private int groupID;
	private String groupName;
	
	public CurrentRecord() {}
	
	public CurrentRecord(int goodsID, int userID, int count) {
		this.goodsID = goodsID;
		this.userID = userID;
		this.count = count;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGoodsID(int goodsID) {
		this.goodsID = goodsID;
	}

	public int getGoodsID() {
		return goodsID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getUserID() {
		return userID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public int getGroupID() {
		return groupID;
	}
}
