package com.fnst.officeapply.service;

import com.fnst.officeapply.common.DesEncrypter;
import com.fnst.officeapply.dao.ManageUserDAO;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.UserInfo;
import com.fnst.officeapply.exception.OfficeException;

public class LoginService {
	
	private static ManageUserDAO userDao;
	
	public LoginService() throws DBPoolException, OfficeException{
		synchronized (this) {
			if(userDao == null){
				userDao = new ManageUserDAO();
			}
		}
	}
	public boolean login(String name, String psd) throws DBPoolException, OfficeException {
		//加密
		psd = DesEncrypter.encrypttoStr(psd, DesEncrypter.KEY_2);
		boolean isPass = userDao.isUserExist(name, psd);
		return isPass;
	}
	
	public String getUserRealName(String name) throws DBPoolException, OfficeException{
		return userDao.getUserRealName(name);
	}
	
	public UserInfo getUser(String name) throws DBPoolException, OfficeException{
		return userDao.getUser(name);
	}
	
}
