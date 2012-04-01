package com.fnst.officeapply.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.fnst.officeapply.action.AdminUserAction;
import com.fnst.officeapply.common.ComUtil;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.Organization;
import com.fnst.officeapply.entity.UserInfo;
import com.fnst.officeapply.exception.OfficeException;

public class AdminUserService extends UserService{

	public AdminUserService() throws DBPoolException, OfficeException{
	}
	
	public List<UserInfo> userList4Page(HttpServletRequest request, AdminUserAction action) throws DBPoolException, OfficeException{
		int pageNum = action.getPageNum();
		String quserRealName = action.getQuserRealName();
		String qgroupName = action.getQgroupName();
		int userCount = userDao.getUserCount(quserRealName, qgroupName, 1);
		int lastPageNum = ComUtil.getLastPageNum(userCount, PAGE_SHOW_USERNUM);
		pageNum = ComUtil.checkPageNum(userCount, PAGE_SHOW_USERNUM, lastPageNum, pageNum);
		int serialNum = (pageNum - 1) * PAGE_SHOW_USERNUM;
		serialNum = (serialNum <= 0 ? 0 : serialNum);
		List<UserInfo> users = userDao.userList4Page(pageNum, PAGE_SHOW_USERNUM, quserRealName, qgroupName, 1);
		List<Organization> projs = orgzDao.orgzList(2);
		request.setAttribute("users", users);
		request.setAttribute("projs", projs);
		request.setAttribute("serialNum", serialNum);
		request.setAttribute("pageNum_u", pageNum);
		request.setAttribute("lastPageNum", lastPageNum);
		request.setAttribute("quserRealName", quserRealName);
		request.setAttribute("qgroupName", qgroupName);
		return users;
	}
	
	public void userAdderPage(HttpServletRequest request, AdminUserAction action) throws DBPoolException, OfficeException{
		List<Organization> deps = orgzDao.orgzList(0);
		List<Organization> secs = orgzDao.orgzList(1);
		List<Organization> projs = orgzDao.orgzList(2);
		request.setAttribute("deps", deps);
		request.setAttribute("secs", secs);
		request.setAttribute("projs", projs);
		request.setAttribute("pageNum_u", action.getPageNum());
		request.setAttribute("quserRealName", action.getQuserRealName());
		request.setAttribute("qgroupName", action.getQgroupName());
	}
	
	public boolean addUser(HttpServletRequest request, UserInfo user) throws DBPoolException, OfficeException{
		return userDao.insertUser(user, 1);
	}
	
	public void userUpdatePage(HttpServletRequest request, AdminUserAction action) throws DBPoolException, OfficeException{
		
		UserInfo userInfo = userDao.getUser(action.getUsername());
		int permission = userInfo.getPermission();
		if(permission >= 2){
			return;
		}
		List<Organization> deps = orgzDao.orgzList(0);
		List<Organization> secs = orgzDao.orgzList(1);
		List<Organization> projs = orgzDao.orgzList(2);
		request.setAttribute("userInfo", userInfo);
		request.setAttribute("deps", deps);
		request.setAttribute("secs", secs);
		request.setAttribute("projs", projs);
		request.setAttribute("pageNum_u", action.getPageNum());
		request.setAttribute("quserRealName", action.getQuserRealName());
		request.setAttribute("qgroupName", action.getQgroupName());
	}
	
	public boolean updateUser(HttpServletRequest request, UserInfo user) throws OfficeException, DBPoolException{
		UserInfo u = userDao.getUser(user.getUserName());
		if(u.getPermission() >= 2){
			return false;
		}
		return userDao.updateUser(user, 1);
	}

	public boolean delUser(HttpServletRequest request, String username) throws OfficeException, DBPoolException{
		UserInfo u = userDao.getUser(username);
		int permission = u.getPermission();
		int userID = u.getUserID();
		if(permission >= 2){
			return false;
		}
		return userDao.deleteUser(userID);
	}
	
	public void autoComplete(HttpServletRequest request, AdminUserAction action) throws DBPoolException, OfficeException {
		String queryWord = action.getQueryWord();
		List<String > names = userDao.namesList(queryWord);
		request.setAttribute("names", names);
	}
	
}
