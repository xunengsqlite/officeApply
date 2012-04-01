package com.fnst.officeapply.common.mail;

import java.util.ResourceBundle;

import com.fnst.officeapply.common.ComUtil;

/**
 * ���[���z�u���ɃA�N�Z�X����N���X�ɂȂ�܂��B
 * 
 */
public class MailConfigInfo {

	/** ���[���T�[�o */
	public static final String SERVER = "smtp.server";

	/** ���[���T�[�o �|�[�g */
	public static final String PORT = "port";
	
	/**���[�������v���g�R�� */
	public static final String TRANSPORT_PROTOCOL_NAME = "transport.protocol.name";

	/** ���[���̃��[�U�� */
	public static final String USERNAME = "username";

	/** ���[���̃��[�U�p�X���[�h */
	public static final String PASSWORD = "password";

	/** ���[���F�؃t���O */
	public static final String USEAUTHORIZATION = "authorization";

	/** ���[���̔����� */
	public static final String MAILFROM = "mailfrom";

	/** ���[���̃G���R�[�f�B���O */
	public static final String MAIL_ENCODING = "mail.encoding";

	/** ���[���̃e���v���[�g�̃p�X */
	public static final String MAIL_TEMPLATE_PATH = "mail.template.path";

	/** ���[���v���p�e�B�t�@�C���� */
	public static final String COMMONMAIL = "CommonMail";

	/** ���[���e���v���[�g�̃L�[ �̃v���t�B�b�N�X*/
	public static final String MAILKIND = "mail.kind.";

	/** ���[���e���v���[�g�̒l�[ �̃v�[�X�g�t�B�N�X */
	public static final String VM = ".vm";

	/** ���[�����̃L�[�̃v�[�X�g�t�B�N�X  */
	public static final String SUBJECT = ".subject";
	
	public static final String MAIL_CONFIG_FILE_SUFFIX = ".properties";

    /**
     * �v���p�e�B�t�@�C���ւ̃A�N�Z�X�I�u�W�F�N�g
     */
	private static ResourceBundle rb = ResourceBundle.getBundle(COMMONMAIL);

	/**
	 * �R���X�g���N�^
	 */
	private MailConfigInfo() {}

	
	/**
	 * �z�u�t�@�C���̃L�[�Œl���擾���܂��B
	 * @param key  �L�[
	 * @return �L�[�ɑΉ�����l
	 */
	public static String getResource(String key) {
		
		String result = "";

		if (!ComUtil.isBlank(key)) {
			try {
				result = rb.getString(key);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return result;
	}

}
