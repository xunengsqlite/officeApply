package com.fnst.officeapply.framework;

import java.util.Enumeration;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingListener;
import com.fnst.officeapply.common.AdminConfig;
import com.fnst.officeapply.common.ComUtil;
import com.fnst.officeapply.common.DesEncrypter;
import com.fnst.officeapply.common.ServletUtil;
import com.fnst.officeapply.common.SessionListener;
import com.fnst.officeapply.entity.UserInfo;
import com.fnst.officeapply.exception.OfficeException;
import com.fnst.officeapply.framework.beanmonitor.StaBaseBean;

public abstract class ActionSupport extends StaBaseBean implements FrameworkConstant{
	
	public static final DispatcherExecuterInfo dispatcher = DispatcherExecuterInfo.newInstance();
	
	public static final String ERROR_PAGE_REQUEST = dispatcher.getValue(DispatcherExecuterInfo.ERROR_PAGE_ACTION_KEY);
	
	public static final String AUTHORIZED_FLAG = "authorized.flag";
	
	public static final String REQUEST_NAME = "requestName";
	
	public static final String REDIRECT_PAGE = dispatcher.getValue(REDIRECT_PAGE_KEY);
	
	public boolean isAuthorized(HttpServletRequest request){
		HttpSession session = request.getSession();
		if (session == null){
			return false;
		}

		String flag = (String) session.getAttribute(AUTHORIZED_FLAG);
		return Boolean.parseBoolean(flag);
	}
	
	public void loginOK(HttpServletRequest request, UserInfo userInfo, int cookieTimeout, String password) throws OfficeException{
		addCookies(userInfo, cookieTimeout, password);
		RedirectBean redirectBean = createRedirectBeanRemovePrefix(request,RedirectBean.REDIRECT_PREFIX);
		request.setAttribute(RedirectBean.REDIRECT_BEAN_KEY, redirectBean);
		HttpSession session = request.getSession(true);
		session.setAttribute("userInfo", userInfo);
		session.removeAttribute("validateCode");
		session.removeAttribute("sessionListener");
		HttpSessionBindingListener sessionListener = new SessionListener(request.getRemoteAddr(), userInfo);
		session.setAttribute("sessionListener", sessionListener);
		session.setAttribute(AUTHORIZED_FLAG, Boolean.TRUE.toString());
	}
	
	public void logoutOK(HttpServletRequest request){
		removeCookie(request);
		HttpSession session = request.getSession(true);
		session.removeAttribute("sessionListener");
		session.invalidate();
	}

	public void loginFail(HttpServletRequest request,String username, String psd) throws OfficeException{
		removeCookie(request);
		RedirectBean redirectBean = createRedirectBean(request, "");
		redirectBean.remove("name");
		redirectBean.remove("psd");
		redirectBean.remove("login");
		redirectBean.remove("cookieTimeout");
		redirectBean.remove("validateCode");
		request.setAttribute("name", username);
		request.setAttribute("psd", psd);
		String errMsg = (psd == null ? "username or password is wrong!" : "validateCode is wrong or timeout!");
		request.setAttribute("errMsg", errMsg);
		request.setAttribute(RedirectBean.REDIRECT_BEAN_KEY, redirectBean);
	}
	
	private void addCookies(UserInfo userInfo, int cookieTimeout, String password) throws OfficeException{
		if(cookieTimeout <= 0){
			return;
		}
		HttpServletResponse response = ServletUtil.getHttpServletResponse();
		String username = (userInfo == null ? null : userInfo.getUserName());
		//取消username加密
		//username = DesEncrypter.encrypttoStr(username, DesEncrypter.KEY_1);
		password = DesEncrypter.encrypttoStr(password, DesEncrypter.KEY_1);
		Cookie userCookie = new Cookie("username", username);
		Cookie psdCookie = new Cookie("password", password);
		userCookie.setMaxAge(cookieTimeout);
		psdCookie.setMaxAge(cookieTimeout);
		userCookie.setPath("/");
		psdCookie.setPath("/");
		response.addCookie(userCookie);
		response.addCookie(psdCookie);
	}
	
