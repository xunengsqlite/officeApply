package com.fnst.officeapply.action;

import javax.servlet.http.HttpServletRequest;
import com.fnst.officeapply.common.AdminConfig;
import com.fnst.officeapply.common.ComUtil;
import com.fnst.officeapply.common.DesEncrypter;
import com.fnst.officeapply.common.InputCheckUtil;
import com.fnst.officeapply.common.mail.MailException;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.UserInfo;
import com.fnst.officeapply.exception.OfficeException;
import com.fnst.officeapply.framework.ActionSupport;
import com.fnst.officeapply.framework.annotation.AuthorizerRequest;
import com.fnst.officeapply.service.ComUserService;

@AuthorizerRequest(authorizerStep=1)
public class ComUserAction extends ActionSupport{
	
	private ComUserService service;
	private AdminConfig adminConfig;
	
	private String username;
	private String psd;
	private String name;
	private String mail;
	private int permissionID;
	private String mailContent;
	private String serverURL;
	private String newpsd;
	private String re_newpsd;
	private int[] userIDArray;
	private int pageNum;
	private String quserRealName;
	private String queryWord;
	
	public ComUserAction() throws DBPoolException, OfficeException{
		service = new ComUserService();
		adminConfig = AdminConfig.getAdminConfig();
	}
	
	@AuthorizerRequest
	public String userNextList(HttpServletRequest request) throws DBPoolException, OfficeException{
		if(!InputCheckUtil.isPositiveNumber(pageNum + "")){
			pageNum = 1;
		}
		if(!InputCheckUtil.checkUserRealName(quserRealName)){
			quserRealName = "";
		}
		
		UserInfo yourself = this.getUserFSession(request);
		String groupName = (yourself == null ? "" : yourself.getGroupName());
		
		service.userList4Page(request, groupName, this);
		return "/WEB-INF/jsp/user/ulist.jsp";
	}

	public String userAdderPage(HttpServletRequest request) throws DBPoolException, OfficeException{
		
		service.userAdderPage(request, this);
		return "/WEB-INF/jsp/user/add.jsp";
	}
	
