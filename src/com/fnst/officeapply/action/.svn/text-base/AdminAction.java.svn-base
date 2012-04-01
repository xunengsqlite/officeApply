package com.fnst.officeapply.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fnst.officeapply.common.AdminConfig;
import com.fnst.officeapply.exception.OfficeException;
import com.fnst.officeapply.framework.ActionSupport;
import com.fnst.officeapply.framework.annotation.AuthorizerRequest;
import com.fnst.officeapply.framework.beanmonitor.InstanceCounter;
import com.fnst.officeapply.framework.beanmonitor.InstanceCounterMBean;

@AuthorizerRequest(authorizerStep=10)
public class AdminAction extends ActionSupport {
	
	//private String DBPoolName;
	private String serverURL;
	
	private String adminMailAddr;
	
	private static AdminConfig adminConfig;
	
	private static final InstanceCounterMBean bean = new InstanceCounter();
	
	public AdminAction() throws OfficeException{
		adminConfig = AdminConfig.getAdminConfig();
	}
	
	public String adminPage(HttpServletRequest request){
		Map<String, String> adminInfos = adminConfig.getAdminInfos();
		request.setAttribute("adminInfos", adminInfos);
		
		return "/WEB-INF/jsp/admin/admin.jsp";
	}
	
	public String save(HttpServletRequest request) throws OfficeException{
		synchronized (adminConfig) {
			adminConfig.setValue(AdminConfig.ADMIN_MAIL_ADDRESS, adminMailAddr);
			//adminConfig.setValue(AdminConfig.DATABASE_POOL_NAME, DBPoolName);
			adminConfig.setValue(AdminConfig.SERVER_URL, serverURL);
			adminConfig.save();
		}
		return this.redirectPage(request, true, "admin/adminPage.action");
	}
	
	public String reload(HttpServletRequest request) throws OfficeException{
		adminConfig.reload();
		return this.redirectPage(request, true, "admin/adminPage.action");
	}
	
	public String beanMonitor(HttpServletRequest request){
		Map<String, Integer> beansInfo = bean.getInstanceCountMap();
		request.setAttribute("beansInfo", beansInfo);
		return "/WEB-INF/jsp/admin/beanMonitor.jsp";
	}
	
	public String getServerURL() {
		return serverURL;
	}

	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}

	public String getAdminMailAddr() {
		return adminMailAddr;
	}

	public void setAdminMailAddr(String adminMailAddr) {
		this.adminMailAddr = adminMailAddr;
	}
	
}
