package com.fnst.officeapply.common.mail;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

public class MailParserUtil {

	private static VelocityEngine engine = null;
	
	private static MailParserUtil instance = null;
	
	public static final String MAIL_CONTENT = "mailContent";
	
	public static final String SERVER_URL = "serverURL";
	
	public static final String MAIL_ADDRESS = "mailAddress";
	
	public static final String USERREALNAME = "userRealName";
	
	public static final String USERNAME = "userName";
	
	public static final String PASSWORD = "password";
	
	public static final String PERMISSION_NAME = "permissionName";
	
	public static final String DEPARTMENT_NAME = "departmentName";
	
	public static final String OFFICE_NAME = "officeName";
	
	public static final String GROUP_NAME = "groupName";
	
	/**
	 * コンストラクタ
	 */
	private MailParserUtil() throws MailException {
		//初始化メッソドを呼び出します
		initEngine();
	}

	/**
	 * 初始化する
	 * @throws MailException 自定義した異常
	 */
	private void initEngine() throws MailException {
		
		engine = new VelocityEngine();
		Properties prop = new Properties();
		
		try {
			//プロパティファイルに定義された値を取得します
			String path = MailConfigInfo.getResource(MailConfigInfo.MAIL_TEMPLATE_PATH);
			String encoding =MailConfigInfo.getResource(MailConfigInfo.MAIL_ENCODING);
			
			//プロパティファイルのパスを取得します
			File file = new File(this.getClass().getResource("/"+MailConfigInfo.COMMONMAIL+".properties").getFile());
			String rootPath = file.getParent() + "/../";
			//プロパティファイルから取得した値をオブジェクトに設定します
			prop.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, rootPath + path);
			prop.setProperty(VelocityEngine.INPUT_ENCODING, encoding);
			prop.setProperty(VelocityEngine.OUTPUT_ENCODING, encoding);
			
			engine.init(prop);
			
		} catch (IOException e) {
			throw new MailException("properties load error:",e);
		} catch (Exception e) {
			throw new MailException("VelocityEngine init error:",e);
		}
		
	}

	/**
	 * 単例パータンでインスタンスを取得します。
	 * @return インスタンス
	 * @throws MailException 自定義した異常
	 */
	public synchronized static MailParserUtil getInstance() throws MailException {
		if (instance == null){
			instance = new MailParserUtil();
		}
		return instance;
	}

 
	/**
	 * メールを作成します。
	 * @param template メールテンプレート
	 * @param model マップオブジェクト
	 * @return メール文字列
	 * @throws MailException 自定義した例外
	 */
	public String parseVelocityTemplate(String template,Map<String, Object> model) 
	throws MailException {
		String encoding =MailConfigInfo.getResource(MailConfigInfo.MAIL_ENCODING);
		VelocityContext velocityContext = new VelocityContext(model);
		StringWriter out = new StringWriter();
		
		try {
			engine.mergeTemplate(template, encoding, velocityContext, out);
			
		} catch (ResourceNotFoundException e) {
			throw new MailException("template parse error:",e);
		} catch (ParseErrorException e) {
			throw new MailException("template parse error:",e);
		} catch (MethodInvocationException e) {
			throw new MailException("template parse error:",e);
		} catch (Exception e) {
			throw new MailException("template parse error:",e);
		}

		String returnString = out.toString();
		return returnString;

	}

}