	public String addUser(HttpServletRequest request) throws DBPoolException, OfficeException, MailException{
		boolean isOK = false;
		UserInfo yourself = this.getUserFSession(request);
		int departmentID = (yourself == null ? -1 : yourself.getDepartmentID());
		int officeID = (yourself == null ? -1 : yourself.getOfficeID());
		int groupID = (yourself == null ? -1 : yourself.getGroupID());
		if (InputCheckUtil.checkUsername(username) && InputCheckUtil.checkPassword(psd)
				&& InputCheckUtil.checkUserRealName(name) && InputCheckUtil.isEmail(mail)) {
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
		return redirectPage(request, isOK, "user/userAdderPage.action?pageNum=" + pageNum + "&quserRealName=" + quserRealName);
	}
	
	public String userUpdatePage(HttpServletRequest request) throws DBPoolException, OfficeException{
		UserInfo yourself = this.getUserFSession(request);
		String ownName = (yourself == null ? "" : yourself.getUserName());
		int groupID = (yourself == null ? -1 : yourself.getGroupID());
		if(ComUtil.isEqual(ownName, username)){
			return redirectPage(request, false, "user/userNextList.action?pageNum=" + pageNum + "&quserRealName=" + quserRealName);
		}
		if(InputCheckUtil.checkUsername(username)){
			service.userUpdatePage(request, groupID, this);
		}
		return "/WEB-INF/jsp/user/update.jsp";
	}
	
	public String updateUser(HttpServletRequest request) throws OfficeException, DBPoolException, MailException{
		UserInfo yourself = this.getUserFSession(request);
		String ownName = (yourself == null ? "" : yourself.getUserName());
		boolean isOK = false;
		
		if (!ComUtil.isEqual(ownName, username) 
				&& InputCheckUtil.checkUsername(username)
				&& InputCheckUtil.checkPassword(psd)
				&& InputCheckUtil.checkUserRealName(name)
				&& InputCheckUtil.isEmail(mail)) {
			// 加密
			String password = DesEncrypter.encrypttoStr(psd, DesEncrypter.KEY_2);
			UserInfo user = new UserInfo(username, password, name, mail, permissionID);
			isOK = service.updateUser(request, user, yourself);
			if(isOK){
				UserInfo u = new UserInfo(username, psd, name, mail, permissionID);
				//提醒邮件,用加密前的密码
				service.updateUserReminder(u);
			}
		}
		return redirectPage(request, isOK, "user/userNextList.action?pageNum=" + pageNum + "&quserRealName=" + quserRealName);
	}
	
	public String delUser(HttpServletRequest request) throws OfficeException, DBPoolException{
		UserInfo yourself = this.getUserFSession(request);
		String ownName = (yourself == null ? "" : yourself.getUserName());
		int groupID = (yourself == null ? -1 : yourself.getGroupID());
		boolean isOK = false;
		if(!ComUtil.isEqual(ownName, username) && InputCheckUtil.checkUsername(username)){
			isOK = service.delUser(username, groupID);
		}
		return redirectPage(request, isOK, "user/userNextList.action?pageNum=" + pageNum + "&quserRealName=" + quserRealName);
	}
	
	public String delAll(HttpServletRequest request) throws OfficeException, DBPoolException{
		UserInfo yourself = this.getUserFSession(request);
		int myID = (yourself == null ? -1 : yourself.getUserID());
		if(userIDArray == null || userIDArray.length == 0){
			return redirectPage(request, false, "user/userNextList.action?pageNum=" + pageNum + "&quserRealName=" + quserRealName);
		}
		for (int i = 0; i < userIDArray.length; i++) {
			if (ComUtil.isEqual(myID, userIDArray[i])) {
				return redirectPage(request, false, "user/userNextList.action?pageNum=" + pageNum + "&quserRealName=" + quserRealName);
			}
		}
		boolean isOK = service.delAll(userIDArray);
		return redirectPage(request, isOK, "user/userNextList.action?pageNum=" + pageNum + "&quserRealName=" + quserRealName);
	}
	
	@AuthorizerRequest
	public String psdUpdatePage(HttpServletRequest request){
		return "/WEB-INF/jsp/user/psdUpdate.jsp";
	}
	
	@AuthorizerRequest
	public String updatePassword(HttpServletRequest request) throws DBPoolException, OfficeException{
		UserInfo user = this.getUserFSession(request);
		String username = (user == null ? "" : user.getUserName());
		boolean isOK = false;
		//加密
		if(ComUtil.isEqual(newpsd, re_newpsd)){
			psd = DesEncrypter.encrypttoStr(psd, DesEncrypter.KEY_2);
			newpsd = DesEncrypter.encrypttoStr(newpsd, DesEncrypter.KEY_2);
			isOK = service.updatePassword(request, newpsd, psd, username);
		}
		return redirectPage(request, isOK, "user/psdUpdatePage.action");
	}
	
	public String applyGoodsReminder(HttpServletRequest request) throws MailException, DBPoolException, OfficeException{
		UserInfo user = this.getUserFSession(request);
		boolean isOK = service.applyGoodsReminder(request, mailContent, serverURL, user);
		return redirectPage(request, isOK, "user/userNextList.action?pageNum=" + pageNum + "&quserRealName=" + quserRealName);
	}
	
	public String mailPage(HttpServletRequest request){
		String serverURL = adminConfig.getValue(AdminConfig.SERVER_URL);
		service.mailPage(request, serverURL, this);
		
		return "/WEB-INF/jsp/user/mailPage.jsp";
	}
	
	@AuthorizerRequest
	public String autoComplete(HttpServletRequest request) throws OfficeException, DBPoolException{
		if(InputCheckUtil.checkUserRealName(queryWord)){
			UserInfo yourself = this.getUserFSession(request);
			String groupName = (yourself == null ? "" : yourself.getGroupName());
			service.autoComplete(request,this,groupName);
		}
		return "/WEB-INF/jsp/user/ajaxdata/namesxml.jsp";
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

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public String getMailContent() {
		return mailContent;
	}

	public String getNewpsd() {
		return newpsd;
	}

	public void setNewpsd(String newpsd) {
		this.newpsd = newpsd;
	}
	
	public void setRe_newpsd(String re_newpsd) {
		this.re_newpsd = re_newpsd;
	}

	public String getRe_newpsd() {
		return re_newpsd;
	}

	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}

	public String getServerURL() {
		return serverURL;
	}

	public void setPermissionID(int permissionID) {
		this.permissionID = permissionID;
	}

	public int getPermissionID() {
		return permissionID;
	}

	public void setUserIDArray(int[] userIDArray) {
		this.userIDArray = userIDArray;
	}

	public int[] getUserIDArray() {
		return userIDArray;
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
	
	public void setQueryWord(String queryWord) {
		this.queryWord = queryWord;
	}
	public String getQueryWord() {
		return queryWord;
	}
	
}
