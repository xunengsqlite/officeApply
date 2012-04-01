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
	 * �R���X�g���N�^
	 */
	private MailParserUtil() throws MailException {
		//���n�����b�\�h���Ăяo���܂�
		initEngine();
	}

	/**
	 * ���n������
	 * @throws MailException ����`�����ُ�
	 */
	private void initEngine() throws MailException {
		
		engine = new VelocityEngine();
		Properties prop = new Properties();
		
		try {
			//�v���p�e�B�t�@�C���ɒ�`���ꂽ�l���擾���܂�
			String path = MailConfigInfo.getResource(MailConfigInfo.MAIL_TEMPLATE_PATH);
			String encoding =MailConfigInfo.getResource(MailConfigInfo.MAIL_ENCODING);
			
			//�v���p�e�B�t�@�C���̃p�X���擾���܂�
			File file = new File(this.getClass().getResource("/"+MailConfigInfo.COMMONMAIL+".properties").getFile());
			String rootPath = file.getParent() + "/../";
			//�v���p�e�B�t�@�C������擾�����l���I�u�W�F�N�g�ɐݒ肵�܂�
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
	 * �P��p�[�^���ŃC���X�^���X���擾���܂��B
	 * @return �C���X�^���X
	 * @throws MailException ����`�����ُ�
	 */
	public synchronized static MailParserUtil getInstance() throws MailException {
		if (instance == null){
			instance = new MailParserUtil();
		}
		return instance;
	}

 
	/**
	 * ���[�����쐬���܂��B
	 * @param template ���[���e���v���[�g
	 * @param model �}�b�v�I�u�W�F�N�g
	 * @return ���[��������
	 * @throws MailException ����`������O
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
