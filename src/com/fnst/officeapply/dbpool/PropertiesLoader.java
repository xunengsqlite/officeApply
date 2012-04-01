package com.fnst.officeapply.dbpool;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
	
	public static Properties getProperties(String propertiesName) throws DBPoolException{
		try {
			InputStream is = PropertiesLoader.class.getResourceAsStream("/" + propertiesName + ".properties");
			Properties props = new Properties();
			props.load(is);
			return props;
		} catch (Exception ex) {
			throw new DBPoolException("不能读取属性文件. " + "请确保" + propertiesName+ ".properties 在CLASSPATH指定的路径中",ex); 
		}
	}
}
