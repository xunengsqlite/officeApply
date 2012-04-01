package com.fnst.officeapply.entity;

public class Authority {

	private int permissionID;
	private int permission;
	private String permissionName;
	
	public Authority() {
	}
	
	public Authority(int permissionID, int permission, String permissionName) {
		this.permissionID = permissionID;
		this.permission = permission;
		this.permissionName = permissionName;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public void setPermissionID(int permissionID) {
		this.permissionID = permissionID;
	}

	public int getPermissionID() {
		return permissionID;
	}
	
}
