package com.fnst.officeapply.entity;

import com.fnst.officeapply.common.ComUtil;

//该类与数据库中UserInfo数据表对应
public class UserInfo {
	private int userID;
	private String userName; // 用户名
	private String password; // 用户密码
	private String userRealName; // 用户真实姓名
	private String mail; // 用户邮箱
	private int permissionID;
	private int permission; // 用户权限
	private String permissionName;
	private int departmentID;
	private String departmentName;
	private int officeID;
	private String officeName;
	private int groupID;
	private String groupName;
	
	public UserInfo() {}
	
	public UserInfo(String userName, String password, String userRealName,
			String mail, int permissionID, int departmentID, int officeID,
			int groupID) {
		this.userName = userName;
		this.password = password;
		this.userRealName = userRealName;
		this.mail = mail;
		this.permissionID = permissionID;
		this.departmentID = departmentID;
		this.officeID = officeID;
		this.groupID = groupID;
	}
	
	public UserInfo(String userName, String password, String userRealName,
			String mail, int permissionID) {
		this.userName = userName;
		this.password = password;
		this.userRealName = userRealName;
		this.mail = mail;
		this.permissionID = permissionID;
	}
	
	public UserInfo(int userID, String userName, String password,
			String userRealName, String mail, int permissionID, int permission,
			String permissionName, int departmentID, String departmentName,
			int officeID, String officeName, int groupID, String groupName) {
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.userRealName = userRealName;
		this.mail = mail;
		this.permissionID = permissionID;
		this.permission = permission;
		this.permissionName = permissionName;
		this.departmentID = departmentID;
		this.departmentName = departmentName;
		this.officeID = officeID;
		this.officeName = officeName;
		this.groupID = groupID;
		this.groupName = groupName;
	}

//	public UserInfo(String userName, String password, String userRealName, String mail, String departmentName,
//			String officeName, String groupName) {
//		this.userName = userName;
//		this.password = password;
//		this.userRealName = userRealName;
//		this.mail = mail;
//		this.departmentName = departmentName;
//		this.officeName = officeName;
//		this.groupName = groupName;
//	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UserInfo)) {
			return false;
		}
		UserInfo user = (UserInfo) obj;
		if (ComUtil.isEqual(this.userName, user.userName) && this.userID == user.userID) {
			return true;
		} else {
			return false;
		}
	}
	
	//equals()为true,hashCode()一定相等，equals()为false,hashCode()不一定不相等，
	public int hashCode(){
		return userID*(userName.hashCode());
	}
	
	// 以下是对上述信息的get与set方法
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionID(int permissionID) {
		this.permissionID = permissionID;
	}

	public int getPermissionID() {
		return permissionID;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	public int getDepartmentID() {
		return departmentID;
	}

	public void setOfficeID(int officeID) {
		this.officeID = officeID;
	}

	public int getOfficeID() {
		return officeID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public int getGroupID() {
		return groupID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getUserID() {
		return userID;
	}

}
