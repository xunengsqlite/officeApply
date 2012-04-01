package com.fnst.officeapply.action;

import javax.servlet.http.HttpServletRequest;
import com.fnst.officeapply.common.ComUtil;
import com.fnst.officeapply.common.DesEncrypter;
import com.fnst.officeapply.common.InputCheckUtil;
import com.fnst.officeapply.common.mail.MailException;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.UserInfo;
import com.fnst.officeapply.exception.OfficeException;
import com.fnst.officeapply.framework.ActionSupport;
import com.fnst.officeapply.framework.annotation.AuthorizerRequest;
import com.fnst.officeapply.service.AdminUserService;

@AuthorizerRequest(authorizerStep=10)
public class AdminUserAction extends ActionSupport {

	private AdminUserService service;
	
	private String username;
	private String psd;
	private String name;
	private String mail;
	private int departmentID;
	private int officeID;
	private int groupID;
	private int pageNum;
	private String quserRealName;
	private String qgroupName;
	private String queryWord;
	
	public AdminUserAction() throws DBPoolException, OfficeException{
		service = new AdminUserService();
	}

	public String userNextList(HttpServletRequest request) throws DBPoolException, OfficeException{
		if(!InputCheckUtil.isPositiveNumber(pageNum + "")){
			pageNum = 1;
		}
		if(!InputCheckUtil.checkUserRealName(quserRealName)){
			quserRealName = "";
		}
		if(!InputCheckUtil.checkOrgzName(qgroupName)){
			qgroupName = "";
		}
		service.userList4Page(request, this);
		return "/WEB-INF/jsp/admin/user/ulist.jsp";
	}

	public String userAdderPage(HttpServletRequest request) throws DBPoolException, OfficeException{
		service.userAdderPage(request, this);
		return "/WEB-INF/jsp/admin/user/add.jsp";
	}
	
	public String addUser(HttpServletRequest request) throws DBPoolException, OfficeException, MailException{
		
		int permissionID = -1;
		
		boolean isOK = false;
		if (InputCheckUtil.checkUsername(username)
				&& InputCheckUtil.checkPassword(psd)
				&& InputCheckUtil.checkUserRealName(name)
				&& InputCheckUtil.isEmail(mail) 
				&& InputCheckUtil.isPositiveNumber(departmentID + "") 
				&& InputCheckUtil.isPositiveNumber(officeID + "") 
				&& InputCheckUtil.isPositiveNumber(groupID + "")) {
			// 加密
			String password = DesEncrypter.encrypttoStr(psd, DesEncrypter.KEY_2);
			UserInfo user = new UserInfo(username, password, name, mail, permissionID, departmentID, officeID, groupID);
			isOK = service.addUser(request, user);
			if(isOK){
				UserInfo u = new UserInfo(username, psd, name, mail, permissionID);
				//提醒邮件,用加密前的密码
				service.addUserReminder(u);
			}
		}
		return redirectPage(request, isOK, "admin/user/userAdderPage.action?pageNum=" + pageNum + "&qgroupName=" + qgroupName + "&quserRealName=" + quserRealName);
	}
	
	public String userUpdatePage(HttpServletRequest request) throws DBPoolException, OfficeException{
		UserInfo yourself = this.getUserFSession(request);
		String ownName = (yourself == null ? null : yourself.getUserName());
		if(ComUtil.isEqual(ownName, username)){
			return redirectPage(request, false, "admin/user/userNextList.action?pageNum=" + pageNum + "&qgroupName=" + qgroupName + "&quserRealName=" + quserRealName);
		}
		if(InputCheckUtil.checkUsername(username)){
			service.userUpdatePage(request, this);
		}
		return "/WEB-INF/jsp/admin/user/update.jsp";
	}
	
	public String updateUser(HttpServletRequest request) throws OfficeException, DBPoolException, MailException{
		int permissionID = -1;
		boolean isOK = false;
		UserInfo yourself = this.getUserFSession(request);
		String ownName = (yourself == null ? null : yourself.getUserName());
		if (!ComUtil.isEqual(ownName, username)
				&& InputCheckUtil.checkUsername(username)
				&& InputCheckUtil.checkPassword(psd)
				&& InputCheckUtil.checkUserRealName(name)
				&& InputCheckUtil.isEmail(mail)
				&& InputCheckUtil.isPositiveNumber(departmentID + "")
				&& InputCheckUtil.isPositiveNumber(officeID + "")
				&& InputCheckUtil.isPositiveNumber(groupID + "")) {
			// 加密
			String password = DesEncrypter.encrypttoStr(psd, DesEncrypter.KEY_2);
			UserInfo user = new UserInfo(username, password, name, mail, permissionID, departmentID, officeID, groupID);
			isOK = service.updateUser(request, user);
			if(isOK){
				UserInfo u = new UserInfo(username, psd, name, mail, permissionID);
				//提醒邮件,用加密前的密码
				service.updateUserReminder(u);
			}
		}
		return redirectPage(request, isOK, "admin/user/userNextList.action?pageNum=" + pageNum + "&qgroupName=" + qgroupName + "&quserRealName=" + quserRealName);
	}
	
	public String delUser(HttpServletRequest request) throws OfficeException, DBPoolException{
		boolean isOK = false;
		UserInfo yourself = this.getUserFSession(request);
		String ownName = (yourself == null ? null : yourself.getUserName());
		if(!ComUtil.isEqual(ownName, username) && InputCheckUtil.checkUsername(username)){
			isOK = service.delUser(request, username);
		}
		return redirectPage(request, isOK, "admin/user/userNextList.action?pageNum=" + pageNum + "&qgroupName=" + qgroupName + "&quserRealName=" + quserRealName);
	}
	
	public String autoComplete(HttpServletRequest request) throws OfficeException, DBPoolException{
		if(InputCheckUtil.checkUserRealName(queryWord)){
			service.autoComplete(request,this);
		}
		return "/WEB-INF/jsp/admin/user/ajaxdata/namesxml.jsp";
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPsd(String psd) {
		this.psd = psd;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	public int getDepartmentID() {
		return departmentID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

	public int getGroupID() {
		return groupID;
	}

	public void setOfficeID(int officeID) {
		this.officeID = officeID;
	}

	public int getOfficeID() {
		return officeID;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public int getPageNum() {
		return pageNum;
	}

	public void setQuserRealName(String quserRealName) {
		this.quserRealName = quserRealName;
	}

	public String getQuserRealName() {
		return quserRealName;
	}

	public void setQgroupName(String qgroupName) {
		this.qgroupName = qgroupName;
	}

	public String getQgroupName() {
		return qgroupName;
	}

	public void setQueryWord(String queryWord) {
		this.queryWord = queryWord;
	}

	public String getQueryWord() {
		return queryWord;
	}
}
