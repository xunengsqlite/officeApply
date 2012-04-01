package com.fnst.officeapply.common;

import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletContext;

import com.fnst.officeapply.entity.UserInfo;

public class OnlineCounter {
	
//	private Set<String> sessions = new HashSet<String>();
	
	private Set<UserInfo> users = new HashSet<UserInfo>();
	
	private OnlineCounter() {
	}

	public synchronized int getCounter(){
		return users.size();
	}
	
	public synchronized boolean addCounter(UserInfo user){
		return users.add(user);
	}
	
	public synchronized boolean removeCounter(UserInfo user){
		return users.remove(user);
	}

	public synchronized Set<UserInfo> showOnliner(){
		return users;
	}
	
	public synchronized static OnlineCounter getInstance(ServletContext application) {
		OnlineCounter counter = null;
		if ((counter=(OnlineCounter)application.getAttribute("counter")) == null) {
			counter = new OnlineCounter();
			application.setAttribute("counter", counter);
		}
		return counter;
	}

}