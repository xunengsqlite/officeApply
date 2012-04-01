package com.fnst.officeapply.dao;

public interface SQLInfo {
	
	public String INSERT_USER="insert into UserInfo values (?,?,?,?,?,?,?,?,?);";
	
	public String UPDATE_USER="update UserInfo set password=?,userRealName=?,userMail=?,permissionID=? where userName=?;";
	
	public String DELETE_USER="delete from UserInfo where id=?;";
	
	public String SHOW_USER="select u.username,u.password,u.userRealName,u.userMail,a.permission,a.permissionName,d.departmentName," +
		"o.officeName,p.groupName from UserInfo u,Authority a,Department d,SectionOffice o," +
		"ProjectGroup p where u.permissionID=a.id and u.departmentID=d.id and u.officeID=o.id and u.groupID=p.id and a.permission<2;";
	
	public String SHOW_USER_BY_NAME="select u.id,u.username,u.password,u.userRealName,u.userMail,u.permissionID,a.permission,a.permissionName,u.departmentID,d.departmentName," +
		"u.officeID,o.officeName,u.groupID,p.groupName from UserInfo u,Authority a,Department d,SectionOffice o," +
		"ProjectGroup p where u.permissionID=a.id and u.departmentID=d.id and u.officeID=o.id and u.groupID=p.id and u.userName=?;";
	
	//public String SHOW_HISTORY_LIST ="select * from HistoryRecordList;";
	
	public String SHOW_HISTORY_DETAIL="select a.goodsName,a.userName,a.count,a.userRealName,p.groupName from HistoryRecord a," +
		"HistoryRecordList b,ProjectGroup p where a.historyRecordListID=b.historyRecordListID and b.groupID=p.id and a.historyRecordListID=? order by ";
}
