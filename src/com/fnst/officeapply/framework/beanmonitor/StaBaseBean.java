package com.fnst.officeapply.framework.beanmonitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class StaBaseBean {

	private static final Map<String, Integer> sta_map = Collections.synchronizedMap(new HashMap<String, Integer>());

	public static int getInstanceCount(Object obj) {

		Class<?> clazz = obj.getClass();
		String class_name = clazz.getName();
		Integer ints = sta_map.get(class_name);
		if (ints != null)
			return ints.intValue();
		return 0;
	}

	public static int getInstanceCount(String class_name) {

		Integer ints = sta_map.get(class_name);
		if (ints != null)
			return ints.intValue();
		return 0;
	}

	public StaBaseBean() {
		Class<?> clazz = this.getClass();
		Integer ints = sta_map.get(clazz.getName());
		if (ints == null)
			ints = new Integer(0);
		ints++;
		sta_map.put(clazz.getName(), ints);
	}

	public static Map<String, Integer> getInstanceCountMap() {
		Map<String, Integer> map1 = new HashMap<String, Integer>();
		List<String> keys = new ArrayList<String>();
		keys.addAll(sta_map.keySet());
		for (String key : keys) {
			map1.put(key, sta_map.get(key));
		}
		return map1;
	}

	@Override
	public void finalize() {
		Class<?> clazz = this.getClass();
		Integer ints = sta_map.get(clazz.getName());
		if (ints != null)
			ints--;
		if (ints != null && ints >= 0)
			sta_map.put(clazz.getName(), ints);
	}
}
