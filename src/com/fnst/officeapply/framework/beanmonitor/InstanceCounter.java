package com.fnst.officeapply.framework.beanmonitor;

import java.util.Map;

public class InstanceCounter implements InstanceCounterMBean {

	@Override
	public int getInstanceCount(String class_name) {
		return StaBaseBean.getInstanceCount(class_name);
	}

	@Override
	public Map<String, Integer> getInstanceCountMap() {
		return StaBaseBean.getInstanceCountMap();
	}

}
