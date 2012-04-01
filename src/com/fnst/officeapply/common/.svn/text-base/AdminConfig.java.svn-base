package com.fnst.officeapply.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.fnst.officeapply.common.mail.MailConfigInfo;
import com.fnst.officeapply.exception.OfficeException;

public class AdminConfig {
	
	public static final String SERVER_URL = "server.url";
	public static final String ADMIN_MAIL_ADDRESS = "admin.mail.address";
	public static final String DATABASE_POOL_NAME = "database.pool.name";
	private static final String ADMIN_FILE_NAME = "admin";
	private static final String ADMIN_FILE_SUFFIX = ".properties";
	private static final String ADMIN_FILE_DIRECTORY = "./resource/admin/";
	private Properties adminProp;
	private String filename;
	private static AdminConfig ac;
	
	public synchronized static AdminConfig getAdminConfig() throws OfficeException{
		if (ac == null){
			String filename=null;
			try {
				filename = AdminConfig.class.getResource("/" + MailConfigInfo.COMMONMAIL + MailConfigInfo.MAIL_CONFIG_FILE_SUFFIX).toURI().getPath();
				File file = new File(filename);
				filename = file.getParentFile().getAbsolutePath() + "/../"+ADMIN_FILE_DIRECTORY+ADMIN_FILE_NAME+ADMIN_FILE_SUFFIX;
			} catch (URISyntaxException e) {
				throw new OfficeException("the admin file's path is error:", e);
			}
			ac = new AdminConfig(filename);
		}
		return ac;
	}
	
	private AdminConfig(String filename) throws OfficeException{
		try {
			this.filename = filename;
			InputStream is =  new FileInputStream(filename);
			adminProp = new Properties();
			adminProp.load(is);
			is.close();
		} catch (Exception e) {
			throw new OfficeException("can not open admin config file", e);
		}
	}
	
	public synchronized void save() throws OfficeException{
		try {
			OutputStream os = new FileOutputStream(filename);
			adminProp.store(os, null);
			os.close();
		} catch (Exception e) {
			throw new OfficeException("can not open admin config file", e);
		}
	}
	
	public synchronized void reload() throws OfficeException{
		try {
			InputStream is =  new FileInputStream(filename);
			adminProp.load(is);
			is.close();
		} catch (Exception e) {
			throw new OfficeException("can not open admin config file", e);
		}
	}
	
	public synchronized String setValue(String key,String value){
		Object oldValue = adminProp.setProperty(key, value);
		return (String)oldValue;
	}
	
	public synchronized String getValue(String key){
		return adminProp.getProperty(key,null);
	}
	
	@SuppressWarnings("unchecked")
	public synchronized Map<String, String> getAdminInfos(){
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> names = (Enumeration<String>)adminProp.propertyNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			map.put(name, adminProp.getProperty(name));
		}
		return map;
	}
}
