package com.fnst.officeapply.common;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.log4j.Logger;

import com.fnst.officeapply.entity.UserInfo;

//监听登录的整个过程 
public class SessionListener implements HttpSessionBindingListener {
	
	public static final Logger logger = Logger.getLogger("userlog");
	private static Map<UserInfo, Integer> loginCounts = new Hashtable<UserInfo, Integer>(); 
	public String userIP;
	private UserInfo userInfo;
	
	public SessionListener(String userIP, UserInfo userInfo) {
		this.userIP = userIP;
		this.userInfo = userInfo;
	}
	
	public void valueBound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		Integer loginCount = 0;
		synchronized (loginCounts) {
			loginCount = loginCounts.get(userInfo);
			loginCount = (loginCount == null ? 0 : loginCount);
			loginCounts.put(userInfo, ++loginCount);
		}
		if(loginCount <= 1){
			OnlineCounter.getInstance(session.getServletContext()).addCounter(userInfo);
			String loginMsg = "login: " + "IP:" + userIP + "\tuserRealName:" + userInfo.getUserRealName();
			logger.info(loginMsg);
		}
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		synchronized (loginCounts) {
			Integer loginCount = loginCounts.get(userInfo);
			if (loginCount != null && loginCount <= 1 || loginCount == null) {
				OnlineCounter.getInstance(session.getServletContext()).removeCounter(userInfo);
				String loginMsg = "logout: " + "IP:" + userIP + "\tuserRealName:" + userInfo.getUserRealName();
				logger.info(loginMsg);
				loginCounts.remove(userInfo);
			} else if (loginCount != null && loginCount > 1) {
				loginCounts.put(userInfo, --loginCount);
			}
		}
	}
	
}
