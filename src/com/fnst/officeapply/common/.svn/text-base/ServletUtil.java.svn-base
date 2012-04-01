package com.fnst.officeapply.common;

import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletUtil {
	private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();
	
	private static ThreadLocal<HttpSession> sessionLocal = new ThreadLocal<HttpSession>();
	private static ThreadLocal<ServletContext> applicationLocal = new ThreadLocal<ServletContext>();
	
	public static void fillLocalPool(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		setHttpServletRequest(request);
		setHttpServletResponse(response);
		setHttpSession(session);
		setServletContext(session.getServletContext());
	}
	
	public static void clearLocalPool(){
		requestLocal.remove();
		responseLocal.remove();
		sessionLocal.remove();
		applicationLocal.remove();
	}
	
	public static HttpServletRequest getHttpServletRequest() {
		return requestLocal.get();
	}
	
	public static void setHttpServletRequest(HttpServletRequest request) {
		requestLocal.set(request);
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String , String[]> getRequestMap(){
		return requestLocal.get().getParameterMap();
	}
	
	public static HttpServletResponse getHttpServletResponse() {
		return responseLocal.get();
	}
	
	public static void setHttpServletResponse(HttpServletResponse response) {
		responseLocal.set(response);
	}
	
	public static HttpSession getHttpSession() {
		return sessionLocal.get();
	}
	
	public static void setHttpSession(HttpSession httpSession) {
		sessionLocal.set(httpSession);
	}
	
	public static ServletContext getServletContext() {
		return applicationLocal.get();
	}
	
	public static void setServletContext(ServletContext servletContext) {
		applicationLocal.set(servletContext);
	}
}