	private void removeCookie(HttpServletRequest request) {
		HttpServletResponse response = ServletUtil.getHttpServletResponse();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (ComUtil.isEqual("username", c.getName())||ComUtil.isEqual(c.getName(), "password")) {
					c.setPath("/");
					c.setMaxAge(0);
					c.setValue(null);
					response.addCookie(c);
				}
			}
		}
	}
	
	public UserInfo getUserFSession(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		UserInfo user = (UserInfo)session.getAttribute("userInfo");
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public RedirectBean createRedirectBean(HttpServletRequest request,String prefix) throws OfficeException{
		//String reqWay = request.getMethod();
		RedirectBean redirectBean = new RedirectBean();
		Enumeration<String> keyEnum = request.getParameterNames();
		while (keyEnum.hasMoreElements()) {
			String key = keyEnum.nextElement();
			redirectBean.addControl(prefix + key, ComUtil.encodingVals(request.getParameterValues(key)));
		}
		return redirectBean;
	}
	
	@SuppressWarnings("unchecked")
	public RedirectBean createRedirectBeanRemovePrefix(HttpServletRequest request,String prefix) throws OfficeException{
		
		RedirectBean redirectBean = new RedirectBean();
		Enumeration<String> keyEnum = request.getParameterNames();
		while (keyEnum.hasMoreElements()) {
			String key = keyEnum.nextElement();
			if (key.startsWith(prefix)){
				String name = key.substring(prefix.length());
				if(name.equals(REQUEST_NAME)){
					redirectBean.setRequest(request.getParameter(key));
				}else{
					redirectBean.addControl(name, ComUtil.encodingVals(request.getParameterValues(key)));
				}
			}
		}
		if(redirectBean.getRequest() == null){
			String defaultAction = dispatcher.getValue(DEFAULT_ACTION);
			logger.debug("defaultAction:" + defaultAction);
			redirectBean.setRequest(defaultAction);
		}
		return redirectBean;
	}
	
	public String gotoAuthorisePage(String LoginRedirect,HttpServletRequest request) throws OfficeException{
		RedirectBean redirectBean = createRedirectBean(request, RedirectBean.REDIRECT_PREFIX);
		redirectBean.addControl(RedirectBean.REDIRECT_PREFIX + REQUEST_NAME, request.getRequestURI());
		redirectBean.setRequest(LoginRedirect);
		request.setAttribute(RedirectBean.REDIRECT_BEAN_KEY, redirectBean);
		return REDIRECT_PAGE;
	}
	
	public String gotoAccessFailPage(String authorizerRedirect,HttpServletRequest request){
		RedirectBean redirectBean = new RedirectBean();
		redirectBean.setRequest(authorizerRedirect);
		redirectBean.addControl("adminMailAddr",  DispatcherExecuterFilter.adminConfig.getValue(AdminConfig.ADMIN_MAIL_ADDRESS));
		request.setAttribute(RedirectBean.REDIRECT_BEAN_KEY, redirectBean);
		return REDIRECT_PAGE;
	}
	
	public String gotoErrorPage(HttpServletRequest request, Exception e){
		RedirectBean redirectBean = new RedirectBean();
		redirectBean.setRequest(ERROR_PAGE_REQUEST);
		redirectBean.addControl("adminMailAddr",  DispatcherExecuterFilter.adminConfig.getValue(AdminConfig.ADMIN_MAIL_ADDRESS));
		redirectBean.addControl("errMsg", e.getMessage());
		request.setAttribute(RedirectBean.REDIRECT_BEAN_KEY, redirectBean);
		return REDIRECT_PAGE;
	}
	
	public String redirectPage(HttpServletRequest request,Object obj,String requestName){
		RedirectBean redirectBean = new RedirectBean();
		redirectBean.addControl("isOK", obj + "");
		redirectBean.setRequest(requestName);
		request.setAttribute(RedirectBean.REDIRECT_BEAN_KEY, redirectBean);
		return REDIRECT_PAGE;
	}

}
