package com.fnst.officeapply.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.fnst.officeapply.common.ComUtil;
import com.fnst.officeapply.common.DesEncrypter;
import com.fnst.officeapply.dbpool.DBPoolException;
import com.fnst.officeapply.entity.UserInfo;
import com.fnst.officeapply.exception.OfficeException;
import com.fnst.officeapply.framework.ActionSupport;
import com.fnst.officeapply.framework.RedirectBean;
import com.fnst.officeapply.service.LoginService;

public class AuthorizerAction extends ActionSupport {

	private String name;
	private String psd;
	private int cookieTimeout;
	private String validateCode;
	private LoginService loginService;
	private static final int VCODE_TIMEOUT = 45000;
	
	public AuthorizerAction() throws DBPoolException, OfficeException{
		loginService = new LoginService();
	}
	
	public String loginPage(HttpServletRequest request) throws OfficeException{
		RedirectBean redirectBean = createRedirectBean(request, "");
		request.setAttribute("redirectBean", redirectBean);
		return "/WEB-INF/login.jsp";
	}

	public String login(HttpServletRequest request) throws DBPoolException, OfficeException{
		boolean isAutoLogin = false;
		boolean isValidCodeOK = true;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (ComUtil.isEqual("username", c.getName())) {
					name = c.getValue();
					//name = DesEncrypter.decrypt(name, DesEncrypter.KEY_1);
					isAutoLogin = true;
				}else if(ComUtil.isEqual(c.getName(), "password")){
					psd = c.getValue();
					psd = DesEncrypter.decrypt(psd, DesEncrypter.KEY_1);
					isAutoLogin = true;
				}
			}
		}
		
		HttpSession session = request.getSession(true);
		if(!isAutoLogin){
			Object[] obj = (Object[])session.getAttribute("validateCode");
			if (obj != null) {
				String vCode = (String) obj[0];
				long time = System.currentTimeMillis() - (Long) obj[1];
				if (time >= VCODE_TIMEOUT) {
					isValidCodeOK = false;
					ComUtil.logger.debug("validateCode is timeout.");
				} else {
					isValidCodeOK = ComUtil.isEqualsIgnoreCase(vCode, validateCode);
				}
			}else{
				isValidCodeOK = false;
			}
		}
		
		if(!isValidCodeOK){
			loginFail(request,name, psd);
			return "/WEB-INF/login.jsp";
		}
		
		boolean isPass = loginService.login(name, psd);
		if(!isPass){
			loginFail(request,name, null);
			return "/WEB-INF/login.jsp";
		}
		
		UserInfo user = loginService.getUser(name);
		loginOK(request, user, cookieTimeout, psd);
		return REDIRECT_PAGE;
	}
	
	public String logout(HttpServletRequest request){
		logoutOK(request);
		return "/WEB-INF/login.jsp";
	}
	
	public String toString() {
		return "name:" + name + "->" + "password:" + psd;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPsd() {
		return psd;
	}

	public void setPsd(String psd) {
		this.psd = psd;
	}

	public void setCookieTimeout(int cookieTimeout) {
		this.cookieTimeout = cookieTimeout;
	}

	public int getCookieTimeout() {
		return cookieTimeout;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getValidateCode() {
		return validateCode;
	}

}
