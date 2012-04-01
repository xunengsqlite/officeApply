package com.fnst.officeapply.action;

import javax.servlet.http.HttpServletRequest;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.UserInfo;
import com.fnst.officeapply.exception.OfficeException;
import com.fnst.officeapply.framework.ActionSupport;
import com.fnst.officeapply.framework.annotation.AuthorizerRequest;
import com.fnst.officeapply.service.HomePageService;

@AuthorizerRequest
public class HomePageAction extends ActionSupport{
	
	private HomePageService service;
	
	public HomePageAction() throws DBPoolException, OfficeException{
		service = new HomePageService();
	}
	
	public String homePage(HttpServletRequest request) throws DBPoolException, OfficeException{
		UserInfo user = getUserFSession(request);
		service.homePage(request, user);
		
		return "/WEB-INF/homePage.jsp";
	}
}
