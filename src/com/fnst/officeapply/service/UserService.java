package com.fnst.officeapply.service;

import java.util.HashMap;
import java.util.Map;
import com.fnst.officeapply.common.AdminConfig;
import com.fnst.officeapply.common.mail.MailException;
import com.fnst.officeapply.common.mail.MailParserUtil;
import com.fnst.officeapply.common.mail.TemplateMailSender;
import com.fnst.officeapply.dao.AuthorityDAO;
import com.fnst.officeapply.dao.ManageUserDAO;
import com.fnst.officeapply.dao.OrganizationDAO;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.UserInfo;
import com.fnst.officeapply.exception.OfficeException;

public abstract class UserService {
	
	protected static ManageUserDAO userDao;
	
	protected static AuthorityDAO authDao;
	
	protected static OrganizationDAO orgzDao;
	
	protected static AdminConfig adminConfig;
	
	protected static final int PAGE_SHOW_USERNUM = 15;
	
	public UserService() throws DBPoolException, OfficeException{
		synchronized (this) {
			if(userDao == null){
				userDao = new ManageUserDAO();
			}
			if(authDao == null){
				authDao = new AuthorityDAO();
			}
			if(orgzDao == null){
				orgzDao = new OrganizationDAO();
			}
			adminConfig = AdminConfig.getAdminConfig();
		}
	}
	
	public boolean addUserReminder(UserInfo userInfo) throws MailException, DBPoolException, OfficeException{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put(MailParserUtil.USERREALNAME, userInfo.getUserRealName());
		paramMap.put(MailParserUtil.USERNAME, userInfo.getUserName());
		paramMap.put(MailParserUtil.PASSWORD, userInfo.getPassword());
		paramMap.put(MailParserUtil.SERVER_URL, adminConfig.getValue(AdminConfig.SERVER_URL));
		paramMap.put(MailParserUtil.MAIL_ADDRESS, adminConfig.getValue(AdminConfig.ADMIN_MAIL_ADDRESS));
		TemplateMailSender.sendEmailByTemplate(1, new String[]{userInfo.getMail()}, null, paramMap);
		return true;
	}
	
	public boolean updateUserReminder(UserInfo userInfo) throws MailException, DBPoolException, OfficeException{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put(MailParserUtil.USERREALNAME, userInfo.getUserRealName());
		paramMap.put(MailParserUtil.USERNAME, userInfo.getUserName());
		paramMap.put(MailParserUtil.PASSWORD, userInfo.getPassword());
//		paramMap.put(MailParserUtil.PERMISSION_NAME, userInfo.getPermissionName());
//		paramMap.put(MailParserUtil.DEPARTMENT_NAME, userInfo.getDepartmentName());
//		paramMap.put(MailParserUtil.OFFICE_NAME, userInfo.getOfficeName());
//		paramMap.put(MailParserUtil.GROUP_NAME, userInfo.getGroupName());
		paramMap.put(MailParserUtil.SERVER_URL, adminConfig.getValue(AdminConfig.SERVER_URL));
		paramMap.put(MailParserUtil.MAIL_ADDRESS, adminConfig.getValue(AdminConfig.ADMIN_MAIL_ADDRESS));
		TemplateMailSender.sendEmailByTemplate(2, new String[]{userInfo.getMail()}, null, paramMap);
		return true;
	}

}
