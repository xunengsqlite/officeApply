package com.fnst.officeapply.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.fnst.officeapply.action.ComUserAction;
import com.fnst.officeapply.common.AdminConfig;
import com.fnst.officeapply.common.ComUtil;
import com.fnst.officeapply.common.mail.MailException;
import com.fnst.officeapply.common.mail.MailParserUtil;
import com.fnst.officeapply.common.mail.TemplateMailSender;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.Authority;
import com.fnst.officeapply.entity.UserInfo;
import com.fnst.officeapply.exception.OfficeException;

public class ComUserService extends UserService{
	
	
	public ComUserService() throws DBPoolException, OfficeException{
	}
	
	public List<UserInfo> userList4Page(HttpServletRequest request, String groupName, ComUserAction action) throws DBPoolException, OfficeException{
		
		int pageNum = action.getPageNum();
		String quserRealName = action.getQuserRealName();
		int userCount = userDao.getUserCount(quserRealName, groupName, 0);
		
		int lastPageNum = ComUtil.getLastPageNum(userCount, PAGE_SHOW_USERNUM);
		pageNum = ComUtil.checkPageNum(userCount, PAGE_SHOW_USERNUM, lastPageNum, pageNum);
		int serialNum = (pageNum - 1) * PAGE_SHOW_USERNUM;
		serialNum = (serialNum <= 0 ? 0 : serialNum);
		
		List<UserInfo> users = userDao.userList4Page(pageNum, PAGE_SHOW_USERNUM, quserRealName, groupName, 0);
		request.setAttribute("users", users);
		request.setAttribute("serialNum", serialNum);
		request.setAttribute("pageNum_u", pageNum);
		request.setAttribute("lastPageNum", lastPageNum);
		request.setAttribute("quserRealName", quserRealName);
		return users;
	}
	
	public List<Authority> userAdderPage(HttpServletRequest request, ComUserAction action) throws DBPoolException, OfficeException{
		List<Authority> auths= authDao.permissionList();
		request.setAttribute("auths", auths);
		request.setAttribute("pageNum_u", action.getPageNum());
		request.setAttribute("quserRealName", action.getQuserRealName());
		return auths;
	}
	public boolean addUser(HttpServletRequest request, UserInfo user) throws DBPoolException, OfficeException{
		return userDao.insertUser(user, 0);
	}
	
	public void userUpdatePage(HttpServletRequest request, int groupID, ComUserAction action) throws DBPoolException, OfficeException{
		List<Authority> auths= authDao.permissionList();
		UserInfo userInfo = userDao.getUser(action.getUsername());
		int GID = userInfo.getGroupID();
		int permission = userInfo.getPermission();
		if(GID != groupID || permission >= 2){
			return;
		}
		request.setAttribute("pageNum_u", action.getPageNum());
		request.setAttribute("quserRealName", action.getQuserRealName());
		request.setAttribute("auths", auths);
		request.setAttribute("userInfo", userInfo);
	}
	
	public boolean updateUser(HttpServletRequest request, UserInfo user, UserInfo self) throws OfficeException, DBPoolException{
		UserInfo u = userDao.getUser(user.getUserName());
		if(u.getGroupID() != self.getGroupID() || u.getPermission() >= 2){
			return false;
		}
		return userDao.updateUser(user, 0);
	}

	public boolean delUser(String username, int groupID) throws OfficeException, DBPoolException{
		UserInfo u = userDao.getUser(username);
		int GID = u.getGroupID();
		int permission = u.getPermission();
		int userID = u.getUserID();
		if(GID != groupID || permission >= 2){
			return false;
		}
		return userDao.deleteUser(userID);
	}

	public boolean delAll(int[] userIDArray) throws DBPoolException, OfficeException {
		return userDao.delAll(userIDArray);
	}
	
	public boolean updatePassword(HttpServletRequest request, String newpsd, String oldpsd, String username) throws DBPoolException, OfficeException{
		return userDao.modifyPassword(newpsd, oldpsd, username);
	}
	
	public boolean applyGoodsReminder(HttpServletRequest request, String mailContent, String serverURL, UserInfo userInfo) throws MailException, DBPoolException, OfficeException{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put(MailParserUtil.MAIL_CONTENT, mailContent);
		paramMap.put(MailParserUtil.SERVER_URL, serverURL);
		paramMap.put(MailParserUtil.MAIL_ADDRESS, adminConfig.getValue(AdminConfig.ADMIN_MAIL_ADDRESS));
		
		int groupID = ((userInfo == null) ? -1 : userInfo.getGroupID());
		//get mygroup mails 
		List<String> mails = userDao.getGroupMails(groupID);
		//mail to
		String toMailAddrs = (userInfo == null ? null : userInfo.getMail());
		//get the mail cc
		String [] mailAddrs = getMailAddrs(mails, toMailAddrs);
		TemplateMailSender.sendEmailByTemplate(0, new String[] { toMailAddrs}, mailAddrs, paramMap);
		return true;
	}
	
	public void mailPage(HttpServletRequest request, String serverURL, ComUserAction action) {
		request.setAttribute(AdminConfig.SERVER_URL, serverURL);
		request.setAttribute("pageNum_u", action.getPageNum());
		request.setAttribute("quserRealName", action.getQuserRealName());
	}

	public void autoComplete(HttpServletRequest request, ComUserAction action, String groupName) throws DBPoolException, OfficeException {
		String queryWord = action.getQueryWord();
		List<String > names = userDao.namesList(queryWord,groupName);
		request.setAttribute("names", names);
	}
	
	private String[] getMailAddrs(List<String> mails, String toMailAddrs){
		for (int i = mails.size() - 1; i >= 0; i--) {
			String mail = mails.get(i);
			if(ComUtil.isEqual(toMailAddrs, mail)){
				mails.remove(i);//删掉收件人
			}
		}
		String [] mailAddrs = new String[mails.size()];
		mailAddrs = mails.toArray(mailAddrs);
		return mailAddrs;
	}
}
