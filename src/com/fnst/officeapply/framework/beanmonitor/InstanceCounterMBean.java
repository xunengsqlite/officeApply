package com.fnst.officeapply.framework.beanmonitor;

import java.util.Map;

public interface InstanceCounterMBean {

	public int getInstanceCount(String class_name);
	
	public Map<String,Integer> getInstanceCountMap();
	
}
