package com.fnst.officeapply.entity;

//该类与数据库中HistoryRecord数据表对应
public class HistoryRecord {
	private int historyRecordListID;   //历史记录ID
	private String goodsName;          //办公用品名
	private String userName;           //用户名
	private int count;                 //数量
	private String userRealName;
	private String groupName;
	
    public HistoryRecord() {
	}

	public HistoryRecord(int historyRecordListID, String goodsName,
			String userName, int count, String userRealName, String groupName) {
		this.historyRecordListID = historyRecordListID;
		this.goodsName = goodsName;
		this.userName = userName;
		this.count = count;
		this.userRealName = userRealName;
		this.groupName = groupName;
	}


	//以下是对上述信息的get与set方法
	public int getHistoryRecordListID() {
		return historyRecordListID;
	}
	public void setHistoryRecordListID(int historyRecordListID) {
		this.historyRecordListID = historyRecordListID;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}

}
